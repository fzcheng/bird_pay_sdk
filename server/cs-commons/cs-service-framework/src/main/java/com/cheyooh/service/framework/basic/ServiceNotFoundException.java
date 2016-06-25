package com.cheyooh.service.framework.basic;

public class ServiceNotFoundException extends RuntimeException{ 	 
	private static final long serialVersionUID = -3528448024548046922L;

	public ServiceNotFoundException() {
		super();		 
	}

	public ServiceNotFoundException(String message, Throwable cause) {
		super(message, cause);		 
	}

	public ServiceNotFoundException(String message) {
		super(message);		 
	}

	public ServiceNotFoundException(Throwable cause) {
		super(cause);		 
	}	 
}
