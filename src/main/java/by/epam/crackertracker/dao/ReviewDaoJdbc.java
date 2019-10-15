/**
 * jdbc dao class with different method for receive table reviews in DB
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.dao;

import by.epam.crackertracker.entity.Review;
import by.epam.crackertracker.exception.TrackerConnectionPoolException;
import by.epam.crackertracker.exception.TrackerDBException;
import by.epam.crackertracker.mapper.ReviewMapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReviewDaoJdbc implements ReviewDao {
    public static final String SELECT_ALL_REVIEW = "SELECT r.idreview, u.login, r.date, r.text from\n" +
            " review r INNER JOIN users u on r.name = u.id where r.show_review = ? order by r.date desc";
    public static final String DELETE_BY_ID = "UPDATE review set show_review = ? where idreview = ?";
    public static final String INSERT_REVIEW = "INSERT INTO review (name, date, text) values ((select id from users where login = ?),?::date,?)";

    private static final Logger LOGGER = LogManager.getRootLogger();

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private ReviewMapper mapper;

    @Override
    public List<Review> selectAllReview(int show){
        return template.query(SELECT_ALL_REVIEW, mapper, show);
    }

    @Override
    public void deleteById(int id, int typeShow) throws TrackerDBException {
        try{
            template.update(DELETE_BY_ID, typeShow,id);
            LOGGER.warn("Review: " + id + " was deleted");
        } catch (Exception e){
            LOGGER.error("Wrong delete review");
            throw new TrackerDBException("Wrong delete review");
        }
    }

    @Override
    public void insertReview(String sender, String text) throws TrackerDBException {
        LocalDate localDate = LocalDate.now();
        try {
            template.update(INSERT_REVIEW, sender,localDate.toString(), text);
            LOGGER.warn("Review: " + text + " was inserted");
        } catch (Exception e){
            LOGGER.error("Wrong insert review: " + text + " from " + sender);
            throw new TrackerDBException("Wrong insert review");
        }
    }

}
