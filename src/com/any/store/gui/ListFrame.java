package com.any.store.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListFrame extends JFrame {
	
	private JButton addPropButton;
	private JButton deletePropButton;
	private JScrollPane jScrollPane2;
	private JList<String> listList;
	private JPanel mainPane;
	private JTextField nameField;
	private JLabel nameLabel;
	private JTextField propertyField;
	private JLabel propertyLabel;
	private JButton saveButton;

	public ListFrame() {
		initComponents();
	}

	private void initComponents() {

		mainPane = new JPanel();
		jScrollPane2 = new JScrollPane();
		listList = new JList<>();
		nameLabel = new JLabel();
		nameField = new JTextField();
		propertyLabel = new JLabel();
		propertyField = new JTextField();
		addPropButton = new JButton();
		deletePropButton = new JButton();
		saveButton = new JButton();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("List");
		setBackground(new Color(69, 73, 74));
		setBounds(new Rectangle(200, 100, 640, 480));
		setPreferredSize(new Dimension(800, 600));

		mainPane.setBackground(new Color(69, 73, 74));
		mainPane.setForeground(new Color(69, 73, 74));

		listList.setBorder(new LineBorder(new Color(187, 187, 187), 2, true));
		listList.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
		listList.setModel(new AbstractListModel<String>() {
			String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };

			public int getSize() {
				return strings.length;
			}

			public String getElementAt(int i) {
				return strings[i];
			}
		});
		listList.setSelectionBackground(new Color(1, 198, 83));
		listList.setBackground(new Color(69, 73, 74));
		listList.setForeground(new Color(187, 187, 187));
		listList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent evt) {
				listListValueChanged(evt);
			}
		});
		jScrollPane2.setViewportView(listList);

		nameLabel.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
		nameLabel.setText("Name: ");
		nameLabel.setForeground(new Color(187, 187, 187));

		nameField.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
		nameField.setText("jTextField1");
		nameField.setBorder(new LineBorder(new Color(187, 187, 187), 2, true));
		nameField.setSelectionColor(new Color(1, 198, 83));
		nameField.setBackground(new Color(69, 73, 74));
		nameField.setForeground(new Color(187, 187, 187));
		nameField.setColumns(15);

		propertyLabel.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
		propertyLabel.setText("Property: ");
		propertyLabel.setForeground(new Color(187, 187, 187));

		propertyField.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
		propertyField.setText("jTextField2");
		propertyField.setBorder(new LineBorder(new Color(187, 187, 187), 2, true));
		propertyField.setSelectionColor(new Color(1, 198, 83));
		propertyField.setBackground(new Color(69, 73, 74));
		propertyField.setForeground(new Color(187, 187, 187));
		propertyField.setColumns(15);

		addPropButton.setBackground(new Color(1, 198, 83));
		addPropButton.setForeground(new Color(226, 226, 226));
		addPropButton.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
		addPropButton.setText("Add ");
		addPropButton.setFocusable(false);
		addPropButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				addPropButtonActionPerformed(evt);
			}
		});

		deletePropButton.setBackground(new Color(1, 198, 83));
		deletePropButton.setForeground(new Color(226, 226, 226));
		deletePropButton.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
		deletePropButton.setText("Delete");
		deletePropButton.setFocusable(false);
		deletePropButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				deletePropButtonActionPerformed(evt);
			}
		});

		saveButton.setBackground(new Color(1, 198, 83));
		saveButton.setForeground(new Color(226, 226, 226));
		saveButton.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
		saveButton.setText("Save");
		saveButton.setFocusable(false);
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				saveButtonActionPerformed(evt);
			}
		});

		GroupLayout mainPaneLayout = new GroupLayout(mainPane);
		mainPane.setLayout(mainPaneLayout);
		mainPaneLayout.setHorizontalGroup(mainPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(mainPaneLayout.createSequentialGroup().addContainerGap()
						.addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
						.addGroup(mainPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(mainPaneLayout.createSequentialGroup()
										.addGroup(mainPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addGroup(mainPaneLayout.createSequentialGroup().addGap(100, 100, 100)
														.addComponent(propertyLabel))
												.addGroup(GroupLayout.Alignment.TRAILING,
														mainPaneLayout.createSequentialGroup().addPreferredGap(
																LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(nameLabel)))
										.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(mainPaneLayout
												.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(nameField, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(addPropButton)
												.addComponent(propertyField, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(deletePropButton))
										.addContainerGap(456, Short.MAX_VALUE))
								.addGroup(GroupLayout.Alignment.TRAILING,
										mainPaneLayout.createSequentialGroup()
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(saveButton).addGap(31, 31, 31)))));
		mainPaneLayout.setVerticalGroup(mainPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(mainPaneLayout.createSequentialGroup().addContainerGap().addComponent(jScrollPane2)
						.addContainerGap())
				.addGroup(mainPaneLayout.createSequentialGroup().addGap(107, 107, 107)
						.addGroup(mainPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(nameLabel).addComponent(nameField, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(52, 52, 52)
						.addGroup(mainPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(propertyLabel).addComponent(propertyField,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18).addComponent(addPropButton).addGap(18, 18, 18)
						.addComponent(deletePropButton)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 184, Short.MAX_VALUE)
						.addComponent(saveButton).addGap(41, 41, 41)));

		getContentPane().add(mainPane, BorderLayout.CENTER);

		pack();
	}

	private void addPropButtonActionPerformed(ActionEvent evt) {
		// GEN-FIRST:event_addPropButtonActionPerformed
		// TODO add your handling code here:
	}

	private void deletePropButtonActionPerformed(ActionEvent evt) {
		// GEN-FIRST:event_deletePropButtonActionPerformed
		// TODO add your handling code here:
	}

	private void listListValueChanged(ListSelectionEvent evt) {
		// GEN-FIRST:event_listListValueChanged
		// TODO add your handling code here:
	}

	private void saveButtonActionPerformed(ActionEvent evt) {
		// GEN-FIRST:event_saveButtonActionPerformed
		// TODO add your handling code here:
	}

	public static void main(String args[]) {
/*
		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException ex) {
			Logger.getLogger(ListFrame.class.getName()).log(Level.SEVERE, null, ex);
		}
*/
		
		EventQueue.invokeLater(() -> {
			new ListFrame().setVisible(true);
		});
	}

}
