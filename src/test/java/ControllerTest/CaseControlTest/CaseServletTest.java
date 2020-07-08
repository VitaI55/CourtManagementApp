package ControllerTest.CaseControlTest;

import Model.Dao.CaseDao;
import Model.Enums.CaseType;
import Model.Enums.Level;
import Model.MainData.Case;
import ModelTest.DaoTest.CaseDaoTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.*;

@RunWith(Parameterized.class)
public class CaseServletTest {
    CaseDao caseDao = Mockito.mock(CaseDao.class);
    CaseDaoTest caseDaoTest = new CaseDaoTest();
    Map<String, List<Object>> testCaseDataMap = new HashMap<>();

    @Parameterized.Parameter()
    public Case actualCase;

    @Test
    public void testCaseMainFunctions() throws SQLException {
        // INSERT + SELECT OPERATIONS TESTING
        actualCase = new Case(actualCase.getCaseType(), actualCase.getLevel(),
                actualCase.getDescription(), actualCase.getJudgeId());
        caseDao.save(actualCase);
        int actualCaseId = caseDaoTest.selectCaseIdByDescription(actualCase.getDescription());
        Mockito.when(caseDao.get(actualCaseId)).thenReturn(actualCase);
        Case expectedCase = caseDao.get(actualCaseId);
        putParamsToTestMap("Actual", actualCase);
        putParamsToTestMap("Expected", expectedCase);
        Assert.assertEquals(testCaseDataMap.get("Expected"), testCaseDataMap.get("Actual"));

        // UPDATE OPERATION TESTING
        actualCase = new Case(actualCaseId, CaseType.CRIMINAL, Level.EXPERT, "Mom help me", 13);
        caseDao.update(actualCase);
        Mockito.when(caseDao.get(actualCaseId)).thenReturn(actualCase);
        expectedCase = caseDao.get(actualCaseId);
        putParamsToTestMap("Actual", actualCase);
        putParamsToTestMap("Expected", expectedCase);
        Assert.assertEquals(testCaseDataMap.get("Expected"), testCaseDataMap.get("Actual"));

        // DELETE OPERATION TESTING
        caseDao.delete(actualCaseId);
        Mockito.when(caseDao.get(actualCaseId)).thenReturn(null);
        Assert.assertNull(caseDao.get(actualCaseId));
    }

    public void putParamsToTestMap(String key, Case testCase) {
        List<Object> params = Arrays.asList(
                testCase.getCaseType(), testCase.getLevel(), testCase.getDescription(), testCase.getJudgeId()
        );
        testCaseDataMap.put(key, params);
    }

    @Parameterized.Parameters
    public static Collection<Object> getTestCases() {
        List<Object> testCases = new ArrayList<>();
        testCases.add(new Case(CaseType.CIVIL, Level.MEDIUM, "Big boy do things", 2));
        testCases.add(new Case(CaseType.CRIMINAL, Level.EASY, "Big boy do something", 3));
        testCases.add(new Case(CaseType.TAXES, Level.HARD, "Big boy do something complicated", 6));

        return testCases;
    }
}
