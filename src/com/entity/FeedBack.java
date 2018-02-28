package com.entity;


public class FeedBack {

	private String fid;
	
	private String content; //反馈内容
	
	private String time; //反馈时间
	
    private String user; //反馈员工
    
    private int isReplied; //是否得到回复

	public FeedBack() {
	}

	public FeedBack(String fid, String content, String time, String user,
			int isReplied) {
		super();
		this.fid = fid;
		this.content = content;
		this.time = time;
		this.user = user;
		this.isReplied = isReplied;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getIsReplied() {
		return isReplied;
	}

	public void setIsReplied(int isReplied) {
		this.isReplied = isReplied;
	}

	@Override
	public String toString() {
		return "FeedBack [fid=" + fid + ", content=" + content + ", time="
				+ time + ", user=" + user + ", isReplied=" + isReplied + "]";
	}
	
}
