package com.any.store.model;

public class List {

	private int id;
	private String name;

	@Override
	public String toString() {
		return "List [id=" + id + ", name=" + name + "]";
	}
	public void print() {
		System.out.println(toString());
	}

	public List(String name) {
		super();
		this.name = name;
	}

	public List(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
