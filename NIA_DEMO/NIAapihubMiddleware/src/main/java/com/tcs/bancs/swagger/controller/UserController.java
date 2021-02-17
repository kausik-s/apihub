package com.tcs.bancs.swagger.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tcs.bancs.swagger.model.ApiDocs;
import com.tcs.bancs.swagger.model.ResponseObject;
import com.tcs.bancs.swagger.model.UploadFileResponse;
import com.tcs.bancs.swagger.model.User;
import com.tcs.bancs.swagger.model.UserApiRequestModel;
import com.tcs.bancs.swagger.model.UserApiResponseModel;
import com.tcs.bancs.swagger.model.UserListResponseModel;
import com.tcs.bancs.swagger.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	@CrossOrigin(origins = "http://localhost:4200")
	 @PostMapping("/login")
	 ResponseObject login(@RequestBody User user) {
	    return userService.authenticate(user);
		
	  }
	
	@CrossOrigin(origins = "http://localhost:4200")
	 @PostMapping("/getUserApiList")
	List<UploadFileResponse> getUserApiList(@RequestBody UserApiRequestModel userObj) {
		
		 List<ApiDocs> dbFiles = userService.getUserApiDetails(userObj).getApiList();
	    // userService.getUserApiDetails(userObj);
	    
	    List<UploadFileResponse> fileList=new ArrayList<UploadFileResponse>();
        for(int i=0;i<dbFiles.size();i++)
        {
        	ApiDocs apiObj=dbFiles.get(i);
        	 String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                     .path("/downloadFile/")
                     .path(apiObj.getId())
                     .toUriString();
        	UploadFileResponse objResponse=new UploadFileResponse(apiObj.getFileName(), fileDownloadUri,
                    apiObj.getFileType(),apiObj.getApiName(),apiObj.getId());
        	fileList.add(objResponse);
        }
       
       /*
        return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
                */
        return fileList;
		
	  }
	
	 
	@CrossOrigin(origins = "http://localhost:4200")
	 @PostMapping("/getAllUserList")
	UserListResponseModel getAllUserList() {
	    return userService.getUserList();
		
	  }


	
}
