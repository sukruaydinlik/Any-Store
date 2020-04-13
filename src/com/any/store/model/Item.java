package com.any.store.model;

public class Item {
	
	private int id;
	private int attId;
	private String value;
	
	@Override
	public String toString() {
		return "Item [id=" + id + ", attId=" + attId + ", value=" + value + "]";
	}

	public Item(int id, int attId, String value) {
		super();
		this.id = id;
		this.attId = attId;
		this.value = value;
	}
	
	public Item(int attId, String value) {
		super();
		this.attId = attId;
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAttId() {
		return attId;
	}

	public void setAttId(int attId) {
		this.attId = attId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	
	
	
}
