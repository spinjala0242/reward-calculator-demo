package com.charter.reward.calculator.model;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionResponse {

	private Long userId;
	private Double amount;
	private Date createdOn;
}
