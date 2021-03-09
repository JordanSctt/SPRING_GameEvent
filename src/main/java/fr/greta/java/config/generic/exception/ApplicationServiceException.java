package fr.greta.java.config.generic.exception;

public class ApplicationServiceException extends Exception {
    public ApplicationServiceException(Throwable throwable) {
        super(throwable);
    }

    public ApplicationServiceException(String message) {
        super(message);
    }
}
