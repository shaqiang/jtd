package com.jlj.vo;

import java.util.ArrayList;
import java.util.List;

import com.jlj.model.Road;

public class StepTimeVO {
	
	private int id;
	private String stepname;
	private int second;
	private List<Road> roads = new ArrayList<Road>();
	
	public String getStepname() {
		return stepname;
	}
	public void setStepname(String stepname) {
		this.stepname = stepname;
	}
	public int getSecond() {
		return second;
	}
	public void setSecond(int second) {
		this.second = second;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Road> getRoads() {
		return roads;
	}
	public void setRoads(List<Road> roads) {
		this.roads = roads;
	}
	
	
	

}
