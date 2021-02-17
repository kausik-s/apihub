package com.tcs.bancs.swagger.model;

import java.util.List;

public class ApiAccessResponseModel {
	
	
	List<ApiAccess> apiAccessList;
	
	ResponseObject response;

	public List<ApiAccess> getApiAccessList() {
		return apiAccessList;
	}

	public void setApiAccessList(List<ApiAccess> apiAccessList) {
		this.apiAccessList = apiAccessList;
	}

	public ResponseObject getResponse() {
		return response;
	}

	public void setResponse(ResponseObject response) {
		this.response = response;
	}
	

}
