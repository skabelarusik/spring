/**
 * interface for different advice dao
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.dao;

import by.epam.crackertracker.entity.Advice;
import by.epam.crackertracker.exception.TrackerDBException;

import java.util.List;

public interface AdviceDao extends TrackerDao {

    void insert(String text) throws TrackerDBException;

    void deleteById(int id) throws TrackerDBException;

    List<Advice> selectAll() throws TrackerDBException;

    public Advice selectRandomAdvice() throws TrackerDBException;

    }
