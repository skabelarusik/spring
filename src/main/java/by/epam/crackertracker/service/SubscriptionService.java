/**
 * service for working with subscribe dao
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.service;

import by.epam.crackertracker.dao.TrackerSubscriptionDaoJdbcImpl;
import by.epam.crackertracker.entity.Role;
import by.epam.crackertracker.entity.TrackerSubscription;
import by.epam.crackertracker.exception.TrackerDBException;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.validator.*;
import org.apache.log4j.Logger;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionService {
    private static final Logger LOGGER = Logger.getRootLogger();

    public List<TrackerSubscription> selectSubscribersCurator(String loginCurator) throws TrackerServiceException {
        List<TrackerSubscription> list;
        TrackerSubscriptionDaoJdbcImpl dao = new TrackerSubscriptionDaoJdbcImpl();
        try {
            list = dao.selectAllByCurator(loginCurator);
        } catch (TrackerDBException e) {
            LOGGER.error("Wrong service select curators subscriptions",e);
            throw new TrackerServiceException("Wrong service select curators subscriptions",e);
        }
        return list;
    }

    public List<TrackerSubscription> selectAllSubscriptions() throws TrackerServiceException {
        TrackerSubscriptionDaoJdbcImpl dao = new TrackerSubscriptionDaoJdbcImpl();
        List<TrackerSubscription> list;
        try {
            list = dao.selectAll();
        } catch (TrackerDBException e) {
            LOGGER.error("Wrong service select all subscriptions",e);
            throw new TrackerServiceException("Wrong service select all subscriptions",e);
        }
        return list;
    }

    public boolean buySubscribe(String id, String cost, String duration, String login, BigDecimal balance,
             String role) throws TrackerServiceException {
        TrackerSubscriptionDaoJdbcImpl dao;
        IdValidator idValidator = new IdValidator();
        CostValidator costValidator = new CostValidator();
        DurationValidator durationValidator = new DurationValidator();
        LoginValidator loginValidator = new LoginValidator();
        RoleValidator roleValidator = new RoleValidator();
        boolean status = false;
        try{
            if(idValidator.isValidate(id) && costValidator.isValidate(cost) && loginValidator.isValidate(login)
                    && durationValidator.isValidate(duration) && roleValidator.isValidate(role) &&
                    role.equals(Role.USER.name())){
                BigDecimal costProg = BigDecimal.valueOf(Double.parseDouble(cost));
                int idProg = Integer.parseInt(id);
                int durationProg = Integer.parseInt(duration);
                if(costProg.doubleValue() <= balance.doubleValue()){
                    dao = new TrackerSubscriptionDaoJdbcImpl();
                    status = dao.insert(idProg, costProg, durationProg, login, balance);
                }
            }
        } catch (TrackerDBException e){
            LOGGER.error("Wrong buy subscribe");
            throw new TrackerServiceException("Wrong buy subscribe", e);
        }
        return status;
    }

    public boolean checkSubscription(String loginValue) throws TrackerServiceException {
        TrackerSubscriptionDaoJdbcImpl dao;
        boolean status = false;
        LoginValidator validator = new LoginValidator();
        try{
            if(validator.isValidate(loginValue)){
                dao = new TrackerSubscriptionDaoJdbcImpl();
                status = dao.checkSubscription(loginValue);
            }
        } catch (TrackerDBException e) {
            throw new TrackerServiceException("Wrong service checking superuser status", e);
        }
        return status;
    }

    public List<TrackerSubscription> selectHistorySubs(int id, String login) throws TrackerServiceException {
        TrackerSubscriptionDaoJdbcImpl dao;
        LoginValidator validator = new LoginValidator();
        List<TrackerSubscription> list = new ArrayList<>();
        IdValidator idValidator = new IdValidator();
        try {
            if (idValidator.isValidate(String.valueOf(id)) && validator.isValidate(login)) {
                dao = new TrackerSubscriptionDaoJdbcImpl();
                list = dao.historySubscr(id, login);
            }
        } catch (TrackerDBException e) {
            throw new TrackerServiceException("Wrong id users in selecting history subscribes id users: " + id);
        }
        return list;
    }
}
