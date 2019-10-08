package by.epam.crackertracker.mapper;

import by.epam.crackertracker.entity.ProgramsName;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProgramNameMapper implements RowMapper<ProgramsName> {

    @Override
    public ProgramsName mapRow(ResultSet resultSet, int i) throws SQLException {
        ProgramsName programsName = new ProgramsName(resultSet.getString(2), resultSet.getString(3),
                new BigDecimal(resultSet.getDouble(4)), resultSet.getInt(5));
        programsName.setId(resultSet.getInt(1));
        return programsName;
    }
}
