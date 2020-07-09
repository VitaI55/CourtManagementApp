package Controller.CaseControl;

import Model.Dao.CaseDao;
import Model.MainData.Case;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/personalCase")
public class PersonalCases extends HttpServlet {
    private final CaseDao caseDao = new CaseDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        List<Case> personalCases = caseDao.selectPersonalCases(id);
        req.setAttribute("listCases", personalCases);
        RequestDispatcher view = req.getRequestDispatcher("CaseView/personal-case.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
