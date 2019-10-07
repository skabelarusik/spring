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
        user.setId(resultSet.getInt(ParameterConstant.PARAM_ID));
        user.setName(resultSet.getString(ParameterConstant.NAME));
        user.setSurname(resultSet.getString(ParameterConstant.SURNAME));
        user.setLogin(resultSet.getString(ParameterConstant.LOGIN));
        user.setPassword(resultSet.getString(ParameterConstant.PASSWORD));
        user.setGender(Gender.valueOf((resultSet.getString(ParameterConstant.GENDER)).toUpperCase().trim()));
        user.setBirthDate(LocalDate.parse(resultSet.getString(ParameterConstant.BIRTHDAY)));
        user.setEmail(resultSet.getString(ParameterConstant.EMAIL));
        user.setRole(Role.ADMIN);
        user.setBalance(new BigDecimal(resultSet.getDouble(ParameterConstant.MONEY)));
        user.setPath(resultSet.getString(ParameterConstant.ATTRIBUTE_AVATAR));
        user.setRegistrDate(LocalDate.parse(resultSet.getString(ParameterConstant.REGISTR_DATE)));
        user.setActive(resultSet.getInt(ParameterConstant.ACTIVE));
        return user;
    }




}
