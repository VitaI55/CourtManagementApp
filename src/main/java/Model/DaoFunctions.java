package Model;

import Exceptions.*;

import java.sql.SQLException;
import java.util.List;

public interface DaoFunctions<T> {

    T get(int id) throws IncorrectJudgeIdException;

    List<T> getAll();

    //insert
    void save(T t) throws Throwable;

    void update(T t) throws Throwable;

    void delete(int id) throws SQLException, IncorrectJudgeIdException;
}
