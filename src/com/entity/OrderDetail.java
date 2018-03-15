package com.entity;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.tool.DateSerializer;


public class OrderDetail {

	private int orderDetailId; // 订单详情ID

	private int foodId; // 菜品ID

	private int count; // 菜品数量

	private int orderId; // 订单ID

	public OrderDetail() {
	}

	public OrderDetail(int orderDetailId, int foodId, int count, int orderId) {
		super();
		this.orderDetailId = orderDetailId;
		this.foodId = foodId;
		this.count = count;
		this.orderId = orderId;
	}

	public int getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(int orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "OrderDetail [orderDetailId=" + orderDetailId + ", foodId="
				+ foodId + ", count=" + count + ", orderId=" + orderId + "]";
	}

}
