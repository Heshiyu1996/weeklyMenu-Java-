package com.entity;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.tool.DateSerializer;


public class FeedBack {

	private int fid;

    @JsonSerialize(using=DateSerializer.class)
	private Date time; //反馈时间
	
    private String userId; //反馈员工Id
	
    private String user; //反馈员工
	
	private String content; //反馈内容
    
    private Integer isReplied; //是否得到回复
    
    private String repliedMsg; //回复的内容

    @JsonSerialize(using=DateSerializer.class)
    private Date repliedTime; //回复的时间

	public FeedBack() {
	}

	public FeedBack(int fid, String content, Date time, String user,
			int isReplied) {
		super();
		this.fid = fid;
		this.content = content;
		this.time = time;
		this.user = user;
		this.isReplied = isReplied;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
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

	public Integer getIsReplied() {
		return isReplied;
	}

	public void setIsReplied(Integer isReplied) {
		this.isReplied = isReplied;
	}

	public String getRepliedMsg() {
		return repliedMsg;
	}

	public void setRepliedMsg(String repliedMsg) {
		this.repliedMsg = repliedMsg;
	}

	public Date getRepliedTime() {
		return repliedTime;
	}

	public void setRepliedTime(Date repliedTime) {
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
