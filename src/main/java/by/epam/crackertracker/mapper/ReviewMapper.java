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

    @Override
    public Review mapRow(ResultSet resultSet, int i) throws SQLException {
        Review review = new Review(resultSet.getString(ParameterConstant.NAME), resultSet.getString(ParameterConstant.TEXT),
               LocalDate.parse(resultSet.getString(ParameterConstant.DATE)));
        review.setId(resultSet.getInt(ParameterConstant.ID_ADVICE));
        review.setId(resultSet.getInt(ParameterConstant.SHOW_REVIEW));
        return review;
    }
}
