package com.legame.paysdk.exception;

public class LogoutException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7976415752264056267L;

	public LogoutException() {
		super();
	}

	public LogoutException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public LogoutException(String detailMessage) {
		super(detailMessage);
	}

	public LogoutException(Throwable throwable) {
		super(throwable);
	}

}
