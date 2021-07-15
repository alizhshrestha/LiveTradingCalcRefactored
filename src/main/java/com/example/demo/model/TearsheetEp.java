package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the tearsheet_eps database table.
 * 
 */
@Entity
@Table(name="tearsheet_eps")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name="TearsheetEp.findAll", query="SELECT t FROM TearsheetEp t")
public class TearsheetEp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private double eps_D;

	private double eps_D_percentile;

	private double eps_D_sectorhigh;

	private double eps_D_sectorlow;

	@Column(name="eps_f")
	private double epsF;

	@Column(name="eps_f_percentile")
	private double epsFPercentile;

	@Column(name="eps_f_sectorhigh")
	private double epsFSectorhigh;

	@Column(name="eps_f_sectorlow")
	private double epsFSectorlow;

	@Column(name="eps_mrq")
	private double epsMrq;

	@Column(name="eps_mrq_percentile")
	private double epsMrqPercentile;

	@Column(name="eps_mrq_sectorhigh")
	private double epsMrqSectorhigh;

	@Column(name="eps_mrq_sectorlow")
	private double epsMrqSectorlow;

	@Column(name="eps_ttm")
	private double epsTtm;

	@Column(name="eps_ttm_percentile")
	private double epsTtmPercentile;

	@Column(name="eps_ttm_sectorhigh")
	private double epsTtmSectorhigh;

	@Column(name="eps_ttm_sectorlow")
	private double epsTtmSectorlow;

	private String sector;

	private String ticker;

}