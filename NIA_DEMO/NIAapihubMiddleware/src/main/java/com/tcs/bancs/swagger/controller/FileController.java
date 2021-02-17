package com.tcs.bancs.swagger.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tcs.bancs.swagger.model.ApiAccess;
import com.tcs.bancs.swagger.model.ApiAccessResponseModel;
import com.tcs.bancs.swagger.model.ApiDocs;
import com.tcs.bancs.swagger.model.ResponseObject;
import com.tcs.bancs.swagger.model.UpdateApiAcessRequest;
import com.tcs.bancs.swagger.model.UploadFileResponse;
import com.tcs.bancs.swagger.model.User;
import com.tcs.bancs.swagger.model.UserApiAccessByIdRequestModel;
import com.tcs.bancs.swagger.model.UserApiRequestModel;
import com.tcs.bancs.swagger.model.UserModel;
import com.tcs.bancs.swagger.service.ApiDocsStorageService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private ApiDocsStorageService apiDocsStorageService;
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("apiName") String apiName,@RequestParam("file") MultipartFile file) {
        ApiDocs dbFile = apiDocsStorageService.storeFile(file,apiName);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(dbFile.getId())
                .toUriString();

        return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/updateFile")
    public UploadFileResponse updateFile(@RequestParam("apiName") String apiName,@RequestParam("file") MultipartFile file,String id) {
        ApiDocs dbFile = apiDocsStorageService.updateFile(file, apiName, id);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(dbFile.getId())
                .toUriString();

        return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }
    
    
    
    
   @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/getApiList")
    public List<UploadFileResponse> getApiList() {
        List<ApiDocs> dbFiles = apiDocsStorageService.getApiList();
        
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
    @PostMapping("/getApiAccessList")
    public ApiAccessResponseModel getApiAccessList() {
        List<ApiDocs> dbFiles = apiDocsStorageService.getApiList();
        ResponseObject objResponse=new ResponseObject();
        List<ApiAccess> accessList=new ArrayList<ApiAccess>();
        ApiAccessResponseModel objResponseModel=new ApiAccessResponseModel();
       
        try
        {
        	
            for(int i=0;i<dbFiles.size();i++)
            {
            	ApiAccess objApiaccess=new ApiAccess();
            	ApiDocs apiObj=dbFiles.get(i);
            	List<User> list=new ArrayList<>(apiObj.getUsers());
            	List<UserModel> assignedUserList=new ArrayList<UserModel>();
            	for(int j=0;j<list.size();j++)
            	{
            		UserModel userModelObj=new UserModel();
            		userModelObj.setId(list.get(j).getId());
            		userModelObj.setItemName(list.get(j).getUserid());
            		assignedUserList.add(userModelObj);
            	}
            	objApiaccess.setUserList(assignedUserList);
            	objApiaccess.setId(apiObj.getId());
            	objApiaccess.setApiName(apiObj.getApiName());
            	
            	accessList.add(objApiaccess);
            	//apiObj.getU
            	 
            }
            objResponse.setErrorCode("0");
            objResponse.setErrorMessage("success");
            
        	
        }
        catch(Exception ex)
        {
        	objResponse.setErrorCode("0");
            objResponse.setErrorMessage(ex.getMessage());
            
        }
        
        
       objResponseModel.setApiAccessList(accessList);
        objResponseModel.setResponse(objResponse);
       
       /*
        return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
                */
        return objResponseModel;
    }
    
    
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/getApiAccessListById")
    public ApiAccessResponseModel getApiAccessListById(@RequestBody UserApiAccessByIdRequestModel apiDetails) {
        Optional<ApiDocs> dbFiles = apiDocsStorageService.getApiListById(apiDetails.getApiId());
        ResponseObject objResponse=new ResponseObject();
        List<ApiAccess> accessList=new ArrayList<ApiAccess>();
        ApiAccessResponseModel objResponseModel=new ApiAccessResponseModel();
       
        try
        {
        	
        	if(dbFiles.isPresent())
        	{
        		ApiAccess objApiaccess=new ApiAccess();
            	ApiDocs apiObj=dbFiles.get();
            	List<User> list=new ArrayList<>(apiObj.getUsers());
            	List<UserModel> assignedUserList=new ArrayList<UserModel>();
            	for(int j=0;j<list.size();j++)
            	{
            		UserModel userModelObj=new UserModel();
            		userModelObj.setId(list.get(j).getId());
            		userModelObj.setItemName(list.get(j).getUserid());
            		assignedUserList.add(userModelObj);
            	}
            	objApiaccess.setUserList(assignedUserList);
            	objApiaccess.setId(apiObj.getId());
            	objApiaccess.setApiName(apiObj.getApiName());
            	
            	accessList.add(objApiaccess);
            	  
                objResponse.setErrorCode("0");
                objResponse.setErrorMessage("success");
        		
        	}
        	else
        	{
        		objResponse.setErrorCode("1");
                objResponse.setErrorMessage("No data found");
        	}
        	
          
            
        	
        }
        catch(Exception ex)
        {
        	objResponse.setErrorCode("0");
            objResponse.setErrorMessage(ex.getMessage());
            
        }
        
        
       objResponseModel.setApiAccessList(accessList);
        objResponseModel.setResponse(objResponse);
       
       /*
        return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
                */
        return objResponseModel;
    }
    
    

    /*
    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }
    */
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
        // Load file from database
    	ApiDocs dbFile = apiDocsStorageService.getFile(fileId);
       
    	/*
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
                */
        
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
               
                .body(new ByteArrayResource(dbFile.getData()));
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
	 @PostMapping("/remove")
	 ResponseObject remove(@RequestBody ApiDocs apiObj) {
	    return apiDocsStorageService.deleteApi(apiObj.getId());
		
	  }
    
    
    
    @CrossOrigin(origins = "http://localhost:4200")
	 @PostMapping("/updateApiAccess")
	 ResponseObject updateApiAccess(@RequestBody UpdateApiAcessRequest accesObj) {
	    return apiDocsStorageService.updateAccess(accesObj);
		
    	
	  }
    
    

}