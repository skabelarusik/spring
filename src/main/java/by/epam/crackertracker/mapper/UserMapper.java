package by.epam.crackertracker.mapper;

import by.epam.crackertracker.entity.Gender;
import by.epam.crackertracker.entity.Role;
import by.epam.crackertracker.entity.User;
import by.epam.crackertracker.util.ParameterConstant;
import by.epam.crackertracker.validator.PathAvatarValidator;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class UserMapper implements RowMapper<User> {

    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(12));
        user.setName(resultSet.getString(1));
        user.setSurname(resultSet.getString(2));
        user.setLogin(resultSet.getString(3));
        user.setPassword(resultSet.getString(4));
        user.setGender(Gender.valueOf((resultSet.getString(5)).toUpperCase().trim()));
        user.setBirthDate(LocalDate.parse(resultSet.getString(7)));
        user.setEmail(resultSet.getString(6));
        user.setRole(Role.valueOf(resultSet.getString(11).toUpperCase().trim()));
        user.setBalance(new BigDecimal(resultSet.getDouble(9)));
        user.setPath(resultSet.getString(10));
        user.setRegistrDate(LocalDate.parse(resultSet.getString(8)));
        user.setActive(resultSet.getInt(13));
        return user;
    }




}
