package by.epam.crackertracker.mapper;

import by.epam.crackertracker.entity.Review;
import by.epam.crackertracker.util.ParameterConstant;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ReviewMapper implements RowMapper<Review> {
    public static final String SELECT_ALL_REVIEW = "SELECT r.idreview, u.login, r.date, r.text from\n" +
            " review r INNER JOIN users u on r.name = u.id where r.show_review = ? order by r.date desc";

    @Override
    public Review mapRow(ResultSet resultSet, int i) throws SQLException {
        Review review = new Review(resultSet.getString(2), resultSet.getString(4),
               LocalDate.parse(resultSet.getString(3)));
        review.setId(resultSet.getInt(1));
        return review;
    }
}
