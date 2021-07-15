package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the nonlifekeystats database table.
 * 
 */
@Entity
@Table(name="nonlifekeystats")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name="Nonlifekeystat.findAll", query="SELECT n FROM Nonlifekeystat n")
public class Nonlifekeystat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idnonlifekeystats;

	private double bookValuePerShare;

	private double changePop1;

	private double changePop2;

	private double changePop3;

	private double changePop4;

	private double changePop5;

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

	private double totalClaimUnexpiredRisk;

	private double totalNoOfClaims;

	private double totalNoOfPolicies;

	private double totalPremium;

	private double totalRenewedPolicies;

	private double totalRevenue;

	private String year;

}