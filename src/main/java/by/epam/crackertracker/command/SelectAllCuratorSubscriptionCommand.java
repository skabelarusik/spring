/**
 * command to show table all subscriptions(by curator) (multitabling, 10 subs in page)
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

public class SelectAllCuratorSubscriptionCommand implements Command {
    private static final Logger LOGGER = Logger.getRootLogger();
    private SubscriptionService service;

    public SelectAllCuratorSubscriptionCommand(SubscriptionService service){
        this.service = service;
    }
    @Override
    public String execute(HttpServletRequest request) {

        String login = (String) request.getSession().getAttribute(ParameterConstant.PARAM_LOGIN);
        List<TrackerSubscription> subscriptionList;
        String page;
        try{
            subscriptionList = service.selectSubscribersCurator(login);
            page = PageConstant.PATH_PAGE_RESULT;
            if(subscriptionList.isEmpty()){
                request.setAttribute(ParameterConstant.MESSAGE_HAVE_SUBS, ParameterConstant.MESSAGE_DONT_HAVE_SUBS);
            }
            request.setAttribute(ParameterConstant.LIST_CURATOR_SUBSCRIBERS ,subscriptionList);
        } catch (TrackerServiceException e){
            LOGGER.error("Command can't select subscription curators : " + login, e);
            page = PageConstant.PATH_PAGE_ERROR;
        }
        return page;
    }
}


