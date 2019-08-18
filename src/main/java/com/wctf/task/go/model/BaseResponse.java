package com.wctf.task.go.model;

import java.io.Serializable;

public class BaseResponse implements Serializable {
	private String msg;
	private int code;
	private String createTs;

	public BaseResponse() {

	}

	public String getCreateTs() {
		return createTs;
	}

	public void setCreateTs(String createTs) {
		this.createTs = createTs;
	}

	public BaseResponse(String msg) {
		this.msg = msg;
	}

	public BaseResponse(String msg, int code) {
		this.msg = msg;
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
