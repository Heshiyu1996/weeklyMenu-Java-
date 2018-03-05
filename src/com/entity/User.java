package com.entity;


public class User {

	private String sid;
	
	private String sname; //员工名字
	
	private String smobile; //员工手机
	
    private String spassword;
    
    private int stype; //员工身份类型

	public User() {
	}
	
	public User(String sid, String sname, String smobile) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.smobile = smobile;
		this.stype = stype;
	}

	public String getsid() {
		return sid;
	}

	public void setsid(String sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSmobile() {
		return smobile;
	}

	public void setSmobile(String smobile) {
		this.smobile = smobile;
	}

    
	public String getSpassword() {
		return spassword;
	}

	public void setSpassword(String spassword) {
		this.spassword = spassword;
	}
	
	public int getStype() {
		return stype;
	}

	public void setStype(int stype) {
		this.stype = stype;
	}


	@Override
	public String toString() {
		return "Staff [sid=" + sid + ", sname=" + sname + ", smobile=" + smobile +", stype=" + stype +"]";
	}
	
	
}
