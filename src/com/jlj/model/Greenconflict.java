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
 * Greenconflict entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "greenconflict", schema = "dbo", catalog = "jtd")
public class Greenconflict implements java.io.Serializable {

	// Fields

	private Integer id;
	private Sig sig;
	private String name;
	private Integer l00;
	private Integer l01;
	private Integer l02;
	private Integer l03;
	private Integer l10;
	private Integer l11;
	private Integer l12;
	private Integer l13;
	private Integer l20;
	private Integer l21;
	private Integer l22;
	private Integer l23;
	private Integer l30;
	private Integer l31;
	private Integer l32;
	private Integer l33;

	// Constructors

	/** default constructor */
	public Greenconflict() {
	}

	/** full constructor */
	public Greenconflict(Sig sig, String name, Integer l00, Integer l01,
			Integer l02, Integer l03, Integer l10, Integer l11, Integer l12,
			Integer l13, Integer l20, Integer l21, Integer l22, Integer l23,
			Integer l30, Integer l31, Integer l32, Integer l33) {
		this.sig = sig;
		this.name = name;
		this.l00 = l00;
		this.l01 = l01;
		this.l02 = l02;
		this.l03 = l03;
		this.l10 = l10;
		this.l11 = l11;
		this.l12 = l12;
		this.l13 = l13;
		this.l20 = l20;
		this.l21 = l21;
		this.l22 = l22;
		this.l23 = l23;
		this.l30 = l30;
		this.l31 = l31;
		this.l32 = l32;
		this.l33 = l33;
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

	@Column(name = "name", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "l00")
	public Integer getL00() {
		return this.l00;
	}

	public void setL00(Integer l00) {
		this.l00 = l00;
	}

	@Column(name = "l01")
	public Integer getL01() {
		return this.l01;
	}

	public void setL01(Integer l01) {
		this.l01 = l01;
	}

	@Column(name = "l02")
	public Integer getL02() {
		return this.l02;
	}

	public void setL02(Integer l02) {
		this.l02 = l02;
	}

	@Column(name = "l03")
	public Integer getL03() {
		return this.l03;
	}

	public void setL03(Integer l03) {
		this.l03 = l03;
	}

	@Column(name = "l10")
	public Integer getL10() {
		return this.l10;
	}

	public void setL10(Integer l10) {
		this.l10 = l10;
	}

	@Column(name = "l11")
	public Integer getL11() {
		return this.l11;
	}

	public void setL11(Integer l11) {
		this.l11 = l11;
	}

	@Column(name = "l12")
	public Integer getL12() {
		return this.l12;
	}

	public void setL12(Integer l12) {
		this.l12 = l12;
	}

	@Column(name = "l13")
	public Integer getL13() {
		return this.l13;
	}

	public void setL13(Integer l13) {
		this.l13 = l13;
	}

	@Column(name = "l20")
	public Integer getL20() {
		return this.l20;
	}

	public void setL20(Integer l20) {
		this.l20 = l20;
	}

	@Column(name = "l21")
	public Integer getL21() {
		return this.l21;
	}

	public void setL21(Integer l21) {
		this.l21 = l21;
	}

	@Column(name = "l22")
	public Integer getL22() {
		return this.l22;
	}

	public void setL22(Integer l22) {
		this.l22 = l22;
	}

	@Column(name = "l23")
	public Integer getL23() {
		return this.l23;
	}

	public void setL23(Integer l23) {
		this.l23 = l23;
	}

	@Column(name = "l30")
	public Integer getL30() {
		return this.l30;
	}

	public void setL30(Integer l30) {
		this.l30 = l30;
	}

	@Column(name = "l31")
	public Integer getL31() {
		return this.l31;
	}

	public void setL31(Integer l31) {
		this.l31 = l31;
	}

	@Column(name = "l32")
	public Integer getL32() {
		return this.l32;
	}

	public void setL32(Integer l32) {
		this.l32 = l32;
	}

	@Column(name = "l33")
	public Integer getL33() {
		return this.l33;
	}

	public void setL33(Integer l33) {
		this.l33 = l33;
	}

}