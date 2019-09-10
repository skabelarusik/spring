/**
 * interface for different message dao
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.dao;

import by.epam.crackertracker.entity.Message;
import by.epam.crackertracker.exception.TrackerDBException;

import java.util.List;

public interface MessageDao extends TrackerDao {

    public List<Message> selectInputMessage(String login, int i) throws TrackerDBException;

    public List<Message> selectOutputMessage(String login, int i) throws TrackerDBException;
}
