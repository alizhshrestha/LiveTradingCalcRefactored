package com.example.demo.common;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

public class FloorsheetLive{
	String tablename;
	String stockSymbol;
	int rate;
	Date timestamp;
	Map<String, String> rows;
	public FloorsheetLive() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FloorsheetLive(String tablename, String stockSymbol, int rate, Date timestamp, Map<String, String> rows) {
		super();
		this.tablename = tablename;
		this.stockSymbol = stockSymbol;
		this.rate = rate;
		this.timestamp = timestamp;
		this.rows = rows;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public String getStockSymbol() {
		return stockSymbol;
	}
	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public Map<String, String> getRows() {
		return rows;
	}
	public void setRows(Map<String, String> rows) {
		this.rows = rows;
	}

	
	
}
