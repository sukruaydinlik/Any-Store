package com.any.store.gui;

import java.util.ArrayList;
import java.util.EventObject;

public class ListEvent extends EventObject{
	private static final long serialVersionUID = 1200239289986234350L;
	
	public ArrayList<String> atts;
	public String listName;
	
	public ListEvent(Object arg0) {
		super(arg0);

	}

	public ListEvent(Object arg0, ArrayList<String> atts, String listName) {
		super(arg0);
		this.atts = atts;
		this.listName = listName;
	}

	public ArrayList<String> getAtts() {
		return atts;
	}

	public void setAtts(ArrayList<String> atts) {
		this.atts = atts;
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

}
