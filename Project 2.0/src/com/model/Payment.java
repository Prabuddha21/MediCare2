package com.model;

import java.util.Date;

public class Payment {

	private int transactionId;
	private Date pTime;
	private double amount;
	private int pid;
	private int appId;
	
	//getter
	
	public void setTransId(int transactionId) {
		
		this.transactionId = transactionId;
	}
	
	public void setTime(Date pTime) {
		
		this.pTime = pTime;
	}
	
	public void setAmount(double amount) {
		
		this.amount = amount;
	}
	
	public void setPid(int pid) {
		
		this.pid = pid;
	}
	
	public void setAppId(int appId) {
		
		this.appId = appId;
	}
	
	//getter
	
	public int getTransId(){
		
		return transactionId;
	}
	
	public Date getTime() {
		
		return pTime;
	}
	
	public double getAmount() {
		
		return amount;
	}
		
	public int getPid() {
		
		return pid;
	}
	
	public int getAppId() {
		
		return appId;
	}
	
	
}
