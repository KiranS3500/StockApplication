package com.zensar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.zensar.dto.Stock;
import com.zensar.entity.StockEntity;
import com.zensar.repo.StockRepository;

@Service
public class StockServiceIMPL implements StockService{

	@Autowired
	ModelMapper modelMapper;
	@Autowired
	StockRepository stockRepository;
	@Override
	public List<Stock> getallStocks() {
		List<StockEntity> list = stockRepository.findAll();
		List<Stock> list2 = new ArrayList<Stock>();
		for(StockEntity s : list)
		{
			Stock ss = convertStockEntityToStockDTO(s);
			list2.add(ss);
		}
		return list2;
	}

	@Override
	public Stock getStockById(int stockId) {
		Optional<StockEntity> opStockEntity = stockRepository.findById(stockId);
		if(opStockEntity.isPresent())
		{
			StockEntity s = opStockEntity.get();
			Stock stock = convertStockEntityToStockDTO(s);
			return stock;
		}
		return null;
	}

	@Override
	public Stock createStock(Stock stock) {
		StockEntity entity = convertStockDTOToStockEntity(stock);
		entity = stockRepository.save(entity);
		stock.setId(entity.getId());
		return stock;
	}

	@Override
	public Stock updateStock(Stock stock1, int stockId) {
		Optional<StockEntity> opStockEntity = stockRepository.findById(stockId);
		if(opStockEntity.isPresent())
		{
			StockEntity s = opStockEntity.get();
			s = convertStockDTOToStockEntity(stock1);
			s.setId(stockId);
			s = stockRepository.save(s);
			stock1.setId(s.getId());
			return stock1;
		}
		return null;
	}

	@Override
	public boolean deleteStockById(int stockId) {
		Optional<StockEntity> opStockEntity = stockRepository.findById(stockId);
		if(opStockEntity.isPresent())
		{
			StockEntity s = opStockEntity.get();
			stockRepository.delete(s);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteallStock() {
		stockRepository.deleteAll();
		return true;
	}

	@Override
	public boolean testParam(int accno, String token) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private Stock convertStockEntityToStockDTO(StockEntity stockEntity)
	{
		TypeMap<StockEntity, Stock> typeMap = this.modelMapper.typeMap(StockEntity.class, Stock.class);
		typeMap.addMappings(mapper -> {mapper.map(source->source.getMarketdata(), Stock::setMarket);});
		Stock stock = this.modelMapper.map(stockEntity, Stock.class);
		return stock;
	}
	
	private StockEntity convertStockDTOToStockEntity(Stock stock)
	{
		TypeMap<Stock, StockEntity> typeMap = this.modelMapper.typeMap(Stock.class, StockEntity.class);
		typeMap.addMappings(mapper -> {mapper.map(source->source.getMarket(), StockEntity::setMarketdata);});
		StockEntity stockEntity = this.modelMapper.map(stock, StockEntity.class);
		return stockEntity;
	}

	@Override
	public List<Stock> getStocksByName(String stockName) {
		List<StockEntity> list = stockRepository.findByName(stockName);
		List<Stock> list2 = new ArrayList<Stock>();
		for(StockEntity s : list)
		{
			Stock ss = convertStockEntityToStockDTO(s);
			list2.add(ss);
		}
		return list2;
		
	}

	@Override
	public List<Stock> getStocksSortedByName(String sortType) {
		
		Sort.Order order = null;
		if("ASC".equalsIgnoreCase(sortType))
		{
			order = new Sort.Order(Sort.Direction.ASC, "marketdata");
		}
		else
		{
			order = new Sort.Order(Sort.Direction.DESC, "marketdata");
		}
		Sort sort = Sort.by(order);
		List<StockEntity> list = stockRepository.findAll(sort);
		//List<StockEntity> list = stockRepository.getStocksSortedByName();
		List<Stock> list2 = new ArrayList<Stock>();
		for(StockEntity s : list)
		{
			Stock ss = convertStockEntityToStockDTO(s);
			list2.add(ss);
		}
		return list2;
	}
	
	

	@Override
	public List<Stock> getStocksByPage(int startIndex, int records) {
		Pageable myPageable = PageRequest.of(startIndex, records);
		Page<StockEntity> pageStockEntity = stockRepository.findAll(myPageable);
		List<StockEntity> list = pageStockEntity.getContent();
		List<Stock> list2 = new ArrayList<Stock>();
		for(StockEntity s : list)
		{
			Stock ss = convertStockEntityToStockDTO(s);
			list2.add(ss);
		}
		return list2;
	}

	@Override
	public List<Stock> getStocksByNameLike(String nameLike) {
		List<StockEntity> list = stockRepository.getNameLikeUsingSQL(nameLike);
		List<Stock> list2 = new ArrayList<Stock>();
		for(StockEntity s : list)
		{
			Stock ss = convertStockEntityToStockDTO(s);
			list2.add(ss);
		}
		return list2;
	}

}
