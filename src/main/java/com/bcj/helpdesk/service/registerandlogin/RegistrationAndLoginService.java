package com.bcj.helpdesk.service.registerandlogin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcj.helpdesk.dao.registerandlogin.RegistrationAndLoginDaoImpl;
import com.bcj.helpdesk.model.registerandlogin.Logininfo;
import com.bcj.helpdesk.model.registerandlogin.UserInfo;


/**
 * @author Harsha Kocherla
 * 
 * This is a Service class for the RegistrationAndLogin functionality, which has all the business logic like 
 * storing the user details in data base when a new user is signed up.
 * 
 * This class also has a method to validate user when he tries to login.
 * 
 * 
 */


@Service
public class RegistrationAndLoginService {

	@Autowired
	private RegistrationAndLoginDaoImpl rlDaoImpl;
	
	
	/**
	 * @param userInfo
	 * @return String
	 * 
	 * This method calls the signup() method of RegistrationAndLoginDaoImpl to save the new user details in data base.
	 * when the user details are saved successfully then the method returns userType.
	 * 
	 * 
	 */
	
	
	public String signup(UserInfo userInfo){
		
		if(rlDaoImpl.signup(userInfo) != null){
			
			return userInfo.getUserType();
			
		}else{
			
			return null;
		}
	}
	
	
	
	/**
	 * @param logininfo
	 * @return String
	 * 
	 * This method calls verifyUser() method of RegistrationAndLoginDaoImpl class to validate the login details 
	 * based on the details present in data base.
	 * 
	 * 
	 */
	
	
	public String verifyUser(Logininfo logininfo){
		
		if(rlDaoImpl.verifyUser(logininfo) != null){
			
			return rlDaoImpl.verifyUser(logininfo);
			
		}else{
			
			return null;
		}
	}
	
	
	/**
	 * @return List<String>
	 * 
	 * This method returns a List of Strings by calling retrieveStates() of RegistrationAndLoginDaoImpl class 
	 * 
	 */
	
	
	public List<String> retrieveStates(){
		
		return rlDaoImpl.retrieveStates();
		
	}
	
	
	/**
	 * @return List<String>
	 * 
	 * This method returns a List of Strings by calling retrieveUserType() of RegistrationAndLoginDaoImpl class
	 * 
	 *  
	 */
	
	
	public List<String> retrieveUserTypes(){
		
		return rlDaoImpl.retrieveUserTypes();
		
	}
}
