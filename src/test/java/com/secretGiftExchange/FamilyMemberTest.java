package com.secretGiftExchange;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.*;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import com.secretGiftExchange.models.FamilyMember;
import com.secretGiftExchange.repository.FamilyMmbrRepository;
import com.secretGiftExchange.services.FamilyMemberService;

@SpringBootTest
public class FamilyMemberTest{

	 @Mock
	    private FamilyMmbrRepository familyMmbrRepository;

	    @InjectMocks // auto inject helloRepository
	    private FamilyMemberService familyMemberService = new FamilyMemberService();

	    @DisplayName("Test a speicifc Family Member by ID")
	    @Test
		public void getFamilyMemberTest() throws Exception {

			
			  FamilyMember familyMember = new FamilyMember(); 
			  familyMember.setId("1");
			  familyMember.setName("Saliha");
			  when(familyMmbrRepository.findById("1")).thenReturn(Optional.of(familyMember));
			  FamilyMember familyMemberReturn = familyMemberService.getFamilyMember("1");
		      assertEquals(familyMember, familyMemberReturn);

		}
	    @DisplayName("Test All family members")
	    @Test
	   	public void getAllFamilyMembers() throws Exception {

	   		  FamilyMember familyMember1 = new FamilyMember(); 
	   		  familyMember1.setId("1");
	   		  familyMember1.setName("Saliha");
	   		  
	   		  FamilyMember familyMember2 = new FamilyMember(); 
	 		  familyMember2.setId("2");
	 		  familyMember2.setName("Farooq");
	 		  
	 		  FamilyMember familyMember3 = new FamilyMember(); 
	  		  familyMember3.setId("3");
	  		  familyMember3.setName("Zuraib");
	  		  
	  		  List<FamilyMember> members = new ArrayList<FamilyMember>();
	   		  
	  		  members.add(familyMember1);
	  		  members.add(familyMember2);
	  		  members.add(familyMember3);
	  		  
	   		  when(familyMmbrRepository.findAll()).thenReturn(members);
	   		  List<FamilyMember> familyMemberReturn = familyMemberService.getAllFamilyMembers();
	   		  
	   	      assertEquals(members, familyMemberReturn);

	   	}
	    @DisplayName("Test saving a member")
	    @Test
	   	public void saveMember() throws Exception {
	    	
	 		  FamilyMember familyMember1 = new FamilyMember(); 
	 		  familyMember1.setId("1");
	 		  familyMember1.setName("Saliha");
	 		  
	   		  when(familyMmbrRepository.save(familyMember1)).thenReturn(familyMember1);

	   		  familyMemberService.saveMember(familyMember1);
	    	
	   		  verify(familyMmbrRepository, times(1)).save(familyMember1);

	   		  
	    }
	    
	    @DisplayName("Test deleting a member")
	    @Test
	   	public void deleteMember() throws Exception {
	    	
	 		  FamilyMember familyMember1 = new FamilyMember(); 
	 		  familyMember1.setId("1");
	 		  familyMember1.setName("Saliha");
	 		  
	 		  doNothing().when(familyMmbrRepository).deleteById("1");
	   		  familyMemberService.deleteMember("1");
	   		  verify(familyMmbrRepository, times(1)).deleteById("1");
	   		  
	    }
}
