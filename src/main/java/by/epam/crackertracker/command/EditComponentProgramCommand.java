package by.epam.crackertracker.command;

import by.epam.crackertracker.entity.Program;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.ProgramService;
import by.epam.crackertracker.service.ProgramsNameService;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class EditComponentProgramCommand implements Command {
    private static final Logger LOGGER = Logger.getRootLogger();
    ProgramService service;

    public EditComponentProgramCommand(ProgramService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter(ParameterConstant.PARAM_NAME_PROGR);
        String login = (String) request.getSession(true).getAttribute(ParameterConstant.PARAM_LOGIN);
        String page;
        try{
            List<Program> listProgram = service.selectAll(name, login);
            request.setAttribute(ParameterConstant.ATTRIBUTE_PROGRAM_COMPONENT, listProgram);
            request.setAttribute(ParameterConstant.PARAM_NAME_PROGR, name);
            page = PageConstant.PATH_PAGE_PROGRAM_COMPONENT;
        } catch (TrackerServiceException e){
            LOGGER.error("Command can't edit program: " + name + " users: " + login, e);
            page = PageConstant.PATH_PAGE_ERROR;
        }
        return page;
    }
}
