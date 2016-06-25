package com.legame.paysdk.exception;

public class InitException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8704937770013508183L;

	public InitException() {
		super();
	}

	public InitException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public InitException(String detailMessage) {
		super(detailMessage);
	}

	public InitException(Throwable throwable) {
		super(throwable);
	}

}
