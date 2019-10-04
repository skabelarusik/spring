/**
 * jdbc dao class with different method for receive table programs name in DB
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.dao;

import by.epam.crackertracker.entity.ProgramsName;
import by.epam.crackertracker.exception.TrackerConnectionPoolException;
import by.epam.crackertracker.exception.TrackerDBException;
import by.epam.crackertracker.pool.ConnectionPool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProgramsNameDaoJdbc implements ProgramsNameDao {
    public static final String SELECT_ALL_PROGRAMS_NAME = "SELECT p.id, p.name, u.login, p.duration, p.cost " +
            "from programs_name p inner join users u on p.curator = u.id where p.show_name = ? limit ? offset ? ";
    public static final String INSERT_PROGRAMS_NAME = "INSERT INTO programs_name (name, curator, duration, cost)" +
            " VALUES (?,(SELECT id from users where login = ?),?,?)";
    public static final String SELECT_ID_PROGRAM = "SELECT id from programs_name where id = ?";
    public static final String SELECT_LOGIN_CURATOR = "SELECT u.login from programs_name pn inner join users u \n" +
            "on u.id = pn.curator where u.login = ? limit 1";
    public static final String SELECT_CURATOR_PROGRAM_NAME = "SELECT pn.id, pn.name, u.login, pn.duration, pn.cost from programs_name pn inner join users u \n" +
            "on u.id = pn.curator where u.login = ? and pn.show_name = ? limit ? offset ?";
    public static final String DELETE_BY_ID = "UPDATE programs_name set show_name = ? where id = ?";
    public static final String UPDATE_PROGRAM_NAME = "UPDATE programs_name SET name = ?, duration = ?, cost = ? WHERE id = ?";
    public static final String SELECT_NAME_PROGRAM = "SELECT name from programs_name where name = ?";
    public static final String HAS_NAME_PROGRAM = "SELECT * from programs_name where id = ? and curator = " +
            "(select id from users where login = ?) ";

    public static final int COUNT_NAMES = 11;

    private static final Logger LOGGER = LogManager.getRootLogger();

    @Autowired
    private JdbcTemplate template;

    @Override
    public List<ProgramsName> selectAll(int page, int type) throws TrackerDBException {
        Connection connection = null;
        List<ProgramsName> list = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SELECT_ALL_PROGRAMS_NAME);
            statement.setInt(1, type);
            statement.setInt(2, COUNT_NAMES);
            statement.setInt(3, (page - 1) * COUNT_NAMES - (page - 1));
            resultSet = statement.executeQuery();
            list = createListProgramsName(resultSet);
        } catch (SQLException | TrackerConnectionPoolException e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong selecting program name");
        } finally {
            this.closeQuietly(resultSet);
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return list;
    }

    public List<ProgramsName> selectCuratorPrograms(String login ,int page, int type) throws  TrackerDBException {
        Connection connection = null;
        List<ProgramsName> list = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            if(hasLogin(connection,login)){
                statement = connection.prepareStatement(SELECT_CURATOR_PROGRAM_NAME);
                statement.setString(1, login);
                statement.setInt(2, type);
                statement.setInt(3, COUNT_NAMES);
                statement.setInt(4, (page - 1) * COUNT_NAMES - (page - 1));
                resultSet = statement.executeQuery();
                list = createListProgramsName(resultSet);
            }

        } catch (SQLException | TrackerConnectionPoolException e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong selecting program name");
        } finally {
            this.closeQuietly(resultSet);
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return list;
    }

    @Override
    public boolean deleteById(int id, int type) throws TrackerDBException  {
        boolean status = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            if (hasId(connection, id)) {
                statement = connection.prepareStatement(DELETE_BY_ID);
                statement.setInt(1, type);
                statement.setInt(2, id);
                statement.executeUpdate();
                status = true;
                LOGGER.warn("Programs name id: " + id + " was deleted");
            }
        }catch (TrackerConnectionPoolException | SQLException e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong delete program name by id" + e);
        } finally {
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return status;
    }

    @Override
    public boolean insert(ProgramsName programsName) throws TrackerDBException {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean status = false;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            if(!isUniqueProductName(connection, programsName.getName())){
                statement = connection.prepareStatement(INSERT_PROGRAMS_NAME);
                statement.setString(1, programsName.getName());
                statement.setString(2, programsName.getCurator());
                statement.setInt(3, programsName.getDuration());
                statement.setBigDecimal(4, programsName.getCost());
                statement.executeUpdate();
                status = true;
                LOGGER.warn("Programs name: " + programsName.getName() + " was insert");
            }
        } catch (SQLException | TrackerConnectionPoolException e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong insert program name" + e);
        } finally {
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return status;
    }

    @Override
    public boolean updateProgramsName(ProgramsName programsName) throws TrackerDBException {
        boolean status = false;
        Connection connection =  null;
        PreparedStatement statement = null;
            try{
                connection = ConnectionPool.getInstance().takeConnection();
                if(hasProgramName(connection, programsName.getId(), programsName.getCurator())){
                    statement = connection.prepareStatement(UPDATE_PROGRAM_NAME);
                    statement.setString(1, programsName.getName());
                    statement.setInt(2, programsName.getDuration());
                    statement.setBigDecimal(3, programsName.getCost());
                    statement.setInt(4, programsName.getId());
                    statement.executeUpdate();
                    status = true;
                    LOGGER.warn("Programs name: " + programsName.getName() + " was update");
                }
            } catch (TrackerConnectionPoolException | SQLException e){
                LOGGER.error(e);
                throw new TrackerDBException("Wrong updating program name",e);
            } finally {
                this.closeQuietly(statement);
                this.closeQuietly(connection);
        }
        return status;
    }

    private List<ProgramsName> createListProgramsName(ResultSet resultSet) throws TrackerDBException {
        List<ProgramsName> list = new ArrayList<>();
        try{
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String curator = resultSet.getString(3);
            int duration = resultSet.getInt(4);
            BigDecimal cost = BigDecimal.valueOf(resultSet.getDouble(5));
            ProgramsName programsName = new ProgramsName(name,curator, cost, duration);
            programsName.setId(id);
            list.add(programsName);
        }
        } catch (SQLException e){
            LOGGER.error(e);
            throw new TrackerDBException("Wrong creating programs name" ,e);
        }
        return list;
    }

    private boolean hasId(Connection connection, int id) throws TrackerDBException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean status = false;
        try{
            statement = connection.prepareStatement(SELECT_ID_PROGRAM);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            status = resultSet.next();
        } catch (SQLException e){
            LOGGER.error(e);
            throw new TrackerDBException("Wrong checking id programs", e);
        } finally {
            closeQuietly(resultSet);
        }
        return status;
    }

    private boolean hasLogin(Connection connection, String login) throws TrackerDBException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean status = false;
        try{
            statement = connection.prepareStatement(SELECT_LOGIN_CURATOR);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            status = resultSet.next();
        } catch (SQLException e){
            LOGGER.error(e);
            throw new TrackerDBException("Wrong checking curator programs", e);
        } finally {
            closeQuietly(resultSet);
        }
        return status;
    }



    private boolean isUniqueProductName(Connection connection, String name) throws TrackerDBException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean status;
        try {
            statement = connection.prepareStatement(SELECT_NAME_PROGRAM);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            status = resultSet.next();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong checking unique programs",e);
        }   finally {
            this.closeQuietly(resultSet);
            this.closeQuietly(statement);
        }
        return status;
    }

    private boolean hasProgramName(Connection connection, int id, String login) throws TrackerDBException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean status = false;
        try{
            statement = connection.prepareStatement(HAS_NAME_PROGRAM);
            statement.setInt(1, id);
            statement.setString(2, login);
            resultSet = statement.executeQuery();
            status = resultSet.next();
        } catch (SQLException e){
            LOGGER.error(e);
            throw new TrackerDBException("Wrong checking program name",e);
        } finally {
            this.closeQuietly(resultSet);
            this.closeQuietly(statement);
        }
        return status;
    }
}
