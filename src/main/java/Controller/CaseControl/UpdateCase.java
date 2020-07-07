package Controller.CaseControl;

import Model.MainData.Case;
import Model.Enums.CaseType;
import Model.Dao.CaseDao;
import Model.Enums.Level;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/caseUpdate")
public class UpdateCase extends HttpServlet {
    private final CaseDao caseDao = new CaseDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Case aCase = caseDao.selectCase(id);
        req.setAttribute("mCase", aCase);
        RequestDispatcher view = req.getRequestDispatcher("edit-case.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int caseId = Integer.parseInt(req.getParameter("id"));
        CaseType type = CaseType.valueOf(req.getParameter("caseType"));
        Level level = Level.valueOf(req.getParameter("level"));
        String description = req.getParameter("description");
        int JudgeId = Integer.parseInt(req.getParameter("judgeId"));
        Case updCase = new Case(caseId, type, level, description, JudgeId);
        try {
            caseDao.updateCase(updCase);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/cases");
        dispatcher.forward(req, resp);
    }
}
