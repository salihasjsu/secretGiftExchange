package com.secretGiftExchange.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secretGiftExchange.models.GiftExchange;
import com.secretGiftExchange.models.MemberPairs;
import com.secretGiftExchange.services.GiftExchangeService;

@RestController
public class GiftExchangeController {

	@Autowired
	GiftExchangeService giftExchService;
	
	@GetMapping("/gift_exchange")  
	private List<MemberPairs> getAllFamilyMembers(){
		return giftExchService.getAllGiftExchange();
	}
}
