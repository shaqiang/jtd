package com.jlj.model;

import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Oplog entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "oplog", schema = "dbo", catalog = "jtd")
public class Oplog implements java.io.Serializable {

	// Fields

	private Integer id;
	private Usero usero;
	private String opevent;
	private String loginip;
	private String optype;
	private Date optime;
	private String signumber;

	// Constructors

	/** default constructor */
	public Oplog() {
	}

	/** full constructor */
	public Oplog(Usero usero, String opevent, String loginip, String optype,String signumber,
			Date optime) {
		this.usero = usero;
		this.opevent = opevent;
		this.loginip = loginip;
		this.optype = optype;
		this.signumber = signumber;
		this.optime = optime;
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
	@JoinColumn(name = "uid")
	public Usero getUsero() {
		return this.usero;
	}

	public void setUsero(Usero usero) {
		this.usero = usero;
	}

	@Column(name = "opevent", length = 100)
	public String getOpevent() {
		return this.opevent;
	}

	public void setOpevent(String opevent) {
		this.opevent = opevent;
	}

	@Column(name = "loginip", length = 20)
	public String getLoginip() {
		return this.loginip;
	}

	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}

	@Column(name = "optype", length = 20)
	public String getOptype() {
		return this.optype;
	}

	public void setOptype(String optype) {
		this.optype = optype;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "optime", length = 23)
	public Date getOptime() {
		return this.optime;
	}

	public void setOptime(Date optime) {
		this.optime = optime;
	}

	@Column(name = "signumber", length = 20)
	public String getSignumber() {
		return signumber;
	}

	public void setSignumber(String signumber) {
		this.signumber = signumber;
	}

	
}