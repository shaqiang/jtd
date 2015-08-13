package com.jlj.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	private Integer timetype;
	private Integer timexf;
	private Date starttime;
	
	private List<Tqsig> tqsigs = new ArrayList<Tqsig>();
	// Constructors

	/** default constructor */
	public Greenroad() {
	}

	/** full constructor */
	public Greenroad(Long marklineid, String sigmids, String name, String remark,Integer type,Integer timetype,Integer timexf,Date starttime) {
		this.marklineid = marklineid;
		this.sigmids = sigmids;
		this.name = name;
		this.remark = remark;
		this.type = type;
		this.timetype = timetype;
		this.timexf = timexf;
		this.starttime = starttime;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "greenroad")
	public List<Tqsig> getTqsigs() {
		return tqsigs;
	}

	public void setTqsigs(List<Tqsig> tqsigs) {
		this.tqsigs = tqsigs;
	}

	@Column(name = "timetype")
	public Integer getTimetype() {
		return timetype;
	}

	public void setTimetype(Integer timetype) {
		this.timetype = timetype;
	}

	@Column(name = "timexf")
	public Integer getTimexf() {
		return timexf;
	}

	public void setTimexf(Integer timexf) {
		this.timexf = timexf;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "starttime", length = 23)
	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	

}