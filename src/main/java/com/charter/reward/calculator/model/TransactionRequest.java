package com.charter.reward.calculator.model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionRequest implements Serializable {

	private Double amount;
	private String date;
}

