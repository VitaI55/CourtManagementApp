package Controller.CaseControl;

import Exceptions.IncorrectJudgeIdException;
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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@WebServlet("/caseUpdate")
public class UpdateCase extends HttpServlet {
    private final CaseDao caseDao = new CaseDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Case aCase = null;
        try {
            aCase = caseDao.get(id);
        } catch (IncorrectJudgeIdException e) {
            e.printStackTrace();
        }
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
            caseDao.update(updCase);
        } catch(InvalidCaseLevelException | InvalidCaseTypeException e){
            System.out.println(e);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/cases");
        dispatcher.forward(req, resp);
    }
}
