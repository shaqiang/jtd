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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Solution entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "solution", schema = "dbo", catalog = "jtd")
public class Solution implements java.io.Serializable {

	// Fields

	private Integer id;
	private Sig sig;
	private String soluname;
	private Integer orderid;

	private List<Step> steps = new ArrayList<Step>();


	// Constructors

	/** default constructor */
	public Solution() {
	}

	/** full constructor */
	public Solution(Sig sig, String soluname, Integer orderid, List<Step> steps) {

		this.sig = sig;
		this.soluname = soluname;
		this.orderid = orderid;
		this.steps = steps;
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
	@JoinColumn(name = "signid")
	public Sig getSig() {
		return this.sig;
	}

	public void setSig(Sig sig) {
		this.sig = sig;
	}

	@Column(name = "soluname", length = 30)
	public String getSoluname() {
		return this.soluname;
	}

	public void setSoluname(String soluname) {
		this.soluname = soluname;
	}

	@Column(name = "orderid")
	public Integer getOrderid() {
		return this.orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "solution")
	public List<Step> getSteps() {
		return steps;
	}

	public void setSteps(List<Step> steps) {
		this.steps = steps;

	}

}