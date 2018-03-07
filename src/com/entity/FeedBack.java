package com.entity;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.tool.DateSerializer;


public class FeedBack {

	private int fid;

    @JsonSerialize(using=DateSerializer.class)
	private Date createTime; //反馈时间
	
    private Integer userId; //反馈员工Id
	
    private String user; //反馈员工
	
	private String content; //反馈内容
    
    private Integer isReplied; //是否得到回复
    
    private String repliedMsg; //回复的内容

    @JsonSerialize(using=DateSerializer.class)
    private Date repliedTime; //回复的时间

	public FeedBack() {
	}

	public FeedBack(int fid, Date createTime, Integer userId, String user,
			String content, Integer isReplied, String repliedMsg,
			Date repliedTime) {
		super();
		this.fid = fid;
		this.createTime = createTime;
		this.userId = userId;
		this.user = user;
		this.content = content;
		this.isReplied = isReplied;
		this.repliedMsg = repliedMsg;
		this.repliedTime = repliedTime;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
		return "FeedBack [fid=" + fid + ", createTime=" + createTime
				+ ", userId=" + userId + ", user=" + user + ", content="
				+ content + ", isReplied=" + isReplied + ", repliedMsg="
				+ repliedMsg + ", repliedTime=" + repliedTime + "]";
	}

	
}
