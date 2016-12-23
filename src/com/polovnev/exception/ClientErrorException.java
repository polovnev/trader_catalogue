package com.polovnev.exception;

public class ClientErrorException extends HttpException {

	private static final long serialVersionUID = 1L;

	public ClientErrorException(int httpStatus) {
		super(httpStatus);
	}

	

}
