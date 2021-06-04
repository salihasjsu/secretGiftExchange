package com.secretGiftExchange.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secretGiftExchange.models.FamilyMember;
import com.secretGiftExchange.repository.FamilyMmbrRepository;

@Service
public class FamilyMemberService {
	@Autowired
	FamilyMmbrRepository familyMemberRepo;
	
	public List<FamilyMember> getAllFamilyMembers(){
		List<FamilyMember> familyMembers = new ArrayList<>();
		familyMemberRepo.findAll().forEach(member -> familyMembers.add(member));
		return familyMembers;
	}
	
	public FamilyMember getFamilyMember(String id) {
		return familyMemberRepo.findById(id).get();
	}
}
