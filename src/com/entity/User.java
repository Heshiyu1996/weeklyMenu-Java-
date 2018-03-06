package com.entity;


public class User {

	private String uid;
	
	private String uname; //员工名字
	
	private String umobile; //员工手机
	
    private String upassword;
    
    private int utype; //员工身份类型

	public User() {
	}
	
	public User(String uid, String uname, String umobile, int utype) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.umobile = umobile;
		this.utype = utype;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUmobile() {
		return umobile;
	}

	public void setUmobile(String umobile) {
		this.umobile = umobile;
	}

    
	public String getUpassword() {
		return upassword;
	}

	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	
	public int getUtype() {
		return utype;
	}

	public void setUtype(int utype) {
		this.utype = utype;
	}


	@Override
	public String toString() {
		return "Staff [uid=" + uid + ", uname=" + uname + ", umobile=" + umobile +", utype=" + utype +"]";
	}
	
	
}
