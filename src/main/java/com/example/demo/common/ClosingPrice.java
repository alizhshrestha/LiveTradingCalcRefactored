package com.example.demo.common;


//Class for closing price data
public class ClosingPrice {

	Double closingPrice;
	int averageCount;
	Double totalClosingPrice;
	public ClosingPrice() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ClosingPrice(Double closingPrice, int averageCount, Double totalClosingPrice) {
		super();
		this.closingPrice = closingPrice;
		this.averageCount = averageCount;
		this.totalClosingPrice = totalClosingPrice;
	}
	public Double getClosingPrice() {
		return closingPrice;
	}
	public void setClosingPrice(Double closingPrice) {
		this.closingPrice = closingPrice;
	}
	public int getAverageCount() {
		return averageCount;
	}
	public void setAverageCount(int averageCount) {
		this.averageCount = averageCount;
	}
	public Double getTotalClosingPrice() {
		return totalClosingPrice;
	}
	public void setTotalClosingPrice(Double totalClosingPrice) {
		this.totalClosingPrice = totalClosingPrice;
	}
	

	
}
