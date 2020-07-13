package Model.Dao;

import Model.DaoCreateUpdate;
import Model.MainData.Case;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CaseCreateUpdate implements DaoCreateUpdate<Case> {
    private static final String INSERT_CASE = "INSERT INTO cases  (caseType, level, description, judgeId) " +
            "VALUES  (?, ?, ?, ?);";
    private static final String UPDATE_CASE_BY_ID = "UPDATE cases SET caseType = ?, level = ?," +
            "description = ?, judgeId = ? WHERE id = ?";
    private final DBConnect dbConnect;

    public CaseCreateUpdate() {
        this.dbConnect = new DBConnect();
    }

    @Override
    public void save(Case c) throws SQLException {
        try (Connection con = dbConnect.getConnection();
             PreparedStatement preparedStatement = con
                     .prepareStatement(INSERT_CASE)) {
            preparedStatement.setString(1, String.valueOf(c.getCaseType()));
            preparedStatement.setString(2, String.valueOf(c.getLevel()));
            preparedStatement.setString(3, c.getDescription());
            preparedStatement.setInt(4, c.getJudgeId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void update(Case c) throws SQLException {
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(UPDATE_CASE_BY_ID)) {
            preparedStatement.setString(1, String.valueOf(c.getCaseType()));
            preparedStatement.setString(2, String.valueOf(c.getLevel()));
            preparedStatement.setString(3, c.getDescription());
            preparedStatement.setInt(4, c.getJudgeId());
            preparedStatement.setInt(5, c.getId());
            preparedStatement.executeUpdate();
        }
    }
}
