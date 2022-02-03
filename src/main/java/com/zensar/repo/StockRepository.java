package com.zensar.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zensar.dto.Stock;
import com.zensar.entity.StockEntity;

public interface StockRepository extends JpaRepository<StockEntity, Integer> {
	
	List<StockEntity> findByName(String stockName);
	
	List<StockEntity> findByNameContains(String nameLike);
	List<StockEntity> findByNameContaining(String nameLike);
	List<StockEntity> findByNameIsContaining(String nameLike);
	
	@Query(value = "SELECT se FROM StockEntity AS se WHERE se.name LIKE ':nameLike'")
	List<StockEntity> getNameLike(String nameLike);
	
	@Query(value = "SELECT * FROM Stock_Master sm WHERE sm.name LIKE %:nameLike%", nativeQuery = true)
	List<StockEntity> getNameLikeUsingSQL(String nameLike);
	
	List<StockEntity> findByOrderByNameAsc();
	
	@Query(value="SELECT se FROM StockEntity AS se ORDER BY se.name") //JPQL
	List<StockEntity> getStocksSortedByName();

}
