package be.systems.layn.mybookmarksapp.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

public class ConflictException extends DatabaseException {

	public ConflictException(String message) {
		super(HttpStatus.CONFLICT, message);
	}
}
