package com.paperscissorsrock.controllers;

import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paperscissorsrock.model.BetResponse;

@Controller
public class BetErrorController implements ErrorController  {
	
	Logger log = Logger.getLogger(BetErrorController.class.getName());
	
	@RequestMapping("/error")
	public ResponseEntity<BetResponse> handleError(HttpServletRequest request) {
		
		log.info("Into handleError.");
		
	    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	    
		BetResponse betResponse = new BetResponse();
		HttpHeaders resHeader = new HttpHeaders();
		resHeader.set("Accept", "application/json");
	    
	    if (status != null) {
	        Integer statusCode = Integer.valueOf(status.toString());
	    
	        if(statusCode == HttpStatus.NOT_FOUND.value()) {
	        	betResponse.setErrorMsg("error-404.");
	        }
	        else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	        	betResponse.setErrorMsg("error-500.");
	        }
	    }
	    
		log.info("Exiting handleError - error: " + betResponse.getErrorMsg());

		return ResponseEntity.ok().headers(resHeader).body(betResponse);
	}

	@Override
	public String getErrorPath() {
		return null;
	}

}