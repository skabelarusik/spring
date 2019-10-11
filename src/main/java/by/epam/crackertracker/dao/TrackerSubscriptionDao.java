/**
 * interface for different subscription dao
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.dao;

import by.epam.crackertracker.entity.TrackerSubscription;
import by.epam.crackertracker.exception.TrackerDBException;

import java.util.List;

interface TrackerSubscriptionDao extends TrackerDao{

    List<TrackerSubscription> selectAll() throws TrackerDBException;

    List<TrackerSubscription> selectAllByCurator(String login) throws TrackerDBException;

    void deleteById(int id) throws TrackerDBException;

    }
