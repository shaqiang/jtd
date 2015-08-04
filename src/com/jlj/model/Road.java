package com.jlj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Road entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "road", schema = "dbo", catalog = "jtd")
public class Road implements java.io.Serializable {

	// Fields

	private Integer id;
	private Step step;
	private Integer roadtype;
	private Integer leftcolor;
	private Integer linecolor;
	private Integer rightcolor;
	private Integer rxcolor;

	// Constructors

	/** default constructor */
	public Road() {
	}

	/** full constructor */
	public Road(Step step, Integer roadtype, Integer leftcolor,
			Integer linecolor, Integer rightcolor, Integer rxcolor) {
		this.step = step;
		this.roadtype = roadtype;
		this.leftcolor = leftcolor;
		this.linecolor = linecolor;
		this.rightcolor = rightcolor;
		this.rxcolor = rxcolor;
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
	@JoinColumn(name = "stepid")
	public Step getStep() {
		return this.step;
	}

	public void setStep(Step step) {
		this.step = step;
	}

	@Column(name = "roadtype")
	public Integer getRoadtype() {
		return this.roadtype;
	}

	public void setRoadtype(Integer roadtype) {
		this.roadtype = roadtype;
	}

	@Column(name = "leftcolor")
	public Integer getLeftcolor() {
		return this.leftcolor;
	}

	public void setLeftcolor(Integer leftcolor) {
		this.leftcolor = leftcolor;
	}

	@Column(name = "linecolor")
	public Integer getLinecolor() {
		return this.linecolor;
	}

	public void setLinecolor(Integer linecolor) {
		this.linecolor = linecolor;
	}

	@Column(name = "rightcolor")
	public Integer getRightcolor() {
		return this.rightcolor;
	}

	public void setRightcolor(Integer rightcolor) {
		this.rightcolor = rightcolor;
	}

	@Column(name = "rxcolor")
	public Integer getRxcolor() {
		return this.rxcolor;
	}

	public void setRxcolor(Integer rxcolor) {
		this.rxcolor = rxcolor;
	}

}