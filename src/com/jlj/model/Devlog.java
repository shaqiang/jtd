package com.jlj.model;

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
 * Devlog entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "devlog", schema = "dbo", catalog = "jtd")
public class Devlog implements java.io.Serializable {

	// Fields

	private Integer id;
	private Sig sig;
	private String devevent;
	private Date devtime;
	private Integer iserror;

	// Constructors

	/** default constructor */
	public Devlog() {
	}

	/** full constructor */
	public Devlog(Sig sig, String devevent, Date devtime,Integer iserror) {
		this.sig = sig;
		this.devevent = devevent;
		this.devtime = devtime;
		this.iserror = iserror;
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
	@JoinColumn(name = "sgid")
	public Sig getSig() {
		return this.sig;
	}

	public void setSig(Sig sig) {
		this.sig = sig;
	}

	@Column(name = "devevent", length = 100)
	public String getDevevent() {
		return this.devevent;
	}

	public void setDevevent(String devevent) {
		this.devevent = devevent;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "devtime", length = 23)
	public Date getDevtime() {
		return this.devtime;
	}

	public void setDevtime(Date devtime) {
		this.devtime = devtime;
	}

	@Column(name = "iserror")
	public Integer getIserror() {
		return iserror;
	}

	public void setIserror(Integer iserror) {
		this.iserror = iserror;
	}

	
}