package com.tcs.bancs.swagger.model;

import java.util.List;

public class UserApiResponseModel {
	
	ResponseObject response;
	List<ApiDocs> apiList;
	
	public ResponseObject getResponse() {
		return response;
	}
	public void setResponse(ResponseObject response) {
		this.response = response;
	}
	
	public List<ApiDocs> getApiList() {
		return apiList;
	}
	
	public void setApiList(List<ApiDocs> apiList) {
		this.apiList = apiList;
	}
	

}
