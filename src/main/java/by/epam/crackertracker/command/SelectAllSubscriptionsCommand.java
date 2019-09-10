/**
 * command to show table all subscriptions (multitabling, 10 subs in page)
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.command;

import by.epam.crackertracker.entity.TrackerSubscription;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;

import by.epam.crackertracker.service.SubscriptionService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SelectAllSubscriptionsCommand implements Command {
    private static final Logger LOGGER = Logger.getRootLogger();
    private SubscriptionService service;

    public SelectAllSubscriptionsCommand(SubscriptionService service){
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        try{
            List<TrackerSubscription> list = service.selectAllSubscriptions();
            request.setAttribute(ParameterConstant.MESSAGE_SUBSCRIBERS, list);
            page = PageConstant.PATH_PAGE_RESULT;
        } catch (TrackerServiceException e){
            LOGGER.error("Command can't select all subs", e);
            page = PageConstant.PATH_PAGE_ERROR;
        }
        return page;
    }
}
