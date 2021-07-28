package com.example.demo.controller;

import java.text.ParseException;
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
	
	
	@GetMapping("/fillData")
	public List<Tearsheetderivedtable> fillData() throws ParseException{
		return stockDataAdjustedService.fillData();
	}
	
	@GetMapping("/fullFill")
	public List<Tearsheetderivedtable> fullFill() throws ParseException{
		return stockDataAdjustedService.fullFill();
	}
	
	@GetMapping("/calculateDaysHighLowPercentile")
	public List<Tearsheetderivedtable> calculateDaysHighLowPercentile() throws ParseException{
		return stockDataAdjustedService.calculateDaysHighLowPercentile();
	}
	
	
	@GetMapping("/calculateProfitabilityChange")
	public List<Tearsheetderivedtable> calculateProfitabilityChange() throws ParseException{
		return stockDataAdjustedService.calculateProfitabilityChange();
	}
	
	@GetMapping("/fiftyHighClosingPrice")
	public List<Tearsheetderivedtable> fiftyHighClosingPrice() throws ParseException{
		return stockDataAdjustedService.fiftyHighClosingPrice();
	}
	
	
	@GetMapping("/fiftyLowClosingPrice")
	public List<Tearsheetderivedtable> fiftyLowClosingPrice() throws ParseException{
		return stockDataAdjustedService.fiftyLowClosingPrice();
	}
	
	@GetMapping("/saveTearsheetDerivedTable")
	public String saveTearsheetDerivedTable() throws ParseException{
		return stockDataAdjustedService.saveTearsheetDerivedTable();
	}
	
	
	
}
