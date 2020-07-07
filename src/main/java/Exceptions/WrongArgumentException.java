package Exceptions;

public class WrongArgumentException {
    private final String cause;

    public WrongArgumentException(String cause) {
        super();
        this.cause = cause;
    }

    @Override
    public String toString() {
        return this.cause + "- You are trying to input incorrect argument";
    }
}
