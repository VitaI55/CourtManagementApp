package ControllerTest;

import Model.Dao.CaseDao;
import Model.Enums.CaseType;
import Model.Enums.Level;
import Model.MainData.Case;
import ModelTest.DaoTest.CaseDaoTest;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CaseDAOTest {
    CaseDao caseDao;
    CaseDaoTest caseDaoTest;
    Map<String, List<Object>> testCaseDataMap;

    @BeforeAll
    public void setUp() {
        this.caseDao = new CaseDao();
        this.caseDaoTest = new CaseDaoTest();
        this.testCaseDataMap = new HashMap<>();
    }

    @ParameterizedTest
    @MethodSource("getTestCases")
    @Order(1)
    public void testCreateCase(Case actualCase) throws SQLException {
        actualCase = new Case(actualCase.getCaseType(), actualCase.getLevel(),
                actualCase.getDescription(), actualCase.getJudgeId());
        caseDao.save(actualCase);
        int actualCaseId = caseDaoTest.selectCaseIdByDescription(actualCase.getDescription());
        Case expectedCase = caseDao.get(actualCaseId);
        putParamsToTestMap("Actual", actualCase);
        putParamsToTestMap("Expected", expectedCase);
        Assert.assertEquals(testCaseDataMap.get("Expected"), testCaseDataMap.get("Actual"));
    }

    @ParameterizedTest
    @MethodSource("getTestCases")
    @Order(2)
    public void testUpdateCase(Case actualCase) throws SQLException {
        int actualCaseId = caseDaoTest.selectCaseIdByDescription(actualCase.getDescription());
        actualCase = new Case(actualCaseId, CaseType.CRIMINAL, Level.EXPERT, "Mom help me", 1);
        caseDao.update(actualCase);
        Case expectedCase = caseDao.get(actualCaseId);
        putParamsToTestMap("Actual", actualCase);
        putParamsToTestMap("Expected", expectedCase);
        Assert.assertEquals(testCaseDataMap.get("Expected"), testCaseDataMap.get("Actual"));
    }

    @ParameterizedTest()
    @MethodSource("getTestCases")
    @Order(3)
    public void testDeleteCase(Case actualCase) throws SQLException {
        int actualCaseId = caseDaoTest.selectCaseIdByDescription(actualCase.getDescription());
        caseDao.delete(actualCaseId);
        Assert.assertNull(caseDao.get(actualCaseId));
    }

    public void putParamsToTestMap(String key, Case testCase) {
        List<Object> params = Arrays.asList(
                testCase.getCaseType(), testCase.getLevel(), testCase.getDescription(), testCase.getJudgeId()
        );
        testCaseDataMap.put(key, params);
    }

    private static Stream<Arguments> getTestCases() {
        return Stream.of(
                Arguments.of(new Case(CaseType.CIVIL, Level.MEDIUM, "Big boy do things", 1)),
                Arguments.of(new Case(CaseType.CRIMINAL, Level.EASY, "Big boy do something", 2)),
                Arguments.of(new Case(CaseType.TAXES, Level.HARD, "Big boy do something complicated", 3))
        );
    }
}
