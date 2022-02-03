package com.zensar.service;

import java.util.List;

import com.zensar.dto.Stock;

public interface StockService {
	public List<Stock> getallStocks();

	public Stock getStockById(int stockId);
	public Stock createStock(Stock stock);
	public Stock updateStock(Stock stock1 ,int stockId);
	public boolean deleteStockById(int stockId);
	public boolean deleteallStock();
	public boolean testParam(int accno,String token);
	
	public List<Stock> getStocksByName(String stockName);
	public List<Stock> getStocksSortedByName(String sortType);
	public List<Stock> getStocksByPage(int startIndex, int records);
	public List<Stock> getStocksByNameLike(String namelike);

}
