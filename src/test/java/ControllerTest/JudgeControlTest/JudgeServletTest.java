package ControllerTest.JudgeControlTest;

import Model.Dao.JudgeDao;
import Model.MainData.Judge;
import ModelTest.DaoTest.JudgeDaoTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.*;

@RunWith(Parameterized.class)
public class JudgeServletTest {
    JudgeDao judgeDao = Mockito.mock(JudgeDao.class);
    JudgeDaoTest judgeDaoTest = new JudgeDaoTest();
    Map<String, List<Object>> testJudgeDataMap = new HashMap<>();

    @Parameterized.Parameter()
    public Judge actualJudge;

    @Test
    public void testJudgeMainFunctions() throws SQLException {
        // INSERT + SELECT OPERATIONS TESTING
        actualJudge = new Judge(actualJudge.getName(), actualJudge.getSurname(),
                actualJudge.getEmail(), actualJudge.getPhoneNumber());
        judgeDao.save(actualJudge);
        int actualJudgeId = judgeDaoTest.selectJudgeIdByEmail(actualJudge.getEmail());
        Mockito.when(judgeDao.get(actualJudgeId)).thenReturn(actualJudge);
        Judge expectedJudge = judgeDao.get(actualJudgeId);
        putParamsToTestMap("Actual", actualJudge);
        putParamsToTestMap("Expected", expectedJudge);
        Assert.assertEquals(testJudgeDataMap.get("Expected"), testJudgeDataMap.get("Actual"));

        // UPDATE OPERATION TESTING
        actualJudge = new Judge(actualJudgeId, "Vitalii", "Kemeniash", "Vkem@gmail.com", 456187950);
        judgeDao.update(actualJudge);
        Mockito.when(judgeDao.get(actualJudgeId)).thenReturn(actualJudge);
        expectedJudge = judgeDao.get(actualJudgeId);
        putParamsToTestMap("Actual", actualJudge);
        putParamsToTestMap("Expected", expectedJudge);
        Assert.assertEquals(testJudgeDataMap.get("Expected"), testJudgeDataMap.get("Actual"));

        // DELETE OPERATION TESTING
        judgeDao.delete(actualJudgeId);
        Mockito.when(judgeDao.get(actualJudgeId)).thenReturn(null);
        Assert.assertNull(judgeDao.get(actualJudgeId));
    }

    public void putParamsToTestMap(String key, Judge testJudge) {
        List<Object> params = Arrays.asList(
                testJudge.getName(), testJudge.getSurname(), testJudge.getEmail(), testJudge.getPhoneNumber()
        );
        testJudgeDataMap.put(key, params);
    }

    @Parameterized.Parameters
    public static Collection<Object> getTestJudges() {
        List<Object> testJudges = new ArrayList<>();
        testJudges.add(new Judge("Arthur", "Medial", "Arthur8@gmail.com", 287456987));
        testJudges.add(new Judge("Mary", "Tomas", "TeMary54@gmail.com", 297451236));
        testJudges.add(new Judge("John", "Golden", "GoMer45@gmail.com", 562147894));

        return testJudges;
    }
}
