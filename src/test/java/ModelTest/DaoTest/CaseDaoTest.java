package ModelTest.DaoTest;

import Model.Dao.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CaseDaoTest {
    private static final String SELECT_CASE_ID_BY_DESC =
            "SELECT id FROM cases WHERE description =?";
    private final DBConnect dbConnect = new DBConnect();

    public int selectCaseIdByDescription(String description) {
        int caseId = 0;
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_CASE_ID_BY_DESC)) {
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
