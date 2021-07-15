package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * The persistent class for the latestprice database table.
 * 
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name="Latestprice.findAll", query="SELECT l FROM Latestprice l")
public class Latestprice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private double amount;

	private double closingprice;

	@Column(name="company_full_name")
	private String companyFullName;

	@Column(name="diff_rs")
	private double diffRs;

	private double high;

	private double low;

	private double open;

	private String ticker;

	@Temporal(TemporalType.DATE)
	@Column(name="TRADING_DATE")
	private Date tradingDate;

	private double transaction;

	private double volume;
}