package Controller.JudgeControl;

import Exceptions.InvalidEmailException;
import Exceptions.InvalidJudgeNameException;
import Model.Dao.JudgeCreateUpdate;
import Model.Dao.JudgeReadDelete;
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

@WebServlet("/update")
public class UpdateJudgeServlet extends HttpServlet {
    private final JudgeCreateUpdate judgeCreateUpdate = new JudgeCreateUpdate();
    private final JudgeReadDelete judgeReadDelete = new JudgeReadDelete();
    private static final Logger JUDGE_UPDATE_LOGGER = LogManager.getLogger(UpdateJudgeServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Judge judge = null;
        try {
            judge = judgeReadDelete.get(id);
        } catch (SQLException e) {
            JUDGE_UPDATE_LOGGER.warn(e);
        }
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
        } catch (SQLException e) {
            JUDGE_UPDATE_LOGGER.debug(e);
        } catch (InvalidJudgeNameException | InvalidEmailException e) {
            JUDGE_UPDATE_LOGGER.warn(e);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/");
        dispatcher.forward(req, resp);
    }
}
