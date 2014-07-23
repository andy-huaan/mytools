package com.tools.jackson.po;

/**
 * ÊµÌåÀà
 * @author Administrator
 *
 */
public class Student {

	private int uid;
    private String uname;
	private double number;
	private boolean isstudent;
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}

	public double getNumber() {
		return number;
	}
	public void setNumber(double number) {
		this.number = number;
	}
	public boolean isIsstudent() {
		return isstudent;
	}
	public void setIsstudent(boolean isstudent) {
		this.isstudent = isstudent;
	}
	@Override
	public String toString() {
		return "uid="+uid+",name="+uname+",number="+number+",isStudent="+isstudent;
	}
	
}