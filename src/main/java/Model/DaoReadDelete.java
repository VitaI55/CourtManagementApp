package Model;

import Exceptions.IncorrectJudgeIdException;

import java.sql.SQLException;
import java.util.List;

public interface DaoReadDelete<T> {

    T get(int id) throws Throwable;

    List<T> getAll() throws SQLException;

    void delete(int id) throws SQLException, IncorrectJudgeIdException;
}
