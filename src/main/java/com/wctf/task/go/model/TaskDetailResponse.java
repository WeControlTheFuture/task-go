package com.wctf.task.go.model;

public class TaskDetailResponse extends BaseResponse {
	private String userName;
	private String userPic;

	public String getUserPic() {
		return userPic;
	}

	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
