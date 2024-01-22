package com.charter.reward.calculator.utils;

import static com.charter.reward.calculator.Constant.CUSTOM_FORMATTER;
import static com.charter.reward.calculator.Constant.DATE_FORMAT_STR;
import static com.charter.reward.calculator.Constant.TIME_ZONE_UTC;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.TimeZone;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import org.springframework.http.HttpStatus;

import com.charter.reward.calculator.exception.RewardException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateUtility {

	public static Date getDate(int monthCount){
		return Date.from(LocalDate.now().minusMonths(monthCount).atStartOfDay(ZoneOffset.UTC).toInstant());
	}

	public static Date formatDate(String dateStr) {
		try {
			DateFormat formatter = new SimpleDateFormat(DATE_FORMAT_STR);
			formatter.setTimeZone(TimeZone.getTimeZone(TIME_ZONE_UTC));
			return formatter.parse(dateStr);
		} catch (ParseException e) {
			throw new RewardException(HttpStatus.BAD_REQUEST, "Invalid Date Format");
		}
	}

	public static boolean isDateValidate(String date) {
		try {
			LocalDate.parse(date, CUSTOM_FORMATTER);
		} catch (DateTimeParseException e) {
			return false;
		}
		return true;
	}
}
