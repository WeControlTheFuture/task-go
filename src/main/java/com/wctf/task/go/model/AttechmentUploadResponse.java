package com.wctf.task.go.model;

import java.sql.Timestamp;

public class AttechmentUploadResponse extends BaseResponse {
	private String userName;
	private String createTs;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCreateTs() {
		return createTs;
	}

	public void setCreateTs(String createTs) {
		this.createTs = createTs;
	}

}
