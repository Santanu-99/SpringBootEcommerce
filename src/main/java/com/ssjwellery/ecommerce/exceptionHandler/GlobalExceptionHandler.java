package com.ssjwellery.ecommerce.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
	public String exceptionHandlerGeneric(Model model) {
		System.out.println("Custom error handling in action===============================>");
		String errorStatusCode = "500";
		String errorDescription = "Internal Server Error";
		model.addAttribute("errorStatus", errorStatusCode );
		model.addAttribute("errorDescription", errorDescription );
		return "error";
	}
	
	
}
