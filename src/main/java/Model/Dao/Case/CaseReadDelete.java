package Model.Dao.Case;

import Model.Dao.DBConnect;
import Model.DaoReadDelete;
import Model.Enums.CaseType;
import Model.Enums.Level;
import Model.MainData.Case;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CaseReadDelete implements DaoReadDelete<Case> {
    private static final Logger CASE_READ_DELETE_LOGGER =
            LogManager.getLogger(CaseReadDelete.class);
    private static final String SELECT_CASE_BY_ID = "SELECT id, caseType, level, description," +
            " judgeId FROM cases WHERE id =?";
    private static final String SELECT_ALL_CASES = "SELECT * FROM cases";
    private static final String DELETE_CASE_BY_ID = "DELETE FROM cases WHERE id = ?;";
    private final DBConnect dbConnect;

    public CaseReadDelete() {
        this.dbConnect = new DBConnect();
    }

    @Override
    public Case get(int id) {
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
            CASE_READ_DELETE_LOGGER.warn(e);
        }
        return c;
    }

    @Override
    public List<Case> getAll() {
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
            CASE_READ_DELETE_LOGGER.warn(e);
        }
        return cases;
    }

    @Override
    public void delete(int id) {
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(DELETE_CASE_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            CASE_READ_DELETE_LOGGER.warn(e);
        }
    }
}
