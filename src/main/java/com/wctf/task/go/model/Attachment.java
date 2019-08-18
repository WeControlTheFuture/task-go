package com.wctf.task.go.model;

import java.sql.Timestamp;

import org.apache.commons.lang3.StringUtils;

import com.wctf.task.go.utils.JsonUtil;

public class Attachment {
	private User user;
	private Timestamp createTs;
	private TaskAttrType type;
	private FileInfo fileInfo;

	public Attachment() {

	}

	public Attachment(TaskAttr attr) {
		this.createTs = attr.getCreateTs();
		this.type = attr.getType();
		if (StringUtils.isNotEmpty(attr.getValue())) {
			this.fileInfo = JsonUtil.fromJson(attr.getValue(), FileInfo.class);
			this.fileInfo.setPic("icons/" + getFilepic(this.fileInfo.getFileName()));
		}
	}

	private String getFilepic(String filename) {
		int start_index = filename.lastIndexOf(".");
		String file_type = "";
		if (start_index != -1)
			file_type = filename.substring(start_index + 1, filename.length()).toLowerCase();
		if ("xls".equals(file_type) || "xlsx".equals(file_type))
			return "excel.jpg";
		else if ("ppt".equals(file_type))
			return "ppt.jpg";
		else if ("doc".equals(file_type) || "docx".equals(file_type))
			return "word.jpg";
		else if ("pdf".equals(file_type))
			return "pdf.jpg";
		else if ("zip".equals(file_type) || "rar".equals(file_type))
			return "zip.jpg";
		else
			return "unknown.jpg";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Timestamp getCreateTs() {
		return createTs;
	}

	public void setCreateTs(Timestamp createTs) {
		this.createTs = createTs;
	}

	public TaskAttrType getType() {
		return type;
	}

	public void setType(TaskAttrType type) {
		this.type = type;
	}

	public FileInfo getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(FileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}

	public static class FileInfo {
		private String fileName;
		private String filePath;
		private String pic;

		public String getPic() {
			return pic;
		}

		public void setPic(String pic) {
			this.pic = pic;
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public String getFilePath() {
			return filePath;
		}

		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}

	}
}
