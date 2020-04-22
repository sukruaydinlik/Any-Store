package com.any.store.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.any.store.model.Attribute;
import com.any.store.model.Database;
import com.any.store.model.Item;
import com.any.store.model.List;

public class Controller {

	private Database db = new Database();
	private DefaultTableModel tableModel = null;
	private ArrayList<Attribute> atts = new ArrayList<Attribute>();
	private ArrayList<List> lists = new ArrayList<List>();
	private ArrayList<Item> items = new ArrayList<Item>();
	ArrayList<String> colValues = new ArrayList<String>();

	// sets table data according to specific list
	public void fillTable(String listName, DefaultTableModel tableModel, JTable table) throws SQLException {
		atts.clear();
		lists.clear();
		items.clear();
		colValues.clear();
		if (this.tableModel == null) {
			this.tableModel = tableModel;
		}
		System.out.println(tableModel == null);
		tableModel.setRowCount(0);
		tableModel.setColumnCount(0);
		tableModel.getDataVector().removeAllElements();

		// ArrayList<String> row = new ArrayList<String>(); // will hold 1 row each time

		int listId = db.searchList(listName); // to find attributes
		atts = db.searchAttribute(listId); // specific attributes for the list
		// System.out.println(atts.size());
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
			result = new String[db.readLists().size()];
			for (int i = 0; i < db.readLists().size(); i++) {
				result[i] = db.readLists().get(i).getName();

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	// returns to attributes specific to the list
	public ArrayList<String> findAttributes(String listName) throws SQLException {

		ArrayList<String> result = new ArrayList<String>();

		int listId = db.searchList(listName);// to find attributes
		System.out.println("List Id: " + listId);

		for (int i = 0; i < db.searchAttribute(listId).size(); i++) {
			result.add(db.searchAttribute(listId).get(i).getName());
		}

		return result;
	}

	public void addItem(ArrayList<String> data, String listName) {
		atts.clear();
		try {
			// get id of the list
			int listId = db.searchList(listName);
			int itemId = db.readItemId();
			// get att_ids
			atts = db.searchAttribute(listId);
			// write item
			//System.out.println(" data size: "+data.size());
			System.out.println("Attribute size: "+atts.size() );

			for (int i = 0; i< data.size();i++) {
				db.writeItem(atts.get(i).getId(), data.get(i), itemId+1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
