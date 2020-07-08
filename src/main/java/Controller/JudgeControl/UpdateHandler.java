package Controller.JudgeControl;

import Exceptions.InvalidEmailException;
import Exceptions.InvalidJudgeNameException;
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

@WebServlet("/update")
public class UpdateHandler extends HttpServlet {
    private final JudgeDao judgeDao = new JudgeDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Judge judge = judgeDao.get(id);
        req.setAttribute("judge", judge);
        RequestDispatcher view = req.getRequestDispatcher("edit-user.jsp");
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
            System.out.println("Unable to update because of: " + e);
        } catch (InvalidJudgeNameException | InvalidEmailException e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/listUser");
        dispatcher.forward(req, resp);
    }
}
