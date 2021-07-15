package com.example.demo.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.LiveTradingCalcApplication;
import com.example.demo.common.Entities;
import com.example.demo.model.StockDataAdjusted;
import com.example.demo.model.Tearsheetderivedtable;
import com.example.demo.repositories.StockDataAdjustedRepository;

@Service
public class StockDataAdjustedService {

	Tearsheetderivedtable tearsheet = new Tearsheetderivedtable();
	List<Tearsheetderivedtable> tearsheetList = new ArrayList<Tearsheetderivedtable>();
	
	// minus 52 week to the current date
	LocalDate today = LocalDate.now(); // Creating the LocalDatetime object
	LocalDate last52weeks = today.minus(52, ChronoUnit.WEEKS);
	
	
	@Autowired
	private StockDataAdjustedRepository repo;

	public List<Entities> getData() {
		return LiveTradingCalcApplication.allDataMap;
	}

	private List<StockDataAdjusted> stockDataAdjustedList;

	public List<StockDataAdjusted> findall() {
		return repo.findAll();
	}

	public List<Entities> findEntities() {
		return LiveTradingCalcApplication.allDataMap;
	}

//	public List<String> getTckLstString() {
//		List<Entities> allDataList = getData();
//		List<String> tcklst = new ArrayList<String>();
//		int size = 0;
//
//		for (Entities e : allDataList) {
//			if (e.getTablename().equals("stocksymbolsforsearchbox")) {
//				for (int i = 0; i < e.getRows().size(); i++) {
//					tcklst.add(e.getRows().get(i).get("StockSymbol"));
//				}
//				size++;
//			}
//
//		}
//		return tcklst;
//	}

	public List<Entities> stockDataAdjustedData() {
		List<Entities> data = getData();
		List<Entities> stockdataadjusteddata = data.stream().filter(m -> m.getTablename().equals("stock_data_adjusted"))
				.collect(Collectors.toList());
		return stockdataadjusteddata;
	}

	public List<Tearsheetderivedtable> fiftyHighClosingPrice() {
		int count = 0;
		
		List<Entities> data = stockDataAdjustedData();

		List<Entities> daysHighList = data.stream().filter(f->LocalDate.parse(String.valueOf(f.getTradingDate())).compareTo(last52weeks)>=0).collect(Collectors.toList());
		
		List<Entities> sortedDaysHighList = daysHighList.stream().sorted(Comparator.comparingDouble(Entities::getClosingPrice).reversed()).collect(Collectors.toList());

		List<Entities> distinctTickerMaxClosingPriceDaysHighList = sortedDaysHighList.stream().filter(distinctByKey(p->p.getTicker())).collect(Collectors.toList());
		
		distinctTickerMaxClosingPriceDaysHighList.stream().forEach(trst->{
			String tick =  trst.getTicker();
			Double fiftyhigh = trst.getClosingPrice();
			
			tearsheet = new Tearsheetderivedtable();
			
			tearsheet.setTicker(tick);
			tearsheet.setFiftyHigh(fiftyhigh);
			
			tearsheetList.add(tearsheet);
			
		});

		System.out.println("Total count::::: " + distinctTickerMaxClosingPriceDaysHighList.size());
		
		return tearsheetList;

	}
	
	public List<Tearsheetderivedtable> fiftyLowClosingPrice() {
		int count = 0;
		
		List<Entities> data = stockDataAdjustedData();

		List<Entities> daysLowList = data.stream().filter(f->LocalDate.parse(String.valueOf(f.getTradingDate())).compareTo(last52weeks)>=0).collect(Collectors.toList());
		
		List<Entities> sortedDaysLowList = daysLowList.stream().sorted(Comparator.comparingDouble(Entities::getClosingPrice)).collect(Collectors.toList());

		List<Entities> distinctTickerMinClosingPriceDaysLowList = sortedDaysLowList.stream().filter(distinctByKey(p->p.getTicker())).collect(Collectors.toList());
		
		distinctTickerMinClosingPriceDaysLowList.stream().forEach(trst->{
			String tick =  trst.getTicker();
			Double fiftylow = trst.getClosingPrice();
			
//			tearsheetList.stream().forEach(t->{
//				if(tearsheetList.size()<=0) {
//					tearsheet.setTicker(tick);
//					tearsheet.setFiftyLow(fiftylow);
//				}else {
//					if(t.getTicker().equals(tick)) {
//						tearsheet.setFiftyLow(fiftylow);
//					}else {
//						tearsheet.setTicker(tick);
//						tearsheet.setFiftyLow(fiftylow);
//					}
//				}
//			});
			
			tearsheet = new Tearsheetderivedtable();
			
			tearsheet.setTicker(tick);
			tearsheet.setFiftyLow(fiftylow);
			
			tearsheetList.add(tearsheet);
			
		});

		System.out.println("Total count::::: " + distinctTickerMinClosingPriceDaysLowList.size());
		
		return tearsheetList;

	}
	
	
	
	
	
	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) 
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
	

	public Date today() {
		final Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

}
