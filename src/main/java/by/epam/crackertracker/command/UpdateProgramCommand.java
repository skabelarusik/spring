package by.epam.crackertracker.command;


import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.ProgramsNameService;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UpdateProgramCommand implements Command {
    private static final Logger LOGGER = Logger.getRootLogger();
    private ProgramsNameService service;
    public UpdateProgramCommand(ProgramsNameService service){
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String name = request.getParameter(ParameterConstant.PARAM_NAME_PROGR);
        String id = request.getParameter(ParameterConstant.PARAM_ID);
        String duration = request.getParameter(ParameterConstant.PARAM_DURATION);
        String cost = request.getParameter(ParameterConstant.PARAM_COST);
        String login = (String) request.getSession(true).getAttribute(ParameterConstant.PARAM_LOGIN);
         try{
            boolean status = service.update(name, id, duration, cost, login);
            if(status){
                page = (String) request.getSession().getAttribute(ParameterConstant.ATTRIBUTE_CURRENT_PAGE);
                request.setAttribute(ParameterConstant.ATTRIBUTE_PAGE_MESSAGE, ParameterConstant.ATTRIBUTE_SUCCESS_UPDATE);
            } else {
                page = PageConstant.PATH_PAGE_UPDATE_PROG;
                request.setAttribute(ParameterConstant.ATTRIBUTE_PAGE_MESSAGE, ParameterConstant.MESSAGE_ERROR_REGIST);
            }
         } catch (TrackerServiceException e){
             LOGGER.error("Command can't add product: " + name, e);
             page = PageConstant.PATH_PAGE_ERROR;
         }
        return page;
    }
}
