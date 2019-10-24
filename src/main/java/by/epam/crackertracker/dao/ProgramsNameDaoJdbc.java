/**
 * jdbc dao class with different method for receive table programs name in DB
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.dao;

import by.epam.crackertracker.entity.ProgramsName;
import by.epam.crackertracker.exception.TrackerConnectionPoolException;
import by.epam.crackertracker.exception.TrackerDBException;
import by.epam.crackertracker.mapper.ProgramNameMapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProgramsNameDaoJdbc implements ProgramsNameDao {
    public static final String SELECT_ALL_PROGRAMS_NAME = "SELECT p.id, p.name, u.login, p.duration, p.cost " +
            "from programs_name p inner join users u on p.curator = u.id where p.show_name = ? limit ? offset ? ";
    public static final String INSERT_PROGRAMS_NAME = "INSERT INTO programs_name (name, curator, duration, cost)" +
            " VALUES (?,(SELECT id from users where login = ?),?,?)";
    public static final String SELECT_CURATOR_PROGRAM_NAME = "SELECT pn.id, pn.name, u.login, pn.duration, pn.cost from programs_name pn inner join users u \n" +
            "on u.id = pn.curator where u.login = ? and pn.show_name = ? limit ? offset ?";
    public static final String DELETE_BY_ID = "UPDATE programs_name set show_name = ? where id = ?";
    public static final String UPDATE_PROGRAM_NAME = "UPDATE programs_name SET name = ?, duration = ?, cost = ? WHERE id = ?";

    public static final int COUNT_NAMES = 11;

    private static final Logger LOGGER = LogManager.getRootLogger();

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private ProgramNameMapper mapper;

    @Override
    public List<ProgramsName> selectAll(int page, int type) throws TrackerDBException {
        List<ProgramsName> list;
        try {
            list = template.query(SELECT_ALL_PROGRAMS_NAME, mapper, type, COUNT_NAMES, ((page - 1) * COUNT_NAMES - (page - 1)));
        } catch (Exception e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong selecting program name");
        }
        return list;
    }

    @Override
    public List<ProgramsName> selectCuratorPrograms(String login ,int page, int type) throws  TrackerDBException {
        List<ProgramsName> list;
        try {
            list = template.query(SELECT_CURATOR_PROGRAM_NAME, mapper, login, type, COUNT_NAMES,
                    ((page - 1) * COUNT_NAMES - (page - 1)));
        } catch (Exception e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong selecting program name");
        }
        return list;
    }

    @Override
    public void deleteById(int id, int type) throws TrackerDBException  {
        try {
            int i = template.update(DELETE_BY_ID, type, id);
            if(i != 1){
                throw new TrackerDBException("Wrong delete program name by id" );
            }
            LOGGER.warn("Programs name id: " + id + " was deleted");
        }catch (Exception e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong delete program name by id" + e);
        }
    }

    @Override
    public void insert(ProgramsName programsName) throws TrackerDBException {

        try{
            template.update(INSERT_PROGRAMS_NAME, programsName.getName(),  programsName.getCurator(), programsName.getDuration(),
                    programsName.getCost());
            LOGGER.warn("Programs name: " + programsName.getName() + " was insert");
        } catch (Exception e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong insert program name" + e);
        }
    }

    @Override
    public void updateProgramsName(ProgramsName programsName) throws TrackerDBException {
        try{
            template.update(UPDATE_PROGRAM_NAME, programsName.getName(), programsName.getDuration(), programsName.getCost(),
                    programsName.getId());
            LOGGER.warn("Programs name: " + programsName.getName() + " was update");
        } catch (Exception e){
            LOGGER.error(e);
            throw new TrackerDBException("Wrong updating program name",e);
        }
    }
}
