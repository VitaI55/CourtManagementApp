package ControllerTest;

import Exceptions.InvalidEmailException;
import Exceptions.InvalidJudgeNameException;
import Model.Dao.JudgeDao;
import Model.MainData.Judge;
import ModelTest.DaoTest.JudgeDaoTest;
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
public class JudgeDAOTest {
    private JudgeDao judgeDao;
    private JudgeDaoTest judgeDaoTest;
    private Map<String, List<Object>> testJudgeDataMap;

    @BeforeAll
    public void setUp() {
        this.judgeDao = new JudgeDao();
        this.judgeDaoTest = new JudgeDaoTest();
        this.testJudgeDataMap = new HashMap<>();
    }

    @ParameterizedTest
    @MethodSource("getTestJudges")
    @Order(1)
    public void testCreateJudge(Judge actualJudge) throws SQLException,
            InvalidEmailException, InvalidJudgeNameException {
        actualJudge = new Judge(actualJudge.getName(), actualJudge.getSurname(),
                actualJudge.getEmail(), actualJudge.getPhoneNumber());
        judgeDao.save(actualJudge);
        int actualJudgeId = judgeDaoTest.selectJudgeIdByEmail(actualJudge.getEmail());
        Judge expectedJudge = judgeDao.get(actualJudgeId);
        putParamsToTestMap("Actual", actualJudge);
        putParamsToTestMap("Expected", expectedJudge);
        Assert.assertEquals(testJudgeDataMap.get("Expected"), testJudgeDataMap.get("Actual"));
    }

    @ParameterizedTest
    @MethodSource("getTestJudges")
    @Order(2)
    public void testUpdateJudge(Judge actualJudge) throws SQLException,
            InvalidEmailException, InvalidJudgeNameException {
        int actualJudgeId = judgeDaoTest.selectJudgeIdByEmail(actualJudge.getEmail());
        actualJudge = new Judge(actualJudgeId, "Vitalii", "Kemeniash", "Vkem@gmail.com", 456187950);
        judgeDao.update(actualJudge);
        Judge expectedJudge = judgeDao.get(actualJudgeId);
        putParamsToTestMap("Actual", actualJudge);
        putParamsToTestMap("Expected", expectedJudge);
        Assert.assertEquals(testJudgeDataMap.get("Expected"), testJudgeDataMap.get("Actual"));
    }

    @ParameterizedTest()
    @MethodSource("getTestJudges")
    @Order(3)
    public void testDeleteJudge(Judge actualJudge) throws SQLException {
        int actualJudgeId = judgeDaoTest.selectJudgeIdByEmail(actualJudge.getEmail());
        judgeDao.delete(actualJudgeId);
        Assert.assertNull(judgeDao.get(actualJudgeId));
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
