package com.jlj.vo;

public class SigStatus {
	private String name;
	private String status;
	private String url;
	public SigStatus(String name, String status, String url) {
		super();
		this.name = name;
		this.status = status;
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
