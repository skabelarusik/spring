package by.epam.crackertracker.dao;

import by.epam.crackertracker.entity.Program;
import by.epam.crackertracker.exception.TrackerDBException;
import by.epam.crackertracker.mapper.ProgramMapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Repository
public class ProgramDaoJdbcImpl implements ProgramDao {

    public static final String INSERT_PRODUCT_TO_PROGRAM = "INSERT INTO programs (name, product, portions, time, day)\n" +
            "values ((SELECT id from programs_name where name = ?),(SELECT idproducts from products where name = ?),?,?,?)";
    public static final String SELECT_ALL = "SELECT p.id, pr1.name, pr2.name, p.portions, p.time, p.day from programs p " +
        "inner join programs_name pr1 on pr1.id = p.name inner join products pr2 on pr2.idproducts = p.product" +
            " where pr1.name = ? order by p.day";
    public static final String DELETE_BY_ID = "DELETE FROM programs where id = ?";
    public static final String SELECT_ALL_SUPERUSER = "SELECT p.id, pr1.name, pr2.name, p.portions, p.time, p.day from programs p \n" +
            "inner join programs_name pr1 on pr1.id = p.name inner join products pr2 on pr2.idproducts = p.product \n" +
            "inner join subscriptions s on s.program = pr1.id  " +
            "where p.day = ? and " +
            " s.subscriber = (SELECT id from users where login = ?) " +
            "and s.start_date <= ? and s.finish_date >= ? order by p.time ";

    private static final Logger LOGGER = LogManager.getRootLogger();

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private ProgramMapper mapper;

    public void insert(Program program) throws TrackerDBException {
        try {
            template.update(INSERT_PRODUCT_TO_PROGRAM, program.getProgramName(), program.getProduct(),
                    program.getPortions(), program.getTime().name(), program.getDay().name());
            LOGGER.warn("Program: " + program.getProgramName() + " inserted");
        } catch (Exception e){
            LOGGER.warn("Program: " + program.getProgramName() + " dont inserted");
            throw new TrackerDBException();
        }
    }

    public List<Program> selectAll(String name){
        return template.query(SELECT_ALL, mapper, name);
    }

    public void deleteById(int idProgram) {
        template.update(DELETE_BY_ID, idProgram);
    }


    public List<Program> selectSuperuserPrograms(String loginValue) throws TrackerDBException {
        List<Program> programList = new ArrayList<>();
        LocalDate date = LocalDate.now();
        String day = date.getDayOfWeek().toString();
        return template.query(SELECT_ALL_SUPERUSER, mapper, day.toLowerCase(), loginValue, Date.valueOf(date.toString())
                , Date.valueOf(date.toString()));
    }
}
