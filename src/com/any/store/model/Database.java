package com.any.store.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Database {

	public ArrayList<Item> items;
	public ArrayList<List> lists;
	public ArrayList<Attribute> attributes;

	private String url = "jdbc:sqlite:db.db";
	private Connection con = null;
	private PreparedStatement prepStat = null;
	private ResultSet rs = null;

	public boolean isConnected;

	public Database() {
		try {
			url = "jdbc:sqlite:databaseFile.db";
			con = DriverManager.getConnection(url);
			this.isConnected = true;
		} catch (SQLException e) {
			this.isConnected = false;
		}
		if (isConnected) {
			System.out.println("Connection successfull!");
		} else {
			System.out.println("Connection error");
		}

		initialize();

	}

	private void initialize() {
		items = new ArrayList<Item>();
		lists = new ArrayList<List>();
		attributes = new ArrayList<Attribute>();
	}

	public void disconnect() {
		try {
			con.close();
			this.isConnected = false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Item> loadItems() throws SQLException {
		int id;
		int attId;
		String value;

		items.clear();

		prepStat = con.prepareStatement("select * from item");

		rs = prepStat.executeQuery();

		while (rs.next()) {
			id = rs.getInt("id");
			attId = rs.getInt("att_id");
			value = rs.getString("value");

			Item item = new Item(id, attId, value);
			items.add(item);
		}
		return items;
	}

	public ArrayList<List> loadLists() throws SQLException {
		int id;
		String name;

		lists.clear();

		prepStat = con.prepareStatement("select * from list");

		rs = prepStat.executeQuery();

		while (rs.next()) {
			id = rs.getInt("id");
			name = rs.getString("name");

			List list = new List(id, name);
			lists.add(list);
		}
		return lists;
	}

	public ArrayList<Attribute> loadAttributes() throws SQLException {
		int id;
		int tableId;
		String name;

		attributes.clear();

		prepStat = con.prepareStatement("select * from attribute");

		rs = prepStat.executeQuery();

		while (rs.next()) {
			id = rs.getInt("id");
			tableId = rs.getInt("table_id");
			name = rs.getString("name");

			Attribute att = new Attribute(id, tableId, name);
			attributes.add(att);
		}
		return attributes;
	}

	public void insertItem(Item item) {

	}

	public void insertList(List list) {

	}

	public void insertAttribute(Attribute att) {

	}

	public ArrayList<List> searchList(String listName) throws SQLException {
		int id;
		String name;

		lists.clear();

		prepStat = con.prepareStatement("select * from list where name = ?");
		prepStat.setString(1, listName);

		rs = prepStat.executeQuery();

		while (rs.next()) {
			id = rs.getInt("id");
			name = rs.getString("name");

			List list = new List(id, name);
			lists.add(list);
		}
		return lists;

	}

	public ArrayList<List> searchItem(int attId) throws SQLException {
		int id;
		String name;

		lists.clear();

		prepStat = con.prepareStatement("select * from item where att_id = ?");
		prepStat.setInt(1, attId);

		rs = prepStat.executeQuery();

		while (rs.next()) {
			id = rs.getInt("id");
			name = rs.getString("name");

			List list = new List(id, name);
			lists.add(list);
		}
		return lists;

	}

	public ArrayList<Attribute> searchAttribute(int listId) throws SQLException {
		int id;
		int tableId;
		String name;

		attributes.clear();

		prepStat = con.prepareStatement("select * from attribute where list_id = ?");
		prepStat.setInt(1, listId);
		rs = prepStat.executeQuery();

		while (rs.next()) {
			id = rs.getInt("id");
			tableId = rs.getInt("table_id");
			name = rs.getString("name");

			Attribute att = new Attribute(id, tableId, name);
			attributes.add(att);
		}
		return attributes;
	}
}
