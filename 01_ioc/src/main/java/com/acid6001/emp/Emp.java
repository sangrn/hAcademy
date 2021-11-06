package com.acid6001.emp;

public abstract class Emp {
	public Emp getEmp() {
		return this;
	}

	public void work() {
		getEmp().work();
	}
}