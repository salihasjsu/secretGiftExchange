package com.secretGiftExchange;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;



import com.secretGiftExchange.models.FamilyMember;
import com.secretGiftExchange.models.MemberPairs;

import com.secretGiftExchange.services.FamilyMemberService;
import com.secretGiftExchange.services.GiftExchangeService;

@SpringBootTest
public class GiftExchangeTest { 
                                                 
	@Autowired                           
    private GiftExchangeService service;
    
    @Autowired
    private FamilyMemberService familyService;
    
    
    private List<FamilyMember> members;
	    @BeforeEach
    	void setup() {
	    	this.members = new ArrayList<>();
	    	this.members.add(new FamilyMember("1", "Saliha"));
	    	this.members.add(new FamilyMember("2", "Farooq"));
	    	this.members.add(new FamilyMember("3", "Zuraib"));
	    	this.members.add(new FamilyMember("4", "Sarah"));
	    	familyService.saveAllMember(members);
    	}
	    @DisplayName("Test gift exhcnage passing year manually")
	    @Test
		public void giftExchangeTest(){
	    	List<MemberPairs> memberpairs = service.getAllGiftExchange(2021);
	    	assertEquals(memberpairs.size(),this.members.size());

		}
	    @DisplayName("Test gift exhcnage passing year manually 2022")
	    @Test
		public void giftExchangeTest_2022(){
	    	List<MemberPairs> memberpairs = service.getAllGiftExchange(2022);
	    	assertEquals(memberpairs.size(),this.members.size()*2);

		}
	    @DisplayName("Test gift exhcnage passing year manually 2023")
	    @Test
		public void giftExchangeTest_2023(){
	    	List<MemberPairs> memberpairs = service.getAllGiftExchange(2023);
	    	assertEquals(memberpairs.size(),this.members.size()*3);

		}
}
