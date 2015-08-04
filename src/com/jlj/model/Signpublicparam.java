package com.jlj.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * Signpublicparam entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "signpublicparam", schema = "dbo", catalog = "jtd")
public class Signpublicparam implements java.io.Serializable {

	// Fields

	private Integer id;
	private Sig sig;
	private Integer qchdtime;
	private Integer kjhstime;
	private String number;
	private String comparam;
	private Integer checkflow;
	private String innermark;
	private Integer workingset;
	private Integer mon;
	private Integer tue;
	private Integer wes;
	private Integer thir;
	private Integer fra;
	private Integer sata;
	private Integer sun;
	private Date paramversion;
	private Integer gmintime;
	private Integer gmaxtime;
	private Integer zdbctime;
	private Integer countdownmode;
	private Integer jgkz;
	private String ip;
	private String centerport;
	private String centerip;
	private Integer checklight;
	private Integer xrfxtime;
	private Integer cycle;
	private Integer xyxr;
	private String sbnetmastk;
	private String gateway;
	private Integer specialmonth0;
	private Integer specialmonth1;
	private Integer specialmonth2;
	private Integer specialmonth3;
	private Integer specialmonth4;
	private Integer specialmonth5;
	private Integer specialmonth6;
	private Integer specialmonth7;
	private Integer specialmonth8;
	private Integer specialmonth9;
	private Integer specialmonth10;
	private Integer specialmonth11;
	private Integer specialmonth12;
	private Integer specialmonth13;
	private Integer specialmonth14;
	private Integer specialmonth15;
	private Integer specialmonth16;
	private Integer specialmonth17;
	private Integer specialmonth18;
	private Integer specialmonth19;
	private Integer specialmonth20;
	private Integer specialmonth21;
	private Integer specialmonth22;
	private Integer specialmonth23;
	private Integer specialday0;
	private Integer specialday1;
	private Integer specialday2;
	private Integer specialday3;
	private Integer specialday4;
	private Integer specialday5;
	private Integer specialday6;
	private Integer specialday7;
	private Integer specialday8;
	private Integer specialday9;
	private Integer specialday10;
	private Integer specialday11;
	private Integer specialday12;
	private Integer specialday13;
	private Integer specialday14;
	private Integer specialday15;
	private Integer specialday16;
	private Integer specialday17;
	private Integer specialday18;
	private Integer specialday19;
	private Integer specialday20;
	private Integer specialday21;
	private Integer specialday22;
	private Integer specialday23;
	

	// Constructors

	/** default constructor */
	public Signpublicparam() {
	}

	/** full constructor */
	public Signpublicparam(Sig sig, Integer qchdtime, Integer kjhstime,
			String number, String comparam, Integer checkflow,
			String innermark, Integer workingset, Integer mon, Integer tue,
			Integer wes, Integer thir, Integer fra, Integer sata, Integer sun,
			Date paramversion, Integer gmintime, Integer gmaxtime,
			Integer zdbctime, Integer countdownmode, Integer jgkz, String ip,
			String centerport, String centerip, Integer checklight,
			Integer xrfxtime, Integer cycle, Integer xyxr, String sbnetmastk,
			String gateway, Integer specialmonth0, Integer specialmonth1,
			Integer specialmonth2, Integer specialmonth3,
			Integer specialmonth4, Integer specialmonth5,
			Integer specialmonth6, Integer specialmonth7,
			Integer specialmonth8, Integer specialmonth9,
			Integer specialmonth10, Integer specialmonth11,
			Integer specialmonth12, Integer specialmonth13,
			Integer specialmonth14, Integer specialmonth15,
			Integer specialmonth16, Integer specialmonth17,
			Integer specialmonth18, Integer specialmonth19,
			Integer specialmonth20, Integer specialmonth21,
			Integer specialmonth22, Integer specialmonth23,
			Integer specialday0, Integer specialday1, Integer specialday2,
			Integer specialday3, Integer specialday4, Integer specialday5,
			Integer specialday6, Integer specialday7, Integer specialday8,
			Integer specialday9, Integer specialday10, Integer specialday11,
			Integer specialday12, Integer specialday13, Integer specialday14,
			Integer specialday15, Integer specialday16, Integer specialday17,
			Integer specialday18, Integer specialday19, Integer specialday20,
			Integer specialday21, Integer specialday22, Integer specialday23) {
		this.sig = sig;
		this.qchdtime = qchdtime;
		this.kjhstime = kjhstime;
		this.number = number;
		this.comparam = comparam;
		this.checkflow = checkflow;
		this.innermark = innermark;
		this.workingset = workingset;
		this.mon = mon;
		this.tue = tue;
		this.wes = wes;
		this.thir = thir;
		this.fra = fra;
		this.sata = sata;
		this.sun = sun;
		this.paramversion = paramversion;
		this.gmintime = gmintime;
		this.gmaxtime = gmaxtime;
		this.zdbctime = zdbctime;
		this.countdownmode = countdownmode;
		this.jgkz = jgkz;
		this.ip = ip;
		this.centerport = centerport;
		this.centerip = centerip;
		this.checklight = checklight;
		this.xrfxtime = xrfxtime;
		this.cycle = cycle;
		this.xyxr = xyxr;
		this.sbnetmastk = sbnetmastk;
		this.gateway = gateway;
		this.specialmonth0 = specialmonth0;
		this.specialmonth1 = specialmonth1;
		this.specialmonth2 = specialmonth2;
		this.specialmonth3 = specialmonth3;
		this.specialmonth4 = specialmonth4;
		this.specialmonth5 = specialmonth5;
		this.specialmonth6 = specialmonth6;
		this.specialmonth7 = specialmonth7;
		this.specialmonth8 = specialmonth8;
		this.specialmonth9 = specialmonth9;
		this.specialmonth10 = specialmonth10;
		this.specialmonth11 = specialmonth11;
		this.specialmonth12 = specialmonth12;
		this.specialmonth13 = specialmonth13;
		this.specialmonth14 = specialmonth14;
		this.specialmonth15 = specialmonth15;
		this.specialmonth16 = specialmonth16;
		this.specialmonth17 = specialmonth17;
		this.specialmonth18 = specialmonth18;
		this.specialmonth19 = specialmonth19;
		this.specialmonth20 = specialmonth20;
		this.specialmonth21 = specialmonth21;
		this.specialmonth22 = specialmonth22;
		this.specialmonth23 = specialmonth23;
		this.specialday0 = specialday0;
		this.specialday1 = specialday1;
		this.specialday2 = specialday2;
		this.specialday3 = specialday3;
		this.specialday4 = specialday4;
		this.specialday5 = specialday5;
		this.specialday6 = specialday6;
		this.specialday7 = specialday7;
		this.specialday8 = specialday8;
		this.specialday9 = specialday9;
		this.specialday10 = specialday10;
		this.specialday11 = specialday11;
		this.specialday12 = specialday12;
		this.specialday13 = specialday13;
		this.specialday14 = specialday14;
		this.specialday15 = specialday15;
		this.specialday16 = specialday16;
		this.specialday17 = specialday17;
		this.specialday18 = specialday18;
		this.specialday19 = specialday19;
		this.specialday20 = specialday20;
		this.specialday21 = specialday21;
		this.specialday22 = specialday22;
		this.specialday23 = specialday23;
		
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

	@Column(name = "qchdtime")
	public Integer getQchdtime() {
		return this.qchdtime;
	}

	public void setQchdtime(Integer qchdtime) {
		this.qchdtime = qchdtime;
	}

	@Column(name = "kjhstime")
	public Integer getKjhstime() {
		return this.kjhstime;
	}

	public void setKjhstime(Integer kjhstime) {
		this.kjhstime = kjhstime;
	}

	@Column(name = "number", length = 20)
	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Column(name = "comparam", length = 20)
	public String getComparam() {
		return this.comparam;
	}

	public void setComparam(String comparam) {
		this.comparam = comparam;
	}

	@Column(name = "checkflow")
	public Integer getCheckflow() {
		return this.checkflow;
	}

	public void setCheckflow(Integer checkflow) {
		this.checkflow = checkflow;
	}

	@Column(name = "innermark", length = 20)
	public String getInnermark() {
		return this.innermark;
	}

	public void setInnermark(String innermark) {
		this.innermark = innermark;
	}

	@Column(name = "workingset")
	public Integer getWorkingset() {
		return this.workingset;
	}

	public void setWorkingset(Integer workingset) {
		this.workingset = workingset;
	}

	@Column(name = "mon")
	public Integer getMon() {
		return this.mon;
	}

	public void setMon(Integer mon) {
		this.mon = mon;
	}

	@Column(name = "tue")
	public Integer getTue() {
		return this.tue;
	}

	public void setTue(Integer tue) {
		this.tue = tue;
	}

	@Column(name = "wes")
	public Integer getWes() {
		return this.wes;
	}

	public void setWes(Integer wes) {
		this.wes = wes;
	}

	@Column(name = "thir")
	public Integer getThir() {
		return this.thir;
	}

	public void setThir(Integer thir) {
		this.thir = thir;
	}

	@Column(name = "fra")
	public Integer getFra() {
		return this.fra;
	}

	public void setFra(Integer fra) {
		this.fra = fra;
	}

	@Column(name = "sata")
	public Integer getSata() {
		return this.sata;
	}

	public void setSata(Integer sata) {
		this.sata = sata;
	}

	@Column(name = "sun")
	public Integer getSun() {
		return this.sun;
	}

	public void setSun(Integer sun) {
		this.sun = sun;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "paramversion", length = 23)
	public Date getParamversion() {
		return this.paramversion;
	}

	public void setParamversion(Date paramversion) {
		this.paramversion = paramversion;
	}

	@Column(name = "gmintime")
	public Integer getGmintime() {
		return this.gmintime;
	}

	public void setGmintime(Integer gmintime) {
		this.gmintime = gmintime;
	}

	@Column(name = "gmaxtime")
	public Integer getGmaxtime() {
		return this.gmaxtime;
	}

	public void setGmaxtime(Integer gmaxtime) {
		this.gmaxtime = gmaxtime;
	}

	@Column(name = "zdbctime")
	public Integer getZdbctime() {
		return this.zdbctime;
	}

	public void setZdbctime(Integer zdbctime) {
		this.zdbctime = zdbctime;
	}

	@Column(name = "countdownmode")
	public Integer getCountdownmode() {
		return this.countdownmode;
	}

	public void setCountdownmode(Integer countdownmode) {
		this.countdownmode = countdownmode;
	}

	@Column(name = "jgkz")
	public Integer getJgkz() {
		return this.jgkz;
	}

	public void setJgkz(Integer jgkz) {
		this.jgkz = jgkz;
	}

	@Column(name = "ip", length = 20)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "centerport", length = 20)
	public String getCenterport() {
		return this.centerport;
	}

	public void setCenterport(String centerport) {
		this.centerport = centerport;
	}

	@Column(name = "centerip", length = 20)
	public String getCenterip() {
		return this.centerip;
	}

	public void setCenterip(String centerip) {
		this.centerip = centerip;
	}

	@Column(name = "checklight")
	public Integer getChecklight() {
		return this.checklight;
	}

	public void setChecklight(Integer checklight) {
		this.checklight = checklight;
	}

	@Column(name = "xrfxtime")
	public Integer getXrfxtime() {
		return this.xrfxtime;
	}

	public void setXrfxtime(Integer xrfxtime) {
		this.xrfxtime = xrfxtime;
	}

	@Column(name = "cycle")
	public Integer getCycle() {
		return this.cycle;
	}

	public void setCycle(Integer cycle) {
		this.cycle = cycle;
	}

	@Column(name = "xyxr")
	public Integer getXyxr() {
		return this.xyxr;
	}

	public void setXyxr(Integer xyxr) {
		this.xyxr = xyxr;
	}

	@Column(name = "sbnetmastk", length = 20)
	public String getSbnetmastk() {
		return this.sbnetmastk;
	}

	public void setSbnetmastk(String sbnetmastk) {
		this.sbnetmastk = sbnetmastk;
	}

	@Column(name = "gateway", length = 20)
	public String getGateway() {
		return this.gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	@Column(name = "specialmonth0")
	public Integer getSpecialmonth0() {
		return this.specialmonth0;
	}

	public void setSpecialmonth0(Integer specialmonth0) {
		this.specialmonth0 = specialmonth0;
	}

	@Column(name = "specialmonth1")
	public Integer getSpecialmonth1() {
		return this.specialmonth1;
	}

	public void setSpecialmonth1(Integer specialmonth1) {
		this.specialmonth1 = specialmonth1;
	}

	@Column(name = "specialmonth2")
	public Integer getSpecialmonth2() {
		return this.specialmonth2;
	}

	public void setSpecialmonth2(Integer specialmonth2) {
		this.specialmonth2 = specialmonth2;
	}

	@Column(name = "specialmonth3")
	public Integer getSpecialmonth3() {
		return this.specialmonth3;
	}

	public void setSpecialmonth3(Integer specialmonth3) {
		this.specialmonth3 = specialmonth3;
	}

	@Column(name = "specialmonth4")
	public Integer getSpecialmonth4() {
		return this.specialmonth4;
	}

	public void setSpecialmonth4(Integer specialmonth4) {
		this.specialmonth4 = specialmonth4;
	}

	@Column(name = "specialmonth5")
	public Integer getSpecialmonth5() {
		return this.specialmonth5;
	}

	public void setSpecialmonth5(Integer specialmonth5) {
		this.specialmonth5 = specialmonth5;
	}

	@Column(name = "specialmonth6")
	public Integer getSpecialmonth6() {
		return this.specialmonth6;
	}

	public void setSpecialmonth6(Integer specialmonth6) {
		this.specialmonth6 = specialmonth6;
	}

	@Column(name = "specialmonth7")
	public Integer getSpecialmonth7() {
		return this.specialmonth7;
	}

	public void setSpecialmonth7(Integer specialmonth7) {
		this.specialmonth7 = specialmonth7;
	}

	@Column(name = "specialmonth8")
	public Integer getSpecialmonth8() {
		return this.specialmonth8;
	}

	public void setSpecialmonth8(Integer specialmonth8) {
		this.specialmonth8 = specialmonth8;
	}

	@Column(name = "specialmonth9")
	public Integer getSpecialmonth9() {
		return this.specialmonth9;
	}

	public void setSpecialmonth9(Integer specialmonth9) {
		this.specialmonth9 = specialmonth9;
	}

	@Column(name = "specialmonth10")
	public Integer getSpecialmonth10() {
		return this.specialmonth10;
	}

	public void setSpecialmonth10(Integer specialmonth10) {
		this.specialmonth10 = specialmonth10;
	}

	@Column(name = "specialmonth11")
	public Integer getSpecialmonth11() {
		return this.specialmonth11;
	}

	public void setSpecialmonth11(Integer specialmonth11) {
		this.specialmonth11 = specialmonth11;
	}

	@Column(name = "specialmonth12")
	public Integer getSpecialmonth12() {
		return this.specialmonth12;
	}

	public void setSpecialmonth12(Integer specialmonth12) {
		this.specialmonth12 = specialmonth12;
	}

	@Column(name = "specialmonth13")
	public Integer getSpecialmonth13() {
		return this.specialmonth13;
	}

	public void setSpecialmonth13(Integer specialmonth13) {
		this.specialmonth13 = specialmonth13;
	}

	@Column(name = "specialmonth14")
	public Integer getSpecialmonth14() {
		return this.specialmonth14;
	}

	public void setSpecialmonth14(Integer specialmonth14) {
		this.specialmonth14 = specialmonth14;
	}

	@Column(name = "specialmonth15")
	public Integer getSpecialmonth15() {
		return this.specialmonth15;
	}

	public void setSpecialmonth15(Integer specialmonth15) {
		this.specialmonth15 = specialmonth15;
	}

	@Column(name = "specialmonth16")
	public Integer getSpecialmonth16() {
		return this.specialmonth16;
	}

	public void setSpecialmonth16(Integer specialmonth16) {
		this.specialmonth16 = specialmonth16;
	}

	@Column(name = "specialmonth17")
	public Integer getSpecialmonth17() {
		return this.specialmonth17;
	}

	public void setSpecialmonth17(Integer specialmonth17) {
		this.specialmonth17 = specialmonth17;
	}

	@Column(name = "specialmonth18")
	public Integer getSpecialmonth18() {
		return this.specialmonth18;
	}

	public void setSpecialmonth18(Integer specialmonth18) {
		this.specialmonth18 = specialmonth18;
	}

	@Column(name = "specialmonth19")
	public Integer getSpecialmonth19() {
		return this.specialmonth19;
	}

	public void setSpecialmonth19(Integer specialmonth19) {
		this.specialmonth19 = specialmonth19;
	}

	@Column(name = "specialmonth20")
	public Integer getSpecialmonth20() {
		return this.specialmonth20;
	}

	public void setSpecialmonth20(Integer specialmonth20) {
		this.specialmonth20 = specialmonth20;
	}

	@Column(name = "specialmonth21")
	public Integer getSpecialmonth21() {
		return this.specialmonth21;
	}

	public void setSpecialmonth21(Integer specialmonth21) {
		this.specialmonth21 = specialmonth21;
	}

	@Column(name = "specialmonth22")
	public Integer getSpecialmonth22() {
		return this.specialmonth22;
	}

	public void setSpecialmonth22(Integer specialmonth22) {
		this.specialmonth22 = specialmonth22;
	}

	@Column(name = "specialmonth23")
	public Integer getSpecialmonth23() {
		return this.specialmonth23;
	}

	public void setSpecialmonth23(Integer specialmonth23) {
		this.specialmonth23 = specialmonth23;
	}

	@Column(name = "specialday0")
	public Integer getSpecialday0() {
		return this.specialday0;
	}

	public void setSpecialday0(Integer specialday0) {
		this.specialday0 = specialday0;
	}

	@Column(name = "specialday1")
	public Integer getSpecialday1() {
		return this.specialday1;
	}

	public void setSpecialday1(Integer specialday1) {
		this.specialday1 = specialday1;
	}

	@Column(name = "specialday2")
	public Integer getSpecialday2() {
		return this.specialday2;
	}

	public void setSpecialday2(Integer specialday2) {
		this.specialday2 = specialday2;
	}

	@Column(name = "specialday3")
	public Integer getSpecialday3() {
		return this.specialday3;
	}

	public void setSpecialday3(Integer specialday3) {
		this.specialday3 = specialday3;
	}

	@Column(name = "specialday4")
	public Integer getSpecialday4() {
		return this.specialday4;
	}

	public void setSpecialday4(Integer specialday4) {
		this.specialday4 = specialday4;
	}

	@Column(name = "specialday5")
	public Integer getSpecialday5() {
		return this.specialday5;
	}

	public void setSpecialday5(Integer specialday5) {
		this.specialday5 = specialday5;
	}

	@Column(name = "specialday6")
	public Integer getSpecialday6() {
		return this.specialday6;
	}

	public void setSpecialday6(Integer specialday6) {
		this.specialday6 = specialday6;
	}

	@Column(name = "specialday7")
	public Integer getSpecialday7() {
		return this.specialday7;
	}

	public void setSpecialday7(Integer specialday7) {
		this.specialday7 = specialday7;
	}

	@Column(name = "specialday8")
	public Integer getSpecialday8() {
		return this.specialday8;
	}

	public void setSpecialday8(Integer specialday8) {
		this.specialday8 = specialday8;
	}

	@Column(name = "specialday9")
	public Integer getSpecialday9() {
		return this.specialday9;
	}

	public void setSpecialday9(Integer specialday9) {
		this.specialday9 = specialday9;
	}

	@Column(name = "specialday10")
	public Integer getSpecialday10() {
		return this.specialday10;
	}

	public void setSpecialday10(Integer specialday10) {
		this.specialday10 = specialday10;
	}

	@Column(name = "specialday11")
	public Integer getSpecialday11() {
		return this.specialday11;
	}

	public void setSpecialday11(Integer specialday11) {
		this.specialday11 = specialday11;
	}

	@Column(name = "specialday12")
	public Integer getSpecialday12() {
		return this.specialday12;
	}

	public void setSpecialday12(Integer specialday12) {
		this.specialday12 = specialday12;
	}

	@Column(name = "specialday13")
	public Integer getSpecialday13() {
		return this.specialday13;
	}

	public void setSpecialday13(Integer specialday13) {
		this.specialday13 = specialday13;
	}

	@Column(name = "specialday14")
	public Integer getSpecialday14() {
		return this.specialday14;
	}

	public void setSpecialday14(Integer specialday14) {
		this.specialday14 = specialday14;
	}

	@Column(name = "specialday15")
	public Integer getSpecialday15() {
		return this.specialday15;
	}

	public void setSpecialday15(Integer specialday15) {
		this.specialday15 = specialday15;
	}

	@Column(name = "specialday16")
	public Integer getSpecialday16() {
		return this.specialday16;
	}

	public void setSpecialday16(Integer specialday16) {
		this.specialday16 = specialday16;
	}

	@Column(name = "specialday17")
	public Integer getSpecialday17() {
		return this.specialday17;
	}

	public void setSpecialday17(Integer specialday17) {
		this.specialday17 = specialday17;
	}

	@Column(name = "specialday18")
	public Integer getSpecialday18() {
		return this.specialday18;
	}

	public void setSpecialday18(Integer specialday18) {
		this.specialday18 = specialday18;
	}

	@Column(name = "specialday19")
	public Integer getSpecialday19() {
		return this.specialday19;
	}

	public void setSpecialday19(Integer specialday19) {
		this.specialday19 = specialday19;
	}

	@Column(name = "specialday20")
	public Integer getSpecialday20() {
		return this.specialday20;
	}

	public void setSpecialday20(Integer specialday20) {
		this.specialday20 = specialday20;
	}

	@Column(name = "specialday21")
	public Integer getSpecialday21() {
		return this.specialday21;
	}

	public void setSpecialday21(Integer specialday21) {
		this.specialday21 = specialday21;
	}

	@Column(name = "specialday22")
	public Integer getSpecialday22() {
		return this.specialday22;
	}

	public void setSpecialday22(Integer specialday22) {
		this.specialday22 = specialday22;
	}

	@Column(name = "specialday23")
	public Integer getSpecialday23() {
		return this.specialday23;
	}

	public void setSpecialday23(Integer specialday23) {
		this.specialday23 = specialday23;
	}

	@Transient
	public Integer[] getSpecialdays(){
		Integer[] specialdays = new Integer[]{this.getSpecialday0(),this.getSpecialday1(),this.getSpecialday2(),
			this.getSpecialday3(),this.getSpecialday4(),this.getSpecialday5(),this.getSpecialday6(),this.getSpecialday7(),this.getSpecialday8(),this.getSpecialday9(),this.getSpecialday10(),
			this.getSpecialday11(),this.getSpecialday12(),this.getSpecialday13(),this.getSpecialday14(),this.getSpecialday15(),this.getSpecialday16(),this.getSpecialday17(),this.getSpecialday18(),
			this.getSpecialday19(),this.getSpecialday20(),this.getSpecialday21(),this.getSpecialday22(),this.getSpecialday23()};
		return specialdays;
	}
	
	@Transient
	public Integer[] getSpecialmonths(){
		Integer[] specialmonths = new Integer[]{this.getSpecialmonth0(),this.getSpecialmonth1(),this.getSpecialmonth2(),
			this.getSpecialmonth3(),this.getSpecialmonth4(),this.getSpecialmonth5(),this.getSpecialmonth6(),this.getSpecialmonth7(),this.getSpecialmonth8(),this.getSpecialmonth9(),this.getSpecialmonth10(),
			this.getSpecialmonth11(),this.getSpecialmonth12(),this.getSpecialmonth13(),this.getSpecialmonth14(),this.getSpecialmonth15(),this.getSpecialmonth16(),this.getSpecialmonth17(),this.getSpecialmonth18(),
			this.getSpecialmonth19(),this.getSpecialmonth20(),this.getSpecialmonth21(),this.getSpecialmonth22(),this.getSpecialmonth23()};
		return specialmonths;
	}
	
	@Transient
	public Integer[] getDays(){
		Integer[] days = new Integer[]{this.getMon(),this.getTue(),this.getWes(),this.getThir(),this.getFra(),this.getSata(),this.getSun()};
		return days;
	}

}