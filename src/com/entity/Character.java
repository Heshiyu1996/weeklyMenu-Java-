package com.entity;


public class Character {

	private int id;
	
	private int userId;
	
	private String provinceCode;
	
	private String province;
	
	private String cityCode;
	
	private String city;
	
	private String nation;
	
	private String taste;
	
	private String tall;
	
	private String height;
	
	private String eatHabit;
	
	private String prepare;
	
	private int alcohol;
	
	private String attention;
	

	public Character() {
	}

	public Character(int id, int userId, String provinceCode, String province,
			String cityCode, String city, String nation, String taste,
			String tall, String height, String eatHabit, String prepare,
			int alcohol, String attention) {
		super();
		this.id = id;
		this.userId = userId;
		this.provinceCode = provinceCode;
		this.province = province;
		this.cityCode = cityCode;
		this.city = city;
		this.nation = nation;
		this.taste = taste;
		this.tall = tall;
		this.height = height;
		this.eatHabit = eatHabit;
		this.prepare = prepare;
		this.alcohol = alcohol;
		this.attention = attention;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getTaste() {
		return taste;
	}

	public void setTaste(String taste) {
		this.taste = taste;
	}

	public String getTall() {
		return tall;
	}

	public void setTall(String tall) {
		this.tall = tall;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getEatHabit() {
		return eatHabit;
	}

	public void setEatHabit(String eatHabit) {
		this.eatHabit = eatHabit;
	}

	public String getPrepare() {
		return prepare;
	}

	public void setPrepare(String prepare) {
		this.prepare = prepare;
	}

	public int getAlcohol() {
		return alcohol;
	}

	public void setAlcohol(int alcohol) {
		this.alcohol = alcohol;
	}

	public String getAttention() {
		return attention;
	}

	public void setAttention(String attention) {
		this.attention = attention;
	}

	@Override
	public String toString() {
		return "Character [id=" + id + ", userId=" + userId + ", provinceCode="
				+ provinceCode + ", province=" + province + ", cityCode="
				+ cityCode + ", city=" + city + ", nation=" + nation
				+ ", taste=" + taste + ", tall=" + tall + ", height=" + height
				+ ", eatHabit=" + eatHabit + ", prepare=" + prepare
				+ ", alcohol=" + alcohol + ", attention=" + attention + "]";
	}
}
