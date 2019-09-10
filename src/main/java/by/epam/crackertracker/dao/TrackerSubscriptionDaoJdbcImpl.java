/**
 * jdbc dao class with different method for receive table subscribes in DB
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.dao;

import by.epam.crackertracker.pool.ConnectionPool;
import by.epam.crackertracker.entity.TrackerSubscription;
import by.epam.crackertracker.exception.TrackerConnectionPoolException;
import by.epam.crackertracker.exception.TrackerDBException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TrackerSubscriptionDaoJdbcImpl implements TrackerSubscriptionDao {
    public static final String SELECT_ALL = "select s.idsubscriptions, u.name, u1.login, s.start_date, " +
            "s.finish_date from subscriptions s inner join programs_name u on s.program = u.id " +
            "inner join users u1 on s.user = u1.id order by s.finish_date desc";
    public static final String DELETE_BY_ID = "delete from subscriptions where id = ?";
    public static final String CHECK_SUPERUSER_STATUS = "SELECT s.idsubscriptions from subscriptions s inner join " +
            "users u on s.user = u.id where u.login = ? and (s.finish_date >= ? and s.start_date <= ?)";
    public static final String SELECT_ALL_BY_CURATOR = "select s.idsubscriptions, u.name, u1.login, s.start_date, \n" +
            "s.finish_date from subscriptions s inner join programs_name u on s.program = u.id \n" +
            "inner join users u1 on s.user = u1.id where u.curator = (SELECT id from users where login = ?) ORDER BY s.finish_date desc";
    public static final String SELECT_HISTORY_BY_ID = "SELECT s.idsubscriptions, p.name, s.start_date, s.finish_date from " +
            "subscriptions s inner join programs_name p on s.program = p.id where s.user = ? order by s.finish_date desc";
    public static final String INSERT_SUBSCRIPTION = "INSERT INTO subscriptions (program, user, start_date, finish_date)" +
            "  values (?,(SELECT id from USERS where login = ?),?,?)";
    public static final String UPDATE_BALANCE = "UPDATE users set money = ? where login = ?";

    public static final String UPDATE_BALANCE_CUR = "UPDATE users set money = ? where id = (SELECT curator from programs_name " +
            "where id = ?)";
    public static final String UPDATE_ROLE_SUPERUSER = "UPDATE users SET status = (SELECT id from role " +
            "where name = 'superuser') WHERE login = ?";

    private static final Logger LOGGER = LogManager.getRootLogger();

    @Override
    public List<TrackerSubscription> selectAll() throws TrackerDBException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<TrackerSubscription> list;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL);
            list = fillingListSubscr (resultSet);
        } catch (TrackerConnectionPoolException | SQLException e){
            LOGGER.error(e);
            throw new TrackerDBException("Wrong select all subscribtions");
        } finally {
            this.closeQuietly(resultSet);
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return list;
    }


    @Override
    public List<TrackerSubscription> selectAllByCurator(String login) throws TrackerDBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<TrackerSubscription> list;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SELECT_ALL_BY_CURATOR);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            list = fillingListSubscr(resultSet);
        } catch (TrackerConnectionPoolException | SQLException e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong select subscribers by login of curator");
        } finally {
            this.closeQuietly(resultSet);
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return list;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }


    public boolean checkSubscription(String loginValue) throws TrackerDBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean status = false;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            LocalDate localDate = LocalDate.now();
            statement = connection.prepareStatement(CHECK_SUPERUSER_STATUS);
            statement.setString(1, loginValue);
            statement.setString(2, localDate.toString());
            statement.setString(3, localDate.toString());
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                status = true;
            }
        } catch (SQLException | TrackerConnectionPoolException e){
            LOGGER.error(e);
            throw new TrackerDBException("Wrong check superuser subscrib");
        } finally {
            this.closeQuietly(resultSet);
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return status;
    }

    public List<TrackerSubscription> historySubscr(int id, String login) throws TrackerDBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<TrackerSubscription> list;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SELECT_HISTORY_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            list = fillingListHistory(resultSet, login);
        } catch (TrackerConnectionPoolException | SQLException e) {
            throw new TrackerDBException("Wrong select history subscriptions , user id: " + id, e);
        } finally {
            this.closeQuietly(resultSet);
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return list;
    }

    private List<TrackerSubscription> fillingListSubscr(ResultSet resultSet) throws TrackerDBException {
        List<TrackerSubscription> list = new ArrayList<>();
        try{
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String nameProg = resultSet.getString(2);
                String subscriber = resultSet.getString(3);
                LocalDate start = LocalDate.parse(resultSet.getString(4));
                LocalDate finish = LocalDate.parse(resultSet.getString(5));
                TrackerSubscription subscription = new TrackerSubscription(subscriber, nameProg, start, finish);
                subscription.setId(id);
                list.add(subscription);
            }
        } catch (SQLException e){
            LOGGER.error(e);
            throw new TrackerDBException("Wrong filling list subscrib");
        }
        return list;
    }

    private List<TrackerSubscription> fillingListHistory(ResultSet resultSet, String login) throws TrackerDBException {
        List<TrackerSubscription> list = new ArrayList<>();
        try{
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String nameProg = resultSet.getString(2);
                LocalDate start = LocalDate.parse(resultSet.getString(3));
                LocalDate finish = LocalDate.parse(resultSet.getString(4));
                TrackerSubscription subscription = new TrackerSubscription(login, nameProg, start, finish);
                subscription.setId(id);
                list.add(subscription);
            }
        } catch (SQLException e){
            LOGGER.error(e);
            throw new TrackerDBException("Wrong filling list subscrib");
        }
        return list;
    }

    public static final String CHECK_BALANCE_CUR = "SELECT money from users where id = (SELECT curator from programs_name where id = ?)";

    public boolean insert(int idProgram, BigDecimal cost, int duration, String login, BigDecimal balance) throws TrackerDBException {
        Connection connection = null;
        PreparedStatement statementUser = null;
        PreparedStatement statementSubs = null;
        PreparedStatement statementUpdRole = null;
        PreparedStatement statementUpdBalCur = null;
        PreparedStatement statementCheckBalCur = null;
        BigDecimal balanceAcc = balance.subtract(cost);
        LocalDate date = LocalDate.now();
        LocalDate endDate = date.plusDays(duration);
        ResultSet resultSet = null;
        boolean status = false;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            connection.setAutoCommit(false);
            statementUser = connection.prepareStatement(INSERT_SUBSCRIPTION);
            statementUser.setInt(1, idProgram);
            statementUser.setString(2, login);
            statementUser.setString(3, date.toString());
            statementUser.setString(4, endDate.toString());
            statementUser.executeUpdate();
            statementSubs = connection.prepareStatement(UPDATE_BALANCE);
            statementSubs.setBigDecimal(1, balanceAcc);
            statementSubs.setString(2,login);
            statementSubs.executeUpdate();
            statementUpdRole = connection.prepareStatement(UPDATE_ROLE_SUPERUSER);
            statementUpdRole.setString(1, login);
            statementUpdRole.executeUpdate();


            statementCheckBalCur = connection.prepareStatement(CHECK_BALANCE_CUR);
            statementCheckBalCur.setInt(1, idProgram);
            resultSet = statementCheckBalCur.executeQuery();
            BigDecimal balanceCur = null;
            while (resultSet.next()){
                balanceCur = resultSet.getBigDecimal(1);
            }
            BigDecimal newCurBalance = balanceCur.add(new BigDecimal(cost.doubleValue()/2));
            statementUpdBalCur = connection.prepareStatement(UPDATE_BALANCE_CUR);
            statementUpdBalCur.setBigDecimal(1, newCurBalance);
            statementUpdBalCur.setInt(2, idProgram);
            statementUpdBalCur.executeUpdate();
            connection.commit();
            status = true;
        } catch (SQLException | TrackerConnectionPoolException e){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new TrackerDBException("Wrong rollback at insert subscription");
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new TrackerDBException("Wrong setAutocommit to connection at insert subscription");
            }
            this.closeQuietly(resultSet);
            this.closeQuietly(statementCheckBalCur);
            this.closeQuietly(statementUpdBalCur);
            this.closeQuietly(statementUpdRole);
            this.closeQuietly(statementSubs);
            this.closeQuietly(statementUser);
            this.closeQuietly(connection);
        }
        return status;
    }
}
