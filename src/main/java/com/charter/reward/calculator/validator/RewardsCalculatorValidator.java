package com.charter.reward.calculator.validator;

import org.springframework.http.HttpStatus;

import com.charter.reward.calculator.exception.RewardException;
import com.charter.reward.calculator.model.TransactionRequest;

public interface RewardsCalculatorValidator {

	void executeValidation(TransactionRequest request);

	/**
	 * Default validation method with a specified HTTP status.
	 *
	 * @param expression   The boolean expression to validate.
	 * @param errorMessage The error message if the validation fails.
	 * @param httpStatus   The HTTP status to set in case of validation failure.
	 */
	default void validate(boolean expression, String errorMessage, HttpStatus httpStatus) {
		if (!expression) {
			throw RewardException.newException(httpStatus, errorMessage);
		}
	}
}
