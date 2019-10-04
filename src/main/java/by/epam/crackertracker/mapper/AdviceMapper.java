package by.epam.crackertracker.mapper;

import by.epam.crackertracker.entity.Advice;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdviceMapper implements RowMapper<Advice> {
    public static final String TEXT = "message";
    public static final String ID = "idadvices";

    @Override
    public Advice mapRow(ResultSet resultSet, int i) throws SQLException {
        Advice advice = new Advice(resultSet.getString(TEXT));
        advice.setId(resultSet.getInt(ID));
        return advice;
    }
}
