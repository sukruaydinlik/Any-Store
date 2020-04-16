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
		items.clear();

		int id;
		int attId;
		String value;
		int itemId;

		prepStat = con.prepareStatement("select * from item");

		rs = prepStat.executeQuery();

		while (rs.next()) {
			id = rs.getInt("id");
			attId = rs.getInt("att_id");
			value = rs.getString("value");
			itemId = rs.getInt("item_id");
			Item item = new Item(id, attId, value, itemId);
			items.add(item);
		}
		return items;
	}

	public ArrayList<List> loadLists() throws SQLException {
		lists.clear();

		int id;
		String name;

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
		attributes.clear();

		int id;
		int listId;
		String name;

		prepStat = con.prepareStatement("select * from attribute");

		rs = prepStat.executeQuery();

		while (rs.next()) {
			id = rs.getInt("id");
			listId = rs.getInt("list_id");
			name = rs.getString("name");

			Attribute att = new Attribute(id, listId, name);
			attributes.add(att);
		}
		return attributes;
	}

	public void insertItem(int attId, String value, int itemId) throws SQLException {
		prepStat = con.prepareStatement("insert into item(att_id,value,item_id) values(?,?,?)");
		prepStat.setInt(1, attId);
		prepStat.setString(2, value);
		prepStat.setInt(3, itemId);
		prepStat.executeUpdate();

	}

	public void insertList(String name) throws SQLException {
		prepStat = con.prepareStatement("insert into list(name) values(?)");
		prepStat.setString(1, name);
		prepStat.executeUpdate();

	}

	public void insertAttribute(int listId, String name) throws SQLException {
		prepStat = con.prepareStatement("insert into attribute(list_id,name) values(?,?)");
		prepStat.setInt(1, listId);
		prepStat.setString(2, name);
		prepStat.executeUpdate();
	}

	public ArrayList<List> searchList(String listName) throws SQLException {
		lists.clear();

		int id;
		String name;

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

	public ArrayList<Item> searchItem(int sattId) throws SQLException {
		items.clear();

		int id;
		int attId;
		String value;
		int itemId;

		prepStat = con.prepareStatement("select * from item where att_id =? group by item_id");
		prepStat.setInt(1, sattId);
//		prepStat.setInt(2, sItemId);

		rs = prepStat.executeQuery();

		while (rs.next()) {
			id = rs.getInt("id");
			attId = rs.getInt("att_id");
			value = rs.getString("value");
			itemId = rs.getInt("item_id");
			Item item = new Item(id, attId, value, itemId);
			items.add(item);
		}

		return items;
	}

	public ArrayList<Attribute> searchAttribute(int idOfList) throws SQLException {
		attributes.clear();

		int id;
		int listId;
		String name;

		prepStat = con.prepareStatement("select * from attribute where list_id = ?");
		prepStat.setInt(1, idOfList);
		rs = prepStat.executeQuery();

		while (rs.next()) {
			id = rs.getInt("id");
			listId = rs.getInt("list_id");
			name = rs.getString("name");

			Attribute att = new Attribute(id, listId, name);
			attributes.add(att);
		}
		return attributes;
	}

	public ArrayList<Item> getRow(int sItemId) throws SQLException {
		items.clear();

		int id;
		int attId;
		String value;
		int itemId;

		prepStat = con.prepareStatement("select * from item where item_id =?");
		prepStat.setInt(1, sItemId);
		rs = prepStat.executeQuery();

		while (rs.next()) {
			id = rs.getInt("id");
			attId = rs.getInt("att_id");
			value = rs.getString("value");
			itemId = rs.getInt("item_id");
			Item item = new Item(id, attId, value, itemId);
			items.add(item);
		}

		return items;

	}

	public ArrayList<Integer> searchItemIds(int attId) throws SQLException {
		ArrayList<Integer> result = new ArrayList<Integer>();
		int itemId;

		prepStat = con.prepareStatement("select item_id from item where att_id = ? group by item_id order by item_id asc");
		prepStat.setInt(1, attId);
		rs = prepStat.executeQuery();

		while (rs.next()) {
			itemId = rs.getInt("item_id");
			result.add(itemId);
		}
		return result;
	}
}
