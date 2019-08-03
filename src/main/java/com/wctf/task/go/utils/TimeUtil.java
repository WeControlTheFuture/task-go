package com.wctf.task.go.utils;

import java.sql.Date;
import java.util.Calendar;

public class TimeUtil {
	public static final double DAYINMILLIS = 1000 * 60 * 60 * 24;

	public static int getLeftDays(Date date) {
		Calendar now = Calendar.getInstance();
		long dayNow = now.getTimeInMillis();
		Calendar input = Calendar.getInstance();
		input.setTime(date);
		input.set(Calendar.YEAR, input.get(Calendar.YEAR));
		long dayInput = input.getTimeInMillis();
		return (int) Math.ceil((dayInput - dayNow) / DAYINMILLIS);
	}

}
