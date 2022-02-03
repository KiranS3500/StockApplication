package com.zensar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "StockMaster")
public class StockEntity {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	@Column(name = "market")
	private String marketdata;
	private double price;
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
	public String getMarketdata() {
		return marketdata;
	}
	public void setMarketdata(String marketdata) {
		this.marketdata = marketdata;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public StockEntity(int id, String name, String marketdata, double price) {
		super();
		this.id = id;
		this.name = name;
		this.marketdata = marketdata;
		this.price = price;
	}
	public StockEntity() {
		super();
	}
	@Override
	public String toString() {
		return "StockEntity [id=" + id + ", name=" + name + ", marketdata=" + marketdata + ", price=" + price + "]";
	}
	
}
