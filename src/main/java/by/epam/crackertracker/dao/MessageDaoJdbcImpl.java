/**
 * jdbc dao class with different method for receive table messages in DB
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.dao;

import by.epam.crackertracker.entity.Message;
import by.epam.crackertracker.exception.TrackerConnectionPoolException;
import by.epam.crackertracker.exception.TrackerDBException;
import by.epam.crackertracker.pool.ConnectionPool;
import by.epam.crackertracker.util.ParameterConstant;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MessageDaoJdbcImpl implements MessageDao {

   private static final String SELECT_INPUT_MESSAGE = "SELECT m.idmessages, u.login, m.topik, m.text, m.date from messages m inner join users u\n" +
           "on m.sender = u.id inner join users u1 on m.recipient = u1.id where u1.login = ? and m.show_recipient=1 order by m.date desc limit ? offset ? ";
   private static final String SELECT_OUTPUT_MESSAGE = "SELECT m.idmessages, u1.login, m.topik, m.text, m.date from messages m " +
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
        List<Message> messageList = new ArrayList<>();
        if(login.isEmpty()){
            throw new TrackerDBException("Wrong login");
        }

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
            try{
                connection = ConnectionPool.getInstance().takeConnection();
                statement = connection.prepareStatement(SELECT_INPUT_MESSAGE);
                statement.setString(1, login);
                statement.setInt(2, COUNT_MESSAGE);
                statement.setInt(3, (i - 1) * COUNT_MESSAGE - (i - 1));
                resultSet = statement.executeQuery();

                while (resultSet.next()){
                    Message message = new Message();
                    int id = resultSet.getInt(1);
                    String sender = resultSet.getString(2);
                    String topik = resultSet.getString(3);
                    String text = resultSet.getString(4);
                    LocalDate date = LocalDate.parse(resultSet.getString(5));
                    message.setLocalDate(date);
                    message.setSender(sender);
                    message.setText(text);
                    message.setTopik(topik);
                    message.setId(id);
                    messageList.add(message);
                    }

            } catch (TrackerConnectionPoolException | SQLException e) {
                LOGGER.error(e);
                throw new TrackerDBException("Wrong statement");
            } finally {
               this.closeQuietly(resultSet);
                this.closeQuietly(statement);
                this.closeQuietly(connection);
            }
        return messageList;
    }

    @Override
    public List<Message> selectOutputMessage(String login, int i) throws TrackerDBException {
        List<Message> outputList = null;
        if(login.isEmpty()){
            throw new TrackerDBException("Wrong login");
        }
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SELECT_OUTPUT_MESSAGE);
            statement.setString(1, login);
            statement.setInt(2, COUNT_MESSAGE);
            statement.setInt(3, (i - 1) * COUNT_MESSAGE - (i - 1));
            resultSet = statement.executeQuery();
            outputList = new ArrayList<>();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String recipient = resultSet.getString(2);
                String topik = resultSet.getString(3);
                String text = resultSet.getString(4);
                LocalDate date = LocalDate.parse(resultSet.getString(5));
                Message message = new Message();
                message.setRecipient(recipient);
                message.setTopik(topik);
                message.setText(text);
                message.setLocalDate(date);
                message.setId(id);
                outputList.add(message);
            }
        } catch (TrackerConnectionPoolException | SQLException e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong statement");
        } finally {
            this.closeQuietly(resultSet);
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return outputList;
    }

    public boolean addMessage(Message message) throws TrackerDBException {
        UserDaoJdbcImpl dao = new UserDaoJdbcImpl();
        Connection connection = null;
        PreparedStatement statement = null;
        boolean status = false;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            if(!dao.isUniqueUser(connection, message.getRecipient())){
                statement = connection.prepareStatement(INSERT_MESSAGE);
                int idSender = dao.selectIdByLogin(connection, message.getSender());
                int idRecipient = dao.selectIdByLogin(connection, message.getRecipient());
                statement.setInt(1, idSender);
                statement.setInt(2, idRecipient);
                statement.setString(3, message.getTopik());
                statement.setString(4, message.getText());
                statement.setString(5, message.getLocalDate().toString());
                statement.executeUpdate();
                status = true;
            }
        } catch (TrackerConnectionPoolException | SQLException e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong insert message");
        } finally {
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return status;
    }

    public boolean deleteMessage(int id, String type) throws TrackerDBException {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean status = false;
        String sqlStr = null;
        if(type.equals(ParameterConstant.INPUT_MESSAGE)){
            sqlStr = DELETE_MESSAGE_INPUT;
        } else {
            sqlStr = DELETE_MESSAGE_OUTPUT;
        }
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(sqlStr);
            statement.setInt(1, id);
            statement.executeUpdate();
            status = true;
        } catch (TrackerConnectionPoolException | SQLException e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong delete message");
        } finally {
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return status;
    }
}
