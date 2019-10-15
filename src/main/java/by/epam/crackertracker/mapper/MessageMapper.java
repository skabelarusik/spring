package by.epam.crackertracker.mapper;

import by.epam.crackertracker.entity.Message;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Component
public class MessageMapper implements RowMapper<Message> {

    @Override
    public Message mapRow(ResultSet resultSet, int i) throws SQLException {
        Message message = new Message();
        message.setId(resultSet.getInt(1));
        message.setSender(resultSet.getString(2));
        message.setRecipient(resultSet.getString(3));
        message.setTopik(resultSet.getString(4));
        message.setText(resultSet.getString(5));
        message.setLocalDate(resultSet.getDate(6).toLocalDate());
        return message;
    }
}
