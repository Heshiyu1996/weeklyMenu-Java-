package com.entity;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.tool.DateSerializer;


public class FeedBack {

	private String fid;

    @JsonSerialize(using=DateSerializer.class)
	private Date time; //反馈时间
	
    private String userId; //反馈员工Id
	
    private String user; //反馈员工
	
	private String content; //反馈内容
    
    private int isReplied; //是否得到回复
    
    private int repliedMsg; //回复的内容
    
    private int repliedTime; //回复的时间

	public FeedBack() {
	}

	public FeedBack(String fid, String content, Date time, String user,
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

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getUser() {
		return user;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public int getRepliedMsg() {
		return repliedMsg;
	}

	public void setRepliedMsg(int repliedMsg) {
		this.repliedMsg = repliedMsg;
	}

	public int getRepliedTime() {
		return repliedTime;
	}

	public void setRepliedTime(int repliedTime) {
		this.repliedTime = repliedTime;
	}

	@Override
	public String toString() {
		return "FeedBack [fid=" + fid + ", content=" + content + ", time="
				+ time + ", user=" + user + ", isReplied=" + isReplied
				+ ", repliedMsg=" + repliedMsg + ", repliedTime=" + repliedTime
				+ "]";
	}
	
}
