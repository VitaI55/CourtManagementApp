package ControllerTest;

import Controller.CaseControl.CreateCaseServlet;
import Controller.CaseControl.MainCaseServlet;
import Controller.CaseControl.PersonalCaseServlet;
import Controller.CaseControl.UpdateCaseServlet;
import Model.Dao.CaseSelector;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class CaseServletTest {
    private CaseSelector caseSelector;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher requestDispatcher;
    private final static String path = "/cases";

    @Before
    public void setUp() {
        caseSelector = new CaseSelector();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        requestDispatcher = mock(RequestDispatcher.class);
    }

    @Test
    public void testCreateCase() throws ServletException, IOException {
        when(request.getParameter("caseType")).thenReturn("CIVIL");
        when(request.getParameter("level")).thenReturn("HARD");
        when(request.getParameter("description")).thenReturn("Very interesting..");
        when(request.getParameter("judgeId")).thenReturn("2");
        when(request.getRequestDispatcher(path)).thenReturn(requestDispatcher);

        final CreateCaseServlet createCase = new CreateCaseServlet();

        createCase.doPost(request, response);

        verify(request, times(1)).getRequestDispatcher(path);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testUpdateCase() throws ServletException, IOException {
        when(request.getParameter("caseType")).thenReturn("CIVIL");
        when(request.getParameter("level")).thenReturn("HARD");
        when(request.getParameter("description")).thenReturn("Very interesting..");
        when(request.getParameter("judgeId")).thenReturn("2");
        when(request.getRequestDispatcher(path)).thenReturn(requestDispatcher);
        int testId = caseSelector.selectCaseIdByDescription("Very interesting..");
        when(request.getParameter("id")).thenReturn(String.valueOf(testId));

        final UpdateCaseServlet updateCase = new UpdateCaseServlet();

        updateCase.doPost(request, response);

        verify(request, times(1)).getRequestDispatcher(path);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testDeleteCase() throws ServletException, IOException {
        when(request.getParameter("action")).thenReturn("delete");
        when(request.getRequestDispatcher("CaseView/list-cases.jsp")).thenReturn(requestDispatcher);
        int testId = caseSelector.selectCaseIdByDescription("Very interesting..");
        when(request.getParameter("id")).thenReturn(String.valueOf(testId));

        final MainCaseServlet caseService = new MainCaseServlet();

        caseService.doGet(request, response);

        verify(request, times(1)).getRequestDispatcher("CaseView/list-cases.jsp");
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testPersonalCase() throws ServletException, IOException {
        when(request.getParameter("id")).thenReturn(String.valueOf(1));
        when(request.getRequestDispatcher("CaseView/personal-case.jsp")).thenReturn(requestDispatcher);
        final PersonalCaseServlet personalCases = new PersonalCaseServlet();

        personalCases.doGet(request, response);

        verify(request, times(1)).getRequestDispatcher("CaseView/personal-case.jsp");
        verify(requestDispatcher).forward(request, response);
    }
}
