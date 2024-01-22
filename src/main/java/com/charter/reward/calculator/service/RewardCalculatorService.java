package com.charter.reward.calculator.service;

import java.util.Map;

import com.charter.reward.calculator.exception.RewardException;
import com.charter.reward.calculator.model.TransactionRequest;
import com.charter.reward.calculator.model.TransactionResponse;

public interface RewardCalculatorService {
	Map<String, Long> getRewardPoints(Long userId);

	TransactionResponse createTransaction(TransactionRequest request, Long userId) throws RewardException;
}
