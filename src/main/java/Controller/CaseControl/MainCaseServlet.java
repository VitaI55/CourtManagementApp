package Controller.CaseControl;

import Model.Dao.Case.CaseReadDelete;
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
import java.util.List;

@WebServlet("/cases")
public class MainCaseServlet extends HttpServlet {
    private CaseReadDelete caseReadDelete;

    @Override
    public void init() throws ServletException {
        super.init();
        this.caseReadDelete = new CaseReadDelete();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Case> listCase = caseReadDelete.getAll();
        req.setAttribute("listCases", listCase);
        String link = "CaseView/list-cases.jsp";
        String action = "";
        if (req.getParameter("action") != null) {
            action = req.getParameter("action");
        }
        if (action.equalsIgnoreCase("delete")) {
            int caseId = Integer.parseInt(req.getParameter("id"));
            deleteCase(req, caseId);
        }
        RequestDispatcher view = req.getRequestDispatcher(link);
        view.forward(req, resp);
    }

    private void deleteCase(HttpServletRequest req, int caseId) {
        caseReadDelete.delete(caseId);
        List<Case> autoRefresh = caseReadDelete.getAll();
        req.setAttribute("listCases", autoRefresh);
    }
}
