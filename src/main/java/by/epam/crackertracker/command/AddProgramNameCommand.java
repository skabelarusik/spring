package by.epam.crackertracker.command;

import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.ProgramsNameService;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import by.epam.crackertracker.util.RouteType;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddProgramNameCommand implements Command {
    private static final Logger LOGGER = Logger.getRootLogger();
    private ProgramsNameService service;

    public AddProgramNameCommand(ProgramsNameService service){
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String login = (String) request.getSession(true).getAttribute(ParameterConstant.PARAM_LOGIN);
        String nameProg = request.getParameter(ParameterConstant.ATTRIBUTE_NAME_PROGRAM);
        String cost = request.getParameter(ParameterConstant.ATTRIBUTE_COST_PROGRAM);
        String duration = request.getParameter(ParameterConstant.ATTRIBUTE_DUR_PROGRAM);
        String page;
        try{
            boolean status = service.insert(login, nameProg, cost, duration);
            if(status){
                request.getSession(true).setAttribute(ParameterConstant.MESSAGE_PROGRAM_NAME, ParameterConstant.MESSAGE_CONGRAT);
            } else {
                request.getSession(true).setAttribute(ParameterConstant.MESSAGE_PROGRAM_NAME, ParameterConstant.MESSAGE_ERROR_REGIST);
            }
            request.setAttribute(ParameterConstant.ATTRIBUTE_ROUTE, RouteType.REDIRECT);
            page = (String) request.getSession().getAttribute(ParameterConstant.ATTRIBUTE_CURRENT_PAGE);
        } catch (TrackerServiceException e){
            LOGGER.error("Command can't add program name: " + nameProg, e);
            page = PageConstant.PATH_PAGE_ERROR;
        }
        return page;
    }
}
