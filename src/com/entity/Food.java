package com.entity;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.tool.DateSerializer;


public class Food {

	private int foodId; //食物id
	
    private String name; //食物名
	
    private String imgUrl; //图片地址
	
	private String material; //食材
    
    private String description; //描述
    
    private int markCount; //收藏量
    
    private int visitCount; //访问量
    
    private int categoryId; //所属类别id
    
    private String category; //所属类别
    
    private int price; // 价格

	public Food() {
	}

	public Food(int foodId, String name, String imgUrl, String material,
			String description, int markCount, int visitCount, int categoryId, String category, int price) {
		super();
		this.foodId = foodId;
		this.name = name;
		this.imgUrl = imgUrl;
		this.material = material;
		this.description = description;
		this.markCount = markCount;
		this.visitCount = visitCount;
		this.categoryId = categoryId;
		this.category = category;
		this.price = price;
	}

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMarkCount() {
		return markCount;
	}

	public void setMarkCount(int markCount) {
		this.markCount = markCount;
	}

	public int getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(int visitCount) {
		this.visitCount = visitCount;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Food [foodId=" + foodId + ", name=" + name + ", imgUrl="
				+ imgUrl + ", material=" + material + ", description="
				+ description + ", markCount=" + markCount + ", visitCount="
				+ visitCount + ", categoryId=" + categoryId + ", category="
				+ category + ", price=" + price + "]";
	}

	
}
