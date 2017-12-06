package com.entity;


public class Staff {

	private int sid;
	
	private String sname; //员工名字
	
	private String smobile; //员工手机
	
	public Staff() {
	}
	
	public Staff(int sid, String sname, String smobile) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.smobile = smobile;
	}

	public int getsid() {
		return sid;
	}

	public void setsid(int sid) {
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


	@Override
	public String toString() {
		return "Staff [sid=" + sid + ", sname=" + sname + ", smobile=" + smobile +"]";
	}
	
	
}
