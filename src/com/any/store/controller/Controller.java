package com.any.store.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.any.store.model.Attribute;
import com.any.store.model.Database;
import com.any.store.model.Item;
import com.any.store.model.List;

public class Controller {
	public Database db = new Database();

	private DefaultTableModel tableModel = null;
	private ArrayList<Attribute> atts = new ArrayList<Attribute>();
	private ArrayList<List> lists = new ArrayList<List>();
	private ArrayList<Item> items = new ArrayList<Item>();
	ArrayList<String> colValues = new ArrayList<String>();

	public void fillTable(String listName, DefaultTableModel tableModel, JTable table) throws SQLException {
		atts.clear();
		lists.clear();
		items.clear();
		colValues.clear();
		if (this.tableModel == null) {
			this.tableModel = tableModel;
		}
//		tableModel.setRowCount(0);
		tableModel.setColumnCount(0);
		tableModel.getDataVector().removeAllElements();

		ArrayList<String> row = new ArrayList<String>(); // will hold 1 row each time

		int listId = db.searchList(listName).get(0).getId(); // to find attributes
		atts = db.searchAttribute(listId); // specific attributes for the list
		ArrayList<Integer> idColumn = new ArrayList<Integer>();
		if (!atts.isEmpty()) {
			idColumn = db.searchItemIds(atts.get(0).getId());

		}
		tableModel.addColumn("Id Item", idColumn.toArray()); // this is the hidden column for items

		for (int i = 0; i < atts.size(); i++) {
			colValues.clear();
			for (int j = 0; j < db.searchItem(atts.get(i).getId()).size(); j++) {
				colValues.add(db.searchItem(atts.get(i).getId()).get(j).getValue());
			}
			tableModel.addColumn(atts.get(i).getName(), colValues.toArray());
		}
		TableColumnModel tcm = table.getColumnModel();
		tcm.removeColumn(tcm.getColumn(0));

		refresh();

	}

	public void refresh() {
		// make the changes to the table, then call fireTableChanged
		tableModel.fireTableDataChanged();
	}

	public String[] getList() {
		String[] result = null;
		try {
			result = new String[db.loadLists().size()];
			for (int i = 0; i < db.loadLists().size(); i++) {
				result[i] = db.loadLists().get(i).getName();

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<String> getAttributes() {
		ArrayList<String> result = new ArrayList<String>();
		try {
			for (int i = 0; i < db.loadAttributes().size(); i++) {
				result.add(db.loadAttributes().get(i).getName());

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}

	public void addItem(ArrayList<String> data) {
		
		// TODO Auto-generated method stub
		
	}
}
