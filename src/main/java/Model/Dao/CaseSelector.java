package Model.Dao;

import Model.Enums.CaseType;
import Model.Enums.Level;
import Model.MainData.Case;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CaseSelector {
    private final DBConnect dbConnect;
    private static final String SELECT_CASE_ID_BY_DESCRIPTION =
            "SELECT id FROM cases WHERE description =?";
    private final static String SELECT_PERSONAL_CASES_BY_JUDGE_ID = "SELECT DISTINCT c.id," +
            " c.caseType, c.level, c.description FROM cases AS c" +
            " INNER JOIN judges ON c.judgeId = ?";

    public CaseSelector() {
        this.dbConnect = new DBConnect();
    }

    public List<Case> selectPersonalCases(int id) {
        List<Case> personalCases = new ArrayList<>();
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_PERSONAL_CASES_BY_JUDGE_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int personsId = rs.getInt("id");
                CaseType caseType = CaseType.valueOf(rs.getString("caseType"));
                Level level = Level.valueOf(rs.getString("level"));
                String description = rs.getString("description");
                personalCases.add(new Case(personsId, caseType, level, description));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personalCases;
    }

    public int selectCaseIdByDescription(String description) {
        int caseId = 0;
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_CASE_ID_BY_DESCRIPTION)) {
            preparedStatement.setString(1, description);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                caseId = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return caseId;
    }
}
