package kh.dajagong.qa.model.exception;

public class QaException extends RuntimeException{
	public QaException() {}
	public QaException (String msg) {
		super(msg);
	}
}
