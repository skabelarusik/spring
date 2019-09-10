package by.epam.crackertracker.service;

import by.epam.crackertracker.dao.ProgramDaoJdbcImpl;
import by.epam.crackertracker.entity.MealDay;
import by.epam.crackertracker.entity.MealTime;
import by.epam.crackertracker.entity.Program;
import by.epam.crackertracker.exception.TrackerDBException;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.validator.*;
import org.apache.log4j.Logger;
import java.util.List;

public class ProgramService {
    private static final Logger LOGGER = Logger.getRootLogger();

    public boolean insert(String day, String time, String product, String nameProgram,
            String portions) throws TrackerServiceException {
        boolean status = false;
        DayValidator dayValidator = new DayValidator();
        TimeValidator timeValidator = new TimeValidator();
        PortionsValidator portionsValidator = new PortionsValidator();
        ProgramNameValidator programNameValidator = new ProgramNameValidator();
        ProductNameValidator productNameValidator = new ProductNameValidator();
        ProgramDaoJdbcImpl dao;
        if(dayValidator.isValidate(day) && timeValidator.isValidate(time) &&  portionsValidator.isValidate(portions) &&
            programNameValidator.isValidate(nameProgram) && productNameValidator.isValidate(product)){
            dao = new ProgramDaoJdbcImpl();
            MealTime mealTime = MealTime.valueOf(time.toUpperCase());
            MealDay mealDay = MealDay.valueOf(day.toUpperCase());
            double port = Double.parseDouble(portions);
            Program program = new Program(nameProgram, product, port, mealDay, mealTime);
            try {
                status = dao.insert(program);
            } catch (TrackerDBException e) {
                LOGGER.error("Wrong service insert product to program",e);
                throw new TrackerServiceException("Wrong service insert product to program",e);
            }
        }
        return status;
    }

    public List<Program> selectAll(String name, String login) throws TrackerServiceException {
        ProgramDaoJdbcImpl dao;
        List<Program> listComponent;
        try {
            dao = new ProgramDaoJdbcImpl();
            listComponent = dao.selectAll(name, login);
        } catch (TrackerDBException e){
            LOGGER.error("Wrong service select all components programs",e);
            throw new TrackerServiceException("Wrong service select all components programs",e);
        }
        return listComponent;
    }

    public boolean deleteById(String id, String login) throws TrackerServiceException {
        IdValidator idValidator = new IdValidator();
        LoginValidator loginValidator = new LoginValidator();
        ProgramDaoJdbcImpl dao;
        boolean status = false;
        if(idValidator.isValidate(id) && loginValidator.isValidate(login)){
            dao = new ProgramDaoJdbcImpl();
            int idProgram = Integer.parseInt(id);
            try {
                status = dao.deleteById(idProgram, login);
            } catch (TrackerDBException e) {
                LOGGER.error("Wrong service delete by id product from program",e);
                throw new TrackerServiceException("Wrong service delete by id product from program",e);
            }
        }
        return status;
    }

    public List<Program> selectSuperuserProducts(String loginValue) throws TrackerServiceException {
        LoginValidator loginValidator = new LoginValidator();
        ProgramDaoJdbcImpl dao;
        List<Program> list;
        if(loginValidator.isValidate(loginValue)){
            dao = new ProgramDaoJdbcImpl();
            try {
                list = dao.selectSuperuserPrograms(loginValue);
            } catch (TrackerDBException e) {
                throw new TrackerServiceException("Wrong created superuser products list",e);
            }
        } else{
            LOGGER.error("Not valid login" + loginValue);
            throw new TrackerServiceException("Not valid login" + loginValue);
        }
        return list;
    }
}
