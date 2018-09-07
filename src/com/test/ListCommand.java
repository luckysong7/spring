package com.test;

public class ListCommand {
	// DTO 역할
	// Spring에서는 Command 라고 만든다 !!
	public String userId;
	public String userName;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
