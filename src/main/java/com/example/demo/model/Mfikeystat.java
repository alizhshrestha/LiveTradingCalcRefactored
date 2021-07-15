package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the mfikeystats database table.
 * 
 */
@Entity
@Table(name="mfikeystats")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name="Mfikeystat.findAll", query="SELECT m FROM Mfikeystat m")
public class Mfikeystat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idMicroFinanceKeyStats;

	private double averageYield;

	private double baseRate;

	private double bookValuePerShare;

	private double capitalFundToRwa;

	private double costOfFunds;

	private double creditDepositRatioAsPerNrbCalculations;

	private String dataSource;

	private double dividendPerShare;

	private double epsAnnualized;

	private double financialSector;

	private double grossProfit;

	private double grossProfitMargin;

	private double growthOverPriorPeriod;

	private double keyFinancials;

	private double latestCapitalization;

	private double marketCapitalization;

	private double mps;

	private double netIncome;

	private double netIncomeMargin;

	private double netInterestSpread;

	private double netLiquidAsset;

	private double nonPerformingLoanNplToTotalLoan;

	private double outstandingShares;

	private String particulars;

	private int quarter;

	private double reportedPeAnnualized;

	private double returnOnAsset;

	private double returnOnEquity;

	private double sharePrice;

	private String ticker;

	private double tier1Capital;

	private double tier1CapitalRatio;

	private double tier2Capital;

	private double tier2CapitalRatio;

	private double totalCapital;

	private double totalLoanLossProvisionToTotalNpl;

	private double totalRevenue;

	private String year;

}