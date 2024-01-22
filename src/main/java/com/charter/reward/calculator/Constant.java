package com.charter.reward.calculator;

import java.time.format.DateTimeFormatter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constant {
	public final static String DATE_FORMAT_STR = "MM-dd-yyyy";
	public final static DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT_STR);
	public final static String TIME_ZONE_UTC = "UTC";
}
