package Model;

import Model.MainData.Judge;

import java.sql.SQLException;
import java.util.List;

public interface JudgeManagement {
    void insertJudge(Judge judge) throws SQLException;

    Judge selectJudge(int id);

    List<Judge> selectAllJudges();

    void deleteJudge(int id) throws SQLException;

    void updateJudge(Judge judge) throws SQLException;
}
