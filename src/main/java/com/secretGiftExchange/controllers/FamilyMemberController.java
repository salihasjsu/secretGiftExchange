package com.secretGiftExchange.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secretGiftExchange.models.FamilyMember;
import com.secretGiftExchange.services.FamilyMemberService;



@RestController
public class FamilyMemberController {
	
	@Autowired
	FamilyMemberService familyMemberService;
	
	//Getting all family members
	@GetMapping("/members")  
	private List<FamilyMember> getAllFamilyMembers(){
		return familyMemberService.getAllFamilyMembers();
	}
	
	//Get one particular family member
	@GetMapping("/members/{id}")  
	private FamilyMember getFamilyMember(@PathVariable("id") String id) {
		return familyMemberService.getFamilyMember(id);
	}
	
	//testing the service
	@RequestMapping(value = "/")
	public String hello() {
	      return "Hello World";
}


}
