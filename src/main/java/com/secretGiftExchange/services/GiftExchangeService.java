package com.secretGiftExchange.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secretGiftExchange.Exceptions.CustomException;
import com.secretGiftExchange.models.FamilyMember;
import com.secretGiftExchange.models.GiftExchange;
import com.secretGiftExchange.models.MemberPairs;
import com.secretGiftExchange.repository.GiftExchangeRepository;

@Service
public class GiftExchangeService {

	@Autowired
	GiftExchangeRepository giftExchRepo;
	@Autowired
	FamilyMemberService familyMemberService;
	List<GiftExchange> giftExhangeHistory; 
	int currentYear;
	

	public List<MemberPairs> getAllGiftExchange(int currYear){
		currentYear = currYear;
		// check if there are already data in database
		List<MemberPairs> memberPairs = getGiftExchangeHistoryFromDB();
		if(memberPairs.size()<=0) {
			getAllGiftExchangeMembers();
			for(GiftExchange e: giftExhangeHistory) {
				if(e.getYear() == currentYear) {
	    	  memberPairs.add(new MemberPairs(e.getMemberId(),e.getMemberName(),
	    			  e.getMemberRecipientId(),e.getMemberRecipientName()));
				}
			}
		}
	     
	    return memberPairs;
	}
	
	private List<MemberPairs> getGiftExchangeHistoryFromDB(){
		getAllGiftHistory();
		List<MemberPairs> gfMembers = new ArrayList<>();
		for(GiftExchange gfExchange: giftExhangeHistory) {
			if(gfExchange.getYear() == currentYear) {
				  gfMembers.add(new MemberPairs(gfExchange.getMemberId(),gfExchange.getMemberName(),
						  gfExchange.getMemberRecipientId(),gfExchange.getMemberRecipientName()));
			}
		}
		return gfMembers;
	}
	
	private void getAllGiftExchangeMembers(){
		//get all family members
		List<FamilyMember> familyMembers = familyMemberService.getAllFamilyMembers();
		//check if family members do not exit
		if(familyMembers.size()==0)
			throw new CustomException("Family Members do not exist.");
		cleanGiftExchangeHistory();
		// getting all history of gift exchanges
		int familySize = familyMembers.size();
		//store member and recipients
		Map<FamilyMember,FamilyMember> familyMemMap = new HashMap<>();
		int randomIdx = new Random().nextInt(familySize - 1) + 1; //getting random index to select pairs
		for(int idx=0;idx<familySize;idx++) {
			int recipientIdx = (idx + randomIdx)%familySize;
			FamilyMember currentMember = familyMembers.get(idx);
			FamilyMember recipient = findRecipientMember(familyMembers,currentMember,recipientIdx);
			if(recipient == null)
				 throw new CustomException("Cannot find a pair.");
			familyMemMap.put(currentMember, recipient);
		}
		
		for(Map.Entry<FamilyMember, FamilyMember> e: familyMemMap.entrySet()) {
			giftExhangeHistory.add(new GiftExchange(e.getKey().getId(), e.getKey().getName(),
	    			  e.getValue().getId(),e.getValue().getName(),currentYear));
		}
		saveGiftExchange(giftExhangeHistory);
		
	}
	//for debugging purposes
	private void printGF() {
		System.out.println(giftExhangeHistory.size());
		for(GiftExchange g:giftExhangeHistory) {
			System.out.println(g.getMemberName() +"----"+ g.getMemberRecipientName());
		}
	}
	
	//removing history after 3 years 
	private void cleanGiftExchangeHistory() {
		//filtering history of specific member
		if(giftExhangeHistory.size()>0) {
			
		int startYear = giftExhangeHistory.get(0).getYear();
		int endYear = giftExhangeHistory.get(giftExhangeHistory.size()-1).getYear();
		if(endYear - startYear >=2) {
			for(Iterator<GiftExchange> it = giftExhangeHistory.iterator(); it.hasNext();) {
				if(it.next().getYear() == startYear)
					it.remove();
				
			}
		}
		}
	}
	private boolean isMemberHistory(String id) {
		for(GiftExchange gf: giftExhangeHistory) {
			if(id == gf.getMemberId()) {
				return true;
			}
		}
		return false;
	}
	private boolean isRecipientAlreadyExists(String memberId,String recipientId) {
		for(GiftExchange gf: giftExhangeHistory) {
			if(memberId==gf.getMemberId() && recipientId == gf.getMemberRecipientId()) {
				return true;
			}
		}
		return false;
	}
	
	private FamilyMember findRecipientMember(List<FamilyMember>members, FamilyMember
			currentMember, int recipientIdx) {
		
		FamilyMember recipient = null;
		int familySize = members.size();
		int nextIdx = 0;
		while(nextIdx < familySize) {
			int selectedIdx = (nextIdx + recipientIdx)%familySize;
			FamilyMember candidateMember = members.get(selectedIdx);

			if(!isMemberHistory(currentMember.getId())) {
				return candidateMember;
			}
			if(currentMember.equals(candidateMember)) {
				nextIdx+=1;
				continue;
			}
			if(!isRecipientAlreadyExists(currentMember.getId(),candidateMember.getId())) {
				return candidateMember;
			}
			nextIdx+=1;
		}
		return recipient;
	}
	
	private void saveGiftExchange(List<GiftExchange> giftExchangeMembers) {
		giftExchRepo.deleteAll();
		giftExchRepo.saveAll(giftExchangeMembers);
	}
	
	private void getAllGiftHistory(){
		giftExhangeHistory = new ArrayList<GiftExchange>();
		giftExchRepo.findAll().forEach(member->giftExhangeHistory.add(member));
		Collections.sort(giftExhangeHistory, (a, b) -> a.getYear() - (b.getYear()));
	}
}
