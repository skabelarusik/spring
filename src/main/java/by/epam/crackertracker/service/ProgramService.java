package by.epam.crackertracker.service;

import by.epam.crackertracker.dao.ProgramDaoJdbcImpl;
import by.epam.crackertracker.entity.MealDay;
import by.epam.crackertracker.entity.MealTime;
import by.epam.crackertracker.entity.Program;
import by.epam.crackertracker.exception.TrackerDBException;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.validator.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramService {
    private static final Logger LOGGER = Logger.getRootLogger();

    @Autowired
    private ProgramDaoJdbcImpl dao;

    @Autowired
    private DayValidator dayValidator;

    @Autowired
    private TimeValidator timeValidator;

    @Autowired
    private PortionsValidator portionsValidator;

    @Autowired
    private ProgramNameValidator programNameValidator;

    @Autowired
    private ProductNameValidator productNameValidator;

    @Autowired
    private LoginValidator loginValidator;

    @Autowired
    private IdValidator idValidator;


    public void insert(String day, String time, String product, String nameProgram,
            String portions) throws TrackerServiceException {
        if(dayValidator.isValidate(day) && timeValidator.isValidate(time) &&  portionsValidator.isValidate(portions) &&
            programNameValidator.isValidate(nameProgram) && productNameValidator.isValidate(product)){
            MealTime mealTime = MealTime.valueOf(time.toUpperCase());
            MealDay mealDay = MealDay.valueOf(day.toUpperCase());
            double port = Double.parseDouble(portions);
            Program program = new Program(nameProgram, product, port, mealDay, mealTime);
            try {
                dao.insert(program);
            } catch (TrackerDBException e) {
                throw new TrackerServiceException();
            }
        }
    }



    public List<Program> selectAll(String name) {
        List<Program> listComponent;
            listComponent = dao.selectAll(name);
            LOGGER.error("Wrong service select all components programs");
        return listComponent;
    }

    public void deleteById(String id) throws TrackerServiceException {
        if(idValidator.isValidate(id)){
            int idProgram = Integer.parseInt(id);
            dao.deleteById(idProgram);
        } else {
            throw new TrackerServiceException("Wrong service delete by id product from program");
        }
    }

    public List<Program> selectSuperuserProducts(String loginValue) throws TrackerServiceException {
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
