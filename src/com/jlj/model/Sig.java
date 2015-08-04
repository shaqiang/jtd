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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Sig entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sig", schema = "dbo", catalog = "jtd")
public class Sig implements java.io.Serializable {

	// Fields

	private Integer id;
	private Userarea userarea;
	private String name;
	private String address;
	private String lat;
	private String lng;
	private String ip;
	private String number;
	private Long mkid;
	private Integer iserror;
	private Integer errorcode;
	private Signpublicparam signpublicparam;
	private List<Devlog> devlogs = new ArrayList<Devlog>();
	private List<Commontime> commontimes = new ArrayList<Commontime>();
	private Sigsystime sigsystime;
	private List<Greenconflict> greenconflicts = new ArrayList<Greenconflict>();
	private List<Solution> solutions = new ArrayList<Solution>();
	private List<Issuedcommand> issuedcommands = new ArrayList<Issuedcommand>();
	// Constructors

	/** default constructor */
	public Sig() {
	}

	/** full constructor */
	public Sig(Userarea userarea, String name, String address, String lat,
			String lng, String ip, String number, Long mkid,Integer iserror,Integer errorcode,
			Signpublicparam signpublicparam, List<Devlog> devlogs,
			Sigsystime sigsystime,List<Greenconflict> greenconflicts,List<Commontime> commontimes,
			List<Solution> solutions,List<Issuedcommand> issuedcommands) {
		this.userarea = userarea;
		this.name = name;
		this.address = address;
		this.lat = lat;
		this.lng = lng;
		this.ip = ip;
		this.number = number;
		this.mkid = mkid;
		this.iserror = iserror;
		this.errorcode = errorcode;
		this.signpublicparam = signpublicparam;
		this.devlogs = devlogs;
		this.sigsystime = sigsystime;
		this.greenconflicts = greenconflicts;
		this.commontimes = commontimes;
		this.solutions = solutions;
		this.issuedcommands = issuedcommands;
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
	@JoinColumn(name = "uaid")
	public Userarea getUserarea() {
		return this.userarea;
	}

	public void setUserarea(Userarea userarea) {
		this.userarea = userarea;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "address")
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	@Column(name = "ip", length = 20)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "mkid")
	public Long getMkid() {
		return this.mkid;
	}

	public void setMkid(Long mkid) {
		this.mkid = mkid;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sig")
	public Signpublicparam getSignpublicparam() {
		return this.signpublicparam;
	}

	public void setSignpublicparam(Signpublicparam signpublicparam) {
		this.signpublicparam = signpublicparam;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sig")
	public List<Devlog> getDevlogs() {
		return this.devlogs;
	}

	public void setDevlogs(List<Devlog> devlogs) {
		this.devlogs = devlogs;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sig")
	public Sigsystime getSigsystime() {
		return this.sigsystime;
	}

	public void setSigsystime(Sigsystime sigsystime) {
		this.sigsystime = sigsystime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sig")
	public List<Greenconflict> getGreenconflicts() {
		return greenconflicts;
	}

	public void setGreenconflicts(List<Greenconflict> greenconflicts) {
		this.greenconflicts = greenconflicts;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sig")
	public List<Commontime> getCommontimes() {
		return commontimes;
	}

	public void setCommontimes(List<Commontime> commontimes) {
		this.commontimes = commontimes;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sig")
	public List<Solution> getSolutions() {
		return this.solutions;
	}

	public void setSolutions(List<Solution> solutions) {
		this.solutions = solutions;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sig")
	public List<Issuedcommand> getIssuedcommands() {
		return issuedcommands;
	}

	public void setIssuedcommands(List<Issuedcommand> issuedcommands) {
		this.issuedcommands = issuedcommands;
	}

	@Column(name = "iserror")
	public Integer getIserror() {
		return iserror;
	}

	public void setIserror(Integer iserror) {
		this.iserror = iserror;
	}

	@Column(name = "errorcode")
	public Integer getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(Integer errorcode) {
		this.errorcode = errorcode;
	}

	@Column(name = "number", length = 10)
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	
	
}