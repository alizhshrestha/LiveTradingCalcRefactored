package com.example.demo.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.LiveTradingCalcApplication;
import com.example.demo.common.FloorsheetLive;
import com.example.demo.common.StockDataAdj;
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
	
	Map<String, Double> tickerClosingPriceMap = new HashMap<String, Double>();
	
	
	@Autowired
	private StockDataAdjustedRepository repo;

	public List<StockDataAdj> getStockData() {
		return LiveTradingCalcApplication.allStockData;
	}
	
	public List<FloorsheetLive> getFloorsheetLiveData() {
		return LiveTradingCalcApplication.allFloorsheetData;
	}

	private List<StockDataAdjusted> stockDataAdjustedList;

	public List<StockDataAdjusted> findall() {
		return repo.findAll();
	}
	
	public List<StockDataAdj> stockDataAdjustedData() {
		List<StockDataAdj> data = getStockData();
		List<StockDataAdj> stockdataadjusteddata = data.stream().filter(m -> m.getTablename().equals("stock_data_adjusted"))
				.collect(Collectors.toList());
		return stockdataadjusteddata;
	}
	
	public List<FloorsheetLive> floorsheetLiveData() {
		List<FloorsheetLive> data = getFloorsheetLiveData();
		List<FloorsheetLive> floorsheetlivedata = data.stream().filter(m -> m.getTablename().equals("floorsheet_live"))
				.collect(Collectors.toList());
		return floorsheetlivedata;
	}

	public List<Tearsheetderivedtable> fiftyHighClosingPrice() {
		int count = 0;
		
		List<StockDataAdj> data = stockDataAdjustedData();
		List<String> tckList = new ArrayList<String>();
		List<Double> closingPriceList = new ArrayList<Double>();

		List<StockDataAdj> daysHighList = data.stream().filter(f->LocalDate.parse(String.valueOf(f.getTradingDate())).compareTo(last52weeks)>=0).collect(Collectors.toList());
		
		data.stream().filter(td->LocalDate.parse(String.valueOf(td.getTradingDate())).equals(today)).forEach(td->{
			Double cls = td.getClosingPrice();
			tickerClosingPriceMap.put(td.getTicker(), cls);
		});
		
		List<StockDataAdj> sortedDaysHighList = daysHighList.stream().sorted(Comparator.comparingDouble(StockDataAdj::getClosingPrice).reversed()).collect(Collectors.toList());
		
		List<StockDataAdj> distinctTickerMaxClosingPriceDaysHighList = sortedDaysHighList.stream().filter(distinctByKey(p->p.getTicker())).collect(Collectors.toList());
		
		distinctTickerMaxClosingPriceDaysHighList.stream().forEach(trst->{
			String tick =  trst.getTicker();
			Double fiftyhightck = trst.getClosingPrice();
			
			tearsheet = new Tearsheetderivedtable();
			
			tearsheet.setTicker(tick);
			tearsheet.setFiftyHigh(fiftyhightck);
			
			tearsheetList.add(tearsheet);
			
		});
		
		
		//For 52weekHighLowPercentile
		distinctTickerMaxClosingPriceDaysHighList.stream().forEach(tk->{
			String tick = tk.getTicker();
			tckList.add(tick);
		});
		
		tckList.stream().forEach(k->{
			List<Double> closingList = new ArrayList<Double>();
			List<StockDataAdj> stdadjList = sortedDaysHighList.stream().filter(t->t.getTicker().equals(k)).collect(Collectors.toList());
			stdadjList.stream().forEach(cls -> {
				Double cl = Double.valueOf(cls.getClosingPrice());
				closingPriceList.add(cl);
			});
			
//			double d = dObj.doubleValue();
			Double tckClsPrc = tickerClosingPriceMap.get(k);

			tearsheetList.stream().filter(tckl->tckl.getTicker().equals(k)).forEach(tckl->{
				if(tckClsPrc!=null) {
					Double pct = getPercentileDataSet(closingPriceList,tckClsPrc.doubleValue()).get(1);
//					tckl.setDaysHighLowPercentile(pct);
					tckl.setFiftyTwoHighLowPercentile(pct);
				}
			});
			
		});

		System.out.println("Total count::::: " + distinctTickerMaxClosingPriceDaysHighList.size());
		
		return tearsheetList;

	}
	
	public List<Tearsheetderivedtable> fiftyLowClosingPrice() {
		int count = 0;
		
		List<StockDataAdj> data = stockDataAdjustedData();

		List<StockDataAdj> daysLowList = data.stream().filter(f->LocalDate.parse(String.valueOf(f.getTradingDate())).compareTo(last52weeks)>=0).collect(Collectors.toList());
		
		List<StockDataAdj> sortedDaysLowList = daysLowList.stream().sorted(Comparator.comparingDouble(StockDataAdj::getClosingPrice)).collect(Collectors.toList());

		List<StockDataAdj> distinctTickerMinClosingPriceDaysLowList = sortedDaysLowList.stream().filter(distinctByKey(p->p.getTicker())).collect(Collectors.toList());
		
		distinctTickerMinClosingPriceDaysLowList.stream().forEach(trst->{
			String tick =  trst.getTicker();
			Double fiftylowtck = trst.getClosingPrice();
			
			tearsheet = new Tearsheetderivedtable();
			
			tearsheet.setTicker(tick);
			tearsheet.setFiftyLow(fiftylowtck);
			
			tearsheetList.add(tearsheet);
			
		});

		System.out.println("Total count::::: " + distinctTickerMinClosingPriceDaysLowList.size());
		
		return tearsheetList;

	}
	
	public List<Tearsheetderivedtable> calculateDaysHighLowPercentile() {
		List<FloorsheetLive> floorsheetliveData = getFloorsheetLiveData();
		
		Double percentile = 0.0;

//		List<StockDataAdj> distinctTickerMinClosingPriceDaysLowList = sortedDaysLowList.stream().filter(distinctByKey(p->p.getTicker())).collect(Collectors.toList());
		//distinct ticker collect
		List<FloorsheetLive> distinctTickerLiveData = floorsheetliveData.stream().filter(distinctByKey(p->p.getStockSymbol())).collect(Collectors.toList());
		List<String> tickerList = new ArrayList<String>();
		distinctTickerLiveData.stream().forEach(f->{
			String tck = f.getStockSymbol();
			tickerList.add(tck);
		});
		
		tickerList.stream().forEach(k->{
			List<Double> rateList = new ArrayList<Double>();
			List<FloorsheetLive> flrshtList = floorsheetliveData.stream().filter(t->t.getStockSymbol().equals(k)).collect(Collectors.toList());
			flrshtList.stream().forEach(rate -> {
				Double rt = Double.valueOf(rate.getRate());
				rateList.add(rt);
			});
			
//			double d = dObj.doubleValue();
			Double tckClsPrc = tickerClosingPriceMap.get(k);

			tearsheetList.stream().filter(tckl->tckl.getTicker().equals(k)).forEach(tckl->{
				if(tckClsPrc!=null) {
					Double pct = getPercentileDataSet(rateList,tckClsPrc.doubleValue()).get(1);
					tckl.setDaysHighLowPercentile(pct);
				}
			});
			
		});

		System.out.println("Ticker size::::::::: " + tickerList.size());
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

	static List<Double> getPercentileDataSet(List<Double> variableList, double variable) {
		double upperBound = 0;
		double lowerBound = 0;
		double percentile = 0;
		double position = 0;
		for (int count = 0; count < variableList.size(); count++) {
			// System.out.println("PerCompare:"+variable+":"+variableList.get(count));
			if (variable == variableList.get(count)) {
				// System.out.println("Yes");
				position = (double) count + 1;
			}
		}
		// System.out.println("Position:"+position);
		if (variableList.size() > 0) {
			percentile = (position / variableList.size()) * 100;
			// System.out.println(percentile);
			upperBound = variableList.get(variableList.size() - 1);
			lowerBound = variableList.get(0);
		}

		List<Double> dataSet = new ArrayList<>();
		dataSet.addAll(Arrays.asList(upperBound, percentile, lowerBound));

		return dataSet;
	}
	
	
}
