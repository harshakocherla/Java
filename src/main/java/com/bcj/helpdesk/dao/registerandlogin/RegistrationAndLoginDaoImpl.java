package com.bcj.helpdesk.dao.registerandlogin;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bcj.helpdesk.model.registerandlogin.Logininfo;
import com.bcj.helpdesk.model.registerandlogin.UsStates;
import com.bcj.helpdesk.model.registerandlogin.UserInfo;
import com.bcj.helpdesk.model.registerandlogin.UserType;
import com.mysql.jdbc.Connection;


/**
 * @author Harsha Kocherla
 * 
 * This is a DAO class which has the connections with the Data Base to collect the required information from the database 
 * using JdbcTemplate.
 * 
 * 
 */


@Repository
public class RegistrationAndLoginDaoImpl {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	JdbcOperations jdbcOperations;
	Connection connection = null;

	private static final Logger logger = Logger.getLogger(RegistrationAndLoginDaoImpl.class);
	
	
	/**
	 * @param userInfo
	 * @return userType
	 * 
	 * This method consists of sql queries to insert the user details into user table and also to retreive userType from user table
	 * 
	 */
	
	
	public String signup(UserInfo userInfo) {
		
		String query = "insert into user (firstName, lastName, email, phone, userType) values (?,?,?,?,?)";
		
		
		try{
			
			jdbcOperations.update(query, new Object[] { userInfo.getFirstName(), userInfo.getLastName(), userInfo.getEmail(),
					userInfo.getPhone(), userInfo.getUserType() });
		}catch(DuplicateKeyException e){
			
			logger.info("user already exist!!");
			return null;
			
		}


			String query1 = "select userId from user where firstName=?&&lastName=?&&email=?&&phone=?&&userType=?";

			int userId = jdbcTemplate.queryForObject(query1, new Object[] { userInfo.getFirstName(), userInfo.getLastName(),
					userInfo.getEmail(), userInfo.getPhone(), userInfo.getUserType() }, Integer.class);

			
			if (0 != userId) {

				String query2 = "insert into address (addressLine1, addressLine2, city, state, zip, user_userId) values(?,?,?,?,?,?)";

				if (0 != jdbcOperations.update(query2,
						new Object[] { userInfo.getAddress().getAddressLine1(), userInfo.getAddress().getAddressLine2(),
								userInfo.getAddress().getCity(), userInfo.getAddress().getState(), userInfo.getAddress().getZip(),
								userId })) {

					String query3 = "insert into login (username, password, user_userId) values(?,?,?)";
					
					
					try{
						
						jdbcOperations.update(query3, new Object[] { userInfo.getLogin().getUsername(),
								userInfo.getLogin().getPassword(), userId });
						
					}catch(DuplicateKeyException e){
			
						return null;
					
					}
					

					String query5 = "select loginId from login where user_userID=?";
					int loginId = jdbcTemplate.queryForObject(query5, new Object[]{userId}, Integer.class);
					
					logger.info("loginid at userDao from db : "+ loginId);
					userInfo.getLogin().setLoginId(loginId);
					logger.info("logind id through user :"+ userInfo.getLogin().getLoginId());
					
						return userInfo.getUserType();
					
					} else {
					
						return null;
					}
				}

			return userInfo.getUserType();


	}

	
	/**
	 * @return usStates
	 * 
	 * This method consist of a sql query to retreive all the states from the state table in database.
	 * 
	 */
	
	
	public List<String> retrieveStates() {
		
		String query = "select * from state";

		List<UsStates> states = jdbcTemplate.query(query, new BeanPropertyRowMapper<UsStates>(UsStates.class));
		List<String> usStates = new ArrayList<String>();

		for (UsStates state : states) {
			
			usStates.add(state.getName());
			
		}
		
		return usStates;

	}

	
	
	/**
	 * @return userType
	 * 
	 * This method is written to retrieve all the different UserInfo types so as to populate the form data of the signup form 
	 * 
	 * 
	 */
	
	
	public List<String> retrieveUserTypes() {
		String query = "select * from usertype";

		List<UserType> usertypes = jdbcTemplate.query(query, new BeanPropertyRowMapper<UserType>(UserType.class));
		List<String> userTypes = new ArrayList<String>();
		
		for (UserType user : usertypes) {
			
			userTypes.add(user.getUserType());
			
		}
		
		for (String s : userTypes) {
			
			logger.info(s + " at dao");
		}

		return userTypes;

	}

	
	/**
	 * @param logininfo
	 * @return userType
	 * 
	 * A query is written to validate login details based userId
	 * 
	 * 
	 */
	
	public String verifyUser(Logininfo logininfo) {
		
		String query = "select user_userId from login where username=?&&password=?";
		
		int userId = 0;
		
		try {
			
			userId = jdbcTemplate.queryForObject(query, new Object[] { logininfo.getUsername(), logininfo.getPassword() },
					Integer.class);
			
		} catch (EmptyResultDataAccessException e) {
			
			logger.info("wrong login");
			return null;
		}
		
		logger.info(userId);
		
		if (0 != userId) {
			
			String query1 = "select userType from user where userId=?";
			
			String userType = (String)jdbcTemplate.queryForObject(query1, new Object[]{userId}, String.class);
			
			logger.info(userType);
			
			String query2 = "select loginId from login where user_userId=?";
			
			int loginId = jdbcTemplate.queryForObject(query2, new Object[]{userId}, Integer.class);
			logininfo.setLoginId(loginId);
			
			logger.info("loginid at dao :"+ logininfo.getLoginId());
			
			return userType;
			
		} else {
			
			logger.info("wrong login...");
			return null;
			
		}
	}
}
