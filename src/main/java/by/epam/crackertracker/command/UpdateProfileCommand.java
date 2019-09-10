/**
 * command to update characteristics user
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.command;

import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.UserService;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import by.epam.crackertracker.exception.TrackerConnectionPoolException;
import by.epam.crackertracker.exception.TrackerDBException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UpdateProfileCommand implements Command {
    private static final Logger LOGGER = Logger.getRootLogger();
    UserService receiver;

    public UpdateProfileCommand(UserService receiver){
        this.receiver = receiver;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String newName = request.getParameter(ParameterConstant.PARAM_NAME);
        String newSurname = request.getParameter(ParameterConstant.PARAM_SURNAME);
        String newEmail = request.getParameter(ParameterConstant.PARAM_EMAIL);
        String newBirthday = request.getParameter(ParameterConstant.PARAM_BIRTHDAY);
        String login = (String) request.getSession().getAttribute(ParameterConstant.PARAM_LOGIN);
        try{
            boolean status = receiver.updateDataUser(login, newName, newSurname, newEmail, newBirthday);
            page = PageConstant.PATH_PAGE_EDITING;
            if(status){
            request.setAttribute(ParameterConstant.ATTRIBUTE_UPDATE, ParameterConstant.MESSAGE_CONGRAT);
                HttpSession session = request.getSession(true);
                session.setAttribute(ParameterConstant.PARAM_NAME, newName);
                session.setAttribute(ParameterConstant.PARAM_SURNAME, newSurname);
                session.setAttribute(ParameterConstant.PARAM_BIRTHDAY, newBirthday);
                session.setAttribute(ParameterConstant.PARAM_EMAIL, newEmail);
            } else {
                request.setAttribute(ParameterConstant.ATTRIBUTE_ERROR_AUTH, ParameterConstant.MESSAGE_ERROR_REGIST);
            }
        } catch (TrackerServiceException e){
            LOGGER.error("Command can't update profile user: " + login, e);
            page = PageConstant.PATH_PAGE_ERROR;
        }
         return page;
    }
}
