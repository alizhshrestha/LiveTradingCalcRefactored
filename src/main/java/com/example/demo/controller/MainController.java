package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.StockDataAdj;
import com.example.demo.model.StockDataAdjusted;
import com.example.demo.model.Tearsheetderivedtable;
import com.example.demo.services.StockDataAdjustedService;

@RestController
public class MainController {

	@Autowired
	private StockDataAdjustedService stockDataAdjustedService;
	
	@GetMapping("/")
	public String hello() {
		return "Hello";
	}
	
	@GetMapping("/find")
	public List<StockDataAdjusted> find(){
		return stockDataAdjustedService.findall();
	}
	
	@GetMapping("/stockDataAdjustedAllData")
	public List<StockDataAdj> stockDataAdjustedAllData(){
		return stockDataAdjustedService.getStockData();
	}
	
	
	@GetMapping("/calculatePercentile")
	public List<Tearsheetderivedtable> calculatePercentile(){
		return stockDataAdjustedService.calculateDaysHighLowPercentile();
	}
	
//	calculatePercentile


//	@GetMapping("/getAllTickers")
//	public List<String> getTckLstString(){
//		return stockDataAdjustedService.getTckLstString();
//	}
	
//	@GetMapping("/cbkeystatsData")
//	public List<Entities> findcbkeystatsData(){
//		return stockDataAdjustedService.cbkeystatsData();
//	}
//	
//	
	@GetMapping("/stockDataAdjustedData")
	public List<StockDataAdj> findstockDataAdjustedData(){
		return stockDataAdjustedService.stockDataAdjustedData();
	}
	
	@GetMapping("/fiftyHighClosingPrice")
	public List<Tearsheetderivedtable> fiftyHighClosingPrice(){
		return stockDataAdjustedService.fiftyHighClosingPrice();
	}
	
	
	@GetMapping("/fiftyLowClosingPrice")
	public List<Tearsheetderivedtable> fiftyLowClosingPrice(){
		return stockDataAdjustedService.fiftyLowClosingPrice();
	}
	
	
}
