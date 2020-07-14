package ControllerTest;

import Controller.JudgeControl.CreateJudgeServlet;
import Controller.JudgeControl.MainJudgeServlet;
import Controller.JudgeControl.UpdateJudgeServlet;
import Model.Dao.JudgeSelector;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class JudgeServletTest {
    private JudgeSelector judgeSelector;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher requestDispatcher;
    private final static String path = "/judges";

    @Before
    public void setUp() {
        judgeSelector = new JudgeSelector();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        requestDispatcher = mock(RequestDispatcher.class);
    }

    @Test
    public void testCreateJudge() throws ServletException, IOException {
        when(request.getParameter("name")).thenReturn("Vital");
        when(request.getParameter("surname")).thenReturn("Kamir");
        when(request.getParameter("email")).thenReturn("kamir@gmail.com");
        when(request.getParameter("phoneNumber")).thenReturn("05055487");
        when(request.getRequestDispatcher(path)).thenReturn(requestDispatcher);

        final CreateJudgeServlet createJudge = new CreateJudgeServlet();
        createJudge.init();
        createJudge.doPost(request, response);

        verify(request, times(1)).getRequestDispatcher(path);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testUpdateJudge() throws ServletException, IOException {
        when(request.getParameter("name")).thenReturn("Vital");
        when(request.getParameter("surname")).thenReturn("Kamir");
        when(request.getParameter("email")).thenReturn("kamir@gmail.com");
        when(request.getParameter("phoneNumber")).thenReturn("05055487");
        when(request.getRequestDispatcher(path)).thenReturn(requestDispatcher);
        int testId = judgeSelector.selectJudgeIdByEmail("kamir@gmail.com");
        when(request.getParameter("id")).thenReturn(String.valueOf(testId));

        final UpdateJudgeServlet updateJudge = new UpdateJudgeServlet();
        updateJudge.init();
        updateJudge.doPost(request, response);

        verify(request, times(1)).getRequestDispatcher(path);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testDeleteJudge() throws ServletException, IOException {
        String path1 = "JudgeView/list-judges.jsp";
        when(request.getParameter("action")).thenReturn("delete");
        when(request.getRequestDispatcher(path1)).thenReturn(requestDispatcher);
        int testId = judgeSelector.selectJudgeIdByEmail("kamir@gmail.com");
        when(request.getParameter("id")).thenReturn(String.valueOf(testId));

        final MainJudgeServlet judgeService = new MainJudgeServlet();
        judgeService.init();
        judgeService.doGet(request, response);

        verify(request, times(1)).getRequestDispatcher(path1);
        verify(requestDispatcher).forward(request, response);
    }
}
