package com.jlj.vo;

public class CommontimeVO {
	
	private int cid;
	private String commontimename;
	
	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCommontimename() {
		return commontimename;
	}
	public void setCommontimename(String commontimename) {
		this.commontimename = commontimename;
	}
	
	public CommontimeVO() {
	}
	public CommontimeVO(int cid, String commontimename) {
		this.cid = cid;
		this.commontimename = commontimename;
	}
	
	
	

}
