package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the tearsheet_roe database table.
 * 
 */
@Entity
@Table(name="tearsheet_roe")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name="TearsheetRoe.findAll", query="SELECT t FROM TearsheetRoe t")
public class TearsheetRoe implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private double roe_D;

	private double roe_D_percentile;

	private double roe_D_sectorhigh;

	private double roe_D_sectorlow;

	@Column(name="roe_f")
	private double roeF;

	@Column(name="roe_f_percentile")
	private double roeFPercentile;

	@Column(name="roe_f_sectorhigh")
	private double roeFSectorhigh;

	@Column(name="roe_f_sectorlow")
	private double roeFSectorlow;

	@Column(name="roe_mrq")
	private double roeMrq;

	@Column(name="roe_mrq_percentile")
	private double roeMrqPercentile;

	@Column(name="roe_mrq_sectorhigh")
	private double roeMrqSectorhigh;

	@Column(name="roe_mrq_sectorlow")
	private double roeMrqSectorlow;

	@Column(name="roe_ttm")
	private double roeTtm;

	@Column(name="roe_ttm_percentile")
	private double roeTtmPercentile;

	@Column(name="roe_ttm_sectorhigh")
	private double roeTtmSectorhigh;

	@Column(name="roe_ttm_sectorlow")
	private double roeTtmSectorlow;

	private String sector;

	private String ticker;

}