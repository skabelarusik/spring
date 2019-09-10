/**
 * command to show table all users(by curator) (multitabling, 10 users in page)
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
public class SelectCuratorUsersCommand implements Command {
    private static final Logger LOGGER = Logger.getRootLogger();
    SubscriptionService service;

    public SelectCuratorUsersCommand(SubscriptionService service){
        this.service = service;
    }
    @Override
    public String execute(HttpServletRequest request) {
        String login = (String) request.getSession().getAttribute(ParameterConstant.PARAM_LOGIN);
        String page;
        try{
            List<TrackerSubscription> listSubs = service.selectSubscribersCurator(login);
            request.setAttribute(ParameterConstant.LIST_CURATOR_SUBSCRIBERS, listSubs);
            page = PageConstant.PATH_PAGE_RESULT;
        } catch (TrackerServiceException e){
            LOGGER.error("Command can'tselect curators : " + login + " subscribers", e);
            page = PageConstant.PATH_PAGE_ERROR;
        }
        return page;
    }
}
