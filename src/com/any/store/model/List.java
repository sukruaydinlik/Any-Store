package com.any.store.model;

public class List {
	private int id;
	private String name;

	public List(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public List(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "Table [id=" + id + ", name=" + name + "]";
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
