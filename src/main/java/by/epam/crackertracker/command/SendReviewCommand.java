/**
 * command to send review
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.command;

import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import by.epam.crackertracker.util.RouteType;
import by.epam.crackertracker.service.ReviewService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class SendReviewCommand implements Command{
    private static final Logger LOGGER = Logger.getRootLogger();
    private ReviewService service;

    public SendReviewCommand(ReviewService service){
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String sender = (String) request.getSession().getAttribute(ParameterConstant.PARAM_LOGIN);
        String text = request.getParameter(ParameterConstant.PARAM_TEXT);
        String page;
        try{
            boolean status = service.sendReview(sender, text);
            page = PageConstant.PATH_PAGE_SEND_REVIEW;
            if(status) {
                request.getSession(true).setAttribute(ParameterConstant.ATTRIBUTE_PAGE_MESSAGE, ParameterConstant.ATTRIBUTE_SUCCESS_INSERT);
            } else {
                request.getSession(true).setAttribute(ParameterConstant.ATTRIBUTE_PAGE_MESSAGE, ParameterConstant.ATTRIBUTE_WRONG_ACTION );
            }
            request.setAttribute(ParameterConstant.ATTRIBUTE_ROUTE, RouteType.REDIRECT);
        } catch (TrackerServiceException e){
            LOGGER.error("Command can't send review: " + text + ", from:" + sender, e);
            page = PageConstant.PATH_PAGE_ERROR;
        }
        return page;
    }
}
