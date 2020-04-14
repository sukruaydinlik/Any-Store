package com.any.store.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import com.any.store.model.Attribute;
import com.any.store.model.Database;
import com.any.store.model.Item;
import com.any.store.model.List;


public class Controller {
    public Database db = new Database();

	private DefaultTableModel tableModel = null;
	ArrayList<Attribute> atts = null;
	ArrayList<List> lists = null;
	ArrayList<Item> items = null;

	public void fillTable(String listName, DefaultTableModel tableModel) throws SQLException {
		atts.clear();
		lists.clear();
		items.clear();
		if (this.tableModel == null) {
			this.tableModel = tableModel;
		}
		ArrayList<String> row = new ArrayList<String>();
		int listId = db.searchList(listName).get(0).getId();
		atts = db.searchAttribute(listId);
		
		items = db.searchItem(atts.get(0).getId());
		
		for (int i = 0; i < atts.size(); i++) {
			tableModel.addColumn(atts.get(i).getName());
		}
		
		
	}

}
