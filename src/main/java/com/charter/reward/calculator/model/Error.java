package com.charter.reward.calculator.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Error {

	private Integer status;
	private String code;
	private String message;
}

