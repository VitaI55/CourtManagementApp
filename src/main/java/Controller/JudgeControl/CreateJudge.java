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

@WebServlet("/create")
public class CreateJudge extends HttpServlet {
    private final JudgeDao judgeDao = new JudgeDao();
    private static final Logger JUDGE_CRETE_LOGGER = LogManager.getLogger(CreateJudge.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("JudgeView/create-user.jsp");
        view.forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        int phoneNumber = Integer.parseInt(req.getParameter("phoneNumber"));
        Judge newJudge = new Judge(name, surname, email, phoneNumber);
        try {
            judgeDao.save(newJudge);
        } catch (SQLException e) {
            JUDGE_CRETE_LOGGER.debug(e);
        } catch (InvalidEmailException | InvalidJudgeNameException e) {
            JUDGE_CRETE_LOGGER.warn(e);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/");
        dispatcher.forward(req, resp);
    }
}
