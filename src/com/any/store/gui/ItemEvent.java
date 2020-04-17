package com.any.store.gui;

import java.util.ArrayList;
import java.util.EventObject;

public class ItemEvent extends EventObject{
	private static final long serialVersionUID = -3602361238777117075L;

	private ArrayList<String> data;
	public ItemEvent(Object source) {
		super(source);
	}

	public ItemEvent(Object source, ArrayList<String> data) {
		super(source);
		this.data = data;
	}

	public ArrayList<String> getData() {
		return data;
	}

	public void setData(ArrayList<String> data) {
		this.data = data;
	}


}
