package com.entity;


public class Search {

	private String keyword;
    
    private int count;

	public Search() {
	}
	
	public Search(String keyword, int count) {
		super();
		this.keyword = keyword;
		this.count = count;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Search [keyword=" + keyword + ", count=" + count + "]";
	}

}
