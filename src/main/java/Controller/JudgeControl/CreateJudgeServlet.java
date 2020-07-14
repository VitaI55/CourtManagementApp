package Controller.JudgeControl;

import Exceptions.InvalidEmailException;
import Exceptions.InvalidJudgeNameException;
import Model.Dao.Judge.JudgeCreateUpdate;
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

@WebServlet("/create-judge")
public class CreateJudgeServlet extends HttpServlet {
    private JudgeCreateUpdate judgeCreateUpdate;
    private static Logger JUDGE_CREATE_LOGGER;

    @Override
    public void init() throws ServletException {
        super.init();
        this.judgeCreateUpdate = new JudgeCreateUpdate();
        JUDGE_CREATE_LOGGER = LogManager.getLogger(CreateJudgeServlet.class);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("JudgeView/create-judge.jsp");
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
            judgeCreateUpdate.save(newJudge);
        } catch (InvalidEmailException | InvalidJudgeNameException e) {
            JUDGE_CREATE_LOGGER.warn(e);
        } finally {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/judges");
            dispatcher.forward(req, resp);
        }
    }
}
