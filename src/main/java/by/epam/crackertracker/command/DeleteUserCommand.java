/**
 * command to delete user
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.command;

import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.UserService;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import by.epam.crackertracker.util.RouteType;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteUserCommand implements Command {
    private static final Logger LOGGER = Logger.getRootLogger();
    UserService service;

    public DeleteUserCommand(UserService service){
        this.service = service;
    }


    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter(ParameterConstant.PARAM_LOGIN);
        String id = request.getParameter(ParameterConstant.PARAM_ID);
        String page;
        try{
            boolean status = service.deleteUser(login, id);
            page = (String) request.getSession().getAttribute(ParameterConstant.ATTRIBUTE_CURRENT_PAGE);
            if(status){
                request.getSession(true).setAttribute(ParameterConstant.ATTRIBUTE_PAGE_MESSAGE, ParameterConstant.ATTRIBUTE_SUCCESS_DELETE);
            } else {
                request.getSession(true).setAttribute(ParameterConstant.ATTRIBUTE_PAGE_MESSAGE, ParameterConstant.ATTRIBUTE_WRONG_ACTION );
            }
            request.setAttribute(ParameterConstant.ATTRIBUTE_ROUTE, RouteType.REDIRECT);
        } catch (TrackerServiceException e){
            LOGGER.error("Command can't deativate user login: " + login, e);
            page = PageConstant.PATH_PAGE_ERROR;
        }
        return page;
    }
}
