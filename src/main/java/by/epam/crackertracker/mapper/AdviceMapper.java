package by.epam.crackertracker.mapper;

import by.epam.crackertracker.entity.Advice;

import by.epam.crackertracker.util.ParameterConstant;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdviceMapper implements RowMapper<Advice> {


    @Override
    public Advice mapRow(ResultSet resultSet, int i) throws SQLException {
        Advice advice = new Advice(resultSet.getString(ParameterConstant.TEXT_ADVICE));
        advice.setId(resultSet.getInt(ParameterConstant.ID_ADVICE));
        return advice;
    }
}
