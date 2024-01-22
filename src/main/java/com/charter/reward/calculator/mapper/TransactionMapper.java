package com.charter.reward.calculator.mapper;

import org.mapstruct.Mapper;

import com.charter.reward.calculator.entity.Transaction;
import com.charter.reward.calculator.model.TransactionResponse;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

	TransactionResponse toTransactionResponse(Transaction transaction);
}
