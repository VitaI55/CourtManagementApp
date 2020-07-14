package ModelTest.DaoTest;

import Model.Dao.Case.CaseCreateUpdate;
import Model.Dao.Case.CaseReadDelete;
import Model.Dao.CaseSelector;
import Model.Enums.CaseType;
import Model.Enums.Level;
import Model.MainData.Case;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CaseDAOTest {
    private CaseCreateUpdate caseCreateUpdate;
    private CaseReadDelete caseReadDelete;
    private CaseSelector caseSelector;
    private Map<String, List<Object>> testCaseDataMap;

    @BeforeAll
    public void setUp() {
        this.caseCreateUpdate = new CaseCreateUpdate();
        this.caseReadDelete = new CaseReadDelete();
        this.caseSelector = new CaseSelector();
        this.testCaseDataMap = new HashMap<>();
    }

    @ParameterizedTest
    @MethodSource("getTestCases")
    @Order(1)
    public void testCreateCase(Case actualCase) {
        actualCase = new Case(actualCase.getCaseType(), actualCase.getLevel(),
                actualCase.getDescription(), actualCase.getJudgeId());
        caseCreateUpdate.save(actualCase);
        int actualCaseId = caseSelector.selectCaseIdByDescription(actualCase.getDescription());
        Case expectedCase = caseReadDelete.get(actualCaseId);
        putParamsToTestMap("Actual", actualCase);
        putParamsToTestMap("Expected", expectedCase);
        Assert.assertEquals(testCaseDataMap.get("Expected"), testCaseDataMap.get("Actual"));
    }

    @ParameterizedTest
    @MethodSource("getTestCases")
    @Order(2)
    public void testUpdateCase(Case actualCase) {
        int actualCaseId = caseSelector.selectCaseIdByDescription(actualCase.getDescription());
        actualCase = new Case(actualCaseId, CaseType.CRIMINAL, Level.EXPERT, "Mom help me", 1);
        caseCreateUpdate.update(actualCase);
        Case expectedCase = caseReadDelete.get(actualCaseId);
        putParamsToTestMap("Actual", actualCase);
        putParamsToTestMap("Expected", expectedCase);
        Assert.assertEquals(testCaseDataMap.get("Expected"), testCaseDataMap.get("Actual"));
    }

    @ParameterizedTest()
    @MethodSource("getTestCases")
    @Order(3)
    public void testDeleteCase(Case actualCase) {
        int actualCaseId = caseSelector.selectCaseIdByDescription(actualCase.getDescription());
        caseReadDelete.delete(actualCaseId);
        Assert.assertNull(caseReadDelete.get(actualCaseId));
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
