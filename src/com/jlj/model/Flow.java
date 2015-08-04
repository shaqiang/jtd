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
 * Greenconflict entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "flow", schema = "dbo", catalog = "jtd")
public class Flow implements java.io.Serializable {

	// Fields

	private Integer id;
	private Sig sig;
	private Integer dleft;
	private Integer dline;
	private Integer dright;
	private Integer nleft;
	private Integer nline;
	private Integer nright;
	private Integer xleft;
	private Integer xline;
	private Integer xright;
	private Integer bleft;
	private Integer bline;
	private Integer bright;
	private Date time;

	// Constructors

	

	/** default constructor */
	public Flow() {
	}

	/** full constructor */
	public Flow(Integer id, Sig sig, Integer dleft, Integer dline,
			Integer dright, Integer nleft, Integer nline, Integer nright,
			Integer xleft, Integer xline, Integer xright, Integer bleft,
			Integer bline, Integer bright, Date time) {
		super();
		this.id = id;
		this.sig = sig;
		this.dleft = dleft;
		this.dline = dline;
		this.dright = dright;
		this.nleft = nleft;
		this.nline = nline;
		this.nright = nright;
		this.xleft = xleft;
		this.xline = xline;
		this.xright = xright;
		this.bleft = bleft;
		this.bline = bline;
		this.bright = bright;
		this.time = time;
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
	@JoinColumn(name = "sigid")
	public Sig getSig() {
		return this.sig;
	}

	public void setSig(Sig sig) {
		this.sig = sig;
	}

	@Column(name = "dleft")
	public Integer getDleft() {
		return dleft;
	}

	public void setDleft(Integer dleft) {
		this.dleft = dleft;
	}

	@Column(name = "dline")
	public Integer getDline() {
		return dline;
	}

	public void setDline(Integer dline) {
		this.dline = dline;
	}

	@Column(name = "dright")
	public Integer getDright() {
		return dright;
	}

	public void setDright(Integer dright) {
		this.dright = dright;
	}

	@Column(name = "nleft")
	public Integer getNleft() {
		return nleft;
	}

	public void setNleft(Integer nleft) {
		this.nleft = nleft;
	}

	@Column(name = "nline")
	public Integer getNline() {
		return nline;
	}

	public void setNline(Integer nline) {
		this.nline = nline;
	}

	@Column(name = "nright")
	public Integer getNright() {
		return nright;
	}

	public void setNright(Integer nright) {
		this.nright = nright;
	}

	@Column(name = "xleft")
	public Integer getXleft() {
		return xleft;
	}

	public void setXleft(Integer xleft) {
		this.xleft = xleft;
	}

	@Column(name = "xline")
	public Integer getXline() {
		return xline;
	}

	public void setXline(Integer xline) {
		this.xline = xline;
	}

	@Column(name = "xright")
	public Integer getXright() {
		return xright;
	}

	public void setXright(Integer xright) {
		this.xright = xright;
	}

	@Column(name = "bleft")
	public Integer getBleft() {
		return bleft;
	}

	public void setBleft(Integer bleft) {
		this.bleft = bleft;
	}

	@Column(name = "bline")
	public Integer getBline() {
		return bline;
	}

	public void setBline(Integer bline) {
		this.bline = bline;
	}

	@Column(name = "bright")
	public Integer getBright() {
		return bright;
	}

	public void setBright(Integer bright) {
		this.bright = bright;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time", length = 23)
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	
	

}