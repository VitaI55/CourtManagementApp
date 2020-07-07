package Exceptions;

public class InvalidCaseLevelException extends Throwable {

    private final String cause;

    public InvalidCaseLevelException(String cause) {
        super();
        this.cause = cause;
    }

    @Override
    public String toString() {
        return this.cause + "- Such case level does not exist";
    }
}
