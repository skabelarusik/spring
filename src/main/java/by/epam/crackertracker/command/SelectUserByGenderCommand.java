/**
 * command to show table all users(by gender) (multitabling, 10 users in page)
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

public class SelectUserByGenderCommand implements Command {
    private static final Logger LOGGER = Logger.getRootLogger();
    private UserService service;

    public SelectUserByGenderCommand(UserService service){
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String gender = request.getParameter(ParameterConstant.PARAM_GENDER);
        request.setAttribute(ParameterConstant.PARAM_GENDER, gender);
        try{
            if(gender != null){
                List<User> listUser = service.selectUsersByGender(request, gender);
                request.setAttribute(ParameterConstant.ATTRIBUTE_LIST_USERS_GENDER, listUser);
                page = PageConstant.PATH_PAGE_RESULT;
            } else {
                request.setAttribute(ParameterConstant.WRONG_DATA, ParameterConstant.MESSAGE_ERROR_REGIST);
                page = request.getParameter(ParameterConstant.ATTRIBUTE_CURRENT_PAGE);
            }
        } catch (TrackerServiceException e){
            LOGGER.error("Command can't select users by gender", e);
            page = PageConstant.PATH_PAGE_ERROR;
        }
        return page;
    }
}
