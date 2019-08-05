package com.wctf.task.go.utils;

import java.io.File;
import java.util.UUID;

public class FileUtil {

	public static File getMonthlyPathFile(String basePath, String fileName) {
		String path = basePath + File.separator + TimeUtil.getYYYYMM() + File.separator;
		File directory = new File(path);
		if (!directory.exists())
			directory.mkdirs();
		String fileNameUUid = UUID.randomUUID().toString();
		return new File(path + fileNameUUid + "." + fileName);
	}
}
