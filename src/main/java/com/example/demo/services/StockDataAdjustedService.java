package com.example.demo.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.LiveTradingCalcApplication;
import com.example.demo.common.ClosingPrice;
import com.example.demo.common.Entities;
import com.example.demo.common.FloorsheetLive;
import com.example.demo.common.StockDataAdj;
import com.example.demo.model.StockDataAdjusted;
import com.example.demo.model.Tearsheetderivedtable;
import com.example.demo.repositories.StockDataAdjustedRepository;
import com.example.demo.repositories.TearsheetderivedtableRepository;

@Service
public class StockDataAdjustedService {

	Tearsheetderivedtable tearsheet = new Tearsheetderivedtable();
	List<Tearsheetderivedtable> starttearsheetList = new ArrayList<Tearsheetderivedtable>();
	List<StockDataAdjusted> stockDataAdjustedList;

	// minus 52 week to the current date
	LocalDate todayDate = findPrevDay(LocalDate.now()); // Creating the LocalDatetime object
//	LocalDate todayDate = LocalDate.now(); // Creating the LocalDatetime object
	LocalDate last52weeks = todayDate.minus(52, ChronoUnit.WEEKS);

	Map<String, Double> tickerClosingPriceMap = new HashMap<String, Double>(); // maps each ticker closing price

	// -------------------
	// Filtered map list of latestpricelive table
	List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
	Map<String, Double> ltsClosingPriceMap = new HashMap<String, Double>();

	Date latest_date = new Date();
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	TearsheetderivedtableRepository repo;

	@Autowired
	private StockDataAdjustedRepository stockDataRepo;

	public List<Entities> getData() {
		return LiveTradingCalcApplication.allDataMap;
	}

	// gets tickerlist from stocksymbolsforsearchbox table
	public List<String> getTckLstString() {
		List<Entities> allDataList = getData();
		List<String> tcklst = new ArrayList<String>();
		int size = 0;

		for (Entities e : allDataList) {
			if (e.getTablename().equals("stocksymbolsforsearchbox")) {
				for (int i = 0; i < e.getRows().size(); i++) {
					tcklst.add(e.getRows().get(i).get("StockSymbol"));
				}
				size++;
			}

		}

		return tcklst;
	}

	// loads ticker initially in tearsheet
	public List<Tearsheetderivedtable> getTckLst() {
		List<Entities> allDataList = getData();
		List<String> tcklst = getTckLstString();

		List<Tearsheetderivedtable> trshtlst = new ArrayList<Tearsheetderivedtable>();
		int size = 0;

		for (String s : tcklst) {
			size++;
			Tearsheetderivedtable trshttbl = new Tearsheetderivedtable();
			trshttbl.setTicker(s);
			starttearsheetList.add(trshttbl);
		}
		return starttearsheetList;
	}

	
	public List<StockDataAdj> getStockData() {
		return LiveTradingCalcApplication.allStockData;
	}

	public List<FloorsheetLive> getFloorsheetLiveData() {
		return LiveTradingCalcApplication.allFloorsheetData;
	}

	public List<StockDataAdjusted> findall() {
		return stockDataRepo.findAll();
	}

	
	
	// 1> 
	// initial fill data
	public List<Tearsheetderivedtable> fillData() {
		List<Entities> allDataList = getData();
		List<Tearsheetderivedtable> tearsheetlst = getTckLst();

		// Latestprice calc
		List<Entities> latestPriceList = allDataList.stream().filter(m -> m.getTablename().equals("latestprice"))
				.collect(Collectors.toList());
		latestPriceList.stream().map(m -> m.getRows()).forEach(m -> {
			m.stream().forEach(f -> {
				tearsheetlst.stream().forEach(tear -> {
					if (tear.getTicker().equals(f.get("TICKER"))) {
						Double amount = Double.valueOf(f.get("amount"));
						Double volume = Double.valueOf(f.get("volume"));
						Double daysHigh = Double.valueOf(f.get("high"));
						Double daysLow = Double.valueOf(f.get("low"));
						Double weightedAvePrice = 0.0;
						if (amount.equals(null) || volume.equals(null)) {
							tear.setWeightedAvePrice(null);
						} else if (volume.equals(0)) {
							tear.setWeightedAvePrice(null);
						} else {
							weightedAvePrice = amount / volume;
							if(weightedAvePrice.isNaN()) {
								tear.setWeightedAvePrice(null);
							}else {
								tear.setWeightedAvePrice(weightedAvePrice);
							}
//							}
							tear.setVolume(amount);
							tear.setDaysHigh(daysHigh);
							tear.setDaysLow(daysLow);
							try {
								Date tradingDate = new SimpleDateFormat("yyyy-MM-dd").parse(f.get("TRADING_DATE"));
								tear.setTradingDate(tradingDate);
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
					}
				});

			});
		});

		// stocksymbolsforsearchbox
		List<Entities> tearstocksymbollist = allDataList.stream()
				.filter(m -> m.getTablename().equals("stocksymbolsforsearchbox")).collect(Collectors.toList());
		tearstocksymbollist.stream().map(m -> m.getRows()).forEach(m -> {
			m.stream().forEach(f -> {
				tearsheetlst.stream().forEach(tear -> {
					if (tear.getTicker().equals(f.get("StockSymbol"))) {
						String SmtmSector = f.get("SmtmSector");
						if (SmtmSector != null) {
							tear.setSector(SmtmSector);
						} else {
							tear.setSector(null);
						}
					}
				});

			});
		});

		// tearsheet_eps
		List<Entities> tearsheeEpsList = allDataList.stream().filter(m -> m.getTablename().equals("tearsheet_eps"))
				.collect(Collectors.toList());
		tearsheeEpsList.stream().map(m -> m.getRows()).forEach(m -> {
			m.stream().forEach(f -> {
				tearsheetlst.stream().forEach(tear -> {
					if (tear.getTicker().equals(f.get("ticker"))) {
						String eps_f = f.get("eps_f");
						if (eps_f != null) {
							tear.setEps(Double.valueOf(eps_f));
						} else {
							tear.setEps(null);
						}
					}
				});

			});
		});

		// tearsheet_eps
		List<Entities> tearsheePeList = allDataList.stream().filter(m -> m.getTablename().equals("tearsheet_pe"))
				.collect(Collectors.toList());
		tearsheePeList.stream().map(m -> m.getRows()).forEach(m -> {
			m.stream().forEach(f -> {
				tearsheetlst.stream().forEach(tear -> {
					if (tear.getTicker().equals(f.get("ticker"))) {
						String pe_f = f.get("pe_f");
						if (pe_f != null) {
							tear.setPe(Double.valueOf(pe_f));
						} else {
							tear.setPe(null);
						}
					}
				});

			});
		});

		// tearsheet_pbv
		List<Entities> tearsheePbvList = allDataList.stream().filter(m -> m.getTablename().equals("tearsheet_pbv"))
				.collect(Collectors.toList());
		tearsheePbvList.stream().map(m -> m.getRows()).forEach(m -> {
			m.stream().forEach(f -> {
				tearsheetlst.stream().forEach(tear -> {
					if (tear.getTicker().equals(f.get("ticker"))) {
						String pbv = f.get("pbv");
						if (pbv != null) {
							tear.setPbv(Double.valueOf(pbv));
						} else {
							tear.setPbv(null);
						}
					}
				});

			});
		});

		// tearsheet_roe
		List<Entities> tearsheeRoeList = allDataList.stream().filter(m -> m.getTablename().equals("tearsheet_roe"))
				.collect(Collectors.toList());
		tearsheeRoeList.stream().map(m -> m.getRows()).forEach(m -> {
			m.stream().forEach(f -> {
				tearsheetlst.stream().forEach(tear -> {
					if (tear.getTicker().equals(f.get("ticker"))) {
						String roe_f = f.get("roe_f");
						if (roe_f != null) {
							tear.setRoe(Double.valueOf(roe_f));
						} else {
							tear.setRoe(null);
						}
					}
				});

			});
		});

		// tearsheet_roa
		List<Entities> tearsheeRoaList = allDataList.stream().filter(m -> m.getTablename().equals("tearsheet_roa"))
				.collect(Collectors.toList());
		tearsheeRoaList.stream().map(m -> m.getRows()).forEach(m -> {
			m.stream().forEach(f -> {
				tearsheetlst.stream().forEach(tear -> {
					if (tear.getTicker().equals(f.get("ticker"))) {
						String roa_f = f.get("roa_f");
						if (roa_f != null) {
							tear.setRoa(Double.valueOf(roa_f));
						} else {
							tear.setRoa(null);
						}
					}
				});

			});
		});

		return tearsheetlst;

	}

	
	// 2> 
	// OneEightyDayAverage calculation
	//needs initial fillData for fullFill()
	public List<Tearsheetderivedtable> fullFill() throws ParseException {
		List<Tearsheetderivedtable> tearsheetList = fillData();

		List<Entities> allDataList = getData();
		// date of now
		Date latest_date = today();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		ClosingPrice clsprc = new ClosingPrice();
		List<Entities> stockdataList = allDataList.stream().filter(f -> f.getTablename().equals("stock_data"))
				.collect(Collectors.toList());
		stockdataList.stream().forEach(e -> {
			tearsheetList.stream().forEach(te -> {
				clsprc.setClosingPrice(0.0);
				clsprc.setTotalClosingPrice(0.0);
				Double averageClosingPrice = 0.0;
				e.getRows().stream().filter(fe -> te.getTicker().equals(fe.get("ticker"))).forEach(fe -> {
					// For 180 days trading date calculation
					try {

						long days = getDifferenceDays(dateFormat.parse(fe.get("trading_date")), latest_date);

						if (dateFormat.parse(fe.get("trading_date")).equals(latest_date)) {
							ltsClosingPriceMap.put(fe.get("ticker"), Double.valueOf(fe.get("closingPrice")));
						}

						if (days == 0) {
							ltsClosingPriceMap.put(fe.get("ticker"), Double.valueOf(fe.get("closingPrice")));
						}

						if (days <= 180) {
							clsprc.setClosingPrice(Double.valueOf(fe.get("closingPrice")));
							clsprc.setTotalClosingPrice(clsprc.getTotalClosingPrice() + clsprc.getClosingPrice());
							clsprc.setAverageCount(clsprc.getAverageCount() + 1);
						}
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				});

				if (clsprc.getAverageCount() != 0) {
					averageClosingPrice = clsprc.getTotalClosingPrice() / clsprc.getAverageCount();
					te.setOneEightyDayAverage(averageClosingPrice);
				} else {
					te.setOneEightyDayAverage(null);
				}

				clsprc.setTotalClosingPrice(0.0);
				clsprc.setAverageCount(0);

			});
		});

		return tearsheetList;

	}


	// 3>
	// 52weekHighLowPercentile and fiftyHigh calculation
	//needs fullFill() for fiftyHighClosingPrice()
	public List<Tearsheetderivedtable> fiftyHighClosingPrice() throws ParseException {
		int count = 0;

		
		List<StockDataAdj> fiftyTwoWeeksData = getFiftyTwoWeeksData(); //gets fifty two weeks data list and store it
		List<Entities> data = getData(); //gets all data existing from database
		List<Tearsheetderivedtable> tearsheetList = calculateProfitabilityChange(); // gets computed tearsheet list

		List<Entities> stockdataEntitieslist = data.stream().filter(f -> f.getTablename().equals("stock_data_adjusted"))
				.collect(Collectors.toList()); // gets stock_data_adjusted data from data variable

		stockdataEntitieslist.stream().forEach(e -> {
			e.getRows().forEach(fe -> {
				if (LocalDate.parse(String.valueOf(fe.get("trading_date"))).equals(todayDate)) {
					tickerClosingPriceMap.put(fe.get("ticker"), Double.valueOf(fe.get("closingPrice"))); //puts latest closing price of each ticker to tickerClosingPriceMap list
				}
			});
		});

		List<StockDataAdj> sortedDaysHighList = fiftyTwoWeeksData.stream()
				.sorted(Comparator.comparingDouble(StockDataAdj::getClosingPrice).reversed()) //sorts fiftyTwoWeeksData according to closing price desc
				.collect(Collectors.toList());
		
		List<StockDataAdj> sortedDaysLowList = fiftyTwoWeeksData.stream()
				.sorted(Comparator.comparingDouble(StockDataAdj::getClosingPrice)) //sorts fiftyTwoWeeksData according to closing price asc
				.collect(Collectors.toList());

		List<StockDataAdj> distinctTickerMaxClosingPriceDaysHighList = sortedDaysHighList.stream()
				.filter(distinctByKey(p -> p.getTicker())).collect(Collectors.toList()); //get max stock data adjusted data

		distinctTickerMaxClosingPriceDaysHighList.stream().forEach(trst -> {
			tearsheetList.stream().filter(f -> f.getTicker().equals(trst.getTicker())).forEach(f -> {
				Double fiftyhightck = trst.getClosingPrice();
				f.setFiftyHigh(fiftyhightck); //sets highest closing price of each ticker to tearsheetList
			});

		});

		// For 52weekHighLowPercentile
		tearsheetList.stream().forEach(k -> {
			List<Double> closingList = new ArrayList<Double>();
			List<StockDataAdj> stdadjList = sortedDaysLowList.stream().filter(t -> t.getTicker().equals(k.getTicker()))
					.collect(Collectors.toList()); // collects each ticker StockDataAdjusted list to stdadjList list

			stdadjList.stream().forEach(cls -> {
				Double cl = Double.valueOf(cls.getClosingPrice()); // gets each ticker closing price list and add to closingList 
				closingList.add(cl);
			});

			Double tckClsPrc = tickerClosingPriceMap.get(k.getTicker()); // gets highest closing price of that ticker
			if (tckClsPrc != null) {
				Double pct = getPercentileDataSet(closingList, tckClsPrc.doubleValue()).get(1); //get 52weekHighLowPercentile and set to tearsheetList
				k.setFiftyTwoHighLowPercentile(pct);
			}

		});
		;

		return tearsheetList;

	}

	// 4>
	// DaysHighLowPercentile calculation
	// needs fiftyHighClosingPrice() for calculateDaysHighLowPercentile()
	public List<Tearsheetderivedtable> calculateDaysHighLowPercentile() throws ParseException {
		List<FloorsheetLive> floorsheetliveData = getFloorsheetLiveData();
		List<Tearsheetderivedtable> tearsheetList = fiftyHighClosingPrice();

		Double percentile = 0.0;

		tearsheetList.stream().forEach(f -> {
			List<Double> rateList = new ArrayList<Double>();
			
//			List<StockDataAdj> sortedDaysLowList = fiftyTwoWeeksData.stream()
//					.sorted(Comparator.comparingDouble(StockDataAdj::getClosingPrice)) //sorts fiftyTwoWeeksData according to closing price desc
//					.collect(Collectors.toList());
			
			List<FloorsheetLive> sortedDaysHighLowPercentileList = floorsheetliveData.stream()
					.sorted(Comparator.comparingDouble(FloorsheetLive::getRate)) //sorts floorsheetliveData according to rate asc
					.collect(Collectors.toList());
			
			List<FloorsheetLive> flrshtList = sortedDaysHighLowPercentileList.stream()
					.filter(t -> t.getStockSymbol().equals(f.getTicker())).collect(Collectors.toList());
			flrshtList.stream().forEach(rate -> {
				Double rt = Double.valueOf(rate.getRate());
				rateList.add(rt);
			});

			Double tckClsPrc = tickerClosingPriceMap.get(f.getTicker());
			System.out.println();
			if (tckClsPrc != null) {
				Double pct = getPercentileDataSet(rateList, tckClsPrc.doubleValue()).get(1);

				f.setDaysHighLowPercentile(pct);
			}
		});

		return tearsheetList;
	}

	// 5>
	// ProfitabilityChange and SentimentChange calculation
	// needs calculateDaysHighLowPercentile() for calculateProfitabilityChange()
	public List<Tearsheetderivedtable> calculateProfitabilityChange() throws ParseException {
		List<Entities> allDataList = getData();
		List<Tearsheetderivedtable> tearsheetList = fullFill();

		Map<String, Double> reportedPeAnnualizedMap = new HashMap<String, Double>();

		tearsheetList.stream().forEach(t -> {
			try {
				String sectorKeyStatsTableName = getSector(t.getSector());
				allDataList.stream().forEach(f -> {
					if (f.getTablename().equals(sectorKeyStatsTableName)) {
						f.getRows().stream().forEach(fe -> {
							if (t.getTicker().equals(fe.get("Ticker"))) {
								List<Map<String, String>> datalist = f.getRows().stream()
										.filter(ff -> ff.get("Ticker").equals(t.getTicker()))
										.collect(Collectors.toList());
								Map<String, String> firstdata = datalist.get(0);
								Map<String, String> seconddata = datalist.get(1);
								if (firstdata.get("EpsAnnualized") != null && seconddata.get("EpsAnnualized") != null) {
									Double epsAnnualized = Double.valueOf(firstdata.get("EpsAnnualized"));
									Double prevAnnualized = Double.valueOf(seconddata.get("EpsAnnualized"));

									if (prevAnnualized != 0) {
										Double profitabilityChange = (epsAnnualized - prevAnnualized)
												/ Math.abs(prevAnnualized);
										t.setProfitabilityChange(profitabilityChange);
									} else {
										t.setProfitabilityChange(null);
									}
								} else {
									t.setProfitabilityChange(null);
								}

								if (firstdata.get("ReportedPeAnnualized") != null) {
									Double reportedPeAnnualized = Double.valueOf(firstdata.get("ReportedPeAnnualized"));
									reportedPeAnnualizedMap.put(t.getTicker(), reportedPeAnnualized);
								} else {
									t.setSentimentChange(null);
								}
							}
						});
					}

				});
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

		tearsheetList.stream().forEach(t -> {
			allDataList.stream().forEach(f -> {
				if (f.getTablename().equals("tearsheet_pe")) {
					f.getRows().stream().forEach(fee -> {
						if (fee.get("ticker").equals(t.getTicker())) {
							if (fee.get("pe_D") != null) {
								Double pe_D = Double.valueOf(fee.get("pe_D"));
								Double reportedPeAnnualized = 0.0, sentimentChange = 0.0;
								reportedPeAnnualized = reportedPeAnnualizedMap.get(t.getTicker());

								System.out.println("ReportedPeAnnualized: " + reportedPeAnnualized);

								if (reportedPeAnnualized == null || reportedPeAnnualized.equals(0)) {
//									reportedPeAnnualized = reportedPeAnnualizedMap.get(t.getTicker());
									t.setSentimentChange(null);
								} else {
									sentimentChange = (pe_D - reportedPeAnnualized) / Math.abs(reportedPeAnnualized);
									t.setSentimentChange(sentimentChange);
								}

							}

						}
					});
				}
			});
		});

		return tearsheetList;

	}

	// 6>
	// fiftyTwoWeeksData load
	// needs calculateProfitabilityChange() for getFiftyTwoWeeksData()
	public List<StockDataAdj> getFiftyTwoWeeksData() {
		List<StockDataAdj> fiftyTwoWeeksData = new ArrayList<StockDataAdj>();

		List<Entities> data = getData();

		List<Entities> stockdataEntitieslist = data.stream().filter(f -> f.getTablename().equals("stock_data_adjusted"))
				.collect(Collectors.toList());
		stockdataEntitieslist.stream().forEach(e -> {
			e.getRows().forEach(fe -> {
				if (LocalDate.parse(String.valueOf(fe.get("trading_date"))).compareTo(last52weeks) >= 0) {
					StockDataAdj sda = new StockDataAdj();
					sda.setTablename(e.getTablename());
					sda.setClosingPrice(Double.valueOf(fe.get("closingPrice")));
					sda.setTicker(fe.get("ticker"));
					try {
						sda.setTradingDate(dateFormat.parse(fe.get("trading_date")));
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					sda.setRows(fe);
					fiftyTwoWeeksData.add(sda);
				}
			});
		});

		return fiftyTwoWeeksData;

	}

	// 7>
	// FiftyLow calculation
	// needs getFiftyTwoWeeksData() for fiftyLowClosingPrice()
	public List<Tearsheetderivedtable> fiftyLowClosingPrice() throws ParseException {
		int count = 0;

		List<StockDataAdj> fiftyTwoWeeksData = getFiftyTwoWeeksData();

		List<Tearsheetderivedtable> tearsheetList = calculateDaysHighLowPercentile();

		List<StockDataAdj> sortedDaysLowList = fiftyTwoWeeksData.stream()
				.sorted(Comparator.comparingDouble(StockDataAdj::getClosingPrice)).collect(Collectors.toList());

		List<StockDataAdj> distinctTickerMinClosingPriceDaysLowList = sortedDaysLowList.stream()
				.filter(distinctByKey(p -> p.getTicker())).collect(Collectors.toList());

		distinctTickerMinClosingPriceDaysLowList.stream().forEach(trst -> {
			tearsheetList.stream().filter(f -> f.getTicker().equals(trst.getTicker())).forEach(f -> {
				Double fiftylowtck = trst.getClosingPrice();
				f.setFiftyLow(fiftylowtck);
			});

		});

		return tearsheetList;

	}
	
	
	// 8>
	// return full calculated Tearsheetderivedtable
	public List<Tearsheetderivedtable> doTearsheet() throws ParseException {
		return fiftyLowClosingPrice();
	}

	// save data to TearsheetDerivedTable
	public String saveTearsheetDerivedTable() throws ParseException {
		List<Tearsheetderivedtable> trshtlst = new ArrayList<Tearsheetderivedtable>();
		if (trshtlst.size() == 0) {
			trshtlst = fiftyLowClosingPrice();
		}

		if (repo.count() <= 0) {
			System.out.println("------------------Empty dataset--------------");
		} else {
			repo.deleteAll();
			System.out.println("------------------Successfully deleted all data--------------");
		}

		for (Tearsheetderivedtable t : trshtlst) {
			repo.save(t);
		}
		return "Saved Successfully";
	}

	
	// Additional required functions
	// gets Sector function of each ticker
	public String getSector(String sector) throws ParseException {
		switch (sector) {
		case "Commercial Banks":
			return "cbkeystats";
		case "Hydro Power":
			return "hpkeystats";
		case "Life Insurance":
			return "lifekeystats";
		case "Manufacturing And Processing":
			return "mpkeystats";
		case "Finance":
			return "financekeystats";
		case "Microcredit":
			return "mfikeystats";
		case "Organized Fund":
			return "ofkeystats";
		case "Non Life Insurance":
			return "nonlifekeystats";
		case "Development Bank":
			return "dbkeystats";
		case "Telecom":
			return "telkeystats";
		case "Hotels":
			return "hotelkeystats";
		default:
			return null;
		}

	}
	
	// for distinct data
	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

	// today date
	public Date today() {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	// function for percentile calculation
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

	// previous day calculation
	public Date getPreviousDate(String str, int k) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(str);
		Calendar cal = Calendar.getInstance();
//	    subtracting a day
		cal.setTime(date);
		cal.add(cal.DATE, -k);

		return cal.getTime();
	}

	//get yesterday date and converts to string
	public String getYesterdayDateString() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		return dateFormat.format(today());
	}

	// calculating difference between dates
	public static long getDifferenceDays(Date d1, Date d2) {
		long diff = d2.getTime() - d1.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	
	//gets next day
	public LocalDate findNextDay(LocalDate localdate) {
		return localdate.plusDays(1);
	}

	
	//gets previous day
	public LocalDate findPrevDay(LocalDate localdate) {
		return localdate.minusDays(1);
	}

}
