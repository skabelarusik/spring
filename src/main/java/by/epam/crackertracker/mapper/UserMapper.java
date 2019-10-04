package by.epam.crackertracker.mapper;

import by.epam.crackertracker.entity.Gender;
import by.epam.crackertracker.entity.Role;
import by.epam.crackertracker.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class UserMapper implements RowMapper<User> {
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String LOGIN = "login";
    public static final String GENDER = "sex";
    public static final String BIRTHDAY = "birthday";
    public static final String EMAIL = "email";
    public static final String REGISTR_DATE = "registrdate";


    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(ID));
        user.setName(resultSet.getString(NAME));
        user.setSurname(resultSet.getString(SURNAME));
        user.setLogin(resultSet.getString(LOGIN));
        user.setGender(Gender.valueOf((resultSet.getString(GENDER)).toUpperCase().trim()));
        user.setBirthDate(LocalDate.parse(resultSet.getString(BIRTHDAY)));
        user.setEmail(resultSet.getString(EMAIL));
        user.setRole(Role.ADMIN);
        user.setRegistrDate(LocalDate.parse(resultSet.getString(REGISTR_DATE)));
        return user;
    }




}
