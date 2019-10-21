package by.epam.crackertracker.mapper;

import by.epam.crackertracker.entity.Bucket;
import by.epam.crackertracker.util.ParameterConstant;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BucketMapper implements RowMapper<Bucket> {
    @Override
    public Bucket mapRow(ResultSet resultSet, int i) throws SQLException {
        Bucket bucket = new Bucket(resultSet.getString(1), resultSet.getString(4),
                resultSet.getInt(5), resultSet.getDouble(2));
        bucket.setId(resultSet.getInt(3));
        return bucket;
    }
}
