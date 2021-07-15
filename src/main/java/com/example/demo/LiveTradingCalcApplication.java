package com.example.demo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
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

import com.example.demo.common.Entities;
import com.example.demo.model.StockDataAdjusted;
import com.example.demo.repositories.StockDataAdjustedRepository;

@SpringBootApplication
public class LiveTradingCalcApplication implements CommandLineRunner {

	@Autowired
	protected DataSource dataSource;

//	List<StockDataAdjusted>

	public static List<Entities> allDataMap = new ArrayList<Entities>();

	public static void main(String[] args) {
		SpringApplication.run(LiveTradingCalcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		loadSystemXData();
		showTables();

	}

	public List<Entities> showTables() throws Exception {
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
			List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
			Map<String, String> rowMap = new HashMap<String, String>();

			List<String> columnsName = new ArrayList<String>();

			if (tableName.equals("stock_data_adjusted")) {
				QUERY = "select * from " + tableName + " order by trading_date desc";
			} else {
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
						ec = new Entities();
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
						allDataMap.add(ec);
						count++;
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			} 
			
//			if (tableName.equals("stocksymbolsforsearchbox")
//					) {
//						try {
//							System.out.println(tableName + ".................................");
//							ResultSet rs = stmt.executeQuery(QUERY);
//							while (rs.next()) {
//								ec = new Entities();
//								rowMap = new LinkedHashMap<String, String>();
//								ec.setTablename(tableName);
//								for(String c : columnsName) {
//									if(c.equals("ticker")) {
//										ec.setTicker(rs.getString("ticker"));
//									}else if(c.equals("closingPrice")) {
//										ec.setClosingPrice(rs.getDouble("closingPrice"));
//									}else if(c.equals("trading_date")) {
//										ec.setTradingDate(rs.getDate("trading_date"));
//									}else {
//										rowMap.put(c, rs.getString(c));
//										ec.setRows(rowMap);
//									}
//								}	
//								allDataMap.add(ec);
//								count++;
//							}
//						} catch (Exception e) {
//							// TODO: handle exception
//							e.printStackTrace();
//						}
//					} 
//			
			
//			stocksymbolsforsearchbox
		}

		System.out.println("Total Records: " + count);

		return allDataMap;
	}

//	

}
