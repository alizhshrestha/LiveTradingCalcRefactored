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
	
	//doTearsheet
	@GetMapping("/doTearsheet")
	public List<Tearsheetderivedtable> doTearsheet() throws ParseException{
		return stockDataAdjustedService.doTearsheet();
	}
	
	@GetMapping("/saveTearsheetDerivedTable")
	public String saveTearsheetDerivedTable() throws ParseException{
		return stockDataAdjustedService.saveTearsheetDerivedTable();
	}
	
	
	
}
