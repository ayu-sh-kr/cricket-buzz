package dev.archimedes.utils.exceptions;

public class NullObjectException extends Exception{
    public NullObjectException() {
        super();
    }

    public NullObjectException(String message) {
        super(message);
    }

    @Override
    public String getLocalizedMessage() {
        return super.getLocalizedMessage();
    }
}
