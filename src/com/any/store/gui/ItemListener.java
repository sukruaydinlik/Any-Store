package com.any.store.gui;

import java.sql.SQLException;

public interface ItemListener {
	public void addEventOccured(ItemEvent ie) throws SQLException;
	public void editEventOccured(ItemEvent ie) throws SQLException;
}
