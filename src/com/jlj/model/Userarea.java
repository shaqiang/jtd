package com.jlj.model;

import java.util.ArrayList;
import java.util.List;

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
 * Userarea entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "userarea", schema = "dbo", catalog = "jtd")
public class Userarea implements java.io.Serializable {

	// Fields

	private Integer id;
	private Usero usero;
	private String uareaname;
	private String lat;
	private String lng;
	private Integer size;
	private List<Sig> sigs = new ArrayList<Sig>();

	// Constructors

	/** default constructor */
	public Userarea() {
	}

	/** full constructor */
	public Userarea(Usero usero, String uareaname, String lat, String lng,
			Integer size, List<Sig> sigs) {
		this.usero = usero;
		this.uareaname = uareaname;
		this.lat = lat;
		this.lng = lng;
		this.size = size;
		this.sigs = sigs;
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

	@Column(name = "uareaname", length = 30)
	public String getUareaname() {
		return this.uareaname;
	}

	public void setUareaname(String uareaname) {
		this.uareaname = uareaname;
	}

	@Column(name = "lat", length = 30)
	public String getLat() {
		return this.lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	@Column(name = "lng", length = 30)
	public String getLng() {
		return this.lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	@Column(name = "size")
	public Integer getSize() {
		return this.size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userarea")
	public List<Sig> getSigs() {
		return this.sigs;
	}

	public void setSigs(List<Sig> sigs) {
		this.sigs = sigs;
	}

}