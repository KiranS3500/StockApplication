package com.zensar.dto;

import lombok.Data;

//@Data
public class Stock {
	private int id;
	private String name;
	private String market;
	private double price;
	
	
	
	
	public Stock() {
		super();
	}
	public Stock(int id, String name, String market, double price) {
		super();
		this.id = id;
		this.name = name;
		this.market = market;
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	

}
