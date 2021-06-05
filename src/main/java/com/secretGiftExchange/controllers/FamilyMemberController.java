package com.secretGiftExchange.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
		try {
		return familyMemberService.getFamilyMember(id);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id does not exist");
		}
	}
	
	//Save the family member
	@PostMapping("/members")
	private String saveFamilyMember(@RequestBody FamilyMember member) {
		familyMemberService.saveMember(member);
		return member.getId();
	}
	@DeleteMapping("/members/{id}")
	private void deleteMember(@PathVariable("id") String id) {
		try {
		familyMemberService.deleteMember(id);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id does not exist");
		}
	}
	
	@PutMapping("/members/{id}")
	private void updateFamilyMember(@PathVariable("id") String id,@RequestBody FamilyMember member) {
		try{
		familyMemberService.updateMember(member, id);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id does not exist");
		}
	}
	//testing the service
	@RequestMapping(value = "/")
	public String hello() {
	      return "Hello World";
}


}
