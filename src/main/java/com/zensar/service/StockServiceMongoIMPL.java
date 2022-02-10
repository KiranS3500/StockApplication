package com.zensar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zensar.Exception.InvalidStockIDException;
import com.zensar.dto.Stock;
import com.zensar.entity.StockDocument;
import com.zensar.repo.StockMongoRepository;
@Service(value = "mongoStockService")
public class StockServiceMongoIMPL implements StockService {

	@Autowired
	StockMongoRepository stockMongoRepository;
	
	@Autowired
	ModelMapper modelMapper; 
	@Override
	public List<Stock> getallStocks() {
		List<StockDocument> list = stockMongoRepository.findAll();
		List<Stock> list2 = new ArrayList<Stock>();
		for(StockDocument stockDocument : list)
		{
			Stock ss = modelMapper.map(stockDocument, Stock.class);
			list2.add(ss);
		}
		return list2;
	}
	
	@Override
	public Stock getStockById(int stockId) {
		Optional<StockDocument> opStockDocument = stockMongoRepository.findById(stockId);
		if(opStockDocument.isPresent())
		{
			StockDocument stockDocument = opStockDocument.get();
			Stock stock = modelMapper.map(stockDocument, Stock.class);
			return stock;
		}
		throw new InvalidStockIDException(""+stockId);
	}

	@Override
	public Stock createStock(Stock stock) {
		StockDocument stockDocument = modelMapper.map(stock, StockDocument.class);
		stockDocument = stockMongoRepository.save(stockDocument);
		stock = modelMapper.map(stockDocument, Stock.class); 
		return stock;
	}

	@Override
	public Stock updateStock(Stock stock1, int stockId) {
		Optional<StockDocument> opStockDocument = stockMongoRepository.findById(stockId);
		if(opStockDocument.isPresent())
		{
			StockDocument s = opStockDocument.get();
			s = modelMapper.map(stock1, StockDocument.class);
			s.setId(stockId);
			s = stockMongoRepository.save(s);
			stock1.setId(s.getId());
			return stock1;
		}
		return null;
	}

	@Override
	public boolean deleteStockById(int stockId) {
		Optional<StockDocument> opStockDocument = stockMongoRepository.findById(stockId);
		if(opStockDocument.isPresent())
		{
			StockDocument s = opStockDocument.get();
			stockMongoRepository.delete(s);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteallStock() {
		stockMongoRepository.deleteAll();
		return true;
	}

	@Override
	public boolean testParam(int accno, String token) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Stock> getStocksByName(String stockName) {
		List<StockDocument> list = stockMongoRepository.findByName(stockName);
		List<Stock> list2 = new ArrayList<Stock>();
		for(StockDocument s : list)
		{
			Stock ss = modelMapper.map(s, Stock.class);
			list2.add(ss);
		}
		return list2;
		
	}

	@Override
	public List<Stock> getStocksSortedByName(String sortType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Stock> getStocksByPage(int startIndex, int records) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Stock> getStocksByNameLike(String namelike) {
		// TODO Auto-generated method stub
		return null;
	}

}
