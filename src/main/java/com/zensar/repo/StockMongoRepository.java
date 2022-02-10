package com.zensar.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zensar.entity.StockDocument;

public interface StockMongoRepository extends MongoRepository<StockDocument, Integer> {
	
List<StockDocument> findByName(String stockName);
	
	List<StockDocument> findByNameContains(String nameLike);
	List<StockDocument> findByNameContaining(String nameLike);
	List<StockDocument> findByNameIsContaining(String nameLike);

}
