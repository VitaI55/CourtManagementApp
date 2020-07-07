package Model.Dao;

import Model.*;
import Model.MainData.Case;
import Model.Enums.CaseType;
import Model.Enums.Level;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CaseDao implements CaseManagement {
    private static final String INSERT_CASE_SQL = "INSERT INTO cases  (caseType, level, description, judgeId) " +
            "VALUES  (?, ?, ?, ?);";
    private static final String SELECT_CASE_BY_ID = "SELECT id, caseType, level, description," +
            " judgeId FROM cases WHERE id =?";
    private static final String SELECT_ALL_CASES = "SELECT * FROM cases";
    private static final String DELETE_CASES_SQL = "DELETE FROM cases WHERE id = ?;";
    private static final String UPDATE_CASES_SQL = "UPDATE cases SET caseType = ?, level = ?," +
            "description = ?, judgeId = ? WHERE id = ?";
    private final static String PERSONAL_CASE_SQL = "SELECT DISTINCT c.id," +
            " c.caseType, c.level, c.description FROM cases AS c" +
            " INNER JOIN judges AS j ON c.judgeId = ?";
    private final DBConnect dbConnect = new DBConnect();

    @Override
    public void insertCase(Case c) {
        try (Connection con = dbConnect.getConnection();
             PreparedStatement preparedStatement = con
                     .prepareStatement(INSERT_CASE_SQL)) {
            preparedStatement.setString(1, String.valueOf(c.getCaseType()));
            preparedStatement.setString(2, String.valueOf(c.getLevel()));
            preparedStatement.setString(3, c.getDescription());
            preparedStatement.setInt(4, c.getJudgeId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Case selectCase(int id) {
        Case c = null;
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_CASE_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                CaseType caseType = CaseType.valueOf(rs.getString("caseType"));
                Level level = Level.valueOf(rs.getString("level"));
                String description = rs.getString("description");
                int JudgeId = rs.getInt("judgeId");
                c = new Case(id, caseType, level, description, JudgeId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public List<Case> selectAllCases() {
        List<Case> cases = new ArrayList<>();
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_ALL_CASES)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                CaseType caseType = CaseType.valueOf(rs.getString("caseType"));
                Level level = Level.valueOf(rs.getString("level"));
                String description = rs.getString("description");
                int JudId = rs.getInt("judgeId");
                cases.add(new Case(id, caseType, level, description, JudId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cases;
    }

    @Override
    public void deleteCase(int id) throws SQLException {
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(DELETE_CASES_SQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void updateCase(Case c) throws SQLException {
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(UPDATE_CASES_SQL)) {
            preparedStatement.setString(1, String.valueOf(c.getCaseType()));
            preparedStatement.setString(2, String.valueOf(c.getLevel()));
            preparedStatement.setString(3, c.getDescription());
            preparedStatement.setInt(4, c.getJudgeId());
            preparedStatement.setInt(5, c.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<Case> selectPersonalCases(int id) {
        List<Case> personalCases = new ArrayList<>();
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(PERSONAL_CASE_SQL)) {
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
}
