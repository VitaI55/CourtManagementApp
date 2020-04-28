package Controller;

import Model.Dao.JudgeDao;
import Model.Judge;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/")
public class UserService extends HttpServlet {
    private final JudgeDao judgeDao = new JudgeDao();
    private static final String LIST_OF_JUDGES = "/list-users.jsp";

    //create bad work
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        doGet(req, resp);
    }
        // read + refresh
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Judge> listJudges = judgeDao.selectAllJudges();
        req.setAttribute("listJudges", listJudges);
        String action = "";
        if(req.getParameter("action") != null) {
            action = req.getParameter("action");
        }
        // method delete
        if (action.equalsIgnoreCase("delete")) {
            int judgeId = Integer.parseInt(req.getParameter("id"));
            try {
                judgeDao.deleteJudge(judgeId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            List<Judge> listJud = judgeDao.selectAllJudges();
            req.setAttribute("listJudges", listJud);
        }
        RequestDispatcher view = req.getRequestDispatcher(LIST_OF_JUDGES);
        view.forward(req, resp);
    }

}
