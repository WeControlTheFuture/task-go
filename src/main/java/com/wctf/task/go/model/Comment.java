package com.wctf.task.go.model;

import java.sql.Timestamp;

public class Comment {
	private User user;
	private Timestamp createTs;
	private TaskAttrType type;
	private String value;

	public Comment() {
	}
	
	public Comment(TaskAttr attr) {
		this.createTs = attr.getCreateTs();
		this.type = attr.getType();
		this.value = attr.getValue();
	}

	public Timestamp getCreateTs() {
		return createTs;
	}

	public void setCreateTs(Timestamp createTs) {
		this.createTs = createTs;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public TaskAttrType getType() {
		return type;
	}

	public void setType(TaskAttrType type) {
		this.type = type;
	}

}
