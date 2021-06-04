package com.secretGiftExchange.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.secretGiftExchange.models.GiftExchange;

@Repository
public interface GiftExchangeRepository extends CrudRepository<GiftExchange,String> {
	
}