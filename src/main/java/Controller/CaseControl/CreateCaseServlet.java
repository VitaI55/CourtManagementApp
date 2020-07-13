package Controller.CaseControl;

import Controller.Validation;
import Exceptions.IncorrectJudgeIdException;
import Exceptions.InvalidCaseLevelException;
import Exceptions.InvalidCaseTypeException;
import Model.Dao.CaseCreateUpdate;
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

@WebServlet("/createCase")
public class CreateCaseServlet extends HttpServlet {
    private final CaseCreateUpdate caseCreateUpdate = new CaseCreateUpdate();
    private static final Logger CASE_CREATE_LOGGER = LogManager.getLogger(CreateCaseServlet.class);
    Validation validation = new Validation();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("CaseView/add-case.jsp");
        view.forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            validation.checkCaseLevel(req.getParameter("level"));
            validation.checkCaseType(req.getParameter("caseType"));
            validation.checkValidCaseJudgeId(req.getParameter("judgeId"));
        } catch (InvalidCaseLevelException | InvalidCaseTypeException | IncorrectJudgeIdException | SQLException e) {
            CASE_CREATE_LOGGER.warn(e);
            RequestDispatcher dispatcher = req.getRequestDispatcher("cases");
            dispatcher.forward(req, resp);
        }
        CaseType caseType = CaseType.valueOf(req.getParameter("caseType"));
        Level level = Level.valueOf(req.getParameter("level"));
        String description = req.getParameter("description");
        int JudgeId = Integer.parseInt(req.getParameter("judgeId"));
        Case newCase = new Case(caseType, level, description, JudgeId);

        try {
            caseCreateUpdate.save(newCase);
        } catch (SQLException e) {
            CASE_CREATE_LOGGER.debug(e);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/cases");
        dispatcher.forward(req, resp);
    }
}
