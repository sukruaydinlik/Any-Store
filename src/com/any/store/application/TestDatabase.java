package com.any.store.application;

import java.sql.SQLException;
import java.util.ArrayList;

import com.any.store.model.Attribute;
import com.any.store.model.Database;
import com.any.store.model.Item;
import com.any.store.model.List;

public class TestDatabase {
	public static void main(String[] args) throws SQLException {
		Database db = new Database();
		ArrayList<Attribute> atts = null;
		ArrayList<List> lists = null;
		ArrayList<Item> items = null;

//		db.insertList("kalem");
//		db.insertAttribute(4, "uç boyutu");
//		db.insertItem(7, "0.7", 6);
		items = db.loadItems();

		for (int i = 0; i < items.size(); i++) {
			System.out.println(items.get(i).toString());
		}
		System.out.println();

		atts = db.loadAttributes();
		for (int i = 0; i < atts.size(); i++) {
			System.out.println(atts.get(i).toString());
		}
		System.out.println();

		lists = db.loadLists();
		for (int i = 0; i < lists.size(); i++) {
			System.out.println(lists.get(i).toString());
		}
		System.out.println();

		atts = db.searchAttribute(1);
		for (int i = 0; i < atts.size(); i++) {
			atts.get(i).printClass();
		}
		lists = db.searchList("kitap");

		for (int i = 0; i < lists.size(); i++) {
			lists.get(i).printClass();
		}
		items = db.searchItem(2);

		for (int i = 0; i < items.size(); i++) {
			items.get(i).printClass();
		}

	}
}
