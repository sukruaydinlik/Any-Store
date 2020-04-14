package com.any.store.model;

public class Attribute {

	private int id;
	private int listId;
	private String name;

	@Override
	public String toString() {
		return "Attribute [id=" + id + ", listId=" + listId + ", name=" + name + "]";
	}
	
	public void printClass() {
		System.out.println(toString());
	}
	public Attribute(int listId, String name) {
		super();
		this.listId = listId;
		this.name = name;
	}

	public Attribute(int id, int listId, String name) {
		super();
		this.id = id;
		this.listId = listId;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getListId() {
		return listId;
	}

	public void setListId(int listId) {
		this.listId = listId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
