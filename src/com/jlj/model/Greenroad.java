package com.jlj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Sigsystime entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "greenroad", schema = "dbo", catalog = "jtd")
public class Greenroad implements java.io.Serializable {

	// Fields

	private Integer id;
	private Long marklineid;
	private String sigmids;
	private String name;
	private String remark;
	private Integer type;
	// Constructors

	/** default constructor */
	public Greenroad() {
	}

	/** full constructor */
	public Greenroad(Long marklineid, String sigmids, String name, String remark,Integer type) {
		this.marklineid = marklineid;
		this.sigmids = sigmids;
		this.name = name;
		this.remark = remark;
		this.type = type;
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
	@Column(name = "marklineid")
	public Long getMarklineid() {
		return marklineid;
	}

	public void setMarklineid(Long marklineid) {
		this.marklineid = marklineid;
	}

	@Column(name = "sigmids", length = 65535)
	public String getSigmids() {
		return sigmids;
	}

	public void setSigmids(String sigmids) {
		this.sigmids = sigmids;
	}

	@Column(name = "name", length = 30)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "remark", length = 30)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	

}