package Model.Dao;

import Controller.Validation;
import Model.DaoReadDelete;
import Model.MainData.Judge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JudgeReadDelete implements DaoReadDelete<Judge> {
    private static final String SELECT_JUDGE_BY_ID = "SELECT id, name, surname, email,phoneNumber FROM judges WHERE id =?";
    private static final String SELECT_ALL_JUDGES = "SELECT * FROM judges";
    private static final String DELETE_JUDGE_BY_ID = "DELETE FROM judges WHERE id = ?;";
    private final DBConnect dbConnect;
    private final Validation validation;

    public JudgeReadDelete() {
        this.validation = new Validation();
        this.dbConnect = new DBConnect();
    }

    @Override
    public Judge get(int id) throws SQLException {
        Judge judge = null;
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_JUDGE_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                int phoneNumber = rs.getInt("phoneNumber");
                judge = new Judge(id, name, surname, email, phoneNumber);
            }
        }
        return judge;
    }

    @Override
    public List<Judge> getAll() throws SQLException {
        List<Judge> judges = new ArrayList<>();
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_ALL_JUDGES)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                int phoneNumber = rs.getInt("phoneNumber");
                judges.add(new Judge(id, name, surname, email, phoneNumber));
            }
        }
        return judges;
    }

    @Override
    public void delete(int id) throws SQLException {
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(DELETE_JUDGE_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
