/**
 * interface for different user dao
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.dao;

import by.epam.crackertracker.entity.Role;
import by.epam.crackertracker.entity.User;
import by.epam.crackertracker.exception.TrackerDBException;

import java.math.BigDecimal;
import java.util.List;

public interface UserDao extends TrackerDao {

    List<User> selectAll(int currentPage, String type) ;

    void insert(User user) throws TrackerDBException;

    void updateRoleUser(int id, Role role) throws TrackerDBException;

    void updateUser(User user) throws TrackerDBException;

    User selectByLogin(String login, String password) throws TrackerDBException;

    List<User> selectByGender (int page, String type, String gender) throws TrackerDBException;

    List<User> selectByRole(int page, String type, String role) throws TrackerDBException;

    void updatePasswordUser(String login, String newPassword) throws TrackerDBException;

    void deposit(String login, BigDecimal sum) throws TrackerDBException;

    List<User> selectAdmin();

    public void updateFilePath(String login, String path) throws TrackerDBException;
    }
