package by.epam.crackertracker.command;

import by.epam.crackertracker.entity.Program;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.ProgramService;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import by.epam.crackertracker.util.RouteType;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AddProductToProgramCommand implements Command {
    private static final Logger LOGGER = Logger.getRootLogger();

    ProgramService service;
    public AddProductToProgramCommand(ProgramService service){
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String day = request.getParameter(ParameterConstant.PARAM_DAY);
        String time = request.getParameter(ParameterConstant.PARAM_TIME);
        String nameProduct = request.getParameter(ParameterConstant.PARAM_NUM_PRODUCT);
        String nameProgram = request.getParameter(ParameterConstant.PARAM_NUM_PROGRAM);
        String portions = request.getParameter(ParameterConstant.PARAM_PORTIONS);
        String page;
        try{
            boolean status = service.insert(day, time, nameProduct, nameProgram, portions);
            String login = (String) request.getSession(true).getAttribute(ParameterConstant.PARAM_LOGIN);
            if(status){
                request.getSession(true).setAttribute(ParameterConstant.ATTRIBUTE_PAGE_MESSAGE, ParameterConstant.ATTRIBUTE_SUCCESS_INSERT);
                List<Program> listProgram = service.selectAll(nameProgram, login);
                request.getSession().setAttribute(ParameterConstant.PARAM_NAME_PROGR, nameProgram);
                request.getSession().setAttribute(ParameterConstant.ATTRIBUTE_PROGRAM_COMPONENT, listProgram);
            } else {
                request.getSession(true).setAttribute(ParameterConstant.ATTRIBUTE_PAGE_MESSAGE, ParameterConstant.ATTRIBUTE_SUCCESS_DELETE);
                request.getSession().setAttribute(ParameterConstant.PARAM_NAME_PROGR, nameProgram);
                List<Program> listProgram = service.selectAll(nameProgram, login);
                request.getSession().setAttribute(ParameterConstant.ATTRIBUTE_PROGRAM_COMPONENT, listProgram);
            }
            request.setAttribute(ParameterConstant.ATTRIBUTE_ROUTE, RouteType.REDIRECT);
            page = PageConstant.PATH_PAGE_PROGRAM_COMPONENT;
        } catch (TrackerServiceException e){
            LOGGER.error("Command can't add product: " + nameProduct + " to program: " + nameProgram, e);
            page = PageConstant.PATH_PAGE_ERROR;
        }
        return page;
    }
}
