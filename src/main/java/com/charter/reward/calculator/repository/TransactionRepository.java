package com.charter.reward.calculator.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.charter.reward.calculator.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	List<Transaction> findAllByUserIdAndCreatedOnAfter(Long userId, Date date);
}
