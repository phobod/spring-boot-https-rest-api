package com.phobod.test.task.exception;

public class CantProcessItemException extends ApplicationException {
	private static final long serialVersionUID = 749361495765926701L;

	public CantProcessItemException(String message) {
		super(message);
	}

	public CantProcessItemException(String message, Throwable cause) {
		super(message, cause);
	}

	public CantProcessItemException(Throwable cause) {
		super(cause);
	}

}
