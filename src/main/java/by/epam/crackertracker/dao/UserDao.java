/**
 * interface for different user dao
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.dao;

import by.epam.crackertracker.entity.Role;
import by.epam.crackertracker.entity.User;
import by.epam.crackertracker.exception.TrackerDBException;

import java.util.List;

public interface UserDao extends TrackerDao {

    List<User> selectAll(int currentPage, String type) ;

    boolean insert(User user) throws TrackerDBException;

    void updateRoleUser(int id, Role role) throws TrackerDBException;

    void updateUser(User user) throws TrackerDBException;

    User selectByLogin(String login, String password) throws TrackerDBException;

    List<User> selectByGender (int page, String type, String gender) throws TrackerDBException;

    List<User> selectByRole(int page, String type, String role) throws TrackerDBException;

    void updatePasswordUser(String login, String newPassword) throws TrackerDBException;
}
