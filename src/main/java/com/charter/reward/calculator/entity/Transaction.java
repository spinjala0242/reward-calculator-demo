package com.charter.reward.calculator.entity;

import static com.charter.reward.calculator.Constant.DATE_FORMAT_STR;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TRANSACTIONS")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long Id;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "amount")
	private Double amount;

	@Column(name = "created_on")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = DATE_FORMAT_STR)
	private Date createdOn;
}
