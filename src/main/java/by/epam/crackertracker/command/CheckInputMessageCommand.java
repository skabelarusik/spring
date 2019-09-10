/**
 * command to check input messages in inner post
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.command;

import by.epam.crackertracker.entity.Message;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import by.epam.crackertracker.service.MessageService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class CheckInputMessageCommand implements Command {
    private static final Logger LOGGER = Logger.getRootLogger();
    public static final int MAX_TABLE_MESSAGE = 6;
    MessageService messageService;

    public CheckInputMessageCommand(MessageService receiver){
        this.messageService = receiver;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String login = (String) request.getSession(true).getAttribute(ParameterConstant.PARAM_LOGIN);
        String currentTablePage = request.getParameter(ParameterConstant.ATTRIBUTE_NEXT_PAGE);
        int pageNumber;
        if(currentTablePage == null){
            pageNumber = 1;
            request.setAttribute(ParameterConstant.ATTRIBUTE_RES_PAGE, pageNumber);
        } else {
            pageNumber = Integer.parseInt(currentTablePage);
            request.setAttribute(ParameterConstant.ATTRIBUTE_RES_PAGE, pageNumber);
            if(pageNumber > 1){
                request.setAttribute(ParameterConstant.ATTRIBUTE_PREV_PAGE, pageNumber - 1);
            }
        }
        List<Message> messageList;
        String page;
        try{
            messageList = messageService.checkInputMessage(login, pageNumber);
            List<Message> newMessageList = null;
            if(messageList.size() == MAX_TABLE_MESSAGE){
                request.setAttribute(ParameterConstant.ATTRIBUTE_NEXT_PAGE, pageNumber + 1);
                newMessageList = new ArrayList<>(messageList.size()-1);
                for(int i = 0 ; i < messageList.size() - 1; i++){
                    newMessageList.add(messageList.get(i));
                }
            } else if(messageList.size() > 0 && messageList.size() < MAX_TABLE_MESSAGE){
                newMessageList = new ArrayList<>(messageList.size());
                for(int i = 0 ; i < messageList.size(); i++){
                    newMessageList.add(messageList.get(i));
                }
            }
            request.setAttribute(ParameterConstant.ATTRIBUTE_LIST_MESSAGE_IN, newMessageList);
            page = PageConstant.PATH_PAGE_MESSAGE;
        } catch (TrackerServiceException e){
            LOGGER.error("Command can't check input messages users: " + login, e);
            page = PageConstant.PATH_PAGE_ERROR;
        }
        return page;
    }
}
