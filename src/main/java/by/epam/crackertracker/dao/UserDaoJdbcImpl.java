/**
 * jdbc dao class with different method for receive table users in DB
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.dao;

import by.epam.crackertracker.entity.Role;
import by.epam.crackertracker.entity.User;
import by.epam.crackertracker.exception.TrackerConnectionPoolException;
import by.epam.crackertracker.exception.TrackerDBException;
import by.epam.crackertracker.mapper.UserMapper;
import by.epam.crackertracker.pool.ConnectionPool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

@Repository
public class UserDaoJdbcImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserMapper userMapper;

    public static final String SELECT_ALL_USERS = "SELECT u.name, u.surname, u.login, u.password, u.sex, u.email," +
            "u.birthday, u.registrdate, u.money, u.avatar, r1.name, u.id, u.active from users u INNER JOIN role r1 on u.status = r1.id where u.active = 1 limit ? offset ?";
    public static final String SELECT_ALL_USERS_INC_AGE = "SELECT u.name, u.surname, u.login, u.password, u.sex, u.email," +
            "u.birthday, u.registrdate, u.money, u.avatar, r1.name, u.id, u.active from users u INNER JOIN role r1 on u.status = r1.id " +
            "order by u.birthday limit ? offset ? ";
    public static final String SELECT_ALL_USERS_DEC_AGE = "SELECT u.name, u.surname, u.login, u.password, u.sex, u.email," +
            "u.birthday, u.registrdate, u.money, u.avatar, r1.name, u.id, u.active from users u INNER JOIN role r1 on u.status = r1.id " +
            "order by u.birthday desc limit ? offset ? ";
    public static final String SELECT_ALL_USERS_INC_LOGIN = "SELECT u.name, u.surname, u.login, u.password, u.sex, u.email," +
            "u.birthday, u.registrdate, u.money, u.avatar, r1.name, u.id, u.active from users u INNER JOIN role r1 on u.status = r1.id " +
            "order by u.login limit ? offset ? ";
    public static final String SELECT_ALL_USERS_DEC_LOGIN = "SELECT u.name, u.surname, u.login, u.password, u.sex, u.email," +
            "u.birthday, u.registrdate, u.money, u.avatar, r1.name, u.id, u.active from users u INNER JOIN role r1 on u.status = r1.id " +
            "order by u.login desc limit ? offset ? ";

    public static final String SELECT_USER_BY_ROLE = "SELECT u.name, u.surname, u.login, u.password, u.sex, u.email," +
            "u.birthday, u.registrdate, u.money, u.avatar, r.name, u.id, u.active from users u INNER JOIN role r on u.status = r.id where r.name = ? limit ? offset ?";
    public static final String SELECT_USER_BY_ROLE_INC_LOGIN = "SELECT u.name, u.surname, u.login, u.password, u.sex, u.email," +
            "u.birthday, u.registrdate, u.money, u.avatar, r.name, u.id, u.active from users u INNER JOIN role r on u.status = r.id where r.name = ? order by u.login limit ? offset ?";
    public static final String SELECT_USER_BY_ROLE_DEC_LOGIN = "SELECT u.name, u.surname, u.login, u.password, u.sex, u.email," +
            "u.birthday, u.registrdate, u.money, u.avatar, r.name, u.id, u.active from users u INNER JOIN role r on u.status = r.id where r.name = ? order by u.login desc limit ? offset ?";

    public static final String SELECT_USER_BY_GENDER = "SELECT u.name, u.surname, u.login, u.password, u.sex, u.email," +
            "u.birthday, u.registrdate, u.money, u.avatar, r.name, u.id, u.active from users u LEFT JOIN role r on u.status = r.id where u.sex = ? limit ? offset ?";
    public static final String SELECT_USER_BY_GENDER_INC_LOGIN = "SELECT u.name, u.surname, u.login, u.password, u.sex, u.email," +
            "u.birthday, u.registrdate, u.money, u.avatar, r.name, u.id, u.active from users u LEFT JOIN role r on u.status = r.id where u.sex = ? order by u.login limit ? offset ?";
    public static final String SELECT_USER_BY_GENDER_DEC_LOGIN = "SELECT u.name, u.surname, u.login, u.password, u.sex, u.email," +
            "u.birthday, u.registrdate, u.money, u.avatar, r.name, u.id, u.active from users u LEFT JOIN role r on u.status = r.id where u.sex = ? order by u.login desc limit ? offset ?";

    public static final String UPDATE_ROLE_USER = "UPDATE users SET status = (select id from role where name = ?) WHERE id = ?";
    public static final String SELECT_LOGIN_USER = "SELECT id from users where login = ?";
    public static final String INSERT_USER = "INSERT INTO users (name, surname, login, password, sex, birthday, email, " +
            "registrdate, avatar) VALUES (?,?,?,?,?,?::date,?,?::date,?)";
    public static final String UPDATE_PASSWORD = "UPDATE users set password = ? WHERE login = ?";
    public static final String SELECT_USER_BY_LOGIN_PASS = "SELECT u.name, u.surname, u.login, u.password, u.sex, u.email, u.birthday, " +
            "u.registrdate, u.money, u.avatar, r.name, u.id, u.active from users u INNER JOIN role r on u.status = r.id where u.login = ?";
    public static final String SELECT_USER_BY_ID = "SELECT id from users where id = ?";
    public static final String UPDATE_DATA_USER = "UPDATE users SET name = ?, surname = ?, " +
            "email = ?, birthday = ?::date where login = ?";
    public static final String UPDATE_BALANCE = "UPDATE users set money = ? where login = ?";
    public static final String UPDATE_PATH = "UPDATE users SET avatar = ? where login = ?";

    public static final int COUNT_USERS = 11;

    private static final Logger LOGGER = LogManager.getRootLogger();


    @Override
    public List<User> selectAll(int currentPage, String type) {
        String sqlReq;
        switch (type) {
            case "DECREASE_AGE":
                sqlReq = SELECT_ALL_USERS_DEC_AGE;
                break;
            case "INCREASE_AGE":
                sqlReq = SELECT_ALL_USERS_INC_AGE;
                break;
            case "INCREASE_LOGIN":
                sqlReq = SELECT_ALL_USERS_INC_LOGIN;
                break;
            case "DECREASE_LOGIN":
                sqlReq = SELECT_ALL_USERS_DEC_LOGIN;
                break;
            default:
                sqlReq = SELECT_ALL_USERS;
                break;
        }
        return jdbcTemplate.query(sqlReq, userMapper, COUNT_USERS, ((currentPage - 1) * COUNT_USERS - (currentPage - 1)));
    }

    @Override
    public List<User> selectByGender (int page, String type, String gender) throws TrackerDBException {
        String sqlReq;
        List<User> userList;
        switch (type){
            case "DECREASE_LOGIN": sqlReq = SELECT_USER_BY_GENDER_DEC_LOGIN;
                break;
            case "INCREASE_LOGIN": sqlReq = SELECT_USER_BY_GENDER_INC_LOGIN;
                break;
            default: sqlReq = SELECT_USER_BY_GENDER;
                break;
        }
        try {
           userList = jdbcTemplate.query(sqlReq, userMapper, gender, COUNT_USERS, ((page - 1) * COUNT_USERS - (page - 1)));
        } catch (Exception e){
            throw new TrackerDBException("Wrong select userList by gender");
        }
        return userList;
}

    @Override
    public List<User> selectByRole(int page, String type, String role) throws TrackerDBException {
        String sqlReq;
        List<User> userList;
        switch (type){
            case "DECREASE_LOGIN": sqlReq = SELECT_USER_BY_ROLE_DEC_LOGIN;
                break;
            case "INCREASE_LOGIN": sqlReq = SELECT_USER_BY_ROLE_INC_LOGIN;
                break;
            default: sqlReq = SELECT_USER_BY_ROLE;
                break;
        }
        try{
            userList =  jdbcTemplate.query(sqlReq, userMapper, role, COUNT_USERS, ((page - 1) * COUNT_USERS - (page - 1)));
        } catch (Exception e){
            throw new TrackerDBException("Wrong select userList by gender");
        }
        return userList;
    }

    @Override
    public User selectByLogin(String login, String pass) throws TrackerDBException {
        User user;
        try{
            user = jdbcTemplate.queryForObject(SELECT_USER_BY_LOGIN_PASS, new UserMapper(), login);
            if(!BCrypt.checkpw(pass, user.getPassword())){
                throw new TrackerDBException("Wrong data");
            }
        } catch (Exception e){
            throw new TrackerDBException("Wrong data");
        }
        return user;
    }

    @Override
    public boolean insert(User user) throws TrackerDBException {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean status;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            status = isUniqueUser(connection, user.getLogin());
            if(status){
                statement = connection.prepareStatement(INSERT_USER);
                statement.setString(1, user.getName());
                statement.setString(3, user.getLogin());
                statement.setString(2, user.getSurname());
                statement.setString(4, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12)));
                statement.setString(5, user.getGender().toString().toLowerCase().trim());
                statement.setString(6, user.getBirthDate().toString());
                statement.setString(7, user.getEmail());
                statement.setString(8, user.getRegistrDate().toString());
                statement.setString(9, user.getPath());
                statement.executeUpdate();
                LOGGER.warn("User: " + user.getLogin()+ " registered");
            }
        } catch (SQLException | TrackerConnectionPoolException ex) {
            LOGGER.error(ex);
            throw new TrackerDBException("Wrong insert user");
        } finally {
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return status;
    }

    @Override
    public void updateRoleUser(int id, Role role) throws TrackerDBException {
        try{
            int i = jdbcTemplate.update(UPDATE_ROLE_USER, role.toString().toLowerCase().trim(), id);
            if(i != 1){
                throw new Exception();
            }
        } catch (Exception e){
            LOGGER.error("Wrong update role user : " + id);
            throw new TrackerDBException("Wrong update role user");
        }
    }

    @Override
    public void updatePasswordUser(String login, String newPassword) throws TrackerDBException {
        try{
            jdbcTemplate.update(UPDATE_PASSWORD, newPassword, login);
        } catch (Exception e){
            LOGGER.error(e);
            throw new TrackerDBException("Wrong update password user");
        }
    }


    public boolean updateFilePath(String login, String path) throws TrackerDBException {
        Connection connection = null;
        boolean status = false;
        PreparedStatement statement = null;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            if(!isUniqueUser(connection, login)){
                statement = connection.prepareStatement(UPDATE_PATH);
                statement.setString(1,path);
                statement.setString(2,login);
                statement.execute();
                status = true;
                LOGGER.warn("User login: " + login + " updated path");
            }
        } catch (TrackerConnectionPoolException | SQLException e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrond update path");
        } finally {
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return status;
    }


    @Override
    public void updateUser(User user) throws TrackerDBException {
        int i = jdbcTemplate.update(UPDATE_DATA_USER,  user.getName(), user.getSurname(), user.getEmail(),
                user.getBirthDate().toString(), user.getLogin());
        if(i != 1){
            throw new TrackerDBException("Wrong updated user");
        }
    }


    public boolean deposit(String login, BigDecimal sum) throws TrackerDBException {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean status  = false;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            if(!isUniqueUser(connection, login)){
                statement = connection.prepareStatement(UPDATE_BALANCE);
                statement.setBigDecimal(1,sum);
                statement.setString(2,login);
                statement.executeUpdate();
                status = true;
                LOGGER.warn("User: " + login + " deposited " + sum + "$");
            }
        }catch (TrackerConnectionPoolException | SQLException e){
            LOGGER.error(e);
            throw new TrackerDBException("Wrong deposit");
        } finally {
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return status;
    }


    public boolean isUniqueUser(Connection connection, String login) throws TrackerDBException {
        boolean status = true;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            statement = connection.prepareStatement(SELECT_LOGIN_USER);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
            status = false;
            }
        } catch (SQLException e){
            LOGGER.error(e);
            throw new TrackerDBException("error check uniqe user");
        } finally {
            this.closeQuietly(resultSet);
            this.closeQuietly(statement);
        }
        return status;
    }

    private boolean hasId(Connection connection, int id) throws TrackerDBException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean status = false;
        try{
            statement = connection.prepareStatement(SELECT_USER_BY_ID);
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                status = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong checking user by id");
        } finally {
            this.closeQuietly(resultSet);
            this.closeQuietly(statement);
        }
        return status;
    }

}
