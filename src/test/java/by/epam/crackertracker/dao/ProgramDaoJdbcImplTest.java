package by.epam.crackertracker.dao;

import by.epam.crackertracker.entity.MealDay;
import by.epam.crackertracker.entity.MealTime;
import by.epam.crackertracker.entity.Program;
import by.epam.crackertracker.exception.TrackerConnectionPoolException;
import by.epam.crackertracker.exception.TrackerDBException;
import by.epam.crackertracker.pool.ConnectionPool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class ProgramDaoJdbcImplTest {
/*
    public static final String INSERT_PRODUCT_TO_PROGRAM = "INSERT INTO programs (name, product, portions, time, day)\n" +
            "values ((SELECT id from programs_name where name = ?),(SELECT idproducts from products where name = ?),?,?,?)";
    public static final String CHECK_ID_PROGRAM = "SELECT id FROM programs_name where name = ?";
    public static final String CHECK_ID_PRODUCT = "SELECT idproducts FROM products where name = ?";
    public static final String SELECT_ALL = "SELECT p.id, pr1.name, pr2.name, p.portions, p.time, p.day from programs p " +
        "inner join programs_name pr1 on pr1.id = p.name inner join products pr2 on pr2.idproducts = p.product" +
            " where pr1.name = ? order by p.day";
    public static final String SELECT_ID_BY_PROGRAM_NAME_CURATOR = "select p.id from programs_name p inner join users u on " +
            "u.id = p.curator where u.login = ? and p.name = ?";






    private static final Logger LOGGER = LogManager.getRootLogger();

    public boolean insert(Program program) throws TrackerDBException {
        boolean status = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            if(checkProductId(connection, program.getProduct()) && checkProgramId(connection, program.getProgramName())){
            statement = connection.prepareStatement(INSERT_PRODUCT_TO_PROGRAM);
            statement.setString(1,program.getProgramName());
            statement.setString(2, program.getProduct());
            statement.setDouble(3, program.getPortions());
            statement.setString(4, program.getTime().name());
            statement.setString(5, program.getDay().name());
            statement.execute();
            status = true;
            LOGGER.warn("Program: " + program.getProgramName() + " inserted");
            }
        } catch (SQLException | TrackerConnectionPoolException e){
            LOGGER.error(e);
            throw new TrackerDBException("Wrong insert product to program", e);
        } finally {
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return status;
    }


    public List<Program> selectAll(String name, String login) throws TrackerDBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Program> list;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            if(hasCuratorProgram(connection, name, login)){
                statement = connection.prepareStatement(SELECT_ALL);
                statement.setString(1, name);
                resultSet = statement.executeQuery();
                list = fillingList(resultSet);
            } else {
                throw new TrackerDBException("Wrong trying access to data");
            }
        } catch (TrackerConnectionPoolException | SQLException e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong select all programs",e);
        } finally {
            this.closeQuietly(resultSet);
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return list;
    }


    public boolean checkProgramId(Connection connection, String program) throws TrackerDBException {
        boolean status = false;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            statement = connection.prepareStatement(CHECK_ID_PROGRAM);
            statement.setString(1, program);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                status = true;
            }
        }catch (SQLException e){
            LOGGER.error(e);
            throw new TrackerDBException("Wrong checking program name",e);
        } finally {
            this.closeQuietly(resultSet);
            this.closeQuietly(statement);
        }
        return status;
    }

    public boolean checkProductId(Connection connection ,String product) throws TrackerDBException {
        boolean status = false;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            statement = connection.prepareStatement(CHECK_ID_PRODUCT);
            statement.setString(1, product);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                status = true;
            }
        }catch (SQLException e){
            LOGGER.error(e);
            throw new TrackerDBException("Wrong checking product",e);
        } finally {
            this.closeQuietly(resultSet);
            this.closeQuietly(statement);
        }
        return status;
    }


    private boolean hasCuratorProgram(Connection connection, String name, String login) throws TrackerDBException {
        boolean status = false;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SELECT_ID_BY_PROGRAM_NAME_CURATOR);
            statement.setString(1, login);
            statement.setString(2, name);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                status = true;
            }
        } catch (SQLException e) {
            throw new TrackerDBException("Wrong checking curators program",e);
        }   finally {
            this.closeQuietly(resultSet);
            this.closeQuietly(statement);
        }
        return status;
    }

    private List<Program> fillingList(ResultSet resultSet) throws TrackerDBException {
        List<Program> list = new ArrayList<>();
        try{
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String progName = resultSet.getString(2);
                String product = resultSet.getString(3);
                double portions = resultSet.getDouble(4);
                MealTime time = MealTime.valueOf(resultSet.getString(5).toUpperCase());
                MealDay day = MealDay.valueOf(resultSet.getString(6).toUpperCase());
                Program program = new Program(progName, product, portions, day, time);
                program.setId(id);
                list.add(program);
            }
        } catch (SQLException e){
            throw new TrackerDBException("Wrong filling program list",e);
        } finally {
            this.closeQuietly(resultSet);
        }
        return list;
    }

    public static final String DELETE_BY_ID = "DELETE FROM programs where id = ?";

    public boolean deleteById(int idProgram) throws TrackerDBException {
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(DELETE_BY_ID);
            statement.setInt(1, idProgram);
            statement.execute();
        } catch (SQLException | TrackerConnectionPoolException e){
            throw new TrackerDBException("Wrong delete by id",e);
        } finally {
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return true;
    }

    String SQL = "SELECT p.id, pr1.name, pr2.name, p.portions, p.time, p.day from programs p \n"+
            "         inner join programs_name pr1 on pr1.id = p.name inner join products pr2 on pr2.idproducts = p.product \n"+
            "         inner join subscriptions s on s.program = pr1.id  where p.day = 'thursday' and s.subscriber = (SELECT id from users where login = 'SLON') \n"+
            "         and s.start_date <= '2019-09-12' and finish_date >= '2019-09-12' order by p.time ";

    public static final String SELECT_ALL_SUPERUSER = "SELECT p.id, pr1.name, pr2.name, p.portions, p.time, p.day from programs p \n" +
            "inner join programs_name pr1 on pr1.id = p.name inner join products pr2 on pr2.idproducts = p.product \n" +
            "inner join subscriptions s on s.program = pr1.id  " +
            "where p.day = ? and " +
            " s.subscriber = (SELECT id from users where login = ?) " +
            "and s.start_date <= ? and s.finish_date >= ? order by p.time ";

    public List<Program> selectSuperuserPrograms(String loginValue) throws TrackerDBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Program> programList = new ArrayList<>();
        LocalDate date = LocalDate.now();
        String day = date.getDayOfWeek().toString();
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SELECT_ALL_SUPERUSER);
            statement.setString(1, day.toLowerCase());
            statement.setString(2, loginValue);
            statement.setDate(3, Date.valueOf(date.toString()));
            statement.setDate(4, Date.valueOf(date.toString()));
            resultSet = statement.executeQuery();
            programList = fillingList(resultSet);
        } catch (SQLException | TrackerConnectionPoolException e){
            throw new TrackerDBException("Wrong select superuser list products");
        } finally {
            this.closeQuietly(resultSet);
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return programList;

    }

 */

}
