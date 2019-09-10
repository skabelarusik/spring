package by.epam.crackertracker.command;

import by.epam.crackertracker.entity.Role;
import by.epam.crackertracker.exception.TrackerDBException;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.SubscriptionService;
import by.epam.crackertracker.service.UserService;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class BuyProgramCommand implements Command {
    private static final Logger LOGGER = Logger.getRootLogger();
    SubscriptionService subscriptionService;
    UserService userService;

    public BuyProgramCommand(SubscriptionService subscriptionService, UserService userService) {
        this.subscriptionService = subscriptionService;
        this.userService = userService;
    }


    @Override
    public String execute(HttpServletRequest request) {
        String idProgram = request.getParameter(ParameterConstant.PARAM_ID);
        String cost = request.getParameter(ParameterConstant.PARAM_COST);
        String duration = request.getParameter(ParameterConstant.PARAM_DURATION);
        String login = (String) request.getSession(true).getAttribute(ParameterConstant.PARAM_LOGIN);
        String role = (String) request.getSession().getAttribute(ParameterConstant.ATTRIBUTE_ROLE);
        BigDecimal balance = (BigDecimal) request.getSession().getAttribute(ParameterConstant.PARAM_BALANCE);
        String page = null;
        boolean status;
        try {
            status = subscriptionService.buySubscribe(idProgram, cost, duration, login, balance, role);
            if(status){
                page = PageConstant.PATH_PAGE_MAIN_SUPERUSER;
                BigDecimal newBalance = balance.subtract(BigDecimal.valueOf(Double.parseDouble(cost)));
                request.getSession().setAttribute(ParameterConstant.PARAM_BALANCE, newBalance);
                request.getSession().setAttribute(ParameterConstant.ATTRIBUTE_ROLE, Role.SUPERUSER);
                request.getSession().setAttribute(ParameterConstant.ATTRIBUTE_CURRENT_PAGE, page);
            } else {
                page = (String) request.getSession().getAttribute(ParameterConstant.ATTRIBUTE_CURRENT_PAGE);
            }
        } catch (TrackerServiceException e){
            LOGGER.error("Command can't buy subscribe", e);
            page = PageConstant.PATH_PAGE_ERROR;
        }
        return page;
    }
}
