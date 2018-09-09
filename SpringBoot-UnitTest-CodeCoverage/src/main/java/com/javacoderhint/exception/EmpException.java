package com.javacoderhint.exception;

public class EmpException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public EmpException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public EmpException() {
		super();
	}
}
