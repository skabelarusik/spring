/**
 * command to change user password
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.command;

import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.UserService;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UpdatePasswordCommand implements Command {
    private static final Logger LOGGER = Logger.getRootLogger();
    UserService service;

    public UpdatePasswordCommand(UserService service){
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String oldPassword = request.getParameter(ParameterConstant.PARAM_OLD_PASSWORD);
        String newPassword = request.getParameter(ParameterConstant.PARAM_NEW_PASSWORD);
        String newPasswordCheck = request.getParameter(ParameterConstant.PARAM_NEW_PASSWORD_CHECK);
        String currentPassword = (String) session.getAttribute(ParameterConstant.PARAM_PASSWORD);
        String login = (String) session.getAttribute(ParameterConstant.PARAM_LOGIN);
        String page;
        try {
            boolean status = service.updatePasswordUser(login, oldPassword, currentPassword,
                    newPassword, newPasswordCheck);
            if (status) {
                session.setAttribute(ParameterConstant.ATTRIBUTE_ERROR_AUTH, ParameterConstant.MESSAGE_CONGRAT);
            } else {
                session.setAttribute(ParameterConstant.ATTRIBUTE_ERROR_AUTH, ParameterConstant.MESSAGE_ERROR_REGIST);
            }
            page = PageConstant.PATH_PAGE_EDITING;
        } catch (TrackerServiceException e){
            LOGGER.error("Command can't update password user: " + login, e);
            page = PageConstant.PATH_PAGE_ERROR;
        }
        return page;
    }
}
