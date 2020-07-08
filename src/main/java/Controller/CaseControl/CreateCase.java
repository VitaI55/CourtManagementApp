package Controller.CaseControl;

import Exceptions.InvalidCaseLevelException;
import Exceptions.InvalidCaseTypeException;
import Model.Dao.CaseDao;
import Model.MainData.Case;
import Model.Enums.CaseType;
import Model.Enums.Level;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/createCase")
public class CreateCase extends HttpServlet {
    private final CaseDao caseDao = new CaseDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CaseType caseType = CaseType.valueOf(req.getParameter("caseType"));
        Level level = Level.valueOf(req.getParameter("level"));
        String description = req.getParameter("description");
        int JudgeId = Integer.parseInt(req.getParameter("judgeId"));
        Case newCase = new Case(caseType, level, description, JudgeId);

        try {
            caseDao.save(newCase);
        } catch (InvalidCaseTypeException | InvalidCaseLevelException e) {
            System.out.println(e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/cases");
        dispatcher.forward(req, resp);
    }
}
