package com.charter.reward.calculator.validator;

import static com.charter.reward.calculator.Constant.DATE_FORMAT_STR;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.charter.reward.calculator.model.TransactionRequest;
import com.charter.reward.calculator.utils.DateUtility;

@Component
public class RewardsCalculatorValidatorImpl implements RewardsCalculatorValidator {

	public void executeValidation(TransactionRequest request) {
		validate(Objects.nonNull(request.getAmount()), "amount is mandatory", HttpStatus.BAD_REQUEST);
		validate(Objects.nonNull(request.getDate()), "date is mandatory", HttpStatus.BAD_REQUEST);
		validate(DateUtility.isDateValidate(request.getDate()), "date format is invalid, only supported " + DATE_FORMAT_STR, HttpStatus.BAD_REQUEST);

	}
}
