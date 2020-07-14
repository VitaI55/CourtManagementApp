package ModelTest.DaoTest;

import Exceptions.InvalidEmailException;
import Exceptions.InvalidJudgeNameException;
import Model.Dao.Judge.JudgeCreateUpdate;
import Model.Dao.Judge.JudgeReadDelete;
import Model.Dao.JudgeSelector;
import Model.MainData.Judge;
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
public class JudgeDAOTest {
    private JudgeCreateUpdate judgeCreateUpdate;
    private JudgeReadDelete judgeReadDelete;
    private JudgeSelector judgeSelector;
    private Map<String, List<Object>> testJudgeDataMap;

    @BeforeAll
    public void setUp() {
        this.judgeCreateUpdate = new JudgeCreateUpdate();
        this.judgeReadDelete = new JudgeReadDelete();
        this.judgeSelector = new JudgeSelector();
        this.testJudgeDataMap = new HashMap<>();
    }

    @ParameterizedTest
    @MethodSource("getTestJudges")
    @Order(1)
    public void testCreateJudge(Judge actualJudge) throws
            InvalidEmailException, InvalidJudgeNameException {
        actualJudge = new Judge(actualJudge.getName(), actualJudge.getSurname(),
                actualJudge.getEmail(), actualJudge.getPhoneNumber());
        judgeCreateUpdate.save(actualJudge);
        int actualJudgeId = judgeSelector.selectJudgeIdByEmail(actualJudge.getEmail());
        Judge expectedJudge = judgeReadDelete.get(actualJudgeId);
        putParamsToTestMap("Actual", actualJudge);
        putParamsToTestMap("Expected", expectedJudge);
        Assert.assertEquals(testJudgeDataMap.get("Expected"), testJudgeDataMap.get("Actual"));
    }

    @ParameterizedTest
    @MethodSource("getTestJudges")
    @Order(2)
    public void testUpdateJudge(Judge actualJudge) throws
            InvalidEmailException, InvalidJudgeNameException {
        int actualJudgeId = judgeSelector.selectJudgeIdByEmail(actualJudge.getEmail());
        actualJudge = new Judge(actualJudgeId, "Vitalii", "Kemeniash",
                "Vkem@gmail.com", 456187950);
        judgeCreateUpdate.update(actualJudge);
        Judge expectedJudge = judgeReadDelete.get(actualJudgeId);
        putParamsToTestMap("Actual", actualJudge);
        putParamsToTestMap("Expected", expectedJudge);
        Assert.assertEquals(testJudgeDataMap.get("Expected"), testJudgeDataMap.get("Actual"));
    }

    @ParameterizedTest()
    @MethodSource("getTestJudges")
    @Order(3)
    public void testDeleteJudge(Judge actualJudge) {
        int actualJudgeId = judgeSelector.selectJudgeIdByEmail(actualJudge.getEmail());
        judgeReadDelete.delete(actualJudgeId);
        Assert.assertNull(judgeReadDelete.get(actualJudgeId));
    }

    public void putParamsToTestMap(String key, Judge testJudge) {
        List<Object> params = Arrays.asList(
                testJudge.getName(), testJudge.getSurname(), testJudge.getEmail(), testJudge.getPhoneNumber()
        );
        testJudgeDataMap.put(key, params);
    }

    private static Stream<Arguments> getTestJudges() {
        return Stream.of(
                Arguments.of(new Judge("Arthur", "Medial", "Arthur8@gmail.com", 287456987)),
                Arguments.of(new Judge("Mary", "Tomas", "TeMary54@gmail.com", 297451236)),
                Arguments.of(new Judge("John", "Golden", "GoMer45@gmail.com", 562147894))
        );
    }
}
