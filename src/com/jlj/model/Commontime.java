package com.jlj.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Commontime entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "commontime", schema = "dbo", catalog = "jtd")
public class Commontime implements java.io.Serializable {

	// Fields

	private Integer id;
	private Sig sig;
	private Integer hour;
	private Integer minute;
	private Integer seconds;
	private Integer workingway;//0表示普通控制方式，1表示黄闪，2表示关灯，3表示协调控制（绿波带），4表示感应控制，5表示中心控制，6未定义；
	private Integer workingprogram;
	private Integer lstime;
	private Integer hdtime;
	private Integer qchdtime;
	private Integer orderid;
	private Integer timetype;
	private Integer t0;
	private Integer t1;
	private Integer t2;
	private Integer t3;
	private Integer t4;
	private Integer t5;
	private Integer t6;
	private Integer t7;
	private Integer t8;
	private Integer t9;
	private Integer t10;
	private Integer t11;
	private Integer t12;
	private Integer t13;
	private Integer t14;
	private Integer t15;
	private Integer t16;
	private Integer t17;
	private Integer t18;
	private Integer t19;
	private Integer t20;
	private Integer t21;
	private Integer t22;
	private Integer t23;
	private Integer t24;
	private Integer t25;
	private Integer t26;
	private Integer t27;
	private Integer t28;
	private Integer t29;
	private Integer t30;
	private Integer t31;
	// Constructors

	/** default constructor */
	public Commontime() {
	}

	/** full constructor */
	public Commontime(Sig sig, Integer hour, Integer minute,
			Integer seconds, Integer workingway, Integer workingprogram,
			Integer lstime, Integer hdtime, Integer qchdtime,Integer orderid,Integer timetype) {
		this.sig = sig;
		this.hour = hour;
		this.minute = minute;
		this.seconds = seconds;
		this.workingway = workingway;
		this.workingprogram = workingprogram;
		this.lstime = lstime;
		this.hdtime = hdtime;
		this.qchdtime = qchdtime;
		this.orderid = orderid;
		this.timetype = timetype;
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

	@Column(name = "workingway")
	public Integer getWorkingway() {
		return this.workingway;
	}

	public void setWorkingway(Integer workingway) {
		this.workingway = workingway;
	}

	@Column(name = "workingprogram")
	public Integer getWorkingprogram() {
		return this.workingprogram;
	}

	public void setWorkingprogram(Integer workingprogram) {
		this.workingprogram = workingprogram;
	}

	@Column(name = "lstime")
	public Integer getLstime() {
		return this.lstime;
	}

	public void setLstime(Integer lstime) {
		this.lstime = lstime;
	}

	@Column(name = "hdtime")
	public Integer getHdtime() {
		return this.hdtime;
	}

	public void setHdtime(Integer hdtime) {
		this.hdtime = hdtime;
	}

	@Column(name = "qchdtime")
	public Integer getQchdtime() {
		return this.qchdtime;
	}

	public void setQchdtime(Integer qchdtime) {
		this.qchdtime = qchdtime;
	}

	@Column(name = "orderid")
	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	@Column(name = "timetype")
	public Integer getTimetype() {
		return timetype;
	}

	public void setTimetype(Integer timetype) {
		this.timetype = timetype;
	}

	@Column(name = "t0")
	public Integer getT0() {
		return t0;
	}

	public void setT0(Integer t0) {
		this.t0 = t0;
	}

	@Column(name = "t1")
	public Integer getT1() {
		return t1;
	}

	public void setT1(Integer t1) {
		this.t1 = t1;
	}

	@Column(name = "t2")
	public Integer getT2() {
		return t2;
	}

	public void setT2(Integer t2) {
		this.t2 = t2;
	}

	@Column(name = "t3")
	public Integer getT3() {
		return t3;
	}

	public void setT3(Integer t3) {
		this.t3 = t3;
	}

	@Column(name = "t4")
	public Integer getT4() {
		return t4;
	}

	public void setT4(Integer t4) {
		this.t4 = t4;
	}

	@Column(name = "t5")
	public Integer getT5() {
		return t5;
	}

	public void setT5(Integer t5) {
		this.t5 = t5;
	}

	@Column(name = "t6")
	public Integer getT6() {
		return t6;
	}

	public void setT6(Integer t6) {
		this.t6 = t6;
	}

	@Column(name = "t7")
	public Integer getT7() {
		return t7;
	}

	public void setT7(Integer t7) {
		this.t7 = t7;
	}

	@Column(name = "t8")
	public Integer getT8() {
		return t8;
	}

	public void setT8(Integer t8) {
		this.t8 = t8;
	}

	@Column(name = "t9")
	public Integer getT9() {
		return t9;
	}

	public void setT9(Integer t9) {
		this.t9 = t9;
	}

	@Column(name = "t10")
	public Integer getT10() {
		return t10;
	}

	public void setT10(Integer t10) {
		this.t10 = t10;
	}

	@Column(name = "t11")
	public Integer getT11() {
		return t11;
	}

	public void setT11(Integer t11) {
		this.t11 = t11;
	}

	@Column(name = "t12")
	public Integer getT12() {
		return t12;
	}

	public void setT12(Integer t12) {
		this.t12 = t12;
	}

	@Column(name = "t13")
	public Integer getT13() {
		return t13;
	}

	public void setT13(Integer t13) {
		this.t13 = t13;
	}

	@Column(name = "t14")
	public Integer getT14() {
		return t14;
	}

	public void setT14(Integer t14) {
		this.t14 = t14;
	}

	@Column(name = "t15")
	public Integer getT15() {
		return t15;
	}

	public void setT15(Integer t15) {
		this.t15 = t15;
	}

	@Column(name = "t16")
	public Integer getT16() {
		return t16;
	}

	public void setT16(Integer t16) {
		this.t16 = t16;
	}

	@Column(name = "t17")
	public Integer getT17() {
		return t17;
	}

	public void setT17(Integer t17) {
		this.t17 = t17;
	}

	@Column(name = "t18")
	public Integer getT18() {
		return t18;
	}

	public void setT18(Integer t18) {
		this.t18 = t18;
	}

	@Column(name = "t19")
	public Integer getT19() {
		return t19;
	}

	public void setT19(Integer t19) {
		this.t19 = t19;
	}

	@Column(name = "t20")
	public Integer getT20() {
		return t20;
	}

	public void setT20(Integer t20) {
		this.t20 = t20;
	}

	@Column(name = "t21")
	public Integer getT21() {
		return t21;
	}

	public void setT21(Integer t21) {
		this.t21 = t21;
	}

	@Column(name = "t22")
	public Integer getT22() {
		return t22;
	}

	public void setT22(Integer t22) {
		this.t22 = t22;
	}

	@Column(name = "t23")
	public Integer getT23() {
		return t23;
	}

	public void setT23(Integer t23) {
		this.t23 = t23;
	}

	@Column(name = "t24")
	public Integer getT24() {
		return t24;
	}

	public void setT24(Integer t24) {
		this.t24 = t24;
	}

	@Column(name = "t25")
	public Integer getT25() {
		return t25;
	}

	public void setT25(Integer t25) {
		this.t25 = t25;
	}

	@Column(name = "t26")
	public Integer getT26() {
		return t26;
	}

	public void setT26(Integer t26) {
		this.t26 = t26;
	}

	@Column(name = "t27")
	public Integer getT27() {
		return t27;
	}

	public void setT27(Integer t27) {
		this.t27 = t27;
	}

	@Column(name = "t28")
	public Integer getT28() {
		return t28;
	}

	public void setT28(Integer t28) {
		this.t28 = t28;
	}

	@Column(name = "t29")
	public Integer getT29() {
		return t29;
	}

	public void setT29(Integer t29) {
		this.t29 = t29;
	}

	@Column(name = "t30")
	public Integer getT30() {
		return t30;
	}

	public void setT30(Integer t30) {
		this.t30 = t30;
	}

	@Column(name = "t31")
	public Integer getT31() {
		return t31;
	}

	public void setT31(Integer t31) {
		this.t31 = t31;
	}

	@Transient
	public Integer[] getTimes(){
		Integer[] times = new Integer[]{this.getT0(),this.getT1(),this.getT2(),this.getT3(),this.getT4(),this.getT5(),this.getT6(),this.getT7(),this.getT8(),this.getT9(),
				this.getT10(),this.getT11(),this.getT12(),this.getT13(),this.getT14(),this.getT15(),this.getT16(),this.getT17(),this.getT18(),this.getT19(),
				this.getT20(),this.getT21(),this.getT22(),this.getT23(),this.getT24(),this.getT25(),this.getT26(),this.getT27(),this.getT28(),this.getT29(),
				this.getT30(),this.getT31()};
		return times;
	}

}