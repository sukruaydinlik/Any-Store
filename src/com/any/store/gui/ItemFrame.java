package com.any.store.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;

public class ItemFrame extends JFrame {

	private static final long serialVersionUID = 4247927514170176340L;

	private GridBagConstraints gbc;
	private JPanel itemPanel;
	private JLabel label;
	private JScrollPane mainJSP;
	private JTextField textField;
	private JLabel listLabel;
	private JLabel listNameLbl;
	private JButton saveButon;
	private ArrayList<JTextField> aLTextFields;
	private ItemListener itemListener;
	private String listName;

	public static void main(String args[]) {
		ArrayList<String> test = new ArrayList<String>();

		for (int i = 0; i < 200; i++) {
			test.add("Column " + i);
		}

		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(ItemFrame.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			Logger.getLogger(ItemFrame.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(ItemFrame.class.getName()).log(Level.SEVERE, null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			Logger.getLogger(ItemFrame.class.getName()).log(Level.SEVERE, null, ex);
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ItemFrame(test, "deneme").setVisible(true);
			}
		});
	}

	public ItemFrame() {
		initComponents("Null");
	}

	public ItemFrame(ArrayList<String> columnNames, String listName) {
		this.listName = listName;
		initComponents(listName);
		setItemPanel(columnNames);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		UIManager.put("TextField.caretForeground", new ColorUIResource(1, 198, 83));

	}

	private void initComponents(String listNameStr) {
		GridBagConstraints gbc;

		mainJSP = new JScrollPane();
		itemPanel = new JPanel();
		listLabel = new JLabel();
		saveButon = new JButton();
		label = new JLabel();
		textField = new JTextField();
		listNameLbl = new JLabel();
		aLTextFields = new ArrayList<JTextField>();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Item");
		setBounds(new Rectangle(5, 5, 640, 480));
		setPreferredSize(new Dimension(800, 600));

		itemPanel.setBackground(new Color(69, 73, 74));
		itemPanel.setForeground(new Color(69, 73, 74));
		itemPanel.setLayout(new GridBagLayout());

		listLabel.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
		listLabel.setText("List: ");
		listLabel.setForeground(new Color(187, 187, 187));

		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = 2;
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(0, 0, 0, 5);
		itemPanel.add(listLabel, gbc);

		/*
		 * saveButton.setBackground(new Color(1, 198, 83)); saveButton.setText("Save");
		 * gridBagConstraints = new GridBagConstraints(); gridBagConstraints.gridx = 14;
		 * gridBagConstraints.gridy = 15; gridBagConstraints.anchor =
		 * GridBagConstraints.SOUTHEAST; gridBagConstraints.weightx = 10.0;
		 * gridBagConstraints.weighty = 10.0; gridBagConstraints.insets = new Insets(0,
		 * 0, 20, 20); itemPanel.add(saveButton, gridBagConstraints);
		 */

		listNameLbl.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
		listNameLbl.setText(listNameStr);
		listNameLbl.setForeground(new Color(187, 187, 187));

		gbc = new GridBagConstraints();
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridheight = 2;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.insets = new Insets(10, 5, 10, 10);
		itemPanel.add(listNameLbl, gbc);

		mainJSP.setViewportView(itemPanel);

		getContentPane().add(mainJSP, BorderLayout.CENTER);

		pack();
	}

	public void setItemPanel(ArrayList<String> labelNames) {

		int gr = 0; // will hold gridy value for saveButton

		// Setting the position of the labels
		gbc = new GridBagConstraints();
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.insets = new Insets(10, 0, 10, 0);

		// Create label and add it to the list
		for (int i = 0; i < labelNames.size(); i++) {
			label = new JLabel();
			label.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
			label.setText(labelNames.get(i) + ": ");
			label.setForeground(new Color(187, 187, 187));

			gbc.gridy++; // increase the vertical position

			itemPanel.add(label, gbc);
		}

		gbc.gridx = 3;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;

		// Create TextFields and add to the list and to the table
		for (int i = 0; i < labelNames.size(); i++) {
			textField = new JTextField(10);

			textField.setFont(new Font("Segoe UI", 0, 14)); // NOI18N

			textField.setText("");
			textField.setAlignmentX(1.0F);
			textField.setBorder(new LineBorder(new Color(187, 187, 187), 2, true));
			textField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
			textField.setMargin(new Insets(2, 15, 2, 6));
			textField.setSelectionColor(new Color(1, 198, 83));
			textField.setBackground(new Color(69, 73, 74));
			textField.setForeground(new Color(187, 187, 187));
			textField.setColumns(10);
			textField.setCaretColor(new Color(1, 198, 83));

			gbc.gridy++;
			itemPanel.add(textField, gbc);
			aLTextFields.add(textField);
			gr = gbc.gridy;
		}

		saveButon.setBackground(new Color(1, 198, 83));
		saveButon.setText("Save");
		saveButon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveBtnActionPerformed();
			}
		});

		gbc = new GridBagConstraints();
		gbc.gridx = 4;
		gbc.gridy = ++gr;
		gbc.anchor = GridBagConstraints.SOUTHEAST;
		gbc.weightx = 10.0;
		gbc.weighty = 10.0;
		gbc.insets = new Insets(0, 0, 20, 20);
		itemPanel.add(saveButon, gbc);

//		itemPanel.updateUI();
	}

	private void saveBtnActionPerformed() {
		// holds data from text fields
		ArrayList<String> data = new ArrayList<String>();

		// add data to the arraylist
		for (int i = 0; i < aLTextFields.size(); i++) {
			data.add(aLTextFields.get(i).getText());
		}
		System.out.println("Data size in item Frame: " + data.size());
		ItemEvent ie = new ItemEvent(this, data, listName);
		if (itemListener != null) {
			itemListener.eventOccured(ie);
		}

	}

	public ArrayList<JTextField> getTextFields() {
		return aLTextFields;
	}

	public void setTextFields(ArrayList<JTextField> textFields) {
		this.aLTextFields = textFields;
	}

	public JButton getSaveButton() {
		return saveButon;
	}

	public void setSaveButton(JButton saveButton) {
		this.saveButon = saveButton;
	}

	public ItemListener getItemLis() {
		return itemListener;
	}

	public void setItemListener(ItemListener itemLis) {
		this.itemListener = itemLis;
	}

}
