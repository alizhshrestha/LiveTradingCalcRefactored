package com.example.demo.common;

import java.util.Date;

public class LatestPrice {

	private double amount;

	private double closingprice;

	private String company_full_name;

	private double diff_rs;

	private double high;

	private double low;

	private double open;

	private String ticker;

	private Date TRADING_DATE;

	private double transaction;

	private double volume;
	
	public LatestPrice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LatestPrice(double amount, double closingprice, String company_full_name, double diff_rs, double high,
			double low, double open, String ticker, Date tRADING_DATE, double transaction, double volume) {
		super();
		this.amount = amount;
		this.closingprice = closingprice;
		this.company_full_name = company_full_name;
		this.diff_rs = diff_rs;
		this.high = high;
		this.low = low;
		this.open = open;
		this.ticker = ticker;
		TRADING_DATE = tRADING_DATE;
		this.transaction = transaction;
		this.volume = volume;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getClosingprice() {
		return closingprice;
	}

	public void setClosingprice(double closingprice) {
		this.closingprice = closingprice;
	}

	public String getCompany_full_name() {
		return company_full_name;
	}

	public void setCompany_full_name(String company_full_name) {
		this.company_full_name = company_full_name;
	}

	public double getDiff_rs() {
		return diff_rs;
	}

	public void setDiff_rs(double diff_rs) {
		this.diff_rs = diff_rs;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public Date getTRADING_DATE() {
		return TRADING_DATE;
	}

	public void setTRADING_DATE(Date tRADING_DATE) {
		TRADING_DATE = tRADING_DATE;
	}

	public double getTransaction() {
		return transaction;
	}

	public void setTransaction(double transaction) {
		this.transaction = transaction;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}
	
	
	
	
}
