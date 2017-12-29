package com.ross.college_list;

import java.io.Serializable;

public class College implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private int numAppear = 1;
	
	public College(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumAppear() {
		return numAppear;
	}

	public void setNumAppear(int numAppear) {
		this.numAppear = numAppear;
	}

	public void addAppear() {
		numAppear++;
	}
}
