package Exceptions;

public class IncorrectJudgeIdException extends Throwable {
    private final String cause;

    public IncorrectJudgeIdException(String cause) {
        super();
        this.cause = cause;
    }

    @Override
    public String toString() {
        return this.cause + "- Judge with this Id does not exist";
    }
}
