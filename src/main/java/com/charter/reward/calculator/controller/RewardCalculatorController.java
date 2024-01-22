package com.charter.reward.calculator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.charter.reward.calculator.model.RewardApiResponse;
import com.charter.reward.calculator.model.TransactionApiResponse;
import com.charter.reward.calculator.model.TransactionRequest;
import com.charter.reward.calculator.model.TransactionResponse;
import com.charter.reward.calculator.service.RewardCalculatorService;


@RestController
@Slf4j
@RequiredArgsConstructor
public class RewardCalculatorController {

	private final RewardCalculatorService rewardCalculatorService;

	@GetMapping("/getReward/{userId}")
	public ResponseEntity<RewardApiResponse> getRewardPoints(@PathVariable("userId") Long userId) {
		log.info("RewardCalculator:getRewardPoints for userId : {}", userId);

		return ResponseEntity.ok(RewardApiResponse.builder().reward(rewardCalculatorService.getRewardPoints(userId)).build());
	}

	@PostMapping("/createTransaction/{userId}")
	public ResponseEntity<TransactionApiResponse> createTransaction(@PathVariable("userId") Long userId,
																																		@RequestBody TransactionRequest request) {
		log.info("RewardCalculator:addTransaction for userId : {}", userId);
		TransactionResponse transaction = rewardCalculatorService.createTransaction(request, userId);
		return new ResponseEntity<>(TransactionApiResponse.builder().message(String.format("Transaction is created with amount : %s for user : %s", transaction.getAmount(), transaction.getUserId())).build(),
			HttpStatus.CREATED
		);
	}
}
