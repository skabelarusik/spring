package by.epam.crackertracker.mapper;

import by.epam.crackertracker.entity.MealDay;
import by.epam.crackertracker.entity.MealTime;
import by.epam.crackertracker.entity.Program;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProgramMapper implements RowMapper<Program> {
    @Override
    public Program mapRow(ResultSet resultSet, int i) throws SQLException {
        Program program = new Program(resultSet.getString(2), resultSet.getString(3)
                , resultSet.getDouble(4), MealDay.valueOf(resultSet.getString(6).toUpperCase())
                , MealTime.valueOf(resultSet.getString(5).toUpperCase()));
        program.setId(resultSet.getInt(1));
        return program;
    }

//    SELECT p.id, pr1.name, pr2.name, p.portions, p.time, p.day from programs p " +
//            "inner join programs_name pr1 on pr1.id = p.name inner join products pr2 on pr2.idproducts = p.product" +
//            " where pr1.name = ? order by p.day";
}
