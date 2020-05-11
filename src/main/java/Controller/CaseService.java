package Controller;

import Model.Case;
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
    //    private static Logger loggerCase
    //           = LogManager.getLogger(CaseServlet.class);
    private static String EDIT_CASE = "/edit-case.jsp";
    private static String LIST_CASES = "/list-cases.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //      loggerCase.info(this.getServletName() + "into doPost()");
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //    loggerCase.info(this.getServletName() + "into doGet()");
        List<Case> listCase = caseDao.selectAllCases();
        req.setAttribute("listCases", listCase);
        String link = LIST_CASES;
        String action = "";
        if(req.getParameter("action") != null) {
            action = req.getParameter("action");
        }
        //delete +
        if (action.equalsIgnoreCase("delete")) {
            int caseId = Integer.parseInt(req.getParameter("id"));
            try {
                caseDao.deleteCase(caseId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            List<Case> autoRefresh = caseDao.selectAllCases();
            req.setAttribute("listCases", autoRefresh);
        }
        RequestDispatcher view = req.getRequestDispatcher(link);
        view.forward(req, resp);
    }
}
