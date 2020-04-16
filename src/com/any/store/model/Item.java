package com.any.store.model;

public class Item {

	private int id;
	private int attId;
	private String value;
	private int itemId;

	@Override
	public String toString() {
		return "Item [id=" + id + ", attId=" + attId + ", value=" + value + ", itemId=" + itemId + "]";
	}
	public void print() {
		System.out.println(toString());
	}

	public Item(int attId, String value, int itemId) {
		super();
		this.attId = attId;
		this.value = value;
		this.itemId = itemId;
	}

	public Item(int id, int attId, String value, int itemId) {
		super();
		this.id = id;
		this.attId = attId;
		this.value = value;
		this.itemId = itemId;
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

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

}
