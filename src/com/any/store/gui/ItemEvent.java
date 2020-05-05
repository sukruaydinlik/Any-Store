package com.any.store.gui;

import java.util.ArrayList;
import java.util.EventObject;

public class ItemEvent extends EventObject {
    private static final long serialVersionUID = -3602361238777117075L;

    private String listName;
    private ArrayList<String> data;
    private int itemId;

    public ItemEvent(Object source, ArrayList<String> data, String listName) {
        super(source);
        this.listName = listName;
        this.data = data;
    }

    public ItemEvent(Object source, ArrayList<String> data, String listName, int itemId) {
        super(source);
        this.listName = listName;
        this.data = data;
        this.itemId = itemId;
    }


    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public ArrayList<String> getData() {
        return data;
    }

    public void setData(ArrayList<String> data) {
        this.data = data;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }


}
