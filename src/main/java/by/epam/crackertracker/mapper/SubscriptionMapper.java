package by.epam.crackertracker.mapper;

import by.epam.crackertracker.entity.TrackerSubscription;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Component
public class SubscriptionMapper implements RowMapper<TrackerSubscription> {

    @Override
    public TrackerSubscription mapRow(ResultSet resultSet, int i) throws SQLException {
        TrackerSubscription subscription = new TrackerSubscription(resultSet.getString(3),
                resultSet.getString(2), LocalDate.parse(resultSet.getString(4)),
                LocalDate.parse(resultSet.getString(5)));
        subscription.setId(resultSet.getInt(1));
        return subscription;
    }
}
