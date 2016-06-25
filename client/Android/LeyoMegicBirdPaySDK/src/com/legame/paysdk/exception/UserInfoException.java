package com.legame.paysdk.exception;

public class UserInfoException extends Exception {

	private static final long serialVersionUID = -1816342084396088344L;

	public UserInfoException() {
		super();
	}

	public UserInfoException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public UserInfoException(String detailMessage) {
		super(detailMessage);
	}

	public UserInfoException(Throwable throwable) {
		super(throwable);
	}

}
