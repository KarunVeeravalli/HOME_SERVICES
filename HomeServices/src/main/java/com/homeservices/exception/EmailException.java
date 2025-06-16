package com.homeservices.exception;

public class EmailException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public EmailException() {
		super() ;
	}
	
	public EmailException(String msg) {
		super(msg);
	}

}
