package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the tearsheet_pbv database table.
 * 
 */
@Entity
@Table(name="tearsheet_pbv")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name="TearsheetPbv.findAll", query="SELECT t FROM TearsheetPbv t")
public class TearsheetPbv implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private double pbv;

	@Column(name="pbv_percentile")
	private double pbvPercentile;

	@Column(name="pbv_sectorhigh")
	private double pbvSectorhigh;

	@Column(name="pbv_sectorlow")
	private double pbvSectorlow;

	private String sector;

	private String ticker;

}