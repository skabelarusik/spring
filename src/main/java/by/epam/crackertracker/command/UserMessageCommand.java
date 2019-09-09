package by.epam.crackertracker.command;

import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;

import javax.servlet.http.HttpServletRequest;

public class UserMessageCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) throws TrackerServiceException {
        String recipient = request.getParameter(ParameterConstant.PARAM_RECIPIENT);
        request.setAttribute(ParameterConstant.PARAM_RECIPIENT, recipient);
        String page = PageConstant.PATH_PAGE_MESSAGE;
        return page;
    }
}
