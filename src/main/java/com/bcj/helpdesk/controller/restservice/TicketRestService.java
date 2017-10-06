package com.bcj.helpdesk.controller.restservice;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bcj.helpdesk.dao.tickets.TicketsDao;


@RestController
public class TicketRestService {

	@Autowired
	TicketsDao ticketsDao;
	
	private static final Logger logger = Logger.getLogger(TicketRestService.class);
	
	@RequestMapping(value = "/getTicketDetailsById/{id}", method = RequestMethod.GET)
	public List<Map<String,Object>> getTicketDetailsById(@PathVariable("id") int loginId) {
		
		List<Map<String,Object>> ticketDetailsById = ticketsDao.retrieveTicketsData(loginId);
		
		logger.info("Start getLogin. ID= "+ loginId);
		
		return ticketDetailsById;
	}
}
