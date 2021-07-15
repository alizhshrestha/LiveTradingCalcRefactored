package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the lifekeystats database table.
 * 
 */
@Entity
@Table(name="lifekeystats")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name="Lifekeystat.findAll", query="SELECT l FROM Lifekeystat l")
public class Lifekeystat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idLifeKeyStats;

	private double bookValuePerShare;

	private double changeOverPriorPeriod1;

	private double changeOverPriorPeriod2;

	private double changeOverPriorPeriod3;

	private double changeOverPriorPeriod4;

	private double changeOverPriorPeriod5;

	private String dataSource;

	private double dividendPerShare;

	private double epsAnnualized;

	private double grossProfit;

	private double grossProfitMargin;

	private double growthOverPriorPeriod;


	private double insuranceSector;

	private double keyFinancials;

	private double mps;

	private double netIncome;

	private double netIncomeMargin;

	private double provisionForUnexpiredRisk;

	private int quarter;

	private double reportedPeAnnualized;

	private double returnOnAsset;

	private double returnOnEquity;

	private String statement;

	private String ticker;

	private double totalClaimAmount;

	private double totalNoOfClaims;

	private double totalNoOfPolicies;

	private double totalPremium;

	private double totalRevenue;

	private String year;

}