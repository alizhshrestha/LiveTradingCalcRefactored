package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the telkeystats database table.
 * 
 */
@Entity
@Table(name="telkeystats")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name="Telkeystat.findAll", query="SELECT t FROM Telkeystat t")
public class Telkeystat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idTelKeyStats;

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

	private int quarter;

	private double reportedPeAnnualized;

	private double returnOnAsset;

	private double returnOnEquity;

	private String statement;

	private String ticker;

	private double totalRevenue;

	private String year;

}