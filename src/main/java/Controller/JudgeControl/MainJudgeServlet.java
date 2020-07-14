package Controller.JudgeControl;

import Model.Dao.Judge.JudgeReadDelete;
import Model.MainData.Judge;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/judges")
public class MainJudgeServlet extends HttpServlet {
    private JudgeReadDelete judgeReadDelete;

    @Override
    public void init() throws ServletException {
        super.init();
        this.judgeReadDelete = new JudgeReadDelete();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        doGet(req, resp);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Judge> listJudges = judgeReadDelete.getAll();
        req.setAttribute("listJudges", listJudges);
        String link = "JudgeView/list-judges.jsp";
        String action = "";
        if (req.getParameter("action") != null) {
            action = req.getParameter("action");
        }
        if (action.equalsIgnoreCase("delete")) {
            int judgeId = Integer.parseInt(req.getParameter("id"));
            deleteJud(req, judgeId);
        }
        RequestDispatcher view = req.getRequestDispatcher(link);
        view.forward(req, resp);
    }

    private void deleteJud(HttpServletRequest req, int judgeId) {
        judgeReadDelete.delete(judgeId);
        List<Judge> listJud = judgeReadDelete.getAll();
        req.setAttribute("listJudges", listJud);
    }
}
