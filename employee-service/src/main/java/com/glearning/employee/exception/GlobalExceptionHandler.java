package com.glearning.employee.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@RestControllerAdvice
@Component
public class GlobalExceptionHandler {

	// Apart from creating an exception handler class we are also modifying the HTTP
	// error code
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Error handleInvalidStudentId(IllegalArgumentException exception) {
		Error error = Error.builder()
				.code(100)
				.message(exception.getMessage())
				.build();
		return error;
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	@ResponseStatus(HttpStatus.GONE)
	public Error handleEmptyDataAccessException(EmptyResultDataAccessException exception) {
		Error error = Error.builder()
				.code(100)
				.message(exception.getMessage())
				.build();
		return error;
	}
}

@Builder
@Data
@AllArgsConstructor
class Error {
	int code;
	String message;
}
