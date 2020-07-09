package Exceptions;

public class InvalidJudgeNameException extends Throwable {
    private final String cause;

    public InvalidJudgeNameException(String cause) {
        super();
        this.cause = cause;
    }

    @Override
    public String toString() {
        return this.cause;
    }
}