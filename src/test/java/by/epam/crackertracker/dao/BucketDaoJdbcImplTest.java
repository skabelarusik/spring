package by.epam.crackertracker.dao;

import by.epam.crackertracker.entity.Bucket;
import by.epam.crackertracker.exception.TrackerConnectionPoolException;
import by.epam.crackertracker.exception.TrackerDBException;
import by.epam.crackertracker.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BucketDaoJdbcImplTest {
    private static final Logger LOGGER = Logger.getRootLogger();

    public static final String ADD_ELEMENT = "INSERT INTO bucket (user_b, prod_b, portions) VALUES ((SELECT id from users " +
            "where login = ?), (SELECT idproducts from products where name = ?), ?)";
    public static final String SELECT_ELEMENTS = "select b.portions,  b.idbucket, p.name, p.calories from bucket b inner join products p on b.prod_b = p.idproducts" +
            " where user_b = (SELECT id from users where login = ?) ";
    public static final String DELETE_ELEMENT = "DELETE from bucket where idbucket = ?";
    public static final String DELETE_ALL = "DELETE from bucket where user_b = (SELECT id from users where login = ?)";
/*

    public void insert()  {

    }

    public void selectAll(String login) throws TrackerDBException {

    }

    private List<Bucket> fillingList(ResultSet resultSet, String login) {
        List<Bucket> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Double portions = resultSet.getDouble(1);
                int id = resultSet.getInt(2);
                String product = resultSet.getString(3);
                int calories = resultSet.getInt(4);
                Bucket bucket = new Bucket(login, product, calories, portions);
                bucket.setId(id);
                list.add(bucket);
            }
        } catch (SQLException e) {
            LOGGER.warn("Wrong filling list:"  + login);
        }
        return list;
    }

    public boolean removeAll(String login) throws TrackerDBException {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean status = false;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(DELETE_ALL);
            statement.setString(1, login);
            statement.executeUpdate();
            status = true;
        }  catch (TrackerConnectionPoolException | SQLException e) {
            LOGGER.warn("Wrong clear bucket users:" + login);
        }   finally {
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return status;
    }

    public boolean deleteById(int id) throws TrackerDBException {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean status = false;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(DELETE_ELEMENT);
            statement.setInt(1, id);
            statement.executeUpdate();
            status = true;
        } catch (TrackerConnectionPoolException | SQLException e) {
            LOGGER.warn("Wrong delete by id");
            throw new TrackerDBException("Wrong delete by id",e);
        } finally {
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return status;
    }

 */
}
