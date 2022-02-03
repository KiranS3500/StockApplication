package com.zensar.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.dto.Stock;
import com.zensar.service.StockService;
import com.zensar.service.StockServiceIMPL;

@RestController
@RequestMapping("/zenStockApp")
@CrossOrigin(origins = "*")
public class StockController {

@Autowired
public StockService stockService;
private static List<Stock> stocks = new ArrayList<Stock>();

@GetMapping(value = "/stock",produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<List<Stock>> getallStocks()
{
	return new ResponseEntity<List<Stock>>(stockService.getallStocks(), HttpStatus.OK);
	}

@GetMapping(value = "/stock/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<Stock> getStockById(@PathVariable("id") int stockId)
{
	
		/*
		 * for(Stock stock : stocks) { if(stock.getId()==stockId) { return stock; } }
		 * return null;
		 */
	
	return  new ResponseEntity<Stock>(stockService.getStockById(stockId), HttpStatus.ACCEPTED);
	}
@PostMapping(value = "/stock",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<Stock> createStock(@RequestBody Stock stock)
{
		/*
		 * stock.setId(stocks.size()+1); stocks.add(stock); return stock;
		 */
	
	
	return new ResponseEntity<Stock>(stockService.createStock(stock), HttpStatus.CREATED)  ;
	}

@PutMapping(value = "/stock/{id}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<Stock> updateStock(@RequestBody Stock stock1 , @PathVariable("id") int stockId)
{
		/*
		 * Stock oldStock = getStockById(stockId); System.out.println(oldStock);
		 * oldStock.setName(stock1.getName()); oldStock.setMarket(stock1.getMarket());
		 * oldStock.setPrice(stock1.getPrice()); return oldStock;
		 */
	
	return new ResponseEntity<Stock>(stockService.updateStock(stock1, stockId),HttpStatus.ACCEPTED);
}
@DeleteMapping(value = "/stock/{id}")
public ResponseEntity<Boolean> deleteStockById(@PathVariable("id") int stockId)
{
		/*
		 * Stock s = getStockById(stockId); stocks.remove(s); return true;
		 */
	return new ResponseEntity<Boolean>(stockService.deleteStockById(stockId),HttpStatus.ACCEPTED);
	}
@DeleteMapping(value = "/stock")
public ResponseEntity<Boolean> deleteallStock()
{
	
		/*
		 * stocks.clear(); return true;
		 */
	return new ResponseEntity<Boolean>(stockService.deleteallStock(),HttpStatus.ACCEPTED);	
}

@GetMapping(value = "/acc")
public ResponseEntity<Boolean> testParam(@RequestParam("id") int accno,@RequestHeader("authtoken") String token)
{
		/*
		 * System.out.println("AccountNumber "+accno);
		 * System.out.println("AuthToken "+token); return true;
		 */	
	return new ResponseEntity<Boolean>(stockService.testParam(accno, token),HttpStatus.ACCEPTED);
}
@GetMapping(value = "/stock/name/{stockName}",produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<List<Stock>> getStocksByName(@PathVariable("stockName") String stockName)
{
	return new ResponseEntity<List<Stock>>(stockService.getStocksByName(stockName),HttpStatus.OK);
}

@GetMapping(value = "/stock/sort/{sortType}",produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<List<Stock>> getStocksSortedByName(@PathVariable("sortType") String sortType)
{
	return new ResponseEntity<List<Stock>>(stockService.getStocksSortedByName(sortType),HttpStatus.OK);
	}

@GetMapping(value = "/stock/{startIndex}/{records}",produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<List<Stock>> getStocksByPage(@PathVariable("startIndex") int startIndex,@PathVariable("records") int records)
{
	return new ResponseEntity<List<Stock>>(stockService.getStocksByPage(startIndex, records),HttpStatus.OK);
	}

@GetMapping(value = "/stock/like/{nameLike}",produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<List<Stock>> getStocksByNameLike(@PathVariable("nameLike") String namelike)
{
	return new ResponseEntity<List<Stock>>(stockService.getStocksByNameLike(namelike),HttpStatus.OK);
}
}
