package by.epam.crackertracker.dao;

import by.epam.crackertracker.entity.Bucket;
import by.epam.crackertracker.exception.TrackerDBException;

import java.util.List;

public interface BucketDao extends TrackerDao {

    void insert(String login, String product, Double portions) throws TrackerDBException;

    List<Bucket> selectAll(String login) throws TrackerDBException;

    void removeAll(String login) throws TrackerDBException;

    void deleteById(int id) throws TrackerDBException;

    }
