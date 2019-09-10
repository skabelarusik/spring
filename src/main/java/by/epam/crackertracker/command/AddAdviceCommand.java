/**
 * command to add advice to DataBase
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.command;

import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import by.epam.crackertracker.service.AdviceService;
import by.epam.crackertracker.util.RouteType;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddAdviceCommand implements Command{
    private static final Logger LOGGER = Logger.getRootLogger();
    AdviceService service;

    public AddAdviceCommand(AdviceService service){
        this.service = service;
    }


    @Override
    public String execute(HttpServletRequest request)  {
        String message = request.getParameter(ParameterConstant.PARAM_ADVICE);
        String page;
        try{
            boolean status = service.addAdvice(message);
            if(status){
                request.getSession(true).setAttribute(ParameterConstant.ATTRIBUTE_PAGE_MESSAGE, ParameterConstant.ATTRIBUTE_SUCCESS_INSERT);
            } else {
                request.getSession(true).setAttribute(ParameterConstant.ATTRIBUTE_PAGE_MESSAGE, ParameterConstant.ATTRIBUTE_WRONG_ACTION);
            }
            request.setAttribute(ParameterConstant.ATTRIBUTE_ROUTE, RouteType.REDIRECT);
            page = (String) request.getSession().getAttribute(ParameterConstant.ATTRIBUTE_CURRENT_PAGE);
        } catch (TrackerServiceException e){
            LOGGER.error("Command can't add advice: " + message, e);
            page = PageConstant.PATH_PAGE_ERROR;
        }
        return page;
    }
}
