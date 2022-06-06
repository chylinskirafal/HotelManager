package pl.chylu.exceptions;

abstract public class ReservationCustomException extends RuntimeException {

    abstract public int getCode();

    public ReservationCustomException(String message) {
        super(message);
    }
}
