package Controller.CaseControl;

import Controller.Validation;
import Exceptions.IncorrectJudgeIdException;
import Exceptions.InvalidCaseLevelException;
import Exceptions.InvalidCaseTypeException;
import Model.Dao.CaseDao;
import Model.Enums.CaseType;
import Model.Enums.Level;
import Model.MainData.Case;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private static final Logger CASE_UPDATE_LOGGER = LogManager.getLogger(UpdateCase.class);
    Validation validation = new Validation();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Case aCase = caseDao.get(id);
        req.setAttribute("mCase", aCase);
        RequestDispatcher view = req.getRequestDispatcher("CaseView/edit-case.jsp");
        view.forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            validation.checkCaseLevel(req.getParameter("level"));
            validation.checkCaseType(req.getParameter("caseType"));
            validation.checkValidCaseJudgeId(req.getParameter("judgeId"));
        } catch (InvalidCaseLevelException | InvalidCaseTypeException |
                IncorrectJudgeIdException | SQLException e) {
            CASE_UPDATE_LOGGER.warn(e);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/cases");
            dispatcher.forward(req, resp);
        }
        int caseId = Integer.parseInt(req.getParameter("id"));
        CaseType type = CaseType.valueOf(req.getParameter("caseType"));
        Level level = Level.valueOf(req.getParameter("level"));
        String description = req.getParameter("description");
        int JudgeId = Integer.parseInt(req.getParameter("judgeId"));
        Case updCase = new Case(caseId, type, level, description, JudgeId);
        try {
            caseDao.update(updCase);
        } catch (SQLException e) {
            CASE_UPDATE_LOGGER.debug(e);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/cases");
        dispatcher.forward(req, resp);
    }
}
