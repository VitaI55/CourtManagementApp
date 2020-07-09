package Exceptions;

public class InvalidEmailException extends Throwable {
    private final String cause;

    public InvalidEmailException(String cause) {
        super();
        this.cause = cause;
    }

    @Override
    public String toString() {
        return this.cause;
    }
}
