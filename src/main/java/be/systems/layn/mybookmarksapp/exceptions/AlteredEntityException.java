package be.systems.layn.mybookmarksapp.exceptions;

import org.springframework.http.HttpStatus;

public class AlteredEntityException extends DatabaseException {

	public AlteredEntityException(String message) {
		super(HttpStatus.PRECONDITION_FAILED, message);
	}
}
