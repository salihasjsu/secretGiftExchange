package com.secretGiftExchange.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.secretGiftExchange.models.FamilyMember;

@Repository
public interface FamilyMmbrRepository extends CrudRepository<FamilyMember,String> {
	
}
