/**
 * jdbc dao class with different method for receive table advices in DB
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.dao;

import by.epam.crackertracker.entity.Advice;
import by.epam.crackertracker.exception.TrackerConnectionPoolException;
import by.epam.crackertracker.exception.TrackerDBException;
import by.epam.crackertracker.pool.ConnectionPool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdviceDaoJdbcImpl implements AdviceDao {
    private static final String SELECT_ALL = "SELECT idadvices, message from advices";
    private static final String SELECT_ADVICE = "SELECT message from advices ORDER BY RANDOM() LIMIT 1";
    private static final String DELETE_BY_ID = "DELETE from advices where idadvices = ?";
    private static final String INSERT_ADVICE = "INSERT into advices (message) values (?)";
    private static final String SELECT_ADVICE_BY_TEXT = "SELECT idadvices from advices where message = ?";
    private static final String SELECT_ADVICE_BY_ID = "SELECT idadvices from advices where idadvices = ?";

    private static final Logger LOGGER = LogManager.getRootLogger();

    @Override
    public boolean insert(String text) throws TrackerDBException {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean status = false;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            if(isUniqeAdvice(connection, text)) {
                statement = connection.prepareStatement(INSERT_ADVICE);
                statement.setString(1, text);
                statement.executeUpdate();
                status = true;
                LOGGER.debug(text + " - advice inserted");
                String a = "asd";
                LOGGER.error("asdsadsad" + a + "sadsadsda");
            }
        } catch (TrackerConnectionPoolException | SQLException e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong insert advice", e);
        } finally {
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return status;
    }

    @Override
    public boolean deleteById(int id) throws TrackerDBException {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean status = false;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            if(hasId(connection, id)){
                statement = connection.prepareStatement(DELETE_BY_ID);
                statement.setInt(1, id);
                statement.executeUpdate();
                status = true;
                LOGGER.debug(id + " advice deleted");
            }
        } catch (TrackerConnectionPoolException | SQLException e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong delete advice by id", e);
        }   finally {
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return status;
    }

    @Override
    public List<Advice> selectAll() throws TrackerDBException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Advice> adviceList = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()){
                String message = resultSet.getString(2);
                int id = resultSet.getInt(1);
                Advice advice = new Advice(message);
                advice.setId(id);
                adviceList.add(advice);
            }
        } catch (TrackerConnectionPoolException | SQLException e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong select advices", e);
        }  finally {
            this.closeQuietly(resultSet);
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return adviceList;
    }

    public String selectRandomAdvice() throws TrackerDBException {
        Connection connection = null;
        String advice = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SELECT_ADVICE);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                advice = resultSet.getString(1);
            }
        } catch ( TrackerConnectionPoolException | SQLException e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong select random advice", e);
        } finally {
            this.closeQuietly(resultSet);
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return advice;
    }

    private boolean isUniqeAdvice(Connection connection, String message) throws TrackerDBException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean status = true;
        try {
            statement = connection.prepareStatement(SELECT_ADVICE_BY_TEXT);
            statement.setString(1, message);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                status = false;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong checking uniqe advice", e);
        } finally {
            this.closeQuietly(resultSet);
            this.closeQuietly(statement);
        }

        return status;
    }

    private boolean hasId(Connection connection, int id) throws TrackerDBException {
        boolean status = false;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_ADVICE_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            status = resultSet.next();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong checking id advice", e);
        } finally {
            this.closeQuietly(resultSet);
            this.closeQuietly(statement);
        }
        return status;
    }
}
