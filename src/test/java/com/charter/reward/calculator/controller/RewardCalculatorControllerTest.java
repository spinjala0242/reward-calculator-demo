package com.charter.reward.calculator.controller;

import com.charter.reward.calculator.exception.RewardException;
import com.charter.reward.calculator.model.RewardApiResponse;
import com.charter.reward.calculator.model.TransactionRequest;
import com.charter.reward.calculator.model.TransactionResponse;
import com.charter.reward.calculator.service.RewardCalculatorService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RewardCalculatorControllerTest {

	@InjectMocks
	private RewardCalculatorController rewardsCalculatorController;

	@Mock
	private RewardCalculatorService rewardCalculatorService;

	@Test
	public void test_getReward() {
		Map<String, Long> responseMap = new TreeMap<>();
		responseMap.put("month-1-reward-point", 100L);
		responseMap.put("month-2-reward-point", 200L);
		responseMap.put("month-3-reward-point", 300L);
		responseMap.put("totalRewardPoints", 600L);

		Mockito.when(rewardCalculatorService.getRewardPoints(Mockito.anyLong())).thenReturn(responseMap);
		ResponseEntity<RewardApiResponse> response = rewardsCalculatorController.getRewardPoints(1L);
		Assert.assertNotNull(response);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.assertEquals(Optional.of(600L), Optional.of(((TreeMap<String, Long>) response.getBody().getReward()).get("totalRewardPoints")));
		Assert.assertEquals(Optional.of(100L), Optional.of(((TreeMap<String, Long>) response.getBody().getReward()).get("month-1-reward-point")));
		Assert.assertEquals(Optional.of(200L), Optional.of(((TreeMap<String, Long>) response.getBody().getReward()).get("month-2-reward-point")));
		Assert.assertEquals(Optional.of(300L), Optional.of(((TreeMap<String, Long>) response.getBody().getReward()).get("month-3-reward-point")));
	}

	@Test(expected = RewardException.class)
	public void test_NoTransactionFound() throws RewardException {
		Mockito.when(rewardCalculatorService.getRewardPoints(Mockito.anyLong())).thenThrow(new RewardException(HttpStatus.NOT_FOUND, "no txn found"));
		rewardsCalculatorController.getRewardPoints(1L);
	}

	@Test
	public void test_addTransaction() {
		Mockito.when(rewardCalculatorService.createTransaction(Mockito.any(), Mockito.anyLong()))
			.thenReturn(TransactionResponse.builder()
				.amount(120.0)
				.userId(10L)
				.build());
		ResponseEntity response = rewardsCalculatorController.createTransaction(10L, TransactionRequest.builder().amount(120.0).date("08-15-2023").build());
		Assert.assertNotNull(response);
		Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		verify(rewardCalculatorService, times(1)).createTransaction(any(), anyLong());

	}

}
