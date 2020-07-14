package Model.Dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JudgeSelector {
    private static final String SELECT_JUDGE_ID_BY_EMAIL =
            "SELECT id FROM judges WHERE email =?";
    private static Logger JUDGE_SELECTOR_LOGGER;
    private final DBConnect dbConnect;

    public JudgeSelector() {
        this.dbConnect = new DBConnect();
        JUDGE_SELECTOR_LOGGER = LogManager.getLogger(JudgeSelector.class);
    }

    public int selectJudgeIdByEmail(String email) {
        int judgeId = 0;
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_JUDGE_ID_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                judgeId = rs.getInt("id");
            }
        } catch (SQLException e) {
            JUDGE_SELECTOR_LOGGER.warn(e);
        }
        return judgeId;
    }
}
