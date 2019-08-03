package com.wctf.task.go.model;

import java.io.Serializable;

public class OrgMemberInfo implements Serializable {
	private String name;
	private String code;
	private String headpic;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getHeadpic() {
		return headpic;
	}

	public void setHeadpic(String headpic) {
		this.headpic = headpic;
	}

}
