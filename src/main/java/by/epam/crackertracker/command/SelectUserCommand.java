/**
 * command to show table all users (multitabling, 10 users in page)
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.command;

import by.epam.crackertracker.entity.User;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.UserService;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SelectUserCommand implements Command{
    private static final Logger LOGGER = Logger.getRootLogger();
    private UserService userReceiver;

    public SelectUserCommand(UserService receiver){
        this.userReceiver = receiver;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        try{
            List<User> list = userReceiver.selectAllUsers(request);
            request.setAttribute(ParameterConstant.ATTRIBUTE_LIST_USERS, list);
            page = PageConstant.PATH_PAGE_RESULT;
        } catch (TrackerServiceException e){
            LOGGER.error("Command can't select users" , e);
            page = PageConstant.PATH_PAGE_ERROR;
        }
        return page;

    }
}
