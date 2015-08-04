package com.jlj.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Usero entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "usero", schema = "dbo", catalog = "jtd")
public class Usero implements java.io.Serializable {

	// Fields

	private Integer id;
	private String username;
	private String password;
	private String uip;
	private Integer ulimit;
	private List<Userarea> userareas = new ArrayList<Userarea>();
	private List<Oplog> oplogs = new ArrayList<Oplog>();

	// Constructors

	/** default constructor */
	public Usero() {
	}

	/** full constructor */
	public Usero(String username, String password, String uip, Integer ulimit,
			List<Userarea> userareas, List<Oplog> oplogs) {
		this.username = username;
		this.password = password;
		this.uip = uip;
		this.ulimit = ulimit;
		this.userareas = userareas;
		this.oplogs = oplogs;
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

	@Column(name = "username", length = 30)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", length = 30)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "uip", length = 20)
	public String getUip() {
		return this.uip;
	}

	public void setUip(String uip) {
		this.uip = uip;
	}

	@Column(name = "ulimit")
	public Integer getUlimit() {
		return this.ulimit;
	}

	public void setUlimit(Integer ulimit) {
		this.ulimit = ulimit;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usero")
	public List<Userarea> getUserareas() {
		return this.userareas;
	}

	public void setUserareas(List<Userarea> userareas) {
		this.userareas = userareas;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usero")
	public List<Oplog> getOplogs() {
		return this.oplogs;
	}

	public void setOplogs(List<Oplog> oplogs) {
		this.oplogs = oplogs;
	}

}