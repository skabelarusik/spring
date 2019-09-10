/**
 * command to add review
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.command;

import by.epam.crackertracker.entity.Review;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import by.epam.crackertracker.service.ReviewService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class LoadReviewCommand implements Command{
    private static final Logger LOGGER = Logger.getRootLogger();
    ReviewService service;

    public LoadReviewCommand(ReviewService service){
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = PageConstant.PATH_PAGE_SEND_REVIEW;
        String show = request.getParameter(ParameterConstant.ATTRIBUTE_SHOW_REVIEW);
        if(show == null){
            show = ParameterConstant.TYPE_1;
        }
        int showReview;
        try{
            if(show.equals(ParameterConstant.TYPE_1)){
                showReview = Integer.parseInt(ParameterConstant.TYPE_1);
                request.setAttribute(ParameterConstant.PARAM_TYPE, ParameterConstant.TYPE_1);
            } else if(show.equals(ParameterConstant.TYPE_0)) {
                showReview = Integer.parseInt(ParameterConstant.TYPE_0);
            } else {
                throw new TrackerServiceException("Wrong service type select all programs name");
            }
        List<Review> reviewList = service.selectAllReview(showReview);
        request.setAttribute(ParameterConstant.ATTRIBUTE_LIST_REVIEW, reviewList);
        } catch (TrackerServiceException e){
            LOGGER.error("Command can't load reviews", e);
            page = PageConstant.PATH_PAGE_ERROR;
        }
        return page;
    }
}
