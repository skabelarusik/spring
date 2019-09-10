package by.epam.crackertracker.command;

import by.epam.crackertracker.entity.TrackerSubscription;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.SubscriptionService;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowHistorySubscriptionCommand implements Command {
    private static final Logger LOGGER = Logger.getRootLogger();
    private SubscriptionService service;

    public ShowHistorySubscriptionCommand(SubscriptionService service){
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String login = (String) request.getSession(true).getAttribute(ParameterConstant.PARAM_LOGIN);
        String page;
        int id = (int) request.getSession(true).getAttribute(ParameterConstant.PARAM_ID);
        try{
            List<TrackerSubscription> subscriptionList = service.selectHistorySubs(id, login);
            request.setAttribute(ParameterConstant.MESSAGE_SUBSCRIBERS, subscriptionList);
            page = PageConstant.PATH_PAGE_RESULT;
        } catch (TrackerServiceException e){
            LOGGER.error("Wrong show history subscribes for user login " + login, e);
            page = PageConstant.PATH_PAGE_ERROR;
        }
        return page;
    }
}
