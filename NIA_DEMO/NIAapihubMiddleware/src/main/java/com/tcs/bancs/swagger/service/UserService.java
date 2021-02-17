package com.tcs.bancs.swagger.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.bancs.swagger.model.ApiDocs;
import com.tcs.bancs.swagger.model.ResponseObject;
import com.tcs.bancs.swagger.model.User;
import com.tcs.bancs.swagger.model.UserApiRequestModel;
import com.tcs.bancs.swagger.model.UserApiResponseModel;
import com.tcs.bancs.swagger.model.UserListResponseModel;
import com.tcs.bancs.swagger.model.UserModel;
import com.tcs.bancs.swagger.repository.UserRepository;

@Service
public class UserService {
	  @Autowired
	  private UserRepository userRepository;
	  
	  public ResponseObject authenticate(User userCred) {

		  ResponseObject respObj=new ResponseObject();
		  List<User> userList=userRepository.findByUserid(userCred.getUserid());
		  //List<User> userList=(List<User>) userRepository.findAll();
		   
		   if(userList.size()>0)
		   {
			   User objUser=userList.get(0);
			   //System.out.println("size of the document"+objUser.getApiDocs().size());
			   
			   if(objUser.getPassword().equals(userCred.getPassword()))
			   {
				   respObj.setErrorCode("0");
		    	   respObj.setErrorMessage(objUser.getRole());
			   }
			   else
			   {
				   respObj.setErrorCode("1");
		    	   respObj.setErrorMessage("wrong username or password");
				   
			   }
		   }
		   else
		   {
			   respObj.setErrorCode("1");
	    	   respObj.setErrorMessage("user not found");
		   }
		  
	       return respObj;
	    }
	  
	  public UserApiResponseModel getUserApiDetails(UserApiRequestModel userObj) {
		  ResponseObject respObj=new ResponseObject();
		  UserApiResponseModel apiResponse=new UserApiResponseModel();
		  try
		  {
			  List<User> userList=userRepository.findByUserid(userObj.getUserid());
			  
			  if(userList.size()>0)
			   {
				   User objUser=userList.get(0);
				   System.out.println("size of the document"+objUser.getApiDocs().size());
				  
				   List<ApiDocs> list = new ArrayList<>(objUser.getApiDocs()); 
				   apiResponse.setApiList(list);
				   respObj.setErrorCode("0");
		    	   respObj.setErrorMessage("success");
				   
			   }
			   else
			   {
				   respObj.setErrorCode("1");
		    	   respObj.setErrorMessage("no api is assigned to the user");
			   }
			  
			  apiResponse.setResponse(respObj);
		       
			  
		  }
		  catch(Exception ex)
		  {
			  respObj.setErrorCode("1");
	    	  respObj.setErrorMessage(ex.getMessage());
	    	  apiResponse.setResponse(respObj);
		      
		  }
		 
		  return apiResponse;
		  
		
		  
	  }
	  
	  
	  public UserListResponseModel getUserList() {
		  ResponseObject respObj=new ResponseObject();
		  UserListResponseModel apiResponse=new UserListResponseModel();
		  try
		  {
			  List<User> userList=(List<User>) userRepository.findAll();
			  List<UserModel> list = new ArrayList<UserModel>(); 
			  if(userList.size()>0)
			   {
				  for(int i=0;i<userList.size();i++)
				  {
					  User objUser=userList.get(i);
					  
					  if(objUser.getRole().equalsIgnoreCase("USER"))
					  {
						  UserModel objUserModel=new UserModel();
						   objUserModel.setId(objUser.getId());
						   objUserModel.setItemName(objUser.getUserid());
						   list.add(objUserModel);
						  
					  }
					  
					 
				  }
				   
				   
				 
				  
				  
				  
				   respObj.setErrorCode("0");
		    	   respObj.setErrorMessage("success");
				   
			   }
			   else
			   {
				   respObj.setErrorCode("1");
		    	   respObj.setErrorMessage("no user found");
			   }
			  
			  apiResponse.setResponse(respObj);
			  apiResponse.setUserList(list);
		       
			  
		  }
		  catch(Exception ex)
		  {
			  respObj.setErrorCode("1");
	    	  respObj.setErrorMessage(ex.getMessage());
	    	  apiResponse.setResponse(respObj);
		      
		  }
		 
		  return apiResponse;
		  
		
		  
	  }


}
