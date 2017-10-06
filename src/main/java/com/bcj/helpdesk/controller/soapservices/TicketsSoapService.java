package com.bcj.helpdesk.controller.soapservices;

import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import com.bcj.helpdesk.controller.restservice.TicketRestService;
import com.bcj.helpdesk.dao.tickets.TicketsDao;

@WebService(targetNamespace = "http://soapservices.controller.helpdesk.bcj.com/", portName = "TicketsSoapServicePort", serviceName = "TicketsSoapServiceService")
public class TicketsSoapService {

	
	@Autowired
	TicketsDao ticketsDao;
	
	private static final Logger logger = Logger.getLogger(TicketRestService.class);
	
	@WebMethod(operationName = "getTicketDetailsById", action = "urn:GetTicketDetailsById")
	public List<Map<String,Object>> getTicketDetailsById(@PathVariable("id") @WebParam(name = "arg0") int loginId) {
		
		List<Map<String,Object>> ticketDetailsById = ticketsDao.retrieveTicketsData(loginId);
		
		logger.info("Start getLogin. ID= "+ loginId);
		
		return ticketDetailsById;
	}
}
