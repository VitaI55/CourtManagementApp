package Model.Dao;

import Model.MainData.Judge;
import Model.JudgeManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JudgeDao implements JudgeManagement {
    private static final String INSERT_JUDGES_SQL = "INSERT INTO judges (name, surname, email, phoneNumber) VALUES  (?, ?, ?, ?);";
    private static final String SELECT_JUDGE_BY_ID = "select id, name, surname, email,phoneNumber from judges where id =?";
    private static final String SELECT_ALL_JUDGES = "select * from judges";
    private static final String DELETE_JUDGE_SQL = "delete from judges where id = ?;";
    private static final String UPDATE_JUDGES_SQL = "UPDATE judges SET name = ?, surname = ?, email = ?, phoneNumber = ? "
            + "where id = ?";
    private final DBConnect dbConnect = new DBConnect();

    @Override
    public void insertJudge(Judge judge) throws SQLException {
        try (Connection con = dbConnect.getConnection();
             PreparedStatement preparedStatement = con
                     .prepareStatement(INSERT_JUDGES_SQL);) {
            preparedStatement.setString(1, judge.getName());
            preparedStatement.setString(2, judge.getSurname());
            preparedStatement.setString(3, judge.getEmail());
            preparedStatement.setInt(4, judge.getPhoneNumber());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Judge selectJudge(int id) {
        Judge judge = null;
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_JUDGE_BY_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                int phoneNumber = rs.getInt("phoneNumber");
                judge = new Judge(id, name, surname, email, phoneNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return judge;
    }

    @Override
    public List<Judge> selectAllJudges() {
        List<Judge> judges = new ArrayList<>();
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_ALL_JUDGES);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                int phoneNumber = rs.getInt("phoneNumber");
                judges.add(new Judge(id, name, surname, email, phoneNumber));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return judges;
    }

    @Override
    public void deleteJudge(int id) throws SQLException {
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(DELETE_JUDGE_SQL);) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void updateJudge(Judge user) throws SQLException {
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(UPDATE_JUDGES_SQL);) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getPhoneNumber());
            preparedStatement.setInt(5, user.getId());
            preparedStatement.executeUpdate();
        }
    }
}
