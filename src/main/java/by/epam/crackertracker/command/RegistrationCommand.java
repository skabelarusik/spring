/**
 * command to register account
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.command;

import by.epam.crackertracker.entity.Gender;
import by.epam.crackertracker.entity.Role;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.UserService;
import by.epam.crackertracker.tag.CuratorMap;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import by.epam.crackertracker.util.RouteType;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Map;

public class RegistrationCommand implements Command {
    private static final Logger LOGGER = Logger.getRootLogger();
    public static final BigDecimal START_BALANCE = BigDecimal.valueOf(0);
    private UserService receiver;


    public RegistrationCommand(UserService receiver){
        this.receiver = receiver;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter(ParameterConstant.PARAM_LOGIN);
        String pass = request.getParameter(ParameterConstant.PARAM_PASSWORD);
        String name = request.getParameter(ParameterConstant.PARAM_NAME);
        String surname = request.getParameter(ParameterConstant.PARAM_SURNAME);
        Gender gender = Gender.valueOf(request.getParameter(ParameterConstant.PARAM_GENDER).toUpperCase().trim());
        String email = request.getParameter(ParameterConstant.PARAM_EMAIL);
        String dateOfBirth = request.getParameter(ParameterConstant.PARAM_BIRTHDAY);
        HttpSession session = request.getSession(true);
        String page;
        try{
            boolean status = receiver.registerUser(login, pass, name, surname, gender, email, dateOfBirth);
            if(status) {
                session.setAttribute(ParameterConstant.ATTRIBUTE_AVATAR, PageConstant.DEFAULT_AVATAR_PATH);
                session.setAttribute(ParameterConstant.PARAM_LOGIN, login);
                session.setAttribute(ParameterConstant.PARAM_EMAIL, email);
                session.setAttribute(ParameterConstant.ATTRIBUTE_ROLE, Role.USER.name());
                session.setAttribute(ParameterConstant.PARAM_NAME, name);
                session.setAttribute(ParameterConstant.PARAM_SURNAME, surname);
                session.setAttribute(ParameterConstant.PARAM_BIRTHDAY, dateOfBirth);
                session.setAttribute(ParameterConstant.PARAM_GENDER, gender.toString());
                session.setAttribute(ParameterConstant.ATTRIBUTE_CURRENT_PAGE, PageConstant.PATH_PAGE_MAIN_USER);
                session.setAttribute(ParameterConstant.PARAM_PASSWORD, pass);
                session.setAttribute(ParameterConstant.PARAM_BALANCE, START_BALANCE);
                session.setAttribute(ParameterConstant.ATTRIBUTE_PAGE_MESSAGE, ParameterConstant.ATTRIBUTE_SUCESS_REGISTER);
                page = PageConstant.PATH_PAGE_MAIN_USER;
                Map<String, Double> map = receiver.selectRandomUser();
                CuratorMap mapRandomCurator = new CuratorMap(map);
                session.setAttribute(ParameterConstant.ATTRIBUTE_LIST_CURATOR, mapRandomCurator);
                request.setAttribute(ParameterConstant.ATTRIBUTE_ROUTE, RouteType.REDIRECT);
            } else {
                session.setAttribute(ParameterConstant.ATTRIBUTE_ERROR_AUTH, ParameterConstant.MESSAGE_ERROR_REGIST);
                session.setAttribute(ParameterConstant.ATTRIBUTE_PAGE_MESSAGE, ParameterConstant.ATTRIBUTE_WRONG_ACTION);
                page = PageConstant.PATH_PAGE_REGISTER;
            }
        } catch (TrackerServiceException e){
            LOGGER.error("Command can't register user" , e);
            page = PageConstant.PATH_PAGE_ERROR;
        }
        return page;
    }

}
