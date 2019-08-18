package com.wctf.task.go.utils;

import com.google.gson.Gson;

public class JsonUtil {
	private static final Gson converter = new Gson();

	public static <T> T fromJson(String str, Class<T> cls) {
		return converter.fromJson(str, cls);
	}
}
