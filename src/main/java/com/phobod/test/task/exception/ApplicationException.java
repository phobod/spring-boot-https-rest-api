package com.phobod.test.task.exception;

public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = -4074348289592182120L;

	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApplicationException(String message) {
		super(message);
	}

	public ApplicationException(Throwable cause) {
		super(cause);
	}

}
