package Model.Dao;

import Controller.Validation;
import Exceptions.*;
import Model.DaoFunctions;
import Model.Enums.CaseType;
import Model.Enums.Level;
import Model.MainData.Case;
import Model.MainData.Judge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class JudgeDao implements DaoFunctions<Judge> {
    private static final String INSERT_JUDGES_SQL = "INSERT INTO judges (name, surname, email, phoneNumber) VALUES  (?, ?, ?, ?);";
    private static final String SELECT_JUDGE_BY_ID = "SELECT id, name, surname, email,phoneNumber FROM judges WHERE id =?";
    private static final String SELECT_ALL_JUDGES = "SELECT * FROM judges";
    private static final String DELETE_JUDGE_SQL = "DELETE FROM judges WHERE id = ?;";
    private static final String UPDATE_JUDGES_SQL = "UPDATE judges SET name = ?, surname = ?, email = ?, phoneNumber = ? "
            + "WHERE id = ?";
    private final DBConnect dbConnect;
    private final Validation validation;

    public JudgeDao() {
        this.validation = new Validation();
        this.dbConnect = new DBConnect();
    }

    @Override
    public void save(Judge judge) throws InvalidJudgeNameException,
            InvalidEmailException, SQLException {
        validation.checkJudgeName(judge.getName());
        validation.checkJudgeEmail(judge.getEmail());
        try (Connection con = dbConnect.getConnection();
             PreparedStatement preparedStatement = con
                     .prepareStatement(INSERT_JUDGES_SQL)) {
            preparedStatement.setString(1, judge.getName());
            preparedStatement.setString(2, judge.getSurname());
            preparedStatement.setString(3, judge.getEmail());
            preparedStatement.setInt(4, judge.getPhoneNumber());
            preparedStatement.executeUpdate();
        }
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
                     .prepareStatement(DELETE_JUDGE_SQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void update(Judge judge) throws SQLException,
            InvalidEmailException, InvalidJudgeNameException {
        validation.checkJudgeEmail(judge.getEmail());
        validation.checkJudgeName(judge.getName());
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(UPDATE_JUDGES_SQL)) {
            preparedStatement.setString(1, judge.getName());
            preparedStatement.setString(2, judge.getSurname());
            preparedStatement.setString(3, judge.getEmail());
            preparedStatement.setInt(4, judge.getPhoneNumber());
            preparedStatement.setInt(5, judge.getId());
            preparedStatement.executeUpdate();
        }
    }
}