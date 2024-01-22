package com.charter.reward.calculator.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RewardPointUtility {

	public static long getRewardPoints(Double amt, Long onePointRange, Long twoPointRange) {
		long amount = Math.round(amt);
		long rewardPoints = 0L;
		if (amount > twoPointRange) {
			rewardPoints = ((amount - onePointRange) - (amount - twoPointRange)) + ((amount - twoPointRange) * 2);
		} else if (amount > onePointRange) {
			rewardPoints = (amount - onePointRange);
		}
		return rewardPoints;
	}
}
