package Model;

import java.sql.SQLException;
import java.util.List;

public interface CaseOperations {
    void insertCase(Case c);
    Case selectCase(int id);
    List<Case> selectAllCases();
    void deleteCase(int id) throws SQLException;
    void updateCase(Case c) throws SQLException;
    List<Case> selectPersonalCases(int id);
}
