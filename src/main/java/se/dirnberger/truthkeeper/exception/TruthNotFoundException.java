package se.dirnberger.truthkeeper.exception;

public class TruthNotFoundException extends RuntimeException {
    public TruthNotFoundException(final String message) {
        super(message);
    }
}
