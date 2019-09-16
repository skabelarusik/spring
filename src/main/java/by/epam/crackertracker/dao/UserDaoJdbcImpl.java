/**
 * jdbc dao class with different method for receive table users in DB
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.dao;

import by.epam.crackertracker.entity.Gender;
import by.epam.crackertracker.entity.Role;
import by.epam.crackertracker.entity.User;
import by.epam.crackertracker.exception.TrackerConnectionPoolException;
import by.epam.crackertracker.exception.TrackerDBException;
import by.epam.crackertracker.pool.ConnectionPool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;


public class UserDaoJdbcImpl implements UserDao {

    public static final String SELECT_ALL_USERS = "SELECT u.id, u.name, u.surname, u.login, u.sex," +
            "u.birthday, u.registrdate, u.email, r1.name from users u INNER JOIN role r1 on u.status = r1.id where u.active = 1 limit ? offset ?";
    public static final String SELECT_ALL_USERS_INC_AGE = "SELECT u.id, u.name, u.surname, u.login, u.sex," +
            "u.birthday, u.registrdate, u.email, r1.name from users u INNER JOIN role r1 on u.status = r1.id " +
            "order by u.birthday limit ? offset ? ";
    public static final String SELECT_ALL_USERS_DEC_AGE = "SELECT u.id, u.name, u.surname, u.login, u.sex," +
            "u.birthday, u.registrdate, u.email, r1.name from users u INNER JOIN role r1 on u.status = r1.id " +
            "order by u.birthday desc limit ? offset ? ";
    public static final String SELECT_ALL_USERS_INC_LOGIN = "SELECT u.id, u.name, u.surname, u.login, u.sex," +
            "u.birthday, u.registrdate, u.email, r1.name from users u INNER JOIN role r1 on u.status = r1.id " +
            "order by u.login limit ? offset ? ";
    public static final String SELECT_ALL_USERS_DEC_LOGIN = "SELECT u.id, u.name, u.surname, u.login, u.sex," +
            "u.birthday, u.registrdate, u.email, r1.name from users u INNER JOIN role r1 on u.status = r1.id " +
            "order by u.login desc limit ? offset ? ";

    public static final String SELECT_USER_BY_ROLE = "SELECT u.id, u.name, u.surname, u.login, u.sex, u.birthday, " +
            "u.registrdate,  u.email, r.name from users u INNER JOIN role r on u.status = r.id where r.name = ? limit ? offset ?";
    public static final String SELECT_USER_BY_ROLE_INC_LOGIN = "SELECT u.id, u.name, u.surname, u.login, u.sex, u.birthday, " +
            "u.registrdate,  u.email, r.name from users u INNER JOIN role r on u.status = r.id where r.name = ? order by u.login limit ? offset ?";
    public static final String SELECT_USER_BY_ROLE_DEC_LOGIN = "SELECT u.id, u.name, u.surname, u.login, u.sex, u.birthday, " +
            "u.registrdate,  u.email, r.name from users u INNER JOIN role r on u.status = r.id where r.name = ? order by u.login desc limit ? offset ?";

    public static final String SELECT_USER_BY_GENDER = "SELECT u.id, u.name, u.surname, u.login, u.sex, u.birthday, " +
            "u.registrdate,  u.email, r.name from users u LEFT JOIN role r on u.status = r.id where u.sex = ? limit ? offset ?";
    public static final String SELECT_USER_BY_GENDER_INC_LOGIN = "SELECT u.id, u.name, u.surname, u.login, u.sex, u.birthday, " +
            "u.registrdate,  u.email, r.name from users u LEFT JOIN role r on u.status = r.id where u.sex = ? order by u.login limit ? offset ?";
    public static final String SELECT_USER_BY_GENDER_DEC_LOGIN = "SELECT u.id, u.name, u.surname, u.login, u.sex, u.birthday, " +
            "u.registrdate,  u.email, r.name from users u LEFT JOIN role r on u.status = r.id where u.sex = ? order by u.login desc limit ? offset ?";

    public static final String DELETE_BY_ID_LOGIN = "UPDATE users set active = 0 where id = ? and login = ?";
    public static final String UPDATE_ROLE_USER = "UPDATE users SET status = ? WHERE id = ?";
    public static final String SELECT_LOGIN_USER = "SELECT id from users where login = ?";
    public static final String INSERT_USER = "INSERT INTO users (name, surname, login, password, sex, birthday, email, " +
            "registrdate, avatar) VALUES (?,?,?,?,?,?::date,?,?::date,?)";
    public static final String UPDATE_PASSWORD = "UPDATE users set password = ? WHERE login = ?";
    public static final String SELECT_USER_BY_LOGIN_PASS = "SELECT u.name, u.surname, u.password, u.sex, u.email, u.birthday, " +
            "u.registrdate, u.money, u.avatar, r.name, u.id from users u INNER JOIN role r on u.status = r.id where u.login = ? and u.active = 1";
    public static final String SELECT_ID_LOGIN_USER = "SELECT id from users where id = ? and login = ?";
    public static final String SELECT_USER_BY_ID = "SELECT id from users where id = ?";
    public static final String UPDATE_DATA_USER = "UPDATE users SET name = ?, surname = ?, " +
            "email = ?, birthday = ?::date where login = ?";
    public static final String UPDATE_BALANCE = "UPDATE users set money = ? where login = ?";
    public static final String UPDATE_PATH = "UPDATE users SET avatar = ? where login = ?";
    public static final String SELECT_ID_CURATOR = "SELECT id from users where status = ?";
    public static final String SELECT_TOP5_CURATOR = "SELECT AVG(c.rate), u.login FROM curator_rate c inner join users u on " +
            "c.login = u.id   where u.status = 4 group by u.id order by AVG(c.rate) desc LIMIT 5";


    public static final int COUNT_USERS = 11;

    private static final Logger LOGGER = LogManager.getRootLogger();

    public Map<String, Double> selectTopFiveCurators () throws TrackerDBException {
        Map<String, Double> mapUser = new LinkedHashMap<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_TOP5_CURATOR);
            while (resultSet.next()) {
                Double id = resultSet.getDouble(1);
                String login = resultSet.getString(2);
                mapUser.put(login, Double.parseDouble(""+id));
                }
        } catch (SQLException | TrackerConnectionPoolException e) {
            throw new TrackerDBException("Wrong select random users");
        } finally {
            this.closeQuietly(resultSet);
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return mapUser;
    }



    @Override
    public List<User> selectAll(int page, String type) throws TrackerDBException {
        Connection connection = null;
        List<User> listProduct;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sqlReq;

        switch (type){
            case "DECREASE_AGE": sqlReq = SELECT_ALL_USERS_DEC_AGE;
                break;
            case "INCREASE_AGE": sqlReq = SELECT_ALL_USERS_INC_AGE;
                break;
            case "INCREASE_LOGIN": sqlReq = SELECT_ALL_USERS_INC_LOGIN;
                break;
            case "DECREASE_LOGIN": sqlReq = SELECT_ALL_USERS_DEC_LOGIN;
                break;
            default: sqlReq = SELECT_ALL_USERS;
                break;
        }


        try{
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, COUNT_USERS);
            statement.setInt(2, (page - 1) * COUNT_USERS - (page - 1));
            resultSet = statement.executeQuery();
            listProduct = createListUsers(resultSet);
        } catch (TrackerConnectionPoolException | SQLException e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong select all users");
        } finally {
            this.closeQuietly(resultSet);
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return listProduct;
    }

    public List<User> selectByGender (int page, String type, String gender) throws TrackerDBException {
        Connection connection = null;
        List<User> list;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sqlReq;
        switch (type){
            case "DECREASE_LOGIN": sqlReq = SELECT_USER_BY_GENDER_DEC_LOGIN;
                break;
            case "INCREASE_LOGIN": sqlReq = SELECT_USER_BY_GENDER_INC_LOGIN;
                break;
            default: sqlReq = SELECT_USER_BY_GENDER;
                break;
        }
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(sqlReq);
            statement.setString(1, gender);
            statement.setInt(2, COUNT_USERS);
            statement.setInt(3, (page - 1) * COUNT_USERS - (page - 1));
            resultSet = statement.executeQuery();
            list = createListUsers(resultSet);
        } catch (TrackerConnectionPoolException | SQLException e){
            LOGGER.error(e);
            throw new TrackerDBException("Wrong select users by gender");
        } finally {
            this.closeQuietly(resultSet);
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return list;
    }



    @Override
    public boolean deleteByIdLogin(int id, String login) throws TrackerDBException {
        boolean status = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            if (hasIdLogin(connection, id, login)) {
                    statement = connection.prepareStatement(DELETE_BY_ID_LOGIN);
                    statement.setInt(1, id);
                    statement.setString(2, login);
                    statement.executeUpdate();
                    status = true;
                    LOGGER.warn("user id:" + id + " banned");
            }
        } catch (TrackerConnectionPoolException | SQLException e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong delete by ID user");
        } finally {
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return status;
    }



    public User selectByLogin(String login, String pass) throws TrackerDBException {
        User user = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SELECT_USER_BY_LOGIN_PASS);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                String password = resultSet.getString(3);
                if(BCrypt.checkpw(pass, password)){
                    user = new User(login);
                    String name = resultSet.getString(1);
                    String surname = resultSet.getString(2);
                    Gender gender = Gender.valueOf(resultSet.getString(4).toUpperCase().trim());
                    String email = resultSet.getString(5);
                    LocalDate birthday= LocalDate.parse(resultSet.getString(6).trim());
                    LocalDate registr = LocalDate.parse(resultSet.getString(7));
                    BigDecimal balance = BigDecimal.valueOf(Double.parseDouble(resultSet.getString(8)));
                    String path = resultSet.getString(9).trim();
                    Role role = Role.valueOf(resultSet.getString(10).toUpperCase().trim());
                    int id = resultSet.getInt(11);
                    user.setName(name);
                    user.setSurname(surname);
                    user.setEmail(email);
                    user.setRole(role);
                    user.setGender(gender);
                    user.setBirthDate(birthday);
                    user.setRegistrDate(registr);
                    user.setBalance(balance);
                    user.setPath(path);
                    user.setId(id);
                }
            }
        } catch (TrackerConnectionPoolException | SQLException e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong select user by login connection");
        } finally {
            this.closeQuietly(resultSet);
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return user;
    }

    public int selectIdByLogin(Connection connection, String login) throws TrackerDBException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int id = 0;
        try{
            statement = connection.prepareStatement(SELECT_LOGIN_USER);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong select user by id");
        } finally {
          this.closeQuietly(resultSet);
          this.closeQuietly(statement);
          this.closeQuietly(connection);
        }
        return id;
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
    public boolean updateRoleUser(int id, Role role) throws TrackerDBException {
        boolean status = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            if (hasId(connection, id)){
                    int idRole = role.ordinal() + 1;
                    statement = connection.prepareStatement(UPDATE_ROLE_USER);
                    statement.setInt(1,idRole);
                    statement.setInt(2, id);
                    statement.executeUpdate();
                    status = true;
                    LOGGER.warn("User id:" + id + " was update to " + role + " status");
            }
        } catch (TrackerConnectionPoolException | SQLException e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong update role user");
        } finally {
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return status;
    }

    public boolean updatePasswordUser(String login, String newPassword) throws TrackerDBException {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean status = false;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(UPDATE_PASSWORD);
            String password = BCrypt.hashpw(newPassword, BCrypt.gensalt(12));
            statement.setString(1, password);
            statement.setString(2, login);
            statement.executeUpdate();
            status = true;
        } catch (TrackerConnectionPoolException | SQLException e){
            LOGGER.error(e);
            throw new TrackerDBException("Wrong update password user");
        } finally {
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return status;
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

    public List<User> selectByRole(int page, String type, String role) throws TrackerDBException {
        Connection connection = null;
        List<User> list;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sqlReq;
        switch (type){
            case "DECREASE_LOGIN": sqlReq = SELECT_USER_BY_ROLE_DEC_LOGIN;
                break;
            case "INCREASE_LOGIN": sqlReq = SELECT_USER_BY_ROLE_INC_LOGIN;
                break;
            default: sqlReq = SELECT_USER_BY_ROLE;
                break;
        }
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(sqlReq);
            statement.setString(1, role);
            statement.setInt(2, COUNT_USERS);
            statement.setInt(3, (page - 1) * COUNT_USERS - (page - 1));
            resultSet = statement.executeQuery();
            list = createListUsers(resultSet);
        } catch (TrackerConnectionPoolException | SQLException e){
            LOGGER.error(e);
            throw new TrackerDBException("Wrong select users by gender");
        } finally {
            this.closeQuietly(resultSet);
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return list;
    }

    @Override
    public boolean updateUser(User user) throws TrackerDBException {
        boolean status = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection =  ConnectionPool.getInstance().takeConnection();
            if (!isUniqueUser(connection, user.getLogin())){
                statement = connection.prepareStatement(UPDATE_DATA_USER);
                statement.setInt(1,user.getId());
                statement.setString(1,user.getName());
                statement.setString(2,user.getSurname());
                statement.setString(3,user.getEmail());
                statement.setString(4, user.getBirthDate().toString());
                statement.setString(5,user.getLogin());
                statement.executeUpdate();
                status = true;
           }
        } catch (TrackerConnectionPoolException | SQLException e){
            LOGGER.error(e);
            throw new TrackerDBException("Wrong update user");
        } finally {
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return status;
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

    private boolean hasIdLogin(Connection connection, int id, String login) throws TrackerDBException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean status = false;
        try {
            statement = connection.prepareStatement(SELECT_ID_LOGIN_USER);
            statement.setInt(1, id);
            statement.setString(2, login);
            resultSet = statement.executeQuery();
            status = resultSet.next();
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new TrackerDBException("wrong check user has id");
        } finally {
            this.closeQuietly(resultSet);
            this.closeQuietly(statement);
            this.closeQuietly(resultSet);
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



    private List<User> createListUsers(ResultSet resultSet) throws TrackerDBException {
        List<User> list = new ArrayList<>();
        try{
            while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String surname = resultSet.getString(3);
            String login = resultSet.getString(4);
            Gender gender = Gender.valueOf((resultSet.getString(5)).toUpperCase().trim());
            LocalDate birthday = LocalDate.parse(resultSet.getString(6));
            LocalDate regist = LocalDate.parse(resultSet.getString(7));
            String email = resultSet.getString(8);
            String status = resultSet.getString(9);
            User user = new User(login);
            user.setId(id);
            user.setEmail(email);
            user.setName(name);
            user.setGender(gender);
            user.setSurname(surname);
            user.setRole(Role.valueOf(status.toUpperCase().trim()));
            user.setBirthDate(birthday);
            user.setRegistrDate(regist);
            list.add(user);
        }
        }catch (SQLException e){
            LOGGER.error(e);
            throw new TrackerDBException("Wrong create list user");
        }
        return list;
    }
}
