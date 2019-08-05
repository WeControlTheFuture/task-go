package com.wctf.task.go.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeUtil {
	public static final double DAYINMILLIS = 1000 * 60 * 60 * 24;
	private static final SimpleDateFormat yyyyMM = new SimpleDateFormat("yyyyMM");
	private static final SimpleDateFormat yyyyMMddHHmmSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String yyyyMMddHHmmSSFormatTs(Timestamp ts) {
		return yyyyMMddHHmmSS.format(ts);
	}
	
	public static String getYYYYMM() {
		return yyyyMM.format(new Date(System.currentTimeMillis()));
	}

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
