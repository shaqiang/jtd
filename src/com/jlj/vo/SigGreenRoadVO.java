package com.jlj.vo;

import java.util.ArrayList;
import java.util.List;

public class SigGreenRoadVO {
	
	private int id;//路口id
	private String name;//路口名称
	private String distance;//与基准点的距离
	private int circleTime;//周期
	private String number;//路口编号
	
	private List<UsefulPhaseVO> usefulPhases;//所有可用相位
	private List<String> pharsePros = new ArrayList<String>();
	
	private String zxphasename;//正向相位
	private String fxphasename;//反向相位
	
	
	private int zxspeed;//正向速度
	private int fxspeed;//反向速度
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public List<UsefulPhaseVO> getUsefulPhases() {
		return usefulPhases;
	}
	public void setUsefulPhases(List<UsefulPhaseVO> usefulPhases) {
		this.usefulPhases = usefulPhases;
	}
	public String getZxphasename() {
		return zxphasename;
	}
	public void setZxphasename(String zxphasename) {
		this.zxphasename = zxphasename;
	}
	public String getFxphasename() {
		return fxphasename;
	}
	public void setFxphasename(String fxphasename) {
		this.fxphasename = fxphasename;
	}
	public int getZxspeed() {
		return zxspeed;
	}
	public void setZxspeed(int zxspeed) {
		this.zxspeed = zxspeed;
	}
	public int getFxspeed() {
		return fxspeed;
	}
	public void setFxspeed(int fxspeed) {
		this.fxspeed = fxspeed;
	}
	public int getCircleTime() {
		return circleTime;
	}
	public void setCircleTime(int circleTime) {
		this.circleTime = circleTime;
	}
	public List<String> getPharsePros() {
		return pharsePros;
	}
	public void setPharsePros(List<String> pharsePros) {
		this.pharsePros = pharsePros;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "{id:"+this.id+","+
		"name:"+"\""+this.name+"\""+","+
		"number:"+"\""+this.number+"\""+","+
		"distance:"+"\""+this.distance+"\""+","+"}";
	}
	
	
	

}
