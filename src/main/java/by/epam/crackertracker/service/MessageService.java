/**
 * service for working with message dao
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.service;

import by.epam.crackertracker.dao.MessageDao;
import by.epam.crackertracker.entity.Message;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.validator.IdValidator;
import by.epam.crackertracker.validator.LoginValidator;
import by.epam.crackertracker.exception.TrackerDBException;
import by.epam.crackertracker.validator.PositiveIntValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MessageService {
    public static final int MAX_SIZE_TOPIC = 40;
    public static final int MAX_SIZE_TEXT = 500;
    private static final Logger LOGGER = Logger.getRootLogger();

    @Autowired
    public MessageDao dao;

    @Autowired
    private LoginValidator loginValidator;

    @Autowired
    private PositiveIntValidator intValidator;

    @Autowired
    private IdValidator validator;

    public List<Message> checkInputMessage(String login, int page) throws TrackerServiceException {
        List<Message> result;
        if(!loginValidator.isValidate(login) && intValidator.isValidate(page)){
            LOGGER.warn("Wrong login or int to check input message");
            throw new TrackerServiceException("Wrong login to check input message");
        }
        try {
           result = dao.selectInputMessage(login, page);
        } catch (TrackerDBException e) {
            LOGGER.error("Wrong service check input message ", e);
            throw new TrackerServiceException("Wrong service check input message",e);
        }
        return result;
    }

    public List<Message> checkOutputMessage(String login, int page) throws TrackerServiceException {
        List<Message> result;
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

    public void sendMessage(String sender, String recipient, String topic, String text) throws TrackerServiceException {
        boolean status = false;
        if(loginValidator.isValidate(recipient) && topic!= null && !topic.isEmpty() &&
            text!=null && !text.isEmpty() && text.length()<=MAX_SIZE_TEXT &&
            topic.length()<=MAX_SIZE_TOPIC){
               Message message = new Message();
               LocalDate date = LocalDate.now();
               message.setTopik(topic);
               message.setText(text);
               message.setRecipient(recipient);
               message.setSender(sender);
               message.setLocalDate(date);
            try {
               dao.addMessage(message);
            } catch (TrackerDBException e) {
                LOGGER.error("Wrong service send message", e);
                throw new TrackerServiceException("Wrong service send message",e);
            }
        } else {
            throw new TrackerServiceException("Wrong send message");
        }
    }

    public void deleteMessage(String id, String type) throws TrackerServiceException {
        if(validator.isValidate(id)){
            int idMessage= Integer.parseInt(id);
            try {
                dao.deleteMessage(idMessage, type);
            } catch (TrackerDBException e) {
                LOGGER.error("Wrong service delete message", e);
                throw new TrackerServiceException("Wrong service delete message",e);
            }
        } else {
            LOGGER.error("Wrong service delete message");
            throw new TrackerServiceException("Wrong service delete message");
        }
    }
}
