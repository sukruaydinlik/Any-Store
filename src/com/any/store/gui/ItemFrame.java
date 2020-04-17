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

public class ItemFrame extends JFrame {

	private static final long serialVersionUID = 4247927514170176340L;

	private GridBagConstraints gbc;
	private JPanel itemPanel;
	private JLabel label;
	private JScrollPane jScrollPane1;
	private JTextField textField;
	private JLabel listLabel;
	private JLabel listName;
	private JButton saveButon;
	private ArrayList<JTextField> textFields;
	private ItemListener itemLis;
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

	public ItemFrame(ArrayList<String> columnNames, String tableName) {
		initComponents(tableName);
		setItemPanel(columnNames);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	private void initComponents(String tableName) {
		GridBagConstraints gridBagConstraints;

		jScrollPane1 = new JScrollPane();
		itemPanel = new JPanel();
		listLabel = new JLabel();
		saveButon = new JButton();
		label = new JLabel();
		textField = new JTextField();
		listName = new JLabel();
		textFields = new ArrayList<JTextField>();
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Item");
		setBounds(new Rectangle(200, 200, 640, 480));
		setPreferredSize(new Dimension(800, 600));
		
		saveButon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> data = new ArrayList<String>();
				for (int i = 0; i < textFields.size(); i++) {
					data.add(textFields.get(i).getText());
				}
				ItemEvent ie = new ItemEvent(this, data);
				if (itemLis != null) {
					itemLis.eventOccured(ie);
				}
			}
		});
		itemPanel.setBackground(new Color(69, 73, 74));
		itemPanel.setForeground(new Color(69, 73, 74));
		itemPanel.setLayout(new GridBagLayout());

		listLabel.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
		listLabel.setText("List: ");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.anchor = GridBagConstraints.LINE_END;
		gridBagConstraints.weightx = 1;
		gridBagConstraints.weighty = 1;
		gridBagConstraints.insets = new Insets(0, 0, 0, 5);
		itemPanel.add(listLabel, gridBagConstraints);
		listLabel.setForeground(new Color(187, 187, 187));

		/*
		 * saveButton.setBackground(new Color(1, 198, 83)); saveButton.setText("Save");
		 * gridBagConstraints = new GridBagConstraints(); gridBagConstraints.gridx = 14;
		 * gridBagConstraints.gridy = 15; gridBagConstraints.anchor =
		 * GridBagConstraints.SOUTHEAST; gridBagConstraints.weightx = 10.0;
		 * gridBagConstraints.weighty = 10.0; gridBagConstraints.insets = new Insets(0,
		 * 0, 20, 20); itemPanel.add(saveButton, gridBagConstraints);
		 */

		listName.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
		listName.setText(tableName);
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.anchor = GridBagConstraints.LINE_START;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(10, 5, 10, 10);
		itemPanel.add(listName, gridBagConstraints);
		listName.setForeground(new Color(187, 187, 187));

		jScrollPane1.setViewportView(itemPanel);

		getContentPane().add(jScrollPane1, BorderLayout.CENTER);

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

			gbc.gridy++;
			itemPanel.add(textField, gbc);
			textFields.add(textField);
			gr = gbc.gridy;
		}

		saveButon.setBackground(new Color(1, 198, 83));
		saveButon.setText("Save");
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

	public ArrayList<JTextField> getTextFields() {
		return textFields;
	}

	public void setTextFields(ArrayList<JTextField> textFields) {
		this.textFields = textFields;
	}

	public JButton getSaveButton() {
		return saveButon;
	}

	public void setSaveButton(JButton saveButton) {
		this.saveButon = saveButton;
	}

	public ItemListener getItemLis() {
		return itemLis;
	}

	public void setItemListener(ItemListener itemLis) {
		this.itemLis = itemLis;
	}

}
