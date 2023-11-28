package in.ashokit.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PhoneBookExceptionHandler {
	
	@ExceptionHandler(value=PhoneBookException.class)
	public String handlePhoneBookException() {
		
		return "error";
	}

}
