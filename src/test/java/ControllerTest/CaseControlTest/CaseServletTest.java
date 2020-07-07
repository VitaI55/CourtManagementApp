package ControllerTest.CaseControlTest;

import Model.Dao.CaseDao;
import Model.Enums.CaseType;
import Model.Enums.Level;
import Model.MainData.Case;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.sql.SQLException;
import java.util.*;

@RunWith(Parameterized.class)
public class CaseServletTest {
    CaseDao caseDao = new CaseDao();
    CaseDaoTest caseDaoTest = new CaseDaoTest();
    Map<String, List<Object>> testDataMap = new HashMap<>();

    @Parameterized.Parameter()
    public Case actualCase;

    @Test
    public void testCaseMainFunctions() throws SQLException {
        // INSERT + SELECT OPERATION TESTING
        actualCase = new Case(actualCase.getCaseType(), actualCase.getLevel(),
                actualCase.getDescription(), actualCase.getJudgeId());
        caseDao.insertCase(actualCase);
        int actualCaseId = caseDaoTest.selectCaseIdByDescription(actualCase.getDescription());
        Case expectedCase = caseDao.selectCase(actualCaseId);
        testDataMap.put("Actual", Arrays.asList(
                actualCase.getCaseType(), actualCase.getLevel(), actualCase.getDescription(), actualCase.getJudgeId()
        ));
        testDataMap.put("Expected", Arrays.asList(
                expectedCase.getCaseType(), expectedCase.getLevel(), expectedCase.getDescription(), expectedCase.getJudgeId()
        ));
        Assert.assertEquals(testDataMap.get("Expected"), testDataMap.get("Actual"));

        // UPDATE OPERATIONS TESTING
        actualCase = new Case(actualCaseId, CaseType.CRIMINAL, Level.EXPERT, "Mom help me", 13);
        caseDao.updateCase(actualCase);
        testDataMap.put("Actual",
                Arrays.asList(actualCase.getCaseType(), actualCase.getLevel(), actualCase.getDescription(), actualCase.getJudgeId()));
        expectedCase = caseDao.selectCase(actualCaseId);
        testDataMap.put("Expected",
                Arrays.asList(expectedCase.getCaseType(), expectedCase.getLevel(), expectedCase.getDescription(), expectedCase.getJudgeId()));
        Assert.assertEquals(testDataMap.get("Expected"), testDataMap.get("Actual"));

        // DELETE OPERATIONS TESTING
        caseDao.deleteCase(actualCaseId);
        Assert.assertNull(caseDao.selectCase(actualCaseId));
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
