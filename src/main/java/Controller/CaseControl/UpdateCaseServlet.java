package Controller.CaseControl;

import Controller.Validation;
import Exceptions.IncorrectJudgeIdException;
import Exceptions.InvalidCaseLevelException;
import Exceptions.InvalidCaseTypeException;
import Model.Dao.Case.CaseCreateUpdate;
import Model.Dao.Case.CaseReadDelete;
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

@WebServlet("/update-case")
public class UpdateCaseServlet extends HttpServlet {
    private static final Logger CASE_UPDATE_LOGGER =
            LogManager.getLogger(UpdateCaseServlet.class);
    private CaseCreateUpdate caseCreateUpdate;
    private CaseReadDelete caseReadDelete;
    private Validation validation;

    @Override
    public void init() throws ServletException {
        super.init();
        this.caseCreateUpdate = new CaseCreateUpdate();
        this.caseReadDelete = new CaseReadDelete();
        this.validation = new Validation();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Case aCase = caseReadDelete.get(id);
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

        caseCreateUpdate.update(updCase);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/cases");
        dispatcher.forward(req, resp);
    }
}
