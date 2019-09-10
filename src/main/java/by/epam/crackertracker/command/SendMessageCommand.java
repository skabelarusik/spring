/**
 * command to send message to another user(or myself)
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.command;

import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import by.epam.crackertracker.exception.TrackerConnectionPoolException;
import by.epam.crackertracker.exception.TrackerDBException;
import by.epam.crackertracker.service.MessageService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class SendMessageCommand implements Command {
    private static final Logger LOGGER = Logger.getRootLogger();
    MessageService service;

    public SendMessageCommand(MessageService service){
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request)  {
        String recipient = request.getParameter(ParameterConstant.PARAM_LOGIN);
        String topik = request.getParameter(ParameterConstant.PARAM_TOPIC);
        String text = request.getParameter(ParameterConstant.PARAM_TEXT);
        String sender = (String) request.getSession().getAttribute(ParameterConstant.PARAM_LOGIN);
        String page;
        try{
            boolean status = service.sendMessage(sender, recipient, topik, text);
            if(status){
                request.setAttribute(ParameterConstant.WRONG_DATA, ParameterConstant.MESSAGE_CONGRAT);
            } else {
                request.setAttribute(ParameterConstant.WRONG_DATA, ParameterConstant.MESSAGE_ERROR_REGIST);
            }
            page = PageConstant.PATH_PAGE_MESSAGE;
        } catch (TrackerServiceException e){
            LOGGER.error("Command can't send message from:" + sender, e);
            page = PageConstant.PATH_PAGE_ERROR;
        }
        return page;
    }
}
