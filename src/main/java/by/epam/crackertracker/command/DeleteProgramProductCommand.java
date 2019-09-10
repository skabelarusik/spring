package by.epam.crackertracker.command;

import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.ProgramService;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import by.epam.crackertracker.util.RouteType;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteProgramProductCommand implements Command {
    private static final Logger LOGGER = Logger.getRootLogger();
    ProgramService service;

    public DeleteProgramProductCommand(ProgramService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter(ParameterConstant.PARAM_ID);
        String login = (String) request.getSession(true).getAttribute(ParameterConstant.PARAM_LOGIN);
        String page = null;
        try{
            boolean status = service.deleteById(id, login);
            if(status) {
                request.setAttribute(ParameterConstant.ATTRIBUTE_PAGE_MESSAGE, ParameterConstant.ATTRIBUTE_SUCCESS_DELETE);
                page = (String) request.getSession().getAttribute(ParameterConstant.ATTRIBUTE_CURRENT_PAGE);
            } else {
                request.setAttribute(ParameterConstant.ATTRIBUTE_PAGE_MESSAGE, ParameterConstant.MESSAGE_ERROR_REGIST);
                page = PageConstant.PATH_PAGE_PROGRAM_COMPONENT;
            }
        } catch (TrackerServiceException e){
            LOGGER.error("Command can't delete product from program id: " + id, e);
            page = PageConstant.PATH_PAGE_ERROR;
        }
        request.setAttribute(ParameterConstant.ATTRIBUTE_ROUTE, RouteType.REDIRECT);
        return page;
    }
}


