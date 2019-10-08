/**
 * jdbc dao class with different method for receive table advices in DB
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.dao;

import by.epam.crackertracker.entity.Advice;
import by.epam.crackertracker.exception.TrackerConnectionPoolException;
import by.epam.crackertracker.exception.TrackerDBException;
import by.epam.crackertracker.mapper.AdviceMapper;
import by.epam.crackertracker.pool.ConnectionPool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AdviceDaoJdbcImpl implements AdviceDao {
    private static final String SELECT_ALL = "SELECT idadvices, message from advices";
    private static final String SELECT_ADVICE = "SELECT message from advices ORDER BY RANDOM() LIMIT 1";
    private static final String DELETE_BY_ID = "DELETE from advices where idadvices = ?";
    private static final String INSERT_ADVICE = "INSERT into advices (message) values (?)";

    private static final Logger LOGGER = LogManager.getRootLogger();

    @Autowired
    private JdbcTemplate template;

    @Override
    public void insert(String text) throws TrackerDBException {
       try{
        template.update(INSERT_ADVICE, text);
        LOGGER.debug(text + " - advice inserted");
       } catch (Exception e) {
            LOGGER.error("Wrong insert advice:" + text);
            throw new TrackerDBException("Wrong insert advice", e);
       }
    }

    @Override
    public void deleteById(int id) throws TrackerDBException {
        try{
            int i = template.update(DELETE_BY_ID, id);
            if(i != 1){
                throw new TrackerDBException("");
            }
            LOGGER.warn("Advice: " + id + " was deleted");
        } catch (Exception e){
            LOGGER.error("Wrong delete advice");
            throw new TrackerDBException("Wrong delete advice");
        }
    }

    @Override
    public List<Advice> selectAll() {
        return template.query(SELECT_ALL, new AdviceMapper());
    }

    @Override
    public Advice selectRandomAdvice() throws TrackerDBException {
        Advice advice;
        try{
            advice= template.queryForObject(SELECT_ADVICE, new AdviceMapper());
        } catch (Exception e){
            LOGGER.warn("Wrong select random advice");
            throw new TrackerDBException("Wrong select random advice");
        }
        return advice;
    }

}
