/**
 * interface for different interface dao, include closing resultset, statement, connection
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.dao;

import by.epam.crackertracker.exception.TrackerDBException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface TrackerDao {

     default void closeQuietly(ResultSet resultSet) throws TrackerDBException {
        if(resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new TrackerDBException("Wrong close resultset");
//logger
            }
        }
    }

    default void closeQuietly(Statement statement) throws TrackerDBException {
        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                throw new TrackerDBException("Wrong close statement");

//logger
            }
        }
    }

    default void closeQuietly(Connection connection) throws TrackerDBException {
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                throw new TrackerDBException("Wrong close connection");

//logger
            }
        }
    }



}
