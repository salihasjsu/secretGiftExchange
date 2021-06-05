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
	
	public void saveMember(FamilyMember member) {
		familyMemberRepo.save(member);
	}
	
	public void updateMember(FamilyMember member, String id) {
		FamilyMember mem = familyMemberRepo.findById(id).get();
		if(mem != null) {
			mem.setName(member.getName());
			familyMemberRepo.save(mem);
		}
	}
	
	public void deleteMember(String id) {
		familyMemberRepo.deleteById(id);
	}
	
	public void saveAllMember(List<FamilyMember> members) {
		familyMemberRepo.saveAll(members);
	}
		
}
