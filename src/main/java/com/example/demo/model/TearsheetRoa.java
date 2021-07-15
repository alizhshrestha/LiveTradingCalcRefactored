package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the tearsheet_roa database table.
 * 
 */
@Entity
@Table(name="tearsheet_roa")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name="TearsheetRoa.findAll", query="SELECT t FROM TearsheetRoa t")
public class TearsheetRoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private double roa_D;

	private double roa_D_percentile;

	private double roa_D_sectorhigh;

	private double roa_D_sectorlow;

	@Column(name="roa_f")
	private double roaF;

	@Column(name="roa_f_percentile")
	private double roaFPercentile;

	@Column(name="roa_f_sectorhigh")
	private double roaFSectorhigh;

	@Column(name="roa_f_sectorlow")
	private double roaFSectorlow;

	@Column(name="roa_mrq")
	private double roaMrq;

	@Column(name="roa_mrq_percentile")
	private double roaMrqPercentile;

	@Column(name="roa_mrq_sectorhigh")
	private double roaMrqSectorhigh;

	@Column(name="roa_mrq_sectorlow")
	private double roaMrqSectorlow;

	@Column(name="roa_ttm")
	private double roaTtm;

	@Column(name="roa_ttm_percentile")
	private double roaTtmPercentile;

	@Column(name="roa_ttm_sectorhigh")
	private double roaTtmSectorhigh;

	@Column(name="roa_ttm_sectorlow")
	private double roaTtmSectorlow;

	private String sector;

	private String ticker;

}