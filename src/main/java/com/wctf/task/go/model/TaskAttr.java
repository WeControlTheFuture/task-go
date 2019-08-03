package com.wctf.task.go.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class TaskAttr implements Serializable {
	private Integer taskId;
	private Timestamp createTs;
	private TaskAttrType type;
	private String value;
	private String userCode;

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public TaskAttrType getType() {
		return type;
	}

	public void setType(TaskAttrType type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Timestamp getCreateTs() {
		return createTs;
	}

	public void setCreateTs(Timestamp createTs) {
		this.createTs = createTs;
	}

}
