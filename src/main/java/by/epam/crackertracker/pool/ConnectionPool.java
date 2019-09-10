/**
 * connection pool, load driver, create connections etc
 * @author Andrey Krupin,  june-august 2019
 */


package by.epam.crackertracker.pool;

import by.epam.crackertracker.creator.ConnectionCreatorTrackerJdbc;
import by.epam.crackertracker.exception.TrackerConnectionPoolException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;


public class ConnectionPool {

        private static ConnectionPool instance;
        private BlockingQueue<ProxyTrackerConnection> freeConnections;
        private List<ProxyTrackerConnection> usedConnections;
        private static ReentrantLock lock = new ReentrantLock();
        private static final int COUNT_CONNECTION;
        public static final String DRIVER;
        private static AtomicBoolean create = new AtomicBoolean(false);

        private static final Logger LOGGER = Logger.getRootLogger();

        static {
            ResourceBundle resource = ResourceBundle.getBundle("database");
            COUNT_CONNECTION = Integer.parseInt(resource.getString("db.poolsize"));
            DRIVER = resource.getString("db.driver");

            try {
                initDriver(DRIVER);
            } catch (TrackerConnectionPoolException e) {
                e.printStackTrace();
            }

        }

        private ConnectionPool()  {
            try{
                init();
            } catch (Exception e){
                LOGGER.fatal(e);
                throw new ExceptionInInitializerError(e);
            }
        }

        private void init() throws TrackerConnectionPoolException {
            freeConnections = new LinkedBlockingQueue<>(COUNT_CONNECTION);
            usedConnections = new ArrayList<>(COUNT_CONNECTION);
            ConnectionCreatorTrackerJdbc factory = new ConnectionCreatorTrackerJdbc();
            for(int i = 0; i < COUNT_CONNECTION; i++){
                try {
                    Connection connection = factory.newConnection();
                    ProxyTrackerConnection proxyConnection = new ProxyTrackerConnection(connection);
                    freeConnections.offer(proxyConnection);
                } catch (TrackerConnectionPoolException e) {
                    LOGGER.error("Wrong initialize connection pool", e);
                    throw new TrackerConnectionPoolException("Wrong initialize connection pool", e);
                }
            }
            LOGGER.info("Connection pool with " + COUNT_CONNECTION + " connections was created");
        }

    public static void initDriver(String driverName) throws TrackerConnectionPoolException {
        if(driverName != null){
            try {
                Class.forName(driverName);
            } catch (ClassNotFoundException e) {
                LOGGER.fatal("Error : wrong registration driver", e);
                throw new TrackerConnectionPoolException("Wrong registration driver", e);
            }

        }
    }

        public static ConnectionPool getInstance() throws TrackerConnectionPoolException {
            if(!create.get()){
                try{
                    lock.lock();
                    if(instance == null){
                        instance = new ConnectionPool();
                        create.set(true);
                        LOGGER.debug("Connaction Pool was created");
                    }
                } finally {
                 lock.unlock();
                }
            }
            return instance;
        }

        public Connection takeConnection() throws TrackerConnectionPoolException {
            Connection connection = null;
            try {
                connection =  freeConnections.take();
                usedConnections.add((ProxyTrackerConnection) connection);
            } catch (InterruptedException e) {
                LOGGER.error("Problems with ConnectionPool: couldn't take connection from pool." + e);
                throw new TrackerConnectionPoolException("Wrong take connection", e);
            }
            return connection;
        }

        public boolean releaseConnection(Connection connection){
            boolean status = false;
            if(connection instanceof ProxyTrackerConnection) {
                freeConnections.offer((ProxyTrackerConnection) connection);
                usedConnections.remove(connection);
                status = true;
            } else {
                LOGGER.warn("Attempt to return wrong connection into the pool!");
            }

            return status;
        }

        public void closePool() throws TrackerConnectionPoolException {
            for(int i = 0; i < COUNT_CONNECTION; i++) {
                try {
                    ProxyTrackerConnection connection = freeConnections.take();
                    connection.finishClose();
                } catch (InterruptedException | SQLException e) {
                    LOGGER.warn("Problems with connection closing! " + e);
                    throw new TrackerConnectionPoolException("Wrong close connection pool",e);
                }
                LOGGER.info("Pool has been closed, drivers deregistered.");
            }
        }
}

