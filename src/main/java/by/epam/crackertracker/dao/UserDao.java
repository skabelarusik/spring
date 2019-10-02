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

    List<User> selectAll() ;

    boolean deleteByIdLogin(int id, String login) throws TrackerDBException;

    boolean insert(User user) throws TrackerDBException;

     boolean updateRoleUser(int id, Role role) throws TrackerDBException;

    public boolean updateUser(User user) throws TrackerDBException;

    }
