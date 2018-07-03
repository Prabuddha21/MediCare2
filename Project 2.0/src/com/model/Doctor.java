package com.model;

import java.util.ArrayList;

public class Doctor extends User{
	
	private String MID;
	private String spec;
	private ArrayList <String> hosp1;
	private String hosp;
	private double rate;
	private int day;
	private String time;
	
	public ArrayList<String> getHosp1() {
		return hosp1;
	}

	public void setHosp1(ArrayList<String> hosp1) {
		this.hosp1 = hosp1;
	}

	public Doctor() {
		
	}
	
	//Setter
	
	public void setMID(String mID) {
		this.MID = mID;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}
	
	public void setRate(double rate) {
		this.rate = rate;
	}
	
	public void setDay(int day) {
		this.day = day;
	}
	
	public void setHosp(String hosp) {
		this.hosp = hosp;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	//Getter
	
	public String getMID() {
		return MID;
	}

	public String getSpec() {
		return spec;
	}	
	
	public double getRate() {
		return rate;
	}

	public int getDay() {
		return day;
	}
	
	public String getHosp() {
		return hosp;
	}
	
	public String getTime() {
		return time;
	}
	
	
}
