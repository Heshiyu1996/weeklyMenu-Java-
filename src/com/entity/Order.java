package com.entity;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.tool.DateSerializer;


public class Order {

	private String orderId; // 订单ID
	
    private String dateCode; // 日期代码
    
    private Integer pid; // 时段
    
    private Integer userId; // 员工Id
    
    private Integer totalMoney; // 订单总额
    
    @JsonSerialize(using=DateSerializer.class)
	private Date createTime; //提交订单时间

	public Order() {
	}

	public Order(String orderId, String dateCode, Integer pid, Integer userId,
			Integer totalMoney, Date createTime) {
		super();
		this.orderId = orderId;
		this.dateCode = dateCode;
		this.pid = pid;
		this.userId = userId;
		this.totalMoney = totalMoney;
		this.createTime = createTime;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getDateCode() {
		return dateCode;
	}

	public void setDateCode(String dateCode) {
		this.dateCode = dateCode;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Integer totalMoney) {
		this.totalMoney = totalMoney;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", dateCode=" + dateCode
				+ ", pid=" + pid + ", totalMoney=" + totalMoney + ", userId=" + userId + ", createTime="
				+ createTime + "]";
	}

	
}
