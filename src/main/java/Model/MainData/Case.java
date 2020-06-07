package Model.MainData;

import Model.Enums.CaseType;
import Model.Enums.Level;

public class Case extends Entity {
    private final CaseType caseType;
    private final Level level;
    private final String description;
    private int judgeId;

    public Case(int id, CaseType caseType, Level level, String description, int JudgeId) {
        super(id);
        this.caseType = caseType;
        this.level = level;
        this.description = description;
        this.judgeId = JudgeId;
    }

    public Case(CaseType caseType, Level level, String description, int JudgeId) {
        this.caseType = caseType;
        this.level = level;
        this.description = description;
        this.judgeId = JudgeId;
    }

    public Case(int id, CaseType caseType, Level level, String description) {
        super(id);
        this.caseType = caseType;
        this.level = level;
        this.description = description;
    }

    public CaseType getCaseType() {
        return caseType;
    }

    public Level getLevel() {
        return level;
    }

    public String getDescription() {
        return description;
    }

    public int getJudgeId() {
        return judgeId;
    }
}
