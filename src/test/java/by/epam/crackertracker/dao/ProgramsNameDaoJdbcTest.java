package by.epam.crackertracker.dao;

import by.epam.crackertracker.entity.ProgramsName;
import by.epam.crackertracker.exception.TrackerConnectionPoolException;
import by.epam.crackertracker.exception.TrackerDBException;
import by.epam.crackertracker.pool.ConnectionPool;
import by.epam.crackertracker.resources.ParametresDaoTest;
import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.flywaydb.core.Flyway;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class ProgramsNameDaoJdbcTest {
    private static Flyway flyway;
    private static EmbeddedPostgres pg;
    private static int PORT = 5865;
    private static String USERNAME = "postgres";
    private static String PASSWORD = "postgres";
    private static String DB_NAME = "postgres";
    private ProgramsNameDaoJdbc dao;


    @BeforeClass
    public static void initDb() throws IOException {
        pg = EmbeddedPostgres.builder().setPort(PORT).start();
        String url = pg.getJdbcUrl(USERNAME, DB_NAME);
        flyway = Flyway.configure().dataSource(url, USERNAME, PASSWORD).load();
        flyway.migrate();
    }

    @AfterClass
    public static void destroyDB() throws TrackerConnectionPoolException {
        flyway.clean();
        ConnectionPool.getInstance().closePool();
    }


    @Test
    public void testSelectAllCorrectData() throws TrackerDBException {
        dao = new ProgramsNameDaoJdbc();
        List<ProgramsName> list = dao.selectAll(ParametresDaoTest.START_PAGE, ParametresDaoTest.SORT);
        Assert.assertFalse(list.isEmpty());
    }

    @Test
    public void testSelectAllWrongStartPage() throws TrackerDBException {
        dao = new ProgramsNameDaoJdbc();
        List<ProgramsName> list = dao.selectAll(ParametresDaoTest.WRONG_PAGE, ParametresDaoTest.SORT);
        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void testSelectAllWrongSort() throws TrackerDBException {
        dao = new ProgramsNameDaoJdbc();
        List<ProgramsName> list = dao.selectAll(ParametresDaoTest.START_PAGE, ParametresDaoTest.WRONG_SORT);
        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void testSelectCuratorPrograms() throws TrackerDBException {
        dao = new ProgramsNameDaoJdbc();
        List<ProgramsName> list = dao.selectCuratorPrograms(ParametresDaoTest.CURATOR, ParametresDaoTest.START_PAGE,ParametresDaoTest.SORT);
        Assert.assertFalse(list.isEmpty());
    }

    @Test
    public void testSelectCuratorProgramsWrongCurator() throws TrackerDBException {
        dao = new ProgramsNameDaoJdbc();
        List<ProgramsName> list = dao.selectCuratorPrograms(ParametresDaoTest.WRONG_CURATOR, ParametresDaoTest.START_PAGE,ParametresDaoTest.SORT);
        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void testDeleteById() throws TrackerDBException {
        dao = new ProgramsNameDaoJdbc();
        Assert.assertTrue(dao.deleteById(ParametresDaoTest.CORRECT_ID,ParametresDaoTest.SORT));
    }

    @Test
    public void testDeleteByWrongId() throws TrackerDBException {
        dao = new ProgramsNameDaoJdbc();
        Assert.assertFalse(dao.deleteById(ParametresDaoTest.WRONG_ID,ParametresDaoTest.SORT));
    }

    @Test
    public void testInsertProgramName() throws TrackerDBException {
        dao = new ProgramsNameDaoJdbc();
        ProgramsName programsName = new ProgramsName(ParametresDaoTest.GOOD_INSERT_TEXT, ParametresDaoTest.CURATOR,
                ParametresDaoTest.COST, ParametresDaoTest.DURATION);
        Assert.assertTrue(dao.insert(programsName));
    }

    @Test(expected = TrackerDBException.class)
    public void testInsertProgramNullName() throws TrackerDBException {
        dao = new ProgramsNameDaoJdbc();
        ProgramsName programsName = new ProgramsName(ParametresDaoTest.NULL_STRING, ParametresDaoTest.CURATOR,
                ParametresDaoTest.COST, ParametresDaoTest.DURATION);
        dao.insert(programsName);
    }

    @Test(expected = TrackerDBException.class)
    public void testInsertProgramWrongCurator() throws TrackerDBException {
        dao = new ProgramsNameDaoJdbc();
        ProgramsName programsName = new ProgramsName(ParametresDaoTest.GOOD_INSERT_TEXT, ParametresDaoTest.WRONG_CURATOR,
                ParametresDaoTest.COST, ParametresDaoTest.DURATION);
        dao.insert(programsName);
    }

    @Test(expected = TrackerDBException.class)
    public void testInsertProgramNegativeCost() throws TrackerDBException {
        dao = new ProgramsNameDaoJdbc();
        ProgramsName programsName = new ProgramsName(ParametresDaoTest.GOOD_INSERT_TEXT, ParametresDaoTest.WRONG_CURATOR,
               ParametresDaoTest.NEGATIVE_COST, ParametresDaoTest.DURATION);
        dao.insert(programsName);
    }

    @Test(expected = TrackerDBException.class)
    public void testInsertProgramBigCost() throws TrackerDBException {
        dao = new ProgramsNameDaoJdbc();
        ProgramsName programsName = new ProgramsName(ParametresDaoTest.GOOD_INSERT_TEXT, ParametresDaoTest.WRONG_CURATOR,
                ParametresDaoTest.WRONG_COST, ParametresDaoTest.DURATION);
        dao.insert(programsName);
    }

    @Test(expected = TrackerDBException.class)
    public void testInsertProgramNegativeDuration() throws TrackerDBException {
        dao = new ProgramsNameDaoJdbc();
        ProgramsName programsName = new ProgramsName(ParametresDaoTest.GOOD_INSERT_TEXT, ParametresDaoTest.WRONG_CURATOR,
                ParametresDaoTest.COST, ParametresDaoTest.NEGATIVE_DURATION);
        dao.insert(programsName);
    }

    @Test(expected = TrackerDBException.class)
    public void testInsertProgramWrongDuration() throws TrackerDBException {
        dao = new ProgramsNameDaoJdbc();
        ProgramsName programsName = new ProgramsName(ParametresDaoTest.GOOD_INSERT_TEXT, ParametresDaoTest.WRONG_CURATOR,
                ParametresDaoTest.COST, ParametresDaoTest.WRONG_DURATION);
        dao.insert(programsName);
    }

}