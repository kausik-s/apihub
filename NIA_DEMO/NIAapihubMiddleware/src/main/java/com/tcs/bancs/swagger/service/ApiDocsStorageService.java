package com.tcs.bancs.swagger.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.tcs.bancs.swagger.exception.FileStorageException;
import com.tcs.bancs.swagger.exception.MyFileNotFoundException;
import com.tcs.bancs.swagger.model.ApiDocs;
import com.tcs.bancs.swagger.model.ResponseObject;
import com.tcs.bancs.swagger.model.UpdateApiAcessRequest;
import com.tcs.bancs.swagger.model.User;
import com.tcs.bancs.swagger.model.UserModel;
import com.tcs.bancs.swagger.repository.ApiDocsRepository;
import com.tcs.bancs.swagger.repository.UserRepository;

@Service
public class ApiDocsStorageService {
	
	
	 @Autowired
	  private UserRepository userRepository;
	
	@Autowired
    private ApiDocsRepository apiDocsRepository;
	
	public ApiDocs storeFile(MultipartFile file,String apiName) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            ApiDocs dbFile = new ApiDocs(fileName, file.getContentType(), file.getBytes(),apiName);

            return apiDocsRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
	
	
	public ApiDocs updateFile(MultipartFile file,String apiName,String id) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            ApiDocs dbFile = new ApiDocs(fileName, file.getContentType(), file.getBytes(),apiName,id);

            return apiDocsRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
	
	public List<ApiDocs> getApiList()
	{
		return apiDocsRepository.findAll();
               
	}
	
	public Optional<ApiDocs> getApiListById(String id)
	{
		return apiDocsRepository.findById(id);
               
	}

    public ApiDocs getFile(String fileId) {
        return apiDocsRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }
    
    public ResponseObject deleteApi(String id)
	{
    	ResponseObject respObj=new ResponseObject();
    	try
    	{
    		apiDocsRepository.deleteById(id);
    		respObj.setErrorCode("0");
    		respObj.setErrorMessage("Record deleted successfully");
    	}
    	catch(Exception ex)
    	{
    		respObj.setErrorCode("1");
    		respObj.setErrorMessage("Error in deletion"+ex.getMessage());
    		
    	}
		 
		
		return respObj;
               
	}
    
    public ResponseObject updateAccess(UpdateApiAcessRequest objUpdatedAccess)
	{
    	ResponseObject respObj=new ResponseObject();
    	try
    	{
    		ApiDocs apiObj=apiDocsRepository.findById(objUpdatedAccess.getId()).get();
    		
    		List<UserModel> currentAccessList=objUpdatedAccess.getUserList();
    		List<User> currentUserObjList=new ArrayList<User>();
    		for(int i=0;i<currentAccessList.size();i++)
    		{
    			User objUser=userRepository.findById(currentAccessList.get(i).getId()).get();
    			currentUserObjList.add(objUser);
    		}
    		
    		Set<User> objuser1=apiObj.getUsers();
    		List<User> list=new ArrayList<User>(apiObj.getUsers());
    		apiObj.getUsers().clear();
    		apiObj.getUsers().addAll(currentUserObjList);
    		apiObj.setUsers(apiObj.getUsers());
    		
    		List<User> list1=new ArrayList<User>(apiObj.getUsers());
    		
    		List<User> list2=new ArrayList<User>(apiObj.getUsers());
    		for(int k=0;k<list2.size();k++)
    		{
    			User objUser=list2.get(k);
    			//objUser.getApiDocs().clear();
    			objUser.getApiDocs().add(apiObj);
    			
    		}
    		
    		apiDocsRepository.save(apiObj);
    		
    		respObj.setErrorCode("0");
    		respObj.setErrorMessage("Access updated successfully");
    	}
    	catch(Exception ex)
    	{
    		respObj.setErrorCode("1");
    		respObj.setErrorMessage("Error in updating acccess"+ex.getMessage());
    		
    	}
		 
		
		return respObj;
               
	}
    

}
