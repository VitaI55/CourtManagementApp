package Controller.JudgeControl;

import Exceptions.InvalidEmailException;
import Exceptions.InvalidJudgeNameException;
import Model.Dao.Judge.JudgeCreateUpdate;
import Model.Dao.Judge.JudgeReadDelete;
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

@WebServlet("/update-judge")
public class UpdateJudgeServlet extends HttpServlet {
    private static final Logger JUDGE_UPDATE_LOGGER =
            LogManager.getLogger(UpdateJudgeServlet.class);
    private JudgeCreateUpdate judgeCreateUpdate;
    private JudgeReadDelete judgeReadDelete;

    @Override
    public void init() throws ServletException {
        super.init();
        this.judgeCreateUpdate = new JudgeCreateUpdate();
        this.judgeReadDelete = new JudgeReadDelete();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Judge judge = judgeReadDelete.get(id);
        req.setAttribute("judge", judge);
        RequestDispatcher view = req.getRequestDispatcher("JudgeView/edit-judge.jsp");
        view.forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int judgeId = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        int phoneNumber = Integer.parseInt(req.getParameter("phoneNumber"));
        Judge updatedJudge = new Judge(judgeId, name, surname, email, phoneNumber);
        try {
            judgeCreateUpdate.update(updatedJudge);
        } catch (InvalidJudgeNameException | InvalidEmailException e) {
            JUDGE_UPDATE_LOGGER.warn(e);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/judges");
        dispatcher.forward(req, resp);
    }
}
