/**
 * service for working with review dao
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.service;

import by.epam.crackertracker.dao.ReviewDaoJdbc;
import by.epam.crackertracker.entity.Review;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.util.ParameterConstant;
import by.epam.crackertracker.validator.IdValidator;
import by.epam.crackertracker.exception.TrackerDBException;
import by.epam.crackertracker.validator.LoginValidator;
import by.epam.crackertracker.validator.ReviewLengthValidator;
import org.apache.log4j.Logger;
import java.util.List;

public class ReviewService {
    private static final Logger LOGGER = Logger.getRootLogger();

    public List<Review> selectAllReview(int show) throws TrackerServiceException {
        ReviewDaoJdbc dao = new ReviewDaoJdbc();
        List<Review> list;
        try {
            list = dao.selectAllReview(show);
        } catch (TrackerDBException e) {
            LOGGER.error("Wrong service select all review",e);
            throw new TrackerServiceException("Wrong service select all review",e);
        }
        return list;
    }

    public boolean deleteById(String id, String buttonName) throws TrackerServiceException {
        IdValidator validator = new IdValidator();
        ReviewDaoJdbc dao = new ReviewDaoJdbc();
        boolean status = false;
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
                status = dao.deleteById(idRev, typeShow);
            } catch (TrackerDBException e) {
                LOGGER.error("Wrong service delete by id review",e);
                throw new TrackerServiceException("Wrong service delete by id review",e);
            }
        }

        return status;
    }

    public boolean sendReview(String sender, String text) throws TrackerServiceException {
        LoginValidator validator = new LoginValidator();
        ReviewLengthValidator reviewLengthValidator = new ReviewLengthValidator();
        ReviewDaoJdbc dao = new ReviewDaoJdbc();
        boolean status = false;
        if(validator.isValidate(sender) && reviewLengthValidator.isValidate(text)){
            try {
                status = dao.insertReview(sender, text);
            } catch (TrackerDBException e) {
                LOGGER.error("Wrong service send review",e);
                throw new TrackerServiceException("Wrong service send review",e);
            }
        }
        return status;
    }
}
