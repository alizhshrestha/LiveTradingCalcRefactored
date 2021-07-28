package com.example.demo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.common.StockDataAdj;
import com.example.demo.model.Latestprice;
import com.example.demo.common.Entities;
import com.example.demo.common.FloorsheetLive;

@SpringBootApplication
public class LiveTradingCalcApplication implements CommandLineRunner {

	@Autowired
	protected DataSource dataSource;

	public static List<StockDataAdj> allStockData = new ArrayList<StockDataAdj>();
	public static List<Latestprice> latestpriceData = new ArrayList<Latestprice>();
	public static List<Entities> allDataMap = new ArrayList<Entities>();
	
	public static List<FloorsheetLive> allFloorsheetData = new ArrayList<FloorsheetLive>();

	public static void main(String[] args) {
		SpringApplication.run(LiveTradingCalcApplication.class, args);
	}

	
	//initially run and loads all data to List of Entities
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		loadStockDataAdjustedData();
		loadLiveTradingData();
		loadSystemX();
	}
	
	
	// loads Livetrading data to list of Entities
	public List<FloorsheetLive> loadLiveTradingData() throws Exception {
		DatabaseMetaData metaData = dataSource.getConnection().getMetaData();
		ResultSet tables = metaData.getTables("livetrading", null, null, new String[] { "TABLE" });
		
		FloorsheetLive floorsheet = new FloorsheetLive();
		List<FloorsheetLive> floorsheetList = new ArrayList<FloorsheetLive>();
		
		String DB_URL = "jdbc:mysql://localhost:3306/livetrading";
		String USER = "root";
		String PASS = "@1uis9818A";

		int count = 0;
		int totalRecords = 0;
		
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		Statement stmt = conn.createStatement();

		while (tables.next()) {
			String tableName = tables.getString("TABLE_NAME");
			ResultSet columns = metaData.getColumns("livetrading", null, tableName, "%");
			Map<String, String> rowMap = new HashMap<String, String>();

			List<String> columnsName = new ArrayList<String>();

			String QUERY = "select * from " + tableName + " order by timestamp asc";

			while (columns.next()) {
				String column = columns.getString("COLUMN_NAME");
				columnsName.add(column);
			}
			
			if(tableName.equals("floorsheet_live")) {
				floorsheet.setTablename(tableName);
				try {
					System.out.println("..................................."+tableName + ".................................");
					ResultSet rs = stmt.executeQuery(QUERY);
					while (rs.next()) {
						floorsheet = new FloorsheetLive();
						rowMap = new LinkedHashMap<String, String>();
						floorsheet.setTablename(tableName);
						for(String c : columnsName) {
							if(c.equals("stockSymbol")) {
								floorsheet.setStockSymbol(rs.getString("stockSymbol"));
							}else if(c.equals("rate")) {
								floorsheet.setRate(rs.getInt("rate"));
							}else if(c.equals("timestamp")) {
								floorsheet.setTimestamp(rs.getDate("timestamp"));
							}else {
								rowMap.put(c, rs.getString(c));
								floorsheet.setRows(rowMap);
							}
						}	
						allFloorsheetData.add(floorsheet);
						count++;
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
			}
		}
		
		System.out.println("Total Records: " + totalRecords);
		
		return allFloorsheetData;
	}
	

	// loads systemx data to list of Entities
	public List<StockDataAdj> loadStockDataAdjustedData() throws Exception {
		DatabaseMetaData metaData = dataSource.getConnection().getMetaData();
		ResultSet tables = metaData.getTables("systemx", null, null, new String[] { "TABLE" });

		String DB_URL = "jdbc:mysql://localhost:3306/systemx";
		String USER = "root";
		String PASS = "@1uis9818A";

		int count = 0;
		int totalRecords = 0;

		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		Statement stmt = conn.createStatement();

		try {
			stmt.executeUpdate("TRUNCATE tearsheetderivedtable");
			System.out.println("Successfully Truncate tearsheetderivedtable");
		} catch (Exception e) {
			// TODO: handle exception
		}

		String QUERY;

		while (tables.next()) {
			String tableName = tables.getString("TABLE_NAME");
			ResultSet columns = metaData.getColumns("systemx", null, tableName, "%");
			StockDataAdj ec = new StockDataAdj();
			Latestprice lp = new Latestprice();
			Map<String, String> rowMap = new HashMap<String, String>();
			List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();

			List<String> columnsName = new ArrayList<String>();

			if(tableName.equals("stock_data")) {
				QUERY = "select * from " + tableName + " order by trading_date desc";
			}else if(tableName.equals("stock_data_adjusted")) {
				QUERY = "select * from " + tableName + " order by trading_date desc";
			}
			else if(tableName.equals("stock_price_actions_derived")) {
				QUERY = "select * from " + tableName;
			}else if(tableName.equals("tearsheetpercentileindicatortable")) {
				QUERY = "select * from " + tableName;
			}else if (tableName.equals("stock_data_adjusted")) {
				QUERY = "select * from " + tableName + " order by trading_date desc";
			}
			else if(tableName.equals("cbkeystats")){
				QUERY = "select * from " + tableName+ " order by Year desc, Quarter desc";
			}
			else if(tableName.equals("dbkeystats")){
				QUERY = "select * from " + tableName+ " order by Year desc, Quarter desc";
			}
			else if(tableName.equals("financekeystats")){
				QUERY = "select * from " + tableName+ " order by Year desc, Quarter desc";
			}
			else if(tableName.equals("hotelkeystats")){
				QUERY = "select * from " + tableName+ " order by Year desc, Quarter desc";
			}
			else if(tableName.equals("hpkeystats")){
				QUERY = "select * from " + tableName+ " order by Year desc, Quarter desc";
			}
			else if(tableName.equals("lifekeystats")){
				QUERY = "select * from " + tableName+ " order by Year desc, Quarter desc";
			}
			else if(tableName.equals("mfikeystats")){
				QUERY = "select * from " + tableName+ " order by Year desc, Quarter desc";
			}
			else if(tableName.equals("mpkeystats")){
				QUERY = "select * from " + tableName+ " order by Year desc, Quarter desc";
			}
			else if(tableName.equals("nonlifekeystats")){
				QUERY = "select * from " + tableName+ " order by Year desc, Quarter desc";
			}
			else if(tableName.equals("ofkeystats")){
				QUERY = "select * from " + tableName+ " order by Year desc, Quarter desc";
			}
			else if(tableName.equals("telkeystats")){
				QUERY = "select * from " + tableName+ " order by Year desc, Quarter desc";
			}
			else {
				QUERY = "select * from " + tableName;
			}

			while (columns.next()) {
				String column = columns.getString("COLUMN_NAME");
				columnsName.add(column);
			}

			if (tableName.equals("stock_data_adjusted")
			) {
				try {
					System.out.println(tableName + ".................................");
					ResultSet rs = stmt.executeQuery(QUERY);
					while (rs.next()) {
						ec = new StockDataAdj();
						rowMap = new LinkedHashMap<String, String>();
						ec.setTablename(tableName);
						for(String c : columnsName) {
							if(c.equals("ticker")) {
								ec.setTicker(rs.getString("ticker"));
							}else if(c.equals("closingPrice")) {
								ec.setClosingPrice(rs.getDouble("closingPrice"));
							}else if(c.equals("trading_date")) {
								ec.setTradingDate(rs.getDate("trading_date"));
							}else {
								rowMap.put(c, rs.getString(c));
								ec.setRows(rowMap);
							}
						}	
						allStockData.add(ec);
						count++;
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			
		}

		System.out.println("Total Records: " + count);

		return allStockData;
	}
	
	
	//Loads systemx data to list of entities
	public List<Entities> loadSystemX() throws Exception {
		DatabaseMetaData metaData = dataSource.getConnection().getMetaData();
		ResultSet tables = metaData.getTables("systemx", null, null, new String[] { "TABLE" });
		
		String DB_URL = "jdbc:mysql://localhost:3306/systemx";
		String USER = "root";
		String PASS = "@1uis9818A";

		int count = 0;
		int totalRecords = 0;
		
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		Statement stmt = conn.createStatement();
		
		try {
			stmt.executeUpdate("TRUNCATE tearsheetderivedtable");
			System.out.println("Successfully Truncate tearsheetderivedtable");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String QUERY;

		while (tables.next()) {
			String tableName = tables.getString("TABLE_NAME");
			ResultSet columns = metaData.getColumns("systemx", null, tableName, "%");
			Entities ec = new Entities();
			List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
			Map<String, String> rowMap = new HashMap<String, String>();
			
			List<String> columnsName = new ArrayList<String>();

			if(tableName.equals("stock_data")) {
				QUERY = "select * from " + tableName + " order by trading_date desc";
			}else if(tableName.equals("stock_data_adjusted")) {
				QUERY = "select * from " + tableName + " order by trading_date desc";
			}
			else if(tableName.equals("stock_price_actions_derived")) {
				QUERY = "select * from " + tableName;
			}else if(tableName.equals("tearsheetpercentileindicatortable")) {
				QUERY = "select * from " + tableName;
			}else if(tableName.equals("stock_data_adjusted")) {
				QUERY = "select * from " + tableName + " order by closingPrice desc";
			}
			else if(tableName.equals("cbkeystats")){
				QUERY = "select * from " + tableName+ " order by Year desc, Quarter desc";
			}
			else if(tableName.equals("dbkeystats")){
				QUERY = "select * from " + tableName+ " order by Year desc, Quarter desc";
			}
			else if(tableName.equals("financekeystats")){
				QUERY = "select * from " + tableName+ " order by Year desc, Quarter desc";
			}
			else if(tableName.equals("hotelkeystats")){
				QUERY = "select * from " + tableName+ " order by Year desc, Quarter desc";
			}
			else if(tableName.equals("hpkeystats")){
				QUERY = "select * from " + tableName+ " order by Year desc, Quarter desc";
			}
			else if(tableName.equals("lifekeystats")){
				QUERY = "select * from " + tableName+ " order by Year desc, Quarter desc";
			}
			else if(tableName.equals("mfikeystats")){
				QUERY = "select * from " + tableName+ " order by Year desc, Quarter desc";
			}
			else if(tableName.equals("mpkeystats")){
				QUERY = "select * from " + tableName+ " order by Year desc, Quarter desc";
			}
			else if(tableName.equals("nonlifekeystats")){
				QUERY = "select * from " + tableName+ " order by Year desc, Quarter desc";
			}
			else if(tableName.equals("ofkeystats")){
				QUERY = "select * from " + tableName+ " order by Year desc, Quarter desc";
			}
			else if(tableName.equals("telkeystats")){
				QUERY = "select * from " + tableName+ " order by Year desc, Quarter desc";
			}
			else {
				QUERY = "select * from " + tableName;
			}
			
			
			
			while (columns.next()) {
				String column = columns.getString("COLUMN_NAME");
				columnsName.add(column);
			}
			
			if(tableName.equals("stock_data") 
					|| tableName.equals("stock_price_actions_derived")
					|| tableName.equals("stocksymbolsforsearchbox")
					|| tableName.equals("tearsheet_eps")
					|| tableName.equals("tearsheet_pe")
					|| tableName.equals("tearsheet_pbv")
					|| tableName.equals("tearsheet_roe")
					|| tableName.equals("tearsheet_roa")
					|| tableName.equals("stock_data_adjusted")
					|| tableName.equals("tearsheetpercentileindicatortable")
					|| tableName.equals("latestprice")
					|| tableName.equals("cbkeystats")
					|| tableName.equals("dbkeystats")
					|| tableName.equals("financekeystats")
					|| tableName.equals("hotelkeystats")
					|| tableName.equals("hpkeystats")
					|| tableName.equals("lifekeystats")
					|| tableName.equals("mfikeystats")
					|| tableName.equals("mpkeystats")
					|| tableName.equals("nonlifekeystats")
					|| tableName.equals("ofkeystats")
					|| tableName.equals("telkeystats"))
			{
				ec.setTablename(tableName);
				
				try {
					System.out.println(tableName + ".................................");
					ResultSet rs = stmt.executeQuery(QUERY);
					while (rs.next()) {
						rowMap = new LinkedHashMap<String, String>();
						for(String s : columnsName) {
							String rowValue = rs.getString(s);
							rowMap.put(s, rowValue  );
//							columnsValues.add(rs.getString(s));
							totalRecords++;
						}	
						mapList.add(rowMap);
						count++;
					}
					ec.setRows(mapList);

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
				allDataMap.add(ec);
				
			}else {
				
			}
		}
		
		System.out.println("Total Records: " + totalRecords);
		
		return allDataMap;
	}
	
	
	

//	

}
