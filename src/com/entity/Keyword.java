package com.entity;



public class Keyword {
	
	String keyword;
	
	public Keyword() {
	}

	public Keyword(String keyword) {
		super();
		this.keyword = keyword;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "Keyword [keyword=" + keyword + "]";
	}
	
}
