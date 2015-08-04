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
 * @author jlj
 */
@Entity
@Table(name = "issuedcommand", schema = "dbo", catalog = "jtd")
public class Issuedcommand implements java.io.Serializable {

	// Fields

	private Integer id;
	private Sig sig;
	private String name;
	private Integer number;
	private String datas;

	// Constructors

	/** default constructor */
	public Issuedcommand() {
	}

	/** full constructor */
	public Issuedcommand(Sig sig, String name, Integer number, String datas) {
		this.sig = sig;
		this.name =name;
		this.number = number;
		this.datas = datas;
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

	@Column(name = "name", length = 30)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "number")
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Column(name = "datas", length = 65535)
	public String getDatas() {
		return datas;
	}

	public void setDatas(String datas) {
		this.datas = datas;
	}

	

}