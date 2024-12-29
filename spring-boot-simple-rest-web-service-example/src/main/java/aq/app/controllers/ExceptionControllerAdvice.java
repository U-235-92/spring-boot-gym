package aq.app.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import aq.app.exceptions.NotEnoughMoneyException;
import aq.app.models.ErrorDetails;

@RestControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(exception = NotEnoughMoneyException.class)
	public ResponseEntity<ErrorDetails> excpeptionNotEnoughMoneyHandler(NotEnoughMoneyException e) {
		ErrorDetails errorDetails = new ErrorDetails("Not enough money to make the payment.");
		return ResponseEntity
				.badRequest()
				.header("exception_class", e.getClass().getName())
				.body(errorDetails);
	}
}
