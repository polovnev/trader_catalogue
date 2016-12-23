package com.polovnev.exception;

public class HttpException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private int httpStatus;

	public HttpException(int httpStatus) {
		this.httpStatus = httpStatus;
	}

	public int getHttpStatus() {
		return httpStatus;
	}

}
