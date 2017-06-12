package com.hry.spring.support;

import java.util.List;

public class Family {
	private User father;
	private List<User> childList;
	
	public User getFather() {
		return father;
	}
	public void setFather(User father) {
		this.father = father;
	}
	public List<User> getChildList() {
		return childList;
	}
	public void setChildList(List<User> childList) {
		this.childList = childList;
	}
}
