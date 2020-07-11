package Controller.JudgeControl;

import Model.Dao.JudgeDao;
import Model.MainData.Judge;
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

@WebServlet("/")
public class JudgeService extends HttpServlet {
    private static final Logger JUDGE_SERVICE_LOGGER = LogManager.getLogger(JudgeService.class);
    private final JudgeDao judgeDao = new JudgeDao();
    private static final String LIST_OF_JUDGES = "JudgeView/list-users.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        doGet(req, resp);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Judge> listJudges = null;
        try {
            listJudges = judgeDao.getAll();
        } catch (SQLException e) {
            JUDGE_SERVICE_LOGGER.warn(e);
            RequestDispatcher view = req.getRequestDispatcher(LIST_OF_JUDGES);
            view.forward(req, resp);
        }
        req.setAttribute("listJudges", listJudges);
        String action = "";
        if (req.getParameter("action") != null) {
            action = req.getParameter("action");
        }
        if (action.equalsIgnoreCase("delete")) {
            int judgeId = Integer.parseInt(req.getParameter("id"));
            try {
                deleteJud(req, judgeId);
            } catch (SQLException e) {
                JUDGE_SERVICE_LOGGER.warn(e);
            }
        }
        RequestDispatcher view = req.getRequestDispatcher(LIST_OF_JUDGES);
        view.forward(req, resp);
    }

    private void deleteJud(HttpServletRequest req, int judgeId) throws SQLException {
        judgeDao.delete(judgeId);
        List<Judge> listJud = judgeDao.getAll();
        req.setAttribute("listJudges", listJud);
    }
}
