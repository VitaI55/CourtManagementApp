package Exceptions;

public class InvalidCaseTypeException extends Throwable {
    private final String cause;

    public InvalidCaseTypeException(String cause) {
        super();
        this.cause = cause;
    }

    @Override
    public String toString() {
        return this.cause + "- Such case type does not exist";
    }
}
