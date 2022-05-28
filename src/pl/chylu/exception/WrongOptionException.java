package pl.chylu.exception;

public class WrongOptionException extends ReservationCustomException {
    private final int code = 101;
    public WrongOptionException(String message) {
        super(message);
    }
    public int getCode() {
        return code;
    }
}