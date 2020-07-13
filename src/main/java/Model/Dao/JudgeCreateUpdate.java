package Model.Dao;

import Controller.Validation;
import Exceptions.InvalidEmailException;
import Exceptions.InvalidJudgeNameException;
import Model.DaoCreateUpdate;
import Model.MainData.Judge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JudgeCreateUpdate implements DaoCreateUpdate<Judge> {
    private static final String INSERT_JUDGE_SQL = "INSERT INTO judges (name, surname, email, phoneNumber) VALUES  (?, ?, ?, ?);";
    private static final String UPDATE_JUDGE_BY_ID = "UPDATE judges SET name = ?, surname = ?, email = ?, phoneNumber = ? "
            + "WHERE id = ?";
    private final DBConnect dbConnect;
    private final Validation validation;

    public JudgeCreateUpdate() {
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
                     .prepareStatement(INSERT_JUDGE_SQL)) {
            preparedStatement.setString(1, judge.getName());
            preparedStatement.setString(2, judge.getSurname());
            preparedStatement.setString(3, judge.getEmail());
            preparedStatement.setInt(4, judge.getPhoneNumber());
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
                     .prepareStatement(UPDATE_JUDGE_BY_ID)) {
            preparedStatement.setString(1, judge.getName());
            preparedStatement.setString(2, judge.getSurname());
            preparedStatement.setString(3, judge.getEmail());
            preparedStatement.setInt(4, judge.getPhoneNumber());
            preparedStatement.setInt(5, judge.getId());
            preparedStatement.executeUpdate();
        }
    }
}
