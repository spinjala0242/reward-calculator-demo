package com.charter.reward.calculator.exception;

import lombok.Getter;

import org.springframework.http.HttpStatus;

public class RewardException extends RuntimeException {
	@Getter
	private final HttpStatus httpStatus;
	@Getter
	private final String exceptionMessage;


	public RewardException(HttpStatus httpStatus, String errorMsg) {
		super(errorMsg);
		this.httpStatus = httpStatus;
		this.exceptionMessage = errorMsg;
	}

	public static RewardException newException(HttpStatus httpStatus, String errorMsg) {
		return new RewardException(httpStatus, errorMsg);
	}

}
