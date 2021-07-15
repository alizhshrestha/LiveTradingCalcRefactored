package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.Entities;
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
	
	@GetMapping("/findall")
	public List<Entities> findall(){
		return stockDataAdjustedService.findEntities();
	}
	
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
	public List<Entities> findstockDataAdjustedData(){
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
