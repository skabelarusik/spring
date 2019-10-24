/**
 * jdbc dao class with different method for receive table subscribes in DB
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.dao;

import by.epam.crackertracker.entity.TrackerSubscription;
import by.epam.crackertracker.exception.TrackerConnectionPoolException;
import by.epam.crackertracker.exception.TrackerDBException;
import by.epam.crackertracker.mapper.SubscriptionMapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Repository
public class TrackerSubscriptionDaoJdbcImpl implements TrackerSubscriptionDao {
    public static final String SELECT_ALL = "select s.idsubscriptions, u.name, u1.login, s.start_date, " +
            "s.finish_date from subscriptions s inner join programs_name u on s.program = u.id " +
            "inner join users u1 on s.subscriber = u1.id order by s.finish_date desc";
    public static final String DELETE_BY_ID = "delete from subscriptions where id = ?";
    public static final String CHECK_SUPERUSER_STATUS = "SELECT s.idsubscriptions from subscriptions s inner join " +
            "users u on s.subscriber = u.id where u.login = ? and (s.finish_date >= ?::date and s.start_date <= ?::date)";
    public static final String SELECT_ALL_BY_CURATOR = "select s.idsubscriptions, u.name, u1.login, s.start_date, \n" +
            "s.finish_date from subscriptions s inner join programs_name u on s.program = u.id \n" +
            "inner join users u1 on s.subscriber = u1.id where u.curator = (SELECT id from users where login = ?) ORDER BY s.finish_date desc";
    public static final String SELECT_HISTORY_BY_ID = "SELECT s.idsubscriptions, p.name, u1.login,  s.start_date, s.finish_date from " +
            "subscriptions s inner join programs_name p on s.program = p.id inner join users u1 on s.subscriber = u1.id " +
            "where s.subscriber = (Select id from users where login = ?) order by s.finish_date desc";
    public static final String INSERT_SUBSCRIPTION = "INSERT INTO subscriptions (program, subscriber, start_date, finish_date)" +
            "  values (?,(SELECT id from USERS where login = ?),?::date,?::date)";
    public static final String UPDATE_BALANCE = "UPDATE users set money = ? where login = ?";

    public static final String UPDATE_BALANCE_CUR = "UPDATE users set money = ? where id = (SELECT curator from programs_name " +
            "where id = ?)";
    public static final String UPDATE_ROLE_SUPERUSER = "UPDATE users SET status = (SELECT id from role " +
            "where name = 'superuser') WHERE login = ?";
    public static final String CHECK_BALANCE_CUR = "SELECT money from users where id = (SELECT curator from programs_name where id = ?)";


    private static final Logger LOGGER = LogManager.getRootLogger();

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private SubscriptionMapper mapper;

    @Override
    public List<TrackerSubscription> selectAll() throws TrackerDBException {
        List<TrackerSubscription> list;
        try{
            list = template.query(SELECT_ALL, mapper);
        } catch (Exception e){
            LOGGER.error(e);
            throw new TrackerDBException("Wrong select all subscribtions");
        }
        return list;
    }

    @Override
    public List<TrackerSubscription> selectAllByCurator(String login) throws TrackerDBException {
        List<TrackerSubscription> list;
        try{

            list = template.query(SELECT_ALL_BY_CURATOR, mapper, login);
        } catch (Exception e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong select subscribers by login of curator");
        }
        return list;
    }

    @Override
    public void deleteById(int id) throws TrackerDBException {
        try {
            template.update(DELETE_BY_ID, id);
        } catch (Exception e){
            LOGGER.error("Wrong delete subscription", e);
            throw new TrackerDBException("Wrong delete subscription");
        }
    }

    public boolean checkSubscription(String loginValue) throws TrackerDBException {
        LocalDate localDate = LocalDate.now();
        boolean status = true;
        try{
            template.update(CHECK_SUPERUSER_STATUS, loginValue, localDate, localDate);
        } catch (Exception e){
            LOGGER.error(e);
            throw new TrackerDBException("Wrong check superuser subscrib");
        }
        return status;
    }

    public List<TrackerSubscription> historySubscr(String login) throws TrackerDBException {
        List<TrackerSubscription> list;
        try{
            list = template.query(SELECT_HISTORY_BY_ID, mapper, login);
        } catch (Exception e) {
            throw new TrackerDBException("Wrong select history subscriptions , user login: " + login, e);
        }
        return list;
    }

    public void insert(int idProgram, BigDecimal cost, int duration, String login, BigDecimal balance) throws TrackerDBException, SQLException {
        BigDecimal balanceAcc = balance.subtract(cost);
        LocalDate date = LocalDate.now();
        LocalDate endDate = date.plusDays(duration);
        template.update(UPDATE_ROLE_SUPERUSER, login);
     //   int i = template.update(INSERT_SUBSCRIPTION, idProgram, login, date.toString(), endDate.toString());
//        if(i == 1){
//            throw new TrackerDBException("");
//        }
//        template.update(UPDATE_BALANCE, balanceAcc, login);
//        template.update(UPDATE_ROLE_SUPERUSER, login);
    }


}
