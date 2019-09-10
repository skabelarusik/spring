/**
 * interface to different creator connection
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.creator;

import by.epam.crackertracker.exception.TrackerConnectionPoolException;
import by.epam.crackertracker.exception.TrackerDBException;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionCreatorFactory {

    public Connection newConnection() throws SQLException, TrackerDBException, TrackerConnectionPoolException;

    public void close();
}
