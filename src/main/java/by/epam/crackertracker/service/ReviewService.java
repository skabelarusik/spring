/**
 * service for working with review.ftl dao
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.service;

import by.epam.crackertracker.dao.ReviewDao;
import by.epam.crackertracker.dao.ReviewDaoJdbc;
import by.epam.crackertracker.entity.Review;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.util.ParameterConstant;
import by.epam.crackertracker.validator.IdValidator;
import by.epam.crackertracker.exception.TrackerDBException;
import by.epam.crackertracker.validator.LoginValidator;
import by.epam.crackertracker.validator.ReviewLengthValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ReviewService {
    private static final Logger LOGGER = Logger.getRootLogger();

    @Autowired
    private ReviewDao dao;

    public List<Review> selectAllReview(int show) {
        return  dao.selectAllReview(show);
    }

    public void deleteById(String id, String buttonName) throws TrackerServiceException {
        IdValidator validator = new IdValidator();
        ReviewDaoJdbc dao = new ReviewDaoJdbc();
        if(id != null && !id.isEmpty() && validator.isValidate(id)){
            int idRev = Integer.parseInt(id);
            int typeShow;
            if(buttonName.equals(ParameterConstant.ATTRIBUTE_DELETE_TYPE)){
                typeShow = 0;
            } else if(buttonName.equals(ParameterConstant.ATTRIBUTE_RESTORE_TYPE)){
                typeShow = 1;
            } else {
                LOGGER.error("Wrong type delete program name by id");
                throw new TrackerServiceException("Wrong type delete program name by id");
            }
            try {
                dao.deleteById(idRev, typeShow);
            } catch (TrackerDBException e) {
                LOGGER.error("Wrong service delete by id review",e);
                throw new TrackerServiceException("Wrong service delete by id review",e);
            }
        }
    }

    public void sendReview(String sender, String text) throws TrackerServiceException {
        LoginValidator validator = new LoginValidator();
        ReviewLengthValidator reviewLengthValidator = new ReviewLengthValidator();
        ReviewDaoJdbc dao = new ReviewDaoJdbc();
        boolean status = false;
        if(validator.isValidate(sender) && reviewLengthValidator.isValidate(text)){
            try {
                dao.insertReview(sender, text);
            } catch (TrackerDBException e) {
                LOGGER.error("Wrong service send review",e);
                throw new TrackerServiceException("Wrong service send review",e);
            }
        }
    }
}
