package com.model;

import java.util.Date;

public class Appointment {
	
	private int appID;
	private Date appDate;
	private int day;
	private String time;
	private int pid; 
	private int did;
	private String hosp;
	private Date selectedDate;
	private Payment payment;
	private String pname;
		
	
	public Appointment() {
		
		this.payment = new Payment();
		
	}
	
	//setters
	public void setPname(String pname) {
		this.pname = pname;
	}

	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}

	
	public void setAppID(int appID) {
		
		this.appID = appID;
	}
	
	public void setDate(Date appDate){
		
		this.appDate = appDate;
	}
	
	public void setDay(int day) {
		
		this.day = day;
	}
	
	public void setTime(String time) {
		
		this.time = time;
	}
	
	public void setPid(int pid) {
		
		this.pid = pid;
	}
	
	public void setDid(int did) {
		
		this.did = did;
	}
	
	public void setHosp(String hosp) {
		
		this.hosp = hosp;
	}
	
	//getters
	public String getPname() {
		return pname;
	}

	public Date getSelectedDate() {
		return selectedDate;
	}
	
	public int getAppID() {
		
		return appID;
	}
	
	public Date getDate() {
		
		return appDate;
	}
	
	public int getDay() {
		
		return day;
	}
	
	public String getTime() {
		
		return time;
	}
	
	public int getPid() {
		
		return pid;
	}
	
	public int getDid() {
		
		return did;
	}
	
	public String getHosp() {
		
		return hosp;
	}
	
	/*public void doPayment() {
		
	}*/
	
}
