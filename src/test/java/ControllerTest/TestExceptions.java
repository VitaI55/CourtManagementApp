package ControllerTest;


import Controller.Validation;
import Exceptions.*;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class TestExceptions {
    Validation validation;

    @Before
    public void setUp(){
        validation = new Validation();
    }

    @Test(expected = InvalidEmailException.class)
    public void testJudgeEmailExceptions() throws InvalidEmailException {
        validation.checkJudgeEmail("Jabadabadu.lasl.");
    }

    @Test(expected = InvalidJudgeNameException.class)
    public void testJudgeNameExceptions() throws InvalidJudgeNameException {
        validation.checkJudgeName("Mar98ko");
    }

    @Test(expected = InvalidCaseLevelException.class)
    public void testCaseLevelException() throws InvalidCaseLevelException {
        validation.checkCaseLevel("SUPERHARD");
    }

    @Test(expected = InvalidCaseTypeException.class)
    public void testCaseTypeException() throws InvalidCaseTypeException {
        validation.checkCaseType("ADMINISTRATIVE");
    }

    @Test(expected = IncorrectJudgeIdException.class)
    public void testJudgeIdInCaseException() throws SQLException, IncorrectJudgeIdException {
        validation.checkValidCaseJudgeId("845");
    }
}
