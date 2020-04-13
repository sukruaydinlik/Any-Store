package com.any.store.model;

public class Attribute {
	private int id;
	private int tableId;
	private String name;
	
	
	@Override
	public String toString() {
		return "Attribute [id=" + id + ", tableId=" + tableId + ", name=" + name + "]";
	}


	public Attribute(int id, int tableId, String name) {
		super();
		this.id = id;
		this.tableId = tableId;
		this.name = name;
	}
	
	public Attribute(int tableId, String name) {
		super();
		this.tableId = tableId;
		this.name = name;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getTableId() {
		return tableId;
	}


	public void setTableId(int tableId) {
		this.tableId = tableId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

}
