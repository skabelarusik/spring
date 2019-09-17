/**
 * jdbc dao class with different method for receive table advices in DB
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.dao;

import by.epam.crackertracker.entity.Advice;
import by.epam.crackertracker.exception.TrackerConnectionPoolException;
import by.epam.crackertracker.exception.TrackerDBException;
import by.epam.crackertracker.pool.ConnectionPool;
import by.epam.crackertracker.resources.ParametresDaoTest;
import by.epam.crackertracker.resources.ParametresTest;
import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.flywaydb.core.Flyway;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdviceDaoJdbcImplTest {

    private static Connection connection;
    private static EmbeddedPostgres pg;

    @BeforeClass
    public static void initDB() throws IOException, SQLException {
        pg = EmbeddedPostgres.start();
        DataSource dataSource = pg.getPostgresDatabase();
        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.migrate();
        connection = dataSource.getConnection();
    }

    @AfterClass
    public static void destroyDB() throws IOException, SQLException {
        connection.close();
        pg.close();
    }

    @Test
    public void testReplayInsert() throws TrackerDBException {
        new AdviceDaoJdbcImpl().insert("Больше двигайтесь");
    }

    /*
    @Test
    public void testInsertCorrect() throws TrackerDBException {
        Assert.assertTrue(new AdviceDaoJdbcImpl().insert(ParametresDaoTest.GOOD_ADVICE_TEXT));
    }

    @Test(expected = TrackerDBException.class)
    public void testInsertReplay() throws TrackerDBException {
        new AdviceDaoJdbcImpl().insert(ParametresDaoTest.EMPTY_TEXT);
    }

    @Test(expected = TrackerDBException.class)
    public void testBigWrongSize() throws TrackerDBException {
        new AdviceDaoJdbcImpl().insert(ParametresTest.BIG_SIZE_400);
    }

    @Test(expected = AssertionError.class)
    public void testInsertNull() throws TrackerDBException {
        Assert.assertTrue(new AdviceDaoJdbcImpl().insert(ParametresDaoTest.EMPTY_TEXT));
    }
/*
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

 */
}
