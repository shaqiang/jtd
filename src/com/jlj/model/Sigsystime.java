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
@Table(name = "sigsystime", schema = "dbo", catalog = "jtd")
public class Sigsystime implements java.io.Serializable {

	// Fields

	private Integer id;
	private Sig sig;
	private Integer year;
	private Integer month;
	private Integer day;
	private String week;
	private Integer hour;
	private Integer minute;
	private Integer seconds;

	// Constructors

	/** default constructor */
	public Sigsystime() {
	}

	/** full constructor */
	public Sigsystime(Sig sig, Integer year, Integer month, Integer day,
			String week, Integer hour, Integer minute, Integer seconds) {
		this.sig = sig;
		this.year = year;
		this.month = month;
		this.day = day;
		this.week = week;
		this.hour = hour;
		this.minute = minute;
		this.seconds = seconds;
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

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "signid")
	public Sig getSig() {
		return this.sig;
	}

	public void setSig(Sig sig) {
		this.sig = sig;
	}

	@Column(name = "year")
	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Column(name = "month")
	public Integer getMonth() {
		return this.month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	@Column(name = "day")
	public Integer getDay() {
		return this.day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	@Column(name = "week", length = 10)
	public String getWeek() {
		return this.week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	@Column(name = "hour")
	public Integer getHour() {
		return this.hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	@Column(name = "minute")
	public Integer getMinute() {
		return this.minute;
	}

	public void setMinute(Integer minute) {
		this.minute = minute;
	}

	@Column(name = "seconds")
	public Integer getSeconds() {
		return this.seconds;
	}

	public void setSeconds(Integer seconds) {
		this.seconds = seconds;
	}

}