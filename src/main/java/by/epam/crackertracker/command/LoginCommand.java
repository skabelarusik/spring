/**
 * command to log in, select start page and insert different attributes
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.command;

import by.epam.crackertracker.entity.Program;
import by.epam.crackertracker.entity.Role;
import by.epam.crackertracker.entity.User;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.ProgramService;
import by.epam.crackertracker.service.SubscriptionService;
import by.epam.crackertracker.service.UserService;
import by.epam.crackertracker.tag.CuratorMap;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import by.epam.crackertracker.service.AdviceService;
import by.epam.crackertracker.util.RouteType;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public class LoginCommand implements Command {
    private static final Logger LOGGER = Logger.getRootLogger();

    private UserService userService;
    private AdviceService adviceService;
    private SubscriptionService subscriptionService;
    private ProgramService programService;

    public LoginCommand(UserService userService, AdviceService adviceService, SubscriptionService subscriptionService,
                        ProgramService programService){
        this.subscriptionService = subscriptionService;
        this.userService = userService;
        this.adviceService = adviceService;
        this.programService = programService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String loginValue = request.getParameter(ParameterConstant.PARAM_LOGIN);
        String passValue = request.getParameter(ParameterConstant.PARAM_PASSWORD);
        String page;
        try{
            User user = userService.checkUser(loginValue, passValue);
            if (user != null) {
                Role role = user.getRole();
                page = selectPage(role);
                HttpSession session = request.getSession(true);
                session.setAttribute(ParameterConstant.ATTRIBUTE_ROLE, role.name());
                session.setAttribute(ParameterConstant.PARAM_LOGIN, loginValue);
                session.setAttribute(ParameterConstant.ATTRIBUTE_AVATAR, user.getPath());
                session.setAttribute(ParameterConstant.PARAM_EMAIL, user.getEmail());
                session.setAttribute(ParameterConstant.PARAM_NAME, user.getName());
                session.setAttribute(ParameterConstant.PARAM_SURNAME, user.getSurname());
                session.setAttribute(ParameterConstant.PARAM_BIRTHDAY, user.getBirthDate().toString());
                session.setAttribute(ParameterConstant.PARAM_GENDER, user.getGender());
                session.setAttribute(ParameterConstant.PARAM_PASSWORD, passValue);
                session.setAttribute(ParameterConstant.PARAM_BALANCE, user.getBalance());
                session.setAttribute(ParameterConstant.ATTRIBUTE_PAGE_MESSAGE, ParameterConstant.ATTRIBUTE_SUCESS_LOGIN);
                String advice = adviceService.selectRandomAdvice();
                Map<String, Double> map = userService.selectRandomUser();
                CuratorMap mapRandomCurator = new CuratorMap(map);
                session.setAttribute(ParameterConstant.ATTRIBUTE_LIST_CURATOR, mapRandomCurator);
                session.setAttribute(ParameterConstant.ATTRIBUTE_CURRENT_PAGE, page);
                session.setAttribute(ParameterConstant.PARAM_ADVICE, advice);
                session.setAttribute(ParameterConstant.PARAM_ID, user.getId());
                if(role == Role.SUPERUSER){
                    boolean status = subscriptionService.checkSubscription(loginValue);
                    if(!status){
                        session.setAttribute(ParameterConstant.ATTRIBUTE_ROLE, Role.USER.name());
                        userService.updateRole(String.valueOf(user.getId()), Role.USER);
                        page = PageConstant.PATH_PAGE_MAIN_USER;
                    } else{
                        List<Program> programList = programService.selectSuperuserProducts(loginValue);
                        request.getSession().setAttribute(ParameterConstant.ATTRIBUTE_PROGRAM_COMPONENT, programList);
                    }
                }
            } else {
                request.getSession(true).setAttribute(ParameterConstant.ATTRIBUTE_PAGE_MESSAGE, ParameterConstant.ATTRIBUTE_WRONG_LOGIN);
                page = PageConstant.PATH_PAGE_LOGIN;
            }
            request.setAttribute(ParameterConstant.ATTRIBUTE_ROUTE, RouteType.REDIRECT);
        } catch (TrackerServiceException e){
            LOGGER.error("Command can't login", e);
            page = PageConstant.PATH_PAGE_ERROR;
        }
        return page;
    }

    private String selectPage(Role role) {
        String page;
        switch (role) {
            case USER: {
                page = PageConstant.PATH_PAGE_MAIN_USER;
                break;
            }
            case ADMIN: {
                page = PageConstant.PATH_PAGE_MAIN_ADMIN;
                break;
            }
            case CURATOR: {
                page = PageConstant.PATH_PAGE_MAIN_CURATOR;
                break;
            }
            case SUPERUSER: {
                page = PageConstant.PATH_PAGE_MAIN_SUPERUSER;
                break;
            }
            default: {
                page = PageConstant.PATH_PAGE_LOGIN;
                break;
            }
        }
        return page;
    }
}







