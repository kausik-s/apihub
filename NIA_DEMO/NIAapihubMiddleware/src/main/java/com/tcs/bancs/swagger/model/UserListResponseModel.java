package com.tcs.bancs.swagger.model;

import java.util.List;

public class UserListResponseModel {
	
	
	ResponseObject response;
	List<UserModel> userList;
	
	public ResponseObject getResponse() {
		return response;
	}
	public void setResponse(ResponseObject response) {
		this.response = response;
	}
	public List<UserModel> getUserList() {
		return userList;
	}
	public void setUserList(List<UserModel> userList) {
		this.userList = userList;
	}
	
	
}
