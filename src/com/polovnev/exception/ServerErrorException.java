package com.polovnev.exception;

public class ServerErrorException extends HttpException {

	private static final long serialVersionUID = 1L;

	public ServerErrorException(int httpStatus) {
		super(httpStatus);
	}

}
