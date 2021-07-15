/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.common;

import java.util.Date;
import java.util.Map;

/**
 *
 * @author alizh
 */
public class Entities {
    String tablename;
    String ticker;
    Double closingPrice;
    Date tradingDate;
    Map<String, String> rows;
	public Entities() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Entities(String tablename, String ticker, Double closingPrice, Date tradingDate, Map<String, String> rows) {
		super();
		this.tablename = tablename;
		this.ticker = ticker;
		this.closingPrice = closingPrice;
		this.tradingDate = tradingDate;
		this.rows = rows;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public Double getClosingPrice() {
		return closingPrice;
	}
	public void setClosingPrice(Double closingPrice) {
		this.closingPrice = closingPrice;
	}
	public Date getTradingDate() {
		return tradingDate;
	}
	public void setTradingDate(Date tradingDate) {
		this.tradingDate = tradingDate;
	}
	public Map<String, String> getRows() {
		return rows;
	}
	public void setRows(Map<String, String> rows) {
		this.rows = rows;
	}
    
}
