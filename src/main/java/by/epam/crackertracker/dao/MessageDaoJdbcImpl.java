/**
 * jdbc dao class with different method for receive table messages in DB
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.dao;

import by.epam.crackertracker.entity.Message;
import by.epam.crackertracker.exception.TrackerConnectionPoolException;
import by.epam.crackertracker.exception.TrackerDBException;
import by.epam.crackertracker.mapper.MessageMapper;
import by.epam.crackertracker.pool.ConnectionPool;
import by.epam.crackertracker.util.ParameterConstant;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class MessageDaoJdbcImpl implements MessageDao {

   private static final String SELECT_INPUT_MESSAGE = "SELECT m.idmessages, u.login, m.recipient, m.topik, m.text, m.date from messages m inner join users u\n" +
           "on m.sender = u.id inner join users u1 on m.recipient = u1.id where u1.login = ? and m.show_recipient=1 order by m.date desc limit ? offset ? ";
   private static final String SELECT_OUTPUT_MESSAGE = "SELECT m.idmessages, u1.login, m.recepient, m.topik, m.text, m.date from messages m " +
           "inner join users u on m.sender = u.id inner join users u1 on m.recipient = u1.id where u.login = ? and m.show_sender=1 order by m.date desc limit ? offset ?";
   private static final String INSERT_MESSAGE = "insert into messages (sender, recipient, topik, text, date) values (?,?,?,?,?)";
   public static final String DELETE_MESSAGE_INPUT = "UPDATE messages set show_recipient = 0 where idmessages = ?";
   public static final String DELETE_MESSAGE_OUTPUT = "UPDATE messages set show_sender = 0 where idmessages = ?";

    public static final int COUNT_MESSAGE = 6;

    private static final Logger LOGGER = LogManager.getRootLogger();

    @Autowired
    private JdbcTemplate template;

    @Override
    public List<Message> selectInputMessage(String login, int i) throws TrackerDBException {
        List<Message> list;
        try{
            list = template.query(SELECT_INPUT_MESSAGE, new MessageMapper(), login, COUNT_MESSAGE, ((i - 1) * COUNT_MESSAGE - (i - 1)));
        } catch (Exception e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong select input message");
        }
        return list;
    }

    @Override
    public List<Message> selectOutputMessage(String login, int i) throws TrackerDBException {
        List<Message> list;
        try{
            list = template.query(SELECT_OUTPUT_MESSAGE, new MessageMapper(), login, COUNT_MESSAGE, ((i - 1) * COUNT_MESSAGE - (i - 1)));
        } catch (Exception e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong select output message");
        }
        return list;
    }

    @Override
    public void addMessage(Message message) throws TrackerDBException {
        try{
            template.update(INSERT_MESSAGE, message.getSender(), message.getRecipient(), message.getTopik(),
                    message.getText(), message.getLocalDate());
        } catch (Exception e) {
            LOGGER.error("Wrong insert message");
            throw new TrackerDBException("Wrong insert message from user : " + message.getSender());
        }
    }

    public void deleteMessage(int id, String type) throws TrackerDBException {
        String sqlStr = null;
        if(type.equals(ParameterConstant.INPUT_MESSAGE)){
            sqlStr = DELETE_MESSAGE_INPUT;
        } else {
            sqlStr = DELETE_MESSAGE_OUTPUT;
        }
        try{
            template.update(sqlStr, id);
        } catch (Exception e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong delete message");
        }
    }
}
