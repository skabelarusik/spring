/**
 * service for working with program name dao
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.service;

import by.epam.crackertracker.dao.ProgramsNameDaoJdbc;
import by.epam.crackertracker.entity.ProgramsName;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.util.ParameterConstant;
import by.epam.crackertracker.validator.*;
import by.epam.crackertracker.exception.TrackerDBException;
import org.apache.log4j.Logger;
import java.math.BigDecimal;
import java.util.List;

public class ProgramsNameService {
    private static final Logger LOGGER = Logger.getRootLogger();

    public List<ProgramsName> selectAllProgramsName(int intPage, int type) throws TrackerServiceException {
        ProgramsNameDaoJdbc dao = new ProgramsNameDaoJdbc();
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
        LoginValidator validator = new LoginValidator();
        PositiveIntValidator intValidator = new PositiveIntValidator();
        if(!validator.isValidate(login) || !intValidator.isValidate(page)){
            LOGGER.warn("Wrong login or page in service select curator program name");
            throw new TrackerServiceException("Wrong login or page in service select curator program name");
        }
        ProgramsNameDaoJdbc dao = new ProgramsNameDaoJdbc();
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
        ProgramsNameDaoJdbc dao;
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
                status = dao.deleteById(idProgram, typeShow);
            } catch (TrackerDBException e) {
                LOGGER.error("Wrong service delete programs name by id ",e);
                throw new TrackerServiceException("Wrong service delete programs name by id ",e);
            }
        }
       return status;

    }

    public boolean insert(String login, String nameProg, String cost, String duration) throws TrackerServiceException {
        LoginValidator validator = new LoginValidator();
        ProgramNameValidator nameValidator = new ProgramNameValidator();
        CostValidator costValidator = new CostValidator();
        DurationValidator durationValidator = new DurationValidator();
        ProgramsNameDaoJdbc dao;
        boolean status = false;
        if(validator.isValidate(login) && nameValidator.isValidate(nameProg) && costValidator.isValidate(cost) &&
            durationValidator.isValidate(duration)){
            dao = new ProgramsNameDaoJdbc();
            BigDecimal costProg = BigDecimal.valueOf(Double.parseDouble(cost));
            int durationProg = Integer.parseInt(duration);
            ProgramsName programsName = new ProgramsName(nameProg, login, costProg, durationProg);
            try {
                status = dao.insert(programsName);
            } catch (TrackerDBException e) {
                LOGGER.error("Wrong service insert program name ",e);
                throw new TrackerServiceException("Wrong service insert program name ",e);
            }
        }
        return status;
    }

    public boolean update(String name, String id, String duration, String cost, String login) throws TrackerServiceException {
        boolean status = false;
        DurationValidator durValidator = new DurationValidator();
        IdValidator idValidator = new IdValidator();
        CostValidator costValidator = new CostValidator();
        ProgramNameValidator nameValidator = new ProgramNameValidator();
        LoginValidator loginValidator = new LoginValidator();
        ProgramsNameDaoJdbc dao = null;
        if(durValidator.isValidate(duration) && idValidator.isValidate(id) && costValidator.isValidate(cost) &&
            nameValidator.isValidate(name) && loginValidator.isValidate(login)){
            int idPr = Integer.parseInt(id);
            int dur = Integer.parseInt(duration);
            BigDecimal costPr = BigDecimal.valueOf(Double.parseDouble(cost));
            ProgramsName programsName = new ProgramsName(name, login, costPr, dur);
            programsName.setId(idPr);
            dao = new ProgramsNameDaoJdbc();
            try {
                status = dao.updateProgramsName(programsName);
            } catch (TrackerDBException e) {
                LOGGER.error("Wrong service update program name ",e);
                throw new TrackerServiceException("Wrong service update program name ",e);
            }
        }
        return status;
    }
}
