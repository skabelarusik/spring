package by.epam.crackertracker.dao;

import by.epam.crackertracker.entity.Bucket;
import by.epam.crackertracker.exception.TrackerConnectionPoolException;
import by.epam.crackertracker.exception.TrackerDBException;
import by.epam.crackertracker.mapper.BucketMapper;
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
    public static final String SELECT_ELEMENTS = "select ?, b.portions, b.idbucket, p.name, p.calories from bucket b inner join products p on b.prod_b = p.idproducts" +
            " where user_b = (SELECT id from users where login = ?) ";
    public static final String DELETE_ELEMENT = "DELETE from bucket where idbucket = ?";
    public static final String DELETE_ALL = "DELETE from bucket where user_b = (SELECT id from users where login = ?)";

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private BucketMapper mapper;

    public void insert(String login, String product, Double portions) throws TrackerDBException {
        try{
            template.update(ADD_ELEMENT, login, product, portions);
        } catch (Exception e){
            throw new TrackerDBException("Wrong insert product: " + product + " to bucket user:" + login);
        }
    }

    @Override
    public List<Bucket> selectAll(String login) throws TrackerDBException {
        List<Bucket> bucketList = new ArrayList<>();
        try{
            bucketList = template.query(SELECT_ELEMENTS, mapper, login, login);
        } catch (Exception e){
            LOGGER.error("Wrong select all elements bucket for user: " + login);
            throw new TrackerDBException("Wrong select all elements bucket");
        }
        return bucketList;
    }


    public void removeAll(String login) throws TrackerDBException {
        try{
            template.update(DELETE_ALL, login);
        } catch (Exception e){
            LOGGER.error("Wrong clear bucket for user: " + login);
            throw new TrackerDBException("Wrong clear bucket");
        }
    }

    public void deleteById(int id) throws TrackerDBException {
        try{
            template.update(DELETE_ELEMENT, id);
        } catch (Exception e) {
            LOGGER.warn("Wrong delete by id");
            throw new TrackerDBException("Wrong delete by id",e);
        }
    }
}
