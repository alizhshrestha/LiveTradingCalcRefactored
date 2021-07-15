package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the hotelkeystats database table.
 * 
 */
@Entity
@Table(name="hotelkeystats")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name="Hotelkeystat.findAll", query="SELECT h FROM Hotelkeystat h")
public class Hotelkeystat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idhotelkeystats;

	private double bookValuePerShare;

	private String dataSource;

	private double dividendPerShare;

	private double epsAnnualized;

	private double grossProfit;

	private double grossProfitMargin;

	private double growthOverPriorPeriod;

	private double keyFinancials;

	private double mps;

	private double netIncome;

	private double netIncomeMargin;

	private double particulars;

	private int quarter;

	private double reportedPeAnnualized;

	private double returnOnAsset;

	private double returnOnEquity;

	private String ticker;

	private double totalRevenue;

	private String year;

}