/**
 * service for working with program name dao
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.service;

import by.epam.crackertracker.dao.ProgramsNameDao;
import by.epam.crackertracker.dao.ProgramsNameDaoJdbc;
import by.epam.crackertracker.entity.ProgramsName;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.util.ParameterConstant;
import by.epam.crackertracker.validator.*;
import by.epam.crackertracker.exception.TrackerDBException;
import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProgramsNameService {
    private static final Logger LOGGER = Logger.getRootLogger();

    @Autowired
    private ProgramsNameDao dao;

    @Autowired
    private LoginValidator loginValidator;

    @Autowired
    private PositiveIntValidator positiveIntValidator;

    @Autowired
    private ProgramNameValidator nameValidator;

    @Autowired
    private CostValidator costValidator;

    @Autowired
    private DurationValidator durationValidator;

    @Autowired
    private IdValidator idValidator;


    public List<ProgramsName> selectAllProgramsName(int intPage, int type) throws TrackerServiceException {
        List<ProgramsName> list;
        try {
            list = dao.selectAll(intPage, type);
        } catch (TrackerDBException e) {
            LOGGER.error("Wrong service select all programs name ",e);
            throw new TrackerServiceException("Wrong service select all programs name ",e);
        }
        return list;
    }

    public List<ProgramsName> selectCuratorProgramsName(String login, int page, int type) throws TrackerServiceException {
//        if(!loginValidator.isValidate(login) || !positiveIntValidator.isValidate(page)){
//            LOGGER.warn("Wrong login or page in service select curator program name");
//            throw new TrackerServiceException("Wrong login or page in service select curator program name");
//        }
        List<ProgramsName> list;
        try {
            list = dao.selectCuratorPrograms(login, page, type);
        } catch (TrackerDBException e) {
            LOGGER.error("Wrong service select curators programs name ",e);
            throw new TrackerServiceException("Wrong service select curators programs name ",e);
        }
        return list;
    }

    public boolean deleteById(String id, String type) throws TrackerServiceException {
        IdValidator validator = new IdValidator();
        boolean status = false;
        if(validator.isValidate(id)){
            int idProgram = Integer.parseInt(id);
            dao = new ProgramsNameDaoJdbc();
            int typeShow;
            if(type.equals(ParameterConstant.ATTRIBUTE_DELETE_TYPE)){
                typeShow = 0;
            } else if(type.equals(ParameterConstant.ATTRIBUTE_RESTORE_TYPE)){
                typeShow = 1;
            } else {
                LOGGER.error("Wrong service select curators programs name");
                throw new TrackerServiceException("Wrong type delete program name by id ");
            }
            try {
                dao.deleteById(idProgram, typeShow);
            } catch (TrackerDBException e) {
                LOGGER.error("Wrong service delete programs name by id ",e);
                throw new TrackerServiceException("Wrong service delete programs name by id ",e);
            }
        }
       return status;

    }

    public void insert(String login, String nameProg, String cost, String duration) throws TrackerServiceException {
        if(loginValidator.isValidate(login) && nameValidator.isValidate(nameProg) && costValidator.isValidate(cost) &&
            durationValidator.isValidate(duration)){
            BigDecimal costProg = BigDecimal.valueOf(Double.parseDouble(cost));
            int durationProg = Integer.parseInt(duration);
            ProgramsName programsName = new ProgramsName(nameProg, login, costProg, durationProg);
            try {
                dao.insert(programsName);
            } catch (TrackerDBException e) {
                LOGGER.error("Wrong service insert program name ",e);
                throw new TrackerServiceException("Wrong service insert program name ",e);
            }
        } else {
            throw new TrackerServiceException("Wrong service insert program name ");
        }
    }

    public void update(String name, String id, String duration, String cost, String login) throws TrackerServiceException {
        if(durationValidator.isValidate(duration) && idValidator.isValidate(id) && costValidator.isValidate(cost) &&
            nameValidator.isValidate(name) && loginValidator.isValidate(login)){
            int idPr = Integer.parseInt(id);
            int dur = Integer.parseInt(duration);
            BigDecimal costPr = BigDecimal.valueOf(Double.parseDouble(cost));
            ProgramsName programsName = new ProgramsName(name, login, costPr, dur);
            programsName.setId(idPr);
            dao = new ProgramsNameDaoJdbc();
            try {
                dao.updateProgramsName(programsName);
            } catch (TrackerDBException e) {
                LOGGER.error("Wrong service update program name ",e);
                throw new TrackerServiceException("Wrong service update program name ",e);
            }
        }
    }
}
