package com.tcs.bancs.swagger.model;

import java.util.List;

public class UpdateApiAcessRequest {
	
	private String id;
	private List<UserModel> userList;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<UserModel> getUserList() {
		return userList;
	}
	public void setUserList(List<UserModel> userList) {
		this.userList = userList;
	}

}
