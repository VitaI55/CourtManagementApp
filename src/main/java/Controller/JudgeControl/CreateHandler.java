package Controller.JudgeControl;

import Model.Dao.JudgeDao;
import Model.MainData.Judge;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/create")
public class CreateHandler extends HttpServlet {

    private final JudgeDao judgeDao = new JudgeDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        int phoneNumber = Integer.parseInt(req.getParameter("phoneNumber"));
        Judge newJudge = new Judge(name, surname, email, phoneNumber);
        try {
            judgeDao.insertJudge(newJudge);
        } catch (SQLException e) {
            System.out.println("Unable to insert, the problem is: " + e);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/listUser");
        dispatcher.forward(req, resp);
    }
}
