/**
 * interface for different review.ftl dao
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.dao;

import by.epam.crackertracker.entity.Review;
import by.epam.crackertracker.exception.TrackerDBException;

import java.util.List;

public interface ReviewDao extends TrackerDao {

    List<Review> selectAllReview(int show);

    void deleteById(int id, int typeShow) throws TrackerDBException;

    void insertReview(String sender, String text) throws TrackerDBException;

}
