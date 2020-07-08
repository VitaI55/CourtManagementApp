package ModelTest.DaoTest;

import Model.Dao.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JudgeDaoTest {
    private static final String SELECT_JUDGE_ID_BY_MAIL =
            "SELECT id FROM judges WHERE email =?";
    private final DBConnect dbConnect = new DBConnect();

    public int selectJudgeIdByEmail(String email) {
        int judgeId = 0;
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_JUDGE_ID_BY_MAIL)) {
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                judgeId = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return judgeId;
    }
}
