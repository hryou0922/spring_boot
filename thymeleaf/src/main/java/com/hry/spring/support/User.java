package com.hry.spring.support;

public class User {
	private String name;
	private boolean isAdmin;
	private String other;
	private int age;

	public User(String name) {
		super();
		this.name = name;
	}

	public User(String name, boolean isAdmin, String other, int age) {
		super();
		this.name = name;
		this.isAdmin = isAdmin;
		this.other = other;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public boolean getIsAdmin() {
		return isAdmin;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
