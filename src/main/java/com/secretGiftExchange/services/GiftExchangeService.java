package com.secretGiftExchange.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secretGiftExchange.models.FamilyMember;
import com.secretGiftExchange.models.GiftExchange;
import com.secretGiftExchange.models.MemberPairs;
import com.secretGiftExchange.repository.FamilyMmbrRepository;
import com.secretGiftExchange.repository.GiftExchangeRepository;

@Service
public class GiftExchangeService {

	@Autowired
	GiftExchangeRepository giftExchRepo;
	@Autowired
	FamilyMmbrRepository familyMmbrRepo;

	

	public List<MemberPairs> getAllGiftExchange(){
		// check if there are already data is in database
		List<MemberPairs> gfMembers = getGiftExchangeFromDB();
		
		if(gfMembers.size()==0) {
			List<GiftExchange> gfExchange = new ArrayList<>();
			Map<FamilyMember, FamilyMember> familyPair = getPairMembers();
	       
			for(Map.Entry<FamilyMember, FamilyMember> e: familyPair.entrySet()) {
	    	  gfExchange.add(new GiftExchange(e.getKey().getId(), e.getKey().getName(),
	    			  e.getValue().getId(),e.getValue().getName(),Calendar.getInstance().get(Calendar.YEAR)));
	    	  gfMembers.add(new MemberPairs(e.getKey().getId(), e.getKey().getName(),
	    			  e.getValue().getId(),e.getValue().getName()));
			}
	      
			saveGiftExchange(gfExchange);
		}
	     
	    return gfMembers;
	}
	
	private List<MemberPairs> getGiftExchangeFromDB(){
		List<GiftExchange> memPairs = new ArrayList<>();
		List<MemberPairs> gfMembers = new ArrayList<>();
		giftExchRepo.findAll().forEach(member->memPairs.add(member));
		for(GiftExchange member: memPairs) {
			if(member.getYear() == Calendar.getInstance().get(Calendar.YEAR)) {
				  gfMembers.add(new MemberPairs(member.getMemberId(),member.getMemberName(),
						  member.getMemberRecipientId(),member.getMemberRecipientName()));
			}
		}
		return gfMembers;
	}
	
	private Map<FamilyMember, FamilyMember> getPairMembers(){
		List<FamilyMember> fmlyMembers = new ArrayList<>();
		
		familyMmbrRepo.findAll().forEach(member -> fmlyMembers.add(member));
		int size = fmlyMembers.size();
		Map<FamilyMember, FamilyMember> familyPair = new HashMap<>(size);
	       int shiftIndex = getShiftIndex(size);
	       for (int i = 0; i < size; i++) {
	            int memberIndx = (i + shiftIndex) % size;
	            familyPair.put(fmlyMembers.get(i), fmlyMembers.get(memberIndx));
	       }
	    return familyPair;
	}
	private int getShiftIndex(int size) {
        Random random = new Random();
        return random.nextInt(size - 1) + 1;
    }
	
	private void saveGiftExchange(List<GiftExchange> giftExchangeMembers) {
		giftExchRepo.saveAll(giftExchangeMembers);
	}
}
