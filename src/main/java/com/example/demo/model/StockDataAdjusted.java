package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * The persistent class for the stock_data_adjusted database table.
 * 
 */
@Entity
@Table(name="stock_data_adjusted")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name="StockDataAdjusted.findAll", query="SELECT s FROM StockDataAdjusted s")
public class StockDataAdjusted implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idstock_data")
	private int idstockData;

	private double amount;

	private double closingPrice;

	@Column(name="company_full_name")
	private String companyFullName;

	private String dataSource;

	@Column(name="diff_rs")
	private double diffRs;

	private double high;

	private double low;

	private double open;

	private String ticker;

	@Temporal(TemporalType.DATE)
	@Column(name="trading_date")
	private Date tradingDate;

	private double transactions;

	private double volume;

}