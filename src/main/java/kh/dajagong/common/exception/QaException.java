package kh.dajagong.common.exception;

public class QaException extends RuntimeException {
    public QaException() {}

    public QaException(String msg) {
        super(msg);
    }
}