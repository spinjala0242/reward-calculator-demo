package com.charter.reward.calculator.service;

import static com.charter.reward.calculator.utils.DateUtility.formatDate;
import static com.charter.reward.calculator.utils.DateUtility.getDate;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.charter.reward.calculator.entity.Transaction;
import com.charter.reward.calculator.exception.RewardException;
import com.charter.reward.calculator.mapper.TransactionMapper;
import com.charter.reward.calculator.model.TransactionRequest;
import com.charter.reward.calculator.model.TransactionResponse;
import com.charter.reward.calculator.repository.TransactionRepository;
import com.charter.reward.calculator.utils.RewardPointUtility;
import com.charter.reward.calculator.validator.RewardsCalculatorValidator;

@Service
@Slf4j
@RequiredArgsConstructor
public class RewardCalculatorServiceImpl implements RewardCalculatorService {

	@Value("${rewardPoint.onePointRange}")
	private Long onePointRange;

	@Value("${rewardPoint.twoPointsRange}")
	private Long twoPointRange;

	@Value("${rewardPoint.monthCount}")
	private Long monthCount;

	private final TransactionRepository transactionsRepository;
	private final RewardsCalculatorValidator rewardsCalculatorValidator;
	private final TransactionMapper transactionMapper;

	@Override
	public Map<String, Long> getRewardPoints(Long userId) {
		Date dateOffset = Date.from(LocalDate.now().minusMonths(monthCount).atStartOfDay(ZoneOffset.UTC).toInstant());
		List<Transaction> transactions = transactionsRepository.findAllByUserIdAndCreatedOnAfter(userId, dateOffset);

		if (transactions.isEmpty()) {
			throw new RewardException(HttpStatus.NOT_FOUND, String.format("No transactions found on userId : %s in last %s months", userId, monthCount));
		}
		Map<String, Long> rewardResponse = new TreeMap<>();
		for (int i = 1; i <= monthCount; i++) {
			Long rewardPoint = getRewardPointsByMonth(transactions, i);
			rewardResponse.put("month-" + i + "-reward-point", rewardPoint);

		}
		rewardResponse.put("totalRewardPoints", rewardResponse.values().stream().mapToLong(Long::longValue).sum());
		return rewardResponse;
	}


	private Long getRewardPointsByMonth(List<Transaction> transactions, int monthCount) {
		Date startDate = getDate(monthCount);
		Date endDate = getDate(monthCount - 1);
		return calculateRewardPoints(
			transactions.stream()
				.filter(tx -> tx.getCreatedOn().compareTo(startDate) > 0 && tx.getCreatedOn().compareTo(endDate) <= 0)
				.collect(Collectors.toList())
		);
	}

	public long calculateRewardPoints(List<Transaction> transactions) {
		return transactions.stream()
			.mapToLong(tx -> RewardPointUtility.getRewardPoints(tx.getAmount(), onePointRange, twoPointRange))
			.sum();
	}

	@Override
	public TransactionResponse createTransaction(TransactionRequest request, Long userId) {
		rewardsCalculatorValidator.executeValidation(request);
		return transactionMapper.toTransactionResponse(
			transactionsRepository.save(
				Transaction.builder()
					.userId(userId)
					.amount(request.getAmount())
					.createdOn(formatDate(request.getDate()))
					.build()
			)
		);
	}
}
