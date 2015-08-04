package com.jlj.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Step entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "step", schema = "dbo", catalog = "jtd")
public class Step implements java.io.Serializable {

	// Fields

	private Integer id;
	private Solution solution;
	private String phasename;
	private String stepname;
	private Integer orderid;
	private List<Road> roads = new ArrayList<Road>();

	// Constructors

	/** default constructor */
	public Step() {
	}

	/** full constructor */

	public Step(Solution solution, String phasename, String stepname, Integer orderid,
			List<Road> roads) {
		this.solution = solution;
		this.phasename = phasename;
		this.stepname = stepname;
		this.orderid = orderid;
		this.roads = roads;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "soid")
	public Solution getSolution() {
		return this.solution;
	}

	public void setSolution(Solution solution) {
		this.solution = solution;
	}

	
	@Column(name = "phasename", length = 30)
	public String getPhasename() {
		return phasename;
	}

	public void setPhasename(String phasename) {
		this.phasename = phasename;
	}

	@Column(name = "stepname", length = 30)
	public String getStepname() {
		return this.stepname;
	}

	public void setStepname(String stepname) {
		this.stepname = stepname;
	}

	@Column(name = "orderid")
	public Integer getOrderid() {
		return this.orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "step")
	public List<Road> getRoads() {
		return this.roads;
	}

	public void setRoads(List<Road> roads) {
		this.roads = roads;
	}

}