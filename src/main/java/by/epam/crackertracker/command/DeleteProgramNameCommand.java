/**
 * command to make invisible program name
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.command;

import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.service.ProgramsNameService;
import by.epam.crackertracker.util.ParameterConstant;
import by.epam.crackertracker.util.RouteType;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteProgramNameCommand implements Command {
    private static final Logger LOGGER = Logger.getRootLogger();
    ProgramsNameService servise;

    public DeleteProgramNameCommand(ProgramsNameService servise){
        this.servise = servise;
    }

    @Override
    public String execute(HttpServletRequest request){
        String id = request.getParameter(ParameterConstant.PARAM_ID);
        String type = request.getParameter(ParameterConstant.ATTRIBUTE_BUTTON_NAME);
        String page;
       try{
           boolean status = servise.deleteById(id, type);
            if(status) {
                request.getSession(true).setAttribute(ParameterConstant.ATTRIBUTE_PAGE_MESSAGE, ParameterConstant.ATTRIBUTE_SUCCESS_DELETE);
            } else {
                request.getSession(true).setAttribute(ParameterConstant.ATTRIBUTE_PAGE_MESSAGE, ParameterConstant.ATTRIBUTE_WRONG_ACTION );
            }
            request.setAttribute(ParameterConstant.ATTRIBUTE_ROUTE, RouteType.REDIRECT);
            page = (String) request.getSession(true).getAttribute(ParameterConstant.ATTRIBUTE_CURRENT_PAGE);
       } catch (TrackerServiceException e){
           LOGGER.error("Command can't delete program name id: " + id, e);
           page = PageConstant.PATH_PAGE_ERROR;
       }
        return page;
    }
}
