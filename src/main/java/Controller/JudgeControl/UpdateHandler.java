package Controller.JudgeControl;

import Exceptions.InvalidEmailException;
import Exceptions.InvalidJudgeNameException;
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

@WebServlet("/update")
public class UpdateHandler extends HttpServlet {
    private final JudgeDao judgeDao = new JudgeDao();
    static final Logger JUDGE_UPDATE_LOGGER = LogManager.getLogger(UpdateHandler.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Judge judge = null;
        try {
            judge = judgeDao.get(id);
        } catch (SQLException e) {
            JUDGE_UPDATE_LOGGER.warn(e);
        }
        req.setAttribute("judge", judge);
        RequestDispatcher view = req.getRequestDispatcher("JudgeView/edit-user.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int judgeId = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        int phoneNumber = Integer.parseInt(req.getParameter("phoneNumber"));
        Judge updatedJudge = new Judge(judgeId, name, surname, email, phoneNumber);
        try {
            judgeDao.update(updatedJudge);
        } catch (SQLException e) {
            JUDGE_UPDATE_LOGGER.debug(e);
        } catch (InvalidJudgeNameException | InvalidEmailException e) {
            JUDGE_UPDATE_LOGGER.warn(e);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/");
        dispatcher.forward(req, resp);
    }
}
