package by.epam.crackertracker.dao;

import by.epam.crackertracker.entity.Bucket;
import by.epam.crackertracker.exception.TrackerConnectionPoolException;
import by.epam.crackertracker.exception.TrackerDBException;
import by.epam.crackertracker.pool.ConnectionPool;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BucketDaoJdbcImpl implements BucketDao {
    private static final Logger LOGGER = Logger.getRootLogger();
    public static final String ADD_ELEMENT = "INSERT INTO bucket (user_b, prod_b, portions) VALUES ((SELECT id from users " +
            "where login = ?), (SELECT idproducts from products where name = ?), ?)";
    public static final String SELECT_ELEMENTS = "select b.portions,  b.idbucket, p.name, p.calories from bucket b inner join products p on b.prod_b = p.idproducts" +
            " where user_b = (SELECT id from users where login = ?) ";
    public static final String DELETE_ELEMENT = "DELETE from bucket where idbucket = ?";
    public static final String DELETE_ALL = "DELETE from bucket where user_b = (SELECT id from users where login = ?)";

    @Autowired
    private JdbcTemplate template;

    public boolean insert(String login, String product, Double portions) throws TrackerDBException {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean status = false;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(ADD_ELEMENT);
            statement.setString(1,login);
            statement.setString(2,product);
            statement.setDouble(3,portions);
            statement.executeUpdate();
            status = true;
        } catch (SQLException | TrackerConnectionPoolException e){
            LOGGER.warn("User:" + login + " input wrong product: " + product);
        } finally {
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return status;
    }

    public List<Bucket> selectAll(String login) throws TrackerDBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Bucket> list = new ArrayList<>();
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SELECT_ELEMENTS);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            list = fillingList(resultSet, login);
        } catch (SQLException | TrackerConnectionPoolException e){
        LOGGER.warn("Wrong select products in bucket users:"  + login);
    } finally {
        this.closeQuietly(resultSet);
        this.closeQuietly(statement);
        this.closeQuietly(connection);
    } return list;
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
}
