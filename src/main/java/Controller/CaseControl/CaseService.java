package Controller.CaseControl;

import Model.MainData.Case;
import Model.Dao.CaseDao;
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
public class CaseService extends HttpServlet {
    private final CaseDao caseDao = new CaseDao();
    private static String LIST_CASES = "/list-cases.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Case> listCase = caseDao.selectAllCases();
        req.setAttribute("listCases", listCase);
        String link = LIST_CASES;
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
        try {
            caseDao.deleteCase(caseId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Case> autoRefresh = caseDao.selectAllCases();
        req.setAttribute("listCases", autoRefresh);
    }
}
