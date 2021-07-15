package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * The persistent class for the stocksymbolsforsearchbox database table.
 * 
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name="Stocksymbolsforsearchbox.findAll", query="SELECT s FROM Stocksymbolsforsearchbox s")
public class Stocksymbolsforsearchbox implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idstocksymbols2;

	private String dataReady;

	@Temporal(TemporalType.DATE)
	private Date firstTradingDate;

	private double nepListShares;

	private String nepseranking;

	private String parentTicker;

	private String sector;

	private String sectorShortForm;

	private String smtmSector;

	private String smtmSectorShortForm;

	private String status;

	@Column(name="stock_full_name")
	private String stockFullName;

	private String stockSymbol;

	private String tickerToSector;

	private String trading;

	private String type;

}