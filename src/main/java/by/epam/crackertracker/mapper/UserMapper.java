package by.epam.crackertracker.mapper;

import by.epam.crackertracker.entity.Gender;
import by.epam.crackertracker.entity.Role;
import by.epam.crackertracker.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class UserMapper implements RowMapper<User> {

    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        user.setLogin(resultSet.getString("login"));
        user.setGender(Gender.valueOf((resultSet.getString("sex")).toUpperCase().trim()));
        user.setBirthDate(LocalDate.parse(resultSet.getString("birthday")));
        user.setEmail(resultSet.getString("email"));
        user.setRole(Role.ADMIN);
        user.setRegistrDate(LocalDate.parse(resultSet.getString("registrdate")));
        return user;
    }




}
