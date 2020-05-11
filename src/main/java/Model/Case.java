package Model;

public class Case extends Entity{
    private CaseType caseType;
    private Level level;
    private String description;
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
