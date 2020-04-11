package com.any.store.controller;

import javax.swing.table.DefaultTableModel;

public class Controller {

    private DefaultTableModel tableModel = null;

    public void fillTable(String inpFilter, DefaultTableModel tableModel) {
        if (this.tableModel == null) {
            this.tableModel = tableModel;
        }

    }

}
