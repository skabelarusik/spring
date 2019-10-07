package by.epam.crackertracker.mapper;

import by.epam.crackertracker.entity.Message;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageMapper implements RowMapper<Message> {


    @Override
    public Message mapRow(ResultSet resultSet, int i) throws SQLException {
        return null;
    }
}
