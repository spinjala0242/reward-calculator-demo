package com.charter.reward.calculator.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.charter.reward.calculator.utils.RewardPointUtility;
import org.junit.jupiter.api.Test;

class RewardPointUtilityTest {

    @Test
    void testGetRewardPointsWithZeroAmount() {
        long rewardPoints = RewardPointUtility.getRewardPoints(0.0, 50L, 100L);
        assertEquals(0L, rewardPoints);
    }

    @Test
    void testGetRewardPointsWithinOnePointRange() {
        long rewardPoints = RewardPointUtility.getRewardPoints(75.0, 100L, 200L);
        assertEquals(0L, rewardPoints);
    }

    @Test
    void testGetRewardPointsWithinTwoPointRange() {
        long rewardPoints = RewardPointUtility.getRewardPoints(120.0, 100L, 150L);
        assertEquals(20L, rewardPoints);
    }

    @Test
    void testGetRewardPointsAboveTwoPointRange() {
        long rewardPoints = RewardPointUtility.getRewardPoints(160.0, 100L, 150L);
        assertEquals(70L, rewardPoints);
    }

    @Test
    void testGetRewardPointsWithNegativeAmount() {
        long rewardPoints = RewardPointUtility.getRewardPoints(-50.0, 100L, 150L);
        assertEquals(0L, rewardPoints);
    }
}
