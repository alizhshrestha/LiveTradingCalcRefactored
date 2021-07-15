package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the tearsheet_pe database table.
 * 
 */
@Entity
@Table(name="tearsheet_pe")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name="TearsheetPe.findAll", query="SELECT t FROM TearsheetPe t")
public class TearsheetPe implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private double pe_D;

	private double pe_D_percentile;

	private double pe_D_sectorhigh;

	private double pe_D_sectorlow;

	@Column(name="pe_f")
	private double peF;

	@Column(name="pe_f_percentile")
	private double peFPercentile;

	@Column(name="pe_f_sectorhigh")
	private double peFSectorhigh;

	@Column(name="pe_f_sectorlow")
	private double peFSectorlow;

	@Column(name="pe_mrq")
	private double peMrq;

	@Column(name="pe_mrq_percentile")
	private double peMrqPercentile;

	@Column(name="pe_mrq_sectorhigh")
	private double peMrqSectorhigh;

	@Column(name="pe_mrq_sectorlow")
	private double peMrqSectorlow;

	@Column(name="pe_ttm")
	private double peTtm;

	@Column(name="pe_ttm_percentile")
	private double peTtmPercentile;

	@Column(name="pe_ttm_sectorhigh")
	private double peTtmSectorhigh;

	@Column(name="pe_ttm_sectorlow")
	private double peTtmSectorlow;

	private String sector;

	private String ticker;
}