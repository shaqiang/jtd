package com.jlj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Sigsystime entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tqsig", schema = "dbo", catalog = "jtd")
public class Tqsig implements java.io.Serializable {

	// Fields

	private Integer id;
	private Greenroad greenroad;
	private String number;
	private String tqdatastr;
	private Integer tqstatus;
	private String lat;
	private String lng;
	private String name;
	private Integer orderid;
	// Constructors

	/** default constructor */
	public Tqsig() {
	}

	/** full constructor */
	public Tqsig(Greenroad greenroad, String number, String tqdatastr, Integer tqstatus,
			String lat, String lng,String name,Integer orderid) {
		this.greenroad = greenroad;
		this.number = number;
		this.tqdatastr = tqdatastr;
		this.tqstatus = tqstatus;
		this.lat = lat;
		this.lng = lng;
		this.name = name;
		this.orderid = orderid;
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
	@JoinColumn(name = "grid")
	public Greenroad getGreenroad() {
		return greenroad;
	}

	public void setGreenroad(Greenroad greenroad) {
		this.greenroad = greenroad;
	}

	@Column(name = "number", length = 10)
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Column(name = "tqdatastr", length = 65535)
	public String getTqdatastr() {
		return tqdatastr;
	}

	public void setTqdatastr(String tqdatastr) {
		this.tqdatastr = tqdatastr;
	}

	@Column(name = "tqstatus")
	public Integer getTqstatus() {
		return tqstatus;
	}

	public void setTqstatus(Integer tqstatus) {
		this.tqstatus = tqstatus;
	}

	@Column(name = "lat", length = 30)
	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	@Column(name = "lng", length = 30)
	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "orderid")
	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	

}