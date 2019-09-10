package by.epam.crackertracker.command;

import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;

import javax.servlet.http.HttpServletRequest;

public class EditProgramNameCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter(ParameterConstant.PARAM_NAME_PROGR);
        String id = request.getParameter(ParameterConstant.PARAM_ID);
        String duration = request.getParameter(ParameterConstant.PARAM_DURATION);
        String cost = request.getParameter(ParameterConstant.PARAM_COST);
        request.setAttribute(ParameterConstant.PARAM_NAME_PROGR, name);
        request.setAttribute(ParameterConstant.PARAM_ID, id);
        request.setAttribute(ParameterConstant.PARAM_DURATION, duration);
        request.setAttribute(ParameterConstant.PARAM_COST, cost);
        return PageConstant.PATH_PAGE_UPDATE_PROG;
    }
}
