/**
 * service for working with message dao
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.service;

import by.epam.crackertracker.dao.MessageDaoJdbcImpl;
import by.epam.crackertracker.dao.UserDaoJdbcImpl;
import by.epam.crackertracker.entity.Message;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.validator.IdValidator;
import by.epam.crackertracker.validator.LoginValidator;
import by.epam.crackertracker.exception.TrackerDBException;
import by.epam.crackertracker.validator.PositiveIntValidator;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.List;

public class MessageService {
    public static final int MAX_SIZE_TOPIC = 40;
    public static final int MAX_SIZE_TEXT = 500;
    private static final Logger LOGGER = Logger.getRootLogger();

    public List<Message> checkInputMessage(String login, int page) throws TrackerServiceException {
        MessageDaoJdbcImpl dao = new MessageDaoJdbcImpl();
        List<Message> result;
        LoginValidator loginValidator = new LoginValidator();
        PositiveIntValidator intValidator = new PositiveIntValidator();
        if(!loginValidator.isValidate(login) && intValidator.isValidate(page)){
            LOGGER.warn("Wrong login or int to check input message");
            throw new TrackerServiceException("Wrong login to check input message");
        }
        try {
            result = dao.selectInputMessage(login, page);
        } catch (TrackerDBException e) {
            LOGGER.error("Wrong service check input message", e);
            throw new TrackerServiceException("Wrong service check input message",e);
        }
        return result;
    }

    public List<Message> checkOutputMessage(String login, int page) throws TrackerServiceException {
        MessageDaoJdbcImpl dao = new MessageDaoJdbcImpl();
        List<Message> result;
        LoginValidator loginValidator = new LoginValidator();
        PositiveIntValidator intValidator = new PositiveIntValidator();
        if(!loginValidator.isValidate(login) && intValidator.isValidate(page)){
            LOGGER.warn("Wrong login or int to check output message");
            throw new TrackerServiceException("Wrong login to check imput message");
        }
        try {
            result = dao.selectOutputMessage(login, page);
        } catch (TrackerDBException e) {
            LOGGER.error("Wrong service check input message", e);
            throw new TrackerServiceException("Wrong service check output message");
        }
        return result;
    }

    public boolean sendMessage(String sender, String recipient, String topic, String text) throws TrackerServiceException {
        LoginValidator loginValidator = new LoginValidator();
        MessageDaoJdbcImpl dao;
        UserDaoJdbcImpl userDao = new UserDaoJdbcImpl();
        boolean status = false;
        if(loginValidator.isValidate(recipient) && topic!= null && !topic.isEmpty() &&
            text!=null && !text.isEmpty() && text.length()<=MAX_SIZE_TEXT &&
            topic.length()<=MAX_SIZE_TOPIC){
               dao = new MessageDaoJdbcImpl();
               Message message = new Message();
               LocalDate date = LocalDate.now();
               message.setTopik(topic);
               message.setText(text);
               message.setRecipient(recipient);
               message.setSender(sender);
               message.setLocalDate(date);
            try {
                status = dao.addMessage(message);
            } catch (TrackerDBException e) {
                LOGGER.error("Wrong service send message", e);
                throw new TrackerServiceException("Wrong service send message",e);
            }
        }
        return status;
    }

    public boolean deleteMessage(String id, String type) throws TrackerServiceException {
        boolean status = false;
        MessageDaoJdbcImpl dao = new MessageDaoJdbcImpl();
        IdValidator validator = new IdValidator();
        if(validator.isValidate(id)){
            int idMessage= Integer.parseInt(id);
            try {
                status = dao.deleteMessage(idMessage, type);
            } catch (TrackerDBException e) {
                LOGGER.error("Wrong service delete message", e);
                throw new TrackerServiceException("Wrong service delete message",e);
            }
        }
        return status;
    }
}
