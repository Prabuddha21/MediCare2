package com.model;

//Parent class
public class User {
	
	private int userID;
	private String uname;
	private String name;
	private String pass;
	private String NIC; 
	private String email;
	private int age;
	private String address;
	private String cont1;
	private String cont2;
	private String cont1o;
	private String cont2o;
	
	
	public User() {
		
	}
	
	//Setters
	public void setCont1(String cont1) {
		this.cont1 = cont1;
	}
	
	public void setCont2(String cont2) {
		this.cont2 = cont2;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public void setUname(String uname) {
		this.uname = uname;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}

	public void setNIC(String NIC) {
		this.NIC = NIC;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public void setAdd(String address) {
		this.address = address;
	}
	
	//Getters
	
	public int getUserID() {
		return userID;
	}
	
	public String getUname() {
		return uname;
	}
	
	public String getName() {
		return name;
	}
	
	public String getpass() {
		return pass;
	}
	
	public String getNIC() {
		return NIC;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getAdd() {
		return address;
	}
	
	public int getAge() {
		return age;
	}
	
	public String getCont1() {
		return cont1;
	}
	
	public String getCont2() {
		return cont2;
	}
	
	
	//for update
	public String getCont1o() {
		return cont1o;
	}

	public void setCont1o(String cont1o) {
		this.cont1o = cont1o;
	}

	public String getCont2o() {
		return cont2o;
	}

	public void setCont2o(String cont2o) {
		this.cont2o = cont2o;
	}

}
