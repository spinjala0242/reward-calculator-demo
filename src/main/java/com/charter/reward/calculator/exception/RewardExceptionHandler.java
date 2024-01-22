package com.charter.reward.calculator.exception;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.charter.reward.calculator.controller.RewardCalculatorController;

@Slf4j
@ControllerAdvice(assignableTypes = {RewardCalculatorController.class})
public class RewardExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(MissingRequestHeaderException.class)
	public ResponseEntity<ErrorResponse> handleMissingRequestHeaderException(
		Exception ex) {
		log.error("A MissingRequestHeaderException occurred : ", ex);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(ErrorResponse.<String>builder()
				.status(HttpStatus.BAD_REQUEST.value())
				.code(HttpStatus.BAD_REQUEST.name())
				.message(ex.getMessage())
				.build());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleThrowable(
		Exception ex) {
		log.error("A Exception occurred : ", ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body(ErrorResponse.<String>builder()
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.code(HttpStatus.INTERNAL_SERVER_ERROR.name())
				.message(ex.getMessage())
				.build());
	}

	@ExceptionHandler({RewardException.class})
	public ResponseEntity<ErrorResponse> handleRewardException(RewardException ex) {
		log.error("A RewardException occurred ", ex);
		return ResponseEntity.status(ex.getHttpStatus())
			.body(ErrorResponse.<String>builder()
				.status(ex.getHttpStatus().value())
				.code(ex.getHttpStatus().name())
				.message(ex.getMessage())
				.build());
	}
}
