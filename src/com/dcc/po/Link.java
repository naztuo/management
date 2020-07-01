package com.dcc.po;


public class Link {
	private int id;
	private String keyword;//关键字
	private String linkad;//短链
	private String states;//状态
	private String datas;//时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getLinkad() {
		return linkad;
	}
	public void setLinkad(String linkad) {
		this.linkad = linkad;
	}
	public String getStates() {
		return states;
	}
	public void setStates(String states) {
		this.states = states;
	}
	public String getDatas() {
		return datas;
	}
	public void setDatas(String datas) {
		this.datas = datas;
	}
	
	
}
