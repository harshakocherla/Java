package com.bcj.helpdesk.controller.registrationandlogin;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bcj.helpdesk.model.registerandlogin.Logininfo;
import com.bcj.helpdesk.model.registerandlogin.UserInfo;
import com.bcj.helpdesk.service.registerandlogin.RegistrationAndLoginService;

/**
 * @author Harsha Kocherla
 * 
 * This is a Controller class which has different methods that return appropriate jsp pages for a valid request. 
 */
@Controller
public class HomeController {

	@Autowired
	private RegistrationAndLoginService rlService;
	
	private static final Logger logger = Logger.getLogger(HomeController.class);
	
	

	/**
	 * @return ModelAndView object
	 * 
	 * For a /home request this method returns home.jsp through view resolver by adding two ArrayList objects to the page.
	 * 
	 */
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView loadHomePage() {
		
		List<String> userType = rlService.retrieveUserTypes();
		List<String> usStates = rlService.retrieveStates();
		
		ModelAndView mav = new ModelAndView("home");
		
		mav.addObject("usStates", usStates);
		mav.addObject("userType", userType);
		
		return mav;
	}

	
	
	/**
	 * @param userInfo
	 * @param model
	 * @return a String
	 * 
	 *  for /signup request this method returns home.jsp page through view resolver after saving the user details into the data base
	 *  by calling signup() of RegistrationAndLoginService.
	 *  
	 */
	
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView signup(UserInfo userInfo, Model model) {
		
		List<String> userTypes = rlService.retrieveUserTypes();
		List<String> usStates = rlService.retrieveStates();
		
		ModelAndView mav = null;
				
		if(rlService.signup(userInfo)==null){
			
			mav = new ModelAndView("signuperror");
			
			mav.addObject("usStates", usStates);
			mav.addObject("userType", userTypes);
			
			mav.addObject("message", "UserInfo already exists!");
			
		return mav;
		
		}else{
			
			mav = new ModelAndView("home");
			
			mav.addObject("usStates", usStates);
			mav.addObject("userType", userTypes);
			
			return mav;
		}
	}

	
	
	/**
	 * @param logininfo
	 * @param model
	 * @param session
	 * @return {@link ModelAndView} object
	 * 
	 * for a /login request this method returns login.jsp page through view resolver based on the user type of the user.
	 * verifyUser() method of RegistrationAndLoginService is called to verify login details and to get user type.
	 * 
	 *  {@link HttpSession} is used to set three session attributes like loginId, username and userType.
	 */
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(Logininfo logininfo, Model model, HttpSession session) {
		
		ModelAndView mav = null;
		String userType = rlService.verifyUser(logininfo);
				
		session.setAttribute("loginId", logininfo.getLoginId());
		session.setAttribute("username", logininfo.getUsername());
		session.setAttribute("userType", userType);
		
		String user = (String) session.getAttribute("username");
		
		if ("student".equals(userType)||"consultant".equals(userType)) {
			
			
			logger.info(user+" at login");
			logger.info("session iD : "+ session.getId());
			logger.info("Session loginId at controller: "+ session.getAttribute("loginId"));
			
			mav = new ModelAndView("login");
			mav.addObject("message", "welcome "+logininfo.getUsername());

			return mav;
			
		}else if("employ".equals(userType)){
			
			logger.info("Session username at home controller"+user);
			logger.info("Session loginId at controller: "+ session.getAttribute("loginId"));
			
			mav = new ModelAndView("employlogin");
			mav.addObject("message", "welcome "+logininfo.getUsername());
			
			return mav;
			
		}else{
			
			mav = new ModelAndView("error");
			mav.addObject("message", "Please enter valid username and password!");
			
			return mav;
	}

	}
	
	
	
	/**
	 * @param session
	 * @return {@link ModelAndView} object
	 * 
	 * for /logout request this method invalidates the session that is start at the time of login.
	 * 
	 * 
	 */
	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpSession session) {
		
		List<String> userType = rlService.retrieveUserTypes();
		List<String> usStates = rlService.retrieveStates();
		
		ModelAndView mav = new ModelAndView("home");
		
		mav.addObject("usStates", usStates);
		mav.addObject("userType", userType);
		
		session.invalidate();
		
		return mav;
	}
	}
