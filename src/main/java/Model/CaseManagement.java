package Model;

import Model.MainData.Case;

import java.sql.SQLException;
import java.util.List;

public interface CaseManagement {
    void insertCase(Case c);

    Case selectCase(int id);

    List<Case> selectAllCases();

    void deleteCase(int id) throws SQLException;

    void updateCase(Case c) throws SQLException;

    List<Case> selectPersonalCases(int id);
}
