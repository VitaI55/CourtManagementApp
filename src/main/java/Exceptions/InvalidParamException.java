package Exceptions;

public class InvalidParamException extends Throwable {

    private final String cause;

    public InvalidParamException(String cause) {
        super();
        this.cause = cause;
    }

    @Override
    public String toString() {
        return this.cause + "- You are trying to input invalid parameter";
    }
}
