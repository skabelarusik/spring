/**
 * command to make deposit in account
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.command;

import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.service.UserService;
import by.epam.crackertracker.util.ParameterConstant;
import by.epam.crackertracker.util.RouteType;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class DepositCommand implements Command {
    private static final Logger LOGGER = Logger.getRootLogger();
    UserService service;

    DepositCommand(UserService service){
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String sum = request.getParameter(ParameterConstant.PARAM_SUM_DEPOSIT);
        String type = request.getParameter(ParameterConstant.PARAM_TYPE);
        String login = (String) request.getSession().getAttribute(ParameterConstant.PARAM_LOGIN);
        BigDecimal oldSum = (BigDecimal) request.getSession().getAttribute(ParameterConstant.PARAM_BALANCE);
        String page;
        try{
            boolean status = service.deposit(sum, type, login, oldSum);

            if(status){
                request.getSession(true).setAttribute(ParameterConstant.MESSAGE_DEPOSIT,
                        ParameterConstant.MESSAGE_CONGRAT);
                BigDecimal result = oldSum.add(BigDecimal.valueOf(Double.parseDouble(sum)));
                request.getSession().setAttribute(ParameterConstant.PARAM_BALANCE, result);
                request.setAttribute(ParameterConstant.MESSAGE_DEPOSIT, ParameterConstant.MESSAGE_CONGRAT);
            } else {
                request.getSession(true).setAttribute(ParameterConstant.MESSAGE_DEPOSIT,
                        ParameterConstant.MESSAGE_ERROR_REGIST);
            }
            request.setAttribute(ParameterConstant.ATTRIBUTE_ROUTE, RouteType.REDIRECT);
            page = PageConstant.PATH_DEPOSIT;
        } catch (TrackerServiceException e){
            LOGGER.error("Command can't deposit user: " + login, e);
            page = PageConstant.PATH_PAGE_ERROR;
        }

        return page;
    }
}
