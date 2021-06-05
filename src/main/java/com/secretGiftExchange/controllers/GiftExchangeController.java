package com.secretGiftExchange.controllers;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.secretGiftExchange.models.MemberPairs;
import com.secretGiftExchange.services.GiftExchangeService;

@RestController
public class GiftExchangeController{

	@Autowired
	GiftExchangeService giftExchService;
	
	@GetMapping("/gift_exchange")  
	private List<MemberPairs> getAllFamilyMembers(){
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		try {
			List<MemberPairs> membersPairs = giftExchService.getAllGiftExchange(currentYear);
			return membersPairs;
			}catch(Exception e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
			}
	}
	// controller for testing the gift exchange cases
	@GetMapping("/gift_exchange/{year}")  
	private List<MemberPairs> getAllFamilyMembers(@PathVariable("year") int year) {
		try {
		List<MemberPairs> membersPairs = giftExchService.getAllGiftExchange(year);
		return membersPairs;
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	
		
	}
}
