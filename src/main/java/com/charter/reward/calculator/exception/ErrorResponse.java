package com.charter.reward.calculator.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {
	private Integer status;
	private String code;
	private String message;
}

