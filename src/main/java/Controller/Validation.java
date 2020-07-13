package Controller;

import Exceptions.*;
import Model.Dao.JudgeReadDelete;
import Model.Enums.CaseType;
import Model.Enums.Level;
import Model.MainData.Judge;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class Validation {

    public void checkJudgeEmail(String email) throws InvalidEmailException {
        if (!Pattern.matches("^(.+)@(.+)$", email)) {
            throw new InvalidEmailException("You entered incorrect data for email");
        }
    }

    public void checkJudgeName(String name) throws InvalidJudgeNameException {
        if (!Pattern.matches("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$", name)) {
            throw new InvalidJudgeNameException("You can not use this as your Name");
        }
    }

    public void checkCaseType(String caseType) throws InvalidCaseTypeException {
        if (caseType.equals(String.valueOf(CaseType.CIVIL)) ||
                caseType.equals(String.valueOf(CaseType.CRIMINAL)) ||
                caseType.equals(String.valueOf(CaseType.TAXES))) {
        } else {
            throw new InvalidCaseTypeException("This case type does not exist");
        }
    }

    public void checkCaseLevel(String level) throws InvalidCaseLevelException {
        if (level.equals(String.valueOf(Level.EASY)) ||
                level.equals(String.valueOf(Level.EXPERT)) ||
                level.equals(String.valueOf(Level.MEDIUM)) ||
                level.equals(String.valueOf(Level.HARD))) {
        } else {
            throw new InvalidCaseLevelException("This case level does not exist");
        }
    }

    public void checkValidCaseJudgeId(String id) throws IncorrectJudgeIdException, SQLException {
        JudgeReadDelete judgeDao = new JudgeReadDelete();
        int count = 0;
        for (Judge judge : judgeDao.getAll()) {
            if (Integer.parseInt(id) == judge.getId()) {
                count++;
            }
        }
        if (count == 0) {
            throw new IncorrectJudgeIdException("Judge with this Id does not exist");
        }
    }
}
