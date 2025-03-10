package be.systems.layn.mybookmarksapp.exceptions;

import org.springframework.http.HttpStatus;

public abstract class DatabaseException extends RuntimeException {

	private final HttpStatus statusCode;
	
	public DatabaseException(HttpStatus statusCode, String message) {
		super(message);
		this.statusCode = statusCode;
	}

	public int getHttpErrorCode() {
		return statusCode.value();
	}

	public String getHttpErrorMessage() {
		return statusCode.name();
	}
}
