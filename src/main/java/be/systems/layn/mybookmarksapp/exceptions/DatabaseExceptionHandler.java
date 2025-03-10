package be.systems.layn.mybookmarksapp.exceptions;

import be.systems.layn.mybookmarksapp.dto.ErrorView;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Clock;
import java.time.LocalDateTime;

@ControllerAdvice
public class DatabaseExceptionHandler extends ResponseEntityExceptionHandler {

	private final Clock clock;

	public DatabaseExceptionHandler(Clock clock) {
		this.clock = clock;
	}

	@ExceptionHandler(ConflictException.class)
	public ErrorView handleConflictException(HttpServletRequest req, ConflictException e) {
		return new ErrorView(LocalDateTime.now(clock), e.getHttpErrorCode(), e.getHttpErrorMessage(), e.getMessage(), req.getRequestURI(), null);
	}
}
