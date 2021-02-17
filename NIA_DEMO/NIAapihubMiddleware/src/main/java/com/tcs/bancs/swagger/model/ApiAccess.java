package com.tcs.bancs.swagger.model;

import java.util.List;




public class ApiAccess {

	
	
	private String id;
   
	private String apiName;
	
	List<UserModel> userList;
   
    
	
	
	

	
	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	
	
	 public List<UserModel> getUserList() {
			return userList;
		}
	 
	 
	public void setUserList(List<UserModel> userList) {
			this.userList = userList;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
		
	

	
	
	
}
