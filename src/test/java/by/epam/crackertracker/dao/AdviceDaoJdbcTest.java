package by.epam.crackertracker.dao;

import by.epam.crackertracker.entity.Advice;
import by.epam.crackertracker.exception.TrackerConnectionPoolException;
import by.epam.crackertracker.exception.TrackerDBException;
import by.epam.crackertracker.pool.ConnectionPool;
import by.epam.crackertracker.resources.ParametresDaoTest;
import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.flywaydb.core.Flyway;
import org.junit.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AdviceDaoJdbcTest {

    private static Flyway flyway;
    private static EmbeddedPostgres pg;
    private static int PORT = 5865;
    private static String USERNAME = "postgres";
    private static String PASSWORD = "postgres";
    private static String DB_NAME = "postgres";
    private AdviceDaoJdbcImpl dao;


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
       }


    @Test
    public void testDeleteByCorrectId() throws TrackerDBException {
        dao = new AdviceDaoJdbcImpl();
        Assert.assertTrue(dao.deleteById(ParametresDaoTest.CORRECT_ID));
    }

    @Test
    public void testDeleteByWrongId() throws TrackerDBException {
        dao = new AdviceDaoJdbcImpl();
        Assert.assertFalse(dao.deleteById(ParametresDaoTest.WRONG_ID));
    }

    @Test
    public void testDeleteByNegativeId() throws TrackerDBException {
        dao = new AdviceDaoJdbcImpl();
        Assert.assertFalse(dao.deleteById(ParametresDaoTest.NEGATIVE_ID));
    }

    @Test(expected = TrackerDBException.class)
    public void testInsertBigAdvice() throws TrackerDBException {
        dao = new AdviceDaoJdbcImpl();
        dao.insert(ParametresDaoTest.BIG_SIZE_400);
    }

    @Test
    public void testInsertRepeatAdvice() throws TrackerDBException {
        dao = new AdviceDaoJdbcImpl();
        Assert.assertFalse(dao.insert(ParametresDaoTest.REPEAT_ADVICE));
    }

    @Test(expected = TrackerDBException.class)
    public void testInsertNullText() throws TrackerDBException {
        dao = new AdviceDaoJdbcImpl();
        dao.insert(ParametresDaoTest.NULL_STRING);
    }

    @Test
    public void testSelectAllAdvices() throws TrackerDBException {
           dao = new AdviceDaoJdbcImpl();
        List<Advice> list = dao.selectAll();
        Assert.assertEquals(list.size(), ParametresDaoTest.LIST_SIZE);
    }

    @Test
    public void testSelectRandomAdvice() throws TrackerDBException {
           dao = new AdviceDaoJdbcImpl();
           String advice = dao.selectRandomAdvice();
           Assert.assertFalse(advice.isEmpty());
    }
}
