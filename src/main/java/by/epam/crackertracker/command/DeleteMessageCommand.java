/**
 * command to make invisible message (sender or recipient)
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.command;

import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.service.MessageService;
import by.epam.crackertracker.util.ParameterConstant;
import by.epam.crackertracker.util.RouteType;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteMessageCommand implements Command {
    private static final Logger LOGGER = Logger.getRootLogger();
    MessageService service;

    public DeleteMessageCommand(MessageService service){
        this.service = service;
    }
    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter(ParameterConstant.PARAM_ID);
        String type = request.getParameter(ParameterConstant.PARAM_TYPE);
        String page;
        try{
            boolean status = service.deleteMessage(id, type);
            if(status){
                request.getSession(true).setAttribute(ParameterConstant.WRONG_DELETE_USER, ParameterConstant.MESSAGE_CONGRAT);
            } else {
                request.getSession(true).setAttribute(ParameterConstant.WRONG_DELETE_USER, ParameterConstant.MESSAGE_ERROR_REGIST);
            }
            request.setAttribute(ParameterConstant.ATTRIBUTE_ROUTE, RouteType.REDIRECT);
            page = PageConstant.PATH_PAGE_MESSAGE;
        } catch (TrackerServiceException e){
            LOGGER.error("Command can't delete message id: " + id, e);
            page = PageConstant.PATH_PAGE_ERROR;
        }
        return page;
    }
}
