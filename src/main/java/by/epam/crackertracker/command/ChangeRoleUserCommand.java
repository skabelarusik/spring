/**
 * command to change role of user
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.command;

import by.epam.crackertracker.entity.Role;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.UserService;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import by.epam.crackertracker.util.RouteType;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ChangeRoleUserCommand implements Command {

    private static final Logger LOGGER = Logger.getRootLogger();
    UserService service;

    public ChangeRoleUserCommand(UserService service){
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter(ParameterConstant.PARAM_ID);
        Role role = Role.valueOf(request.getParameter(ParameterConstant.ATTRIBUTE_ROLE).toUpperCase().trim());
        String page;
        try{
            boolean status = service.updateRole(id, role);
            if(status){
                request.getSession(true).setAttribute(ParameterConstant.ATTRIBUTE_PAGE_MESSAGE, ParameterConstant.ATTRIBUTE_SUCCESS_UPDATE);
            } else {
                request.getSession(true).setAttribute(ParameterConstant.ATTRIBUTE_PAGE_MESSAGE, ParameterConstant.ATTRIBUTE_WRONG_ACTION );
            }
            request.setAttribute(ParameterConstant.ATTRIBUTE_ROUTE, RouteType.REDIRECT);
            page = (String) request.getSession().getAttribute(ParameterConstant.ATTRIBUTE_CURRENT_PAGE);
        } catch (TrackerServiceException e){
            LOGGER.error("Command can't change role user id: " + id, e);
            page = PageConstant.PATH_PAGE_ERROR;
        }
        return page;
    }
}
