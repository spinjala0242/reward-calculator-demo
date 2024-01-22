package com.charter.reward.calculator.service;

import java.util.Map;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.charter.reward.calculator.exception.RewardException;
import com.charter.reward.calculator.model.TransactionRequest;
import com.charter.reward.calculator.model.TransactionResponse;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application.yaml")
public class RewardsCalculatorServiceTest {

    @Autowired
    RewardCalculatorService service;

    @Test(expected = RewardException.class)
    public void test_createTransactionWithInvalidDAte() {
        TransactionRequest txn1 = TransactionRequest.builder()
                .amount(110.00)
                .date("01-152024")
                .build();
        service.createTransaction(txn1, 3L);
    }

    @Test(expected = RewardException.class)
    public void test_createTransactionWithoutAmount() {
        TransactionRequest txn1 = TransactionRequest.builder()
                .date("01-152024")
                .build();
        service.createTransaction(txn1, 3L);
    }

    @Test(expected = RewardException.class)
    public void test_createTransactionWithoutDate() {
        TransactionRequest txn1 = TransactionRequest.builder()
                .amount(110.00)
                .build();
        service.createTransaction(txn1, 3L);
    }

    @Test
    public void test_createTransaction() {
        TransactionRequest txn1 = TransactionRequest.builder()
                .amount(110.00)
                .date("01-15-2024")
                .build();
        TransactionResponse response = service.createTransaction(txn1, 101L);
        Assert.assertNotNull(response);
        Assert.assertEquals(Optional.of(response.getAmount()), Optional.of(110.0));
        Assert.assertEquals(Optional.of(response.getUserId()), Optional.of(101L));
    }

    @Test(expected = RewardException.class)
    public void test_noTransactionsFoundException() {
        service.getRewardPoints(21L);
    }

    @Test
    public void test_getRewardPoint() {
        TransactionRequest txn1 = TransactionRequest.builder()
                .amount(110.00)
                .date("01-15-2024")
                .build();
        TransactionRequest txn2 = TransactionRequest.builder()
                .amount(75.00)
                .date("01-15-2024")
                .build();
        TransactionRequest txn3 = TransactionRequest.builder()
                .amount(45.00)
                .date("01-15-2024")
                .build();
        service.createTransaction(txn1, 10L);
        service.createTransaction(txn2, 10L);
        service.createTransaction(txn3, 10L);
        Map<String, Long> response = service.getRewardPoints(10L);
        Assert.assertNotNull(response);
        Assert.assertEquals(Optional.of(95L), Optional.of(response.get("totalRewardPoints")));
        Assert.assertEquals(Optional.of(95L), Optional.of(response.get("month-1-reward-point")));
        Assert.assertEquals(Optional.of(0L), Optional.of(response.get("month-2-reward-point")));
        Assert.assertEquals(Optional.of(0L), Optional.of(response.get("month-3-reward-point")));
    }
}