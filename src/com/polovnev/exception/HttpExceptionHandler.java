package com.polovnev.exception;

public class HttpExceptionHandler {
	
	public void handle(HttpException httpException) {
		if(httpException instanceof ClientErrorException) {
			System.out.println("Client Error");
			if(httpException.getHttpStatus() == 405) {
				System.out.println("405 Method Not Allowed");
			}
		}
		
		if(httpException instanceof ServerErrorException) {
			System.out.println("Server Error");
			if(httpException.getHttpStatus() == 500) {
				System.out.println("500 Internal Server Error");
			}
			
		}
	}

}
