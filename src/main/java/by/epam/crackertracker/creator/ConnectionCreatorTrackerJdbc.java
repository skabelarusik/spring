/**
 * class for create connection by parametres from resource-file
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.creator;

import by.epam.crackertracker.exception.TrackerConnectionPoolException;
import org.apache.log4j.Logger;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionCreatorTrackerJdbc implements ConnectionCreatorFactory {

    private static final Logger LOGGER = Logger.getRootLogger();

    @Override
    public Connection newConnection() throws TrackerConnectionPoolException {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        String url = resource.getString("db.url");
        String user = resource.getString("db.user");
        String pass = resource.getString("db.password");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, pass);
            LOGGER.info("connection created");
        } catch (SQLException e) {
            LOGGER.error("Wrong create connection", e);
            throw new TrackerConnectionPoolException("Wrong create connection", e);
        }
        return connection;
    }

    @Override
    public void close() {

    }
}
