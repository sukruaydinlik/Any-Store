package com.any.store.controller;

import com.any.store.model.Attribute;
import com.any.store.model.Database;
import com.any.store.model.Item;
import com.any.store.model.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.util.ArrayList;

public class Controller {

    private final Database db = new Database();
    private DefaultTableModel tableModel = null;
    private ArrayList<Attribute> atts = new ArrayList<Attribute>();
    private final ArrayList<List> lists = new ArrayList<List>();
    private final ArrayList<Item> items = new ArrayList<Item>();
    ArrayList<String> colValues = new ArrayList<String>();

    // sets table data according to specific list
    public void fillTable(String listName, DefaultTableModel tableModel, JTable table) {
        atts.clear();
        lists.clear();
        items.clear();
        colValues.clear();

        if (this.tableModel == null) {
            this.tableModel = tableModel;
        }

        // System.out.println(tableModel == null);
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        tableModel.getDataVector().removeAllElements();

        // ArrayList<String> row = new ArrayList<String>(); // will hold 1 row each time

        atts = db.searchAttribute(listName); // specific attributes for the list
        // // System.out.println(atts.size());
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
        result = new String[db.readLists().size()];
        for (int i = 0; i < db.readLists().size(); i++) {
            result[i] = db.readLists().get(i).getName();

        }

        return result;
    }

    // returns to attributes specific to the list
    public ArrayList<String> findAttributes(String listName) {

        ArrayList<String> result = new ArrayList<String>();

        for (int i = 0; i < db.searchAttribute(listName).size(); i++) {
            result.add(db.searchAttribute(listName).get(i).getName());
        }

        return result;
    }

    public void addItem(ArrayList<String> data, String listName) {
        atts.clear();

        // get id of the list
        int itemId = db.readItemId();

        // get att_ids
        atts = db.searchAttribute(listName);

        // write item
        for (int i = 0; i < data.size(); i++) {
            db.writeItem(atts.get(i).getId(), data.get(i), itemId + 1);
        }
    }

    public void addList(String listName, ArrayList<String> atts) {
        // System.out.println(db.searchList(listName));
        if (db.searchList(listName) == 0) {
            // System.out.println("here");
            db.writeList(listName);

            for (String att : atts) {
                db.writeAttribute(listName, att);
            }

        } else {
            // System.out.println("List already exists!!");
            JOptionPane.showMessageDialog(null, "List already exists!!");
        }

    }

    public void editList(String listName, ArrayList<String> newAtts) {
        atts.clear();
        atts = db.searchAttribute(listName);
        ArrayList<String> olds = new ArrayList<>();

        for (int i = 0; i < atts.size(); i++) {
            olds.add(atts.get(i).getName());
        }
        //if already has, then delete it
        for (int i = 0; i < newAtts.size(); ) {
            if (!olds.contains(newAtts.get(i))) {
                db.writeAttribute(listName, newAtts.get(i));
                newAtts.remove(i);
            }else{
                i++;
            }
        }
        //if not in remaining, then delete
        for (int i = 0; i < olds.size(); i++) {
            System.out.println(olds.get(i));
            if (!newAtts.contains(olds.get(i))) {
                System.out.println("removing attribute " + olds.get(i));
                db.removeAttribute(olds.get(i), listName);
            }
        }
    }

    public void editItem(ArrayList<String> data, String listName, int itemId) {
        ArrayList<Attribute> atts = db.searchAttribute(listName);
        db.removeItem(itemId);
        for (int i = 0; i < atts.size(); i++) {
            db.writeItem(atts.get(i).getId(),data.get(i), itemId);
        }
    }

    public void deleteItem(int itemId) {
        db.removeItem(itemId);
    }

    public void deleteList(String listName) {
        db.removeList(listName);
    }

    public ArrayList<Item> getRow(int itemId) {
        return db.searchRow(itemId);
    }
}
