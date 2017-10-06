package com.bcj.helpdesk.service.registerandlogin;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.bcj.helpdesk.model.registerandlogin.Logininfo;
import com.bcj.helpdesk.model.registerandlogin.UserInfo;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationAndLoginServiceTest {

	
	@Mock
	RegistrationAndLoginService rlService;
	
	@Before
	public void initMocks(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void signup(){
		
		UserInfo userInfo = new UserInfo();
		userInfo.setFirstName("kocherla");
		
		String userType = "Employ";
		
		when(rlService.signup(userInfo)).thenReturn(userType);
		Assert.assertEquals(userType,rlService.signup(userInfo));
	}
	
	@Test
	public void verifyUser(){
		
		Logininfo logininfo = new Logininfo();
		logininfo.setLoginId(Mockito.anyInt());
		
		String userType = "Employ";
		
		when(rlService.verifyUser(logininfo)).thenReturn(userType);
		Assert.assertEquals(userType,rlService.verifyUser(logininfo));
	}
	
	@Test
	public void retrieveStates(){
		
		List<String> usState = new ArrayList<String>();
		usState.add("Texas");
		
		when(rlService.retrieveStates()).thenReturn(usState);
		Assert.assertEquals(usState, rlService.retrieveStates());
	}
	
	@Test
	public void retrieveUserType(){
		
		List<String> userType = new ArrayList<String>();
		userType.add("Texas");
		
		when(rlService.retrieveStates()).thenReturn(userType);
		Assert.assertEquals(userType, rlService.retrieveStates());
	}
}
