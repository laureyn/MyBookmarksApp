//package be.systems.layn.mybookmarksapp.exceptions;
//
//import be.systems.layn.mybookmarksapp.dto.ErrorView;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.time.Clock;
//import java.time.LocalDateTime;
//
//@ControllerAdvice
//class GlobalDefaultExceptionHandler {
//
//	private final Clock clock;
//
//	GlobalDefaultExceptionHandler(Clock clock) {
//		this.clock = clock;
//	}
//
//	@ExceptionHandler(value = Exception.class)
//	public ErrorView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
//		return new ErrorView(LocalDateTime.now(clock), HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(), e.getMessage(), req.getRequestURI(), null);
//	}
//}
