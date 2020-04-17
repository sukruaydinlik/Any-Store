package com.any.store.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.any.store.controller.Controller;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = -1583374613377445225L;
	
	private ItemFrame itemFrame;
	private JButton addItemBtn;
	private JButton addListBtn;
	private JButton deleteItemBtn;
	private JButton deleteListBtn;
	private JButton editItemBtn;
	private JButton editListButton;
	private JPanel instrumentPanel;
	private JScrollPane jScrollPane2;
	private JList<String> listList;
	private JLabel logoLabel;
	private JTable mainTable;
	private JPanel menuPanel;
	private JLabel mottoLabel;
	private JButton searchBtn;
	private JTextField searchField;
	private JPanel tablePanel;
	private JScrollPane tableScrollPane;
	private DefaultTableModel tableModel;
	private Controller cnt;
	private String[] list;
	private String selectedList;
	
	public MainFrame() {
		initComponents();
	}

	private void initComponents() {
		GridBagConstraints gridBagConstraints;

		cnt = new Controller();
		menuPanel = new JPanel();
		searchBtn = new JButton();
		searchField = new JTextField();
		addItemBtn = new JButton();
		addListBtn = new JButton();
		editItemBtn = new JButton();
		editListButton = new JButton();
		deleteItemBtn = new JButton();
		deleteListBtn = new JButton();
		instrumentPanel = new JPanel();
		logoLabel = new JLabel();
		mottoLabel = new JLabel();
		jScrollPane2 = new JScrollPane();
		listList = new JList<>();
		tablePanel = new JPanel();
		tableScrollPane = new JScrollPane();
		mainTable = new JTable();
		list = cnt.getList();
		selectedList = "";
		
		// setting frame
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Any Store - Store anything");
		setBackground(new Color(1, 198, 83));
		setBounds(new Rectangle(100, 100, 0, 0));

		
		// set menu panel
		menuPanel.setBackground(new Color(99, 99, 99));
		menuPanel.setForeground(new Color(99, 99, 99));
		menuPanel.setPreferredSize(new Dimension(781, 50));

		// set search button
		searchBtn.setIcon(new ImageIcon(getClass().getResource("/com/any/store/icons/icons8_search_26px_1.png"))); // NOI18N
		searchBtn.setToolTipText("Search in collection");
		searchBtn.setContentAreaFilled(false);
		searchBtn.setFocusPainted(false);
		searchBtn.setPreferredSize(new Dimension(30, 30));
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				searchButtonActionPerformed(evt);
			}
		});

		// set search field
		searchField.setText("jTextField1");
		searchField.setBorder(new LineBorder(new Color(1, 198, 83), 2, true));
		searchField.setFont(new Font("Segoe UI Semibold", 1, 14)); // NOI18N
		searchField.setMinimumSize(new Dimension(64, 30));
		searchField.setPreferredSize(new Dimension(190, 30));
		searchField.setSelectionColor(new Color(1, 198, 83));
		searchField.setBackground(new Color(99, 99, 99));
		searchField.setForeground(new Color(226, 226, 226));
		searchField.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent evt) {
				searchFieldFocusGained(evt);
			}

			public void focusLost(FocusEvent evt) {
				searchFieldFocusLost(evt);
			}
		});
		searchField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent evt) {
				searchFieldKeyPressed(evt);
			}
		});
		
		// set add item button
		addItemBtn.setIcon(new ImageIcon(getClass().getResource("/com/any/store/icons/icons8_add_file_26px.png"))); // NOI18N
		addItemBtn.setToolTipText("Add item");
		addItemBtn.setContentAreaFilled(false);
		addItemBtn.setFocusPainted(false);
		//addItemButton.setBorder(null);
		addItemBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				addItemButtonActionPerformed(evt);
			}
		});
		
		// set add list button
		addListBtn
				.setIcon(new ImageIcon(getClass().getResource("/com/any/store/icons/icons8_add_property_26px_1.png"))); // NOI18N
		addListBtn.setToolTipText("Add list");
		addListBtn.setContentAreaFilled(false);
		addListBtn.setFocusPainted(false);
		//addListButton.setBorder(null);
		addListBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				addListButtonActionPerformed(evt);
			}
		});
		
		// set edit item button
		editItemBtn.setIcon(new ImageIcon(getClass().getResource("/com/any/store/icons/icons8_edit_file_26px.png"))); // NOI18N
		editItemBtn.setToolTipText("Edit item");
		editItemBtn.setContentAreaFilled(false);
		editItemBtn.setFocusPainted(false);
		//editItemButton.setBorder(null);
		editItemBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				editItemButtonActionPerformed(evt);
			}
		});

		// set edit list button
		editListButton
				.setIcon(new ImageIcon(getClass().getResource("/com/any/store/icons/icons8_edit_property_26px_2.png"))); // NOI18N
		editListButton.setToolTipText("Edit list");
		editListButton.setContentAreaFilled(false);
		editListButton.setFocusPainted(false);
		//editListButton.setBorder(null);
		editListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				editListButtonActionPerformed(evt);
			}
		});

		// set delete item button
		deleteItemBtn
				.setIcon(new ImageIcon(getClass().getResource("/com/any/store/icons/icons8_delete_file_26px.png"))); // NOI18N
		deleteItemBtn.setToolTipText("Delete item");
		deleteItemBtn.setContentAreaFilled(false);
		deleteItemBtn.setFocusPainted(false);
		//deleteItemButton.setBorder(null);
		deleteItemBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				deleteItemButtonActionPerformed(evt);
			}
		});

		// set delete list button
		deleteListBtn.setIcon(
				new ImageIcon(getClass().getResource("/com/any/store/icons/icons8_delete_document_26px_2.png"))); // NOI18N
		deleteListBtn.setToolTipText("Delete list");
		deleteListBtn.setContentAreaFilled(false);
		deleteListBtn.setFocusPainted(false);
		//deleteListButton.setBorder(null);
		deleteListBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				deleteListButtonActionPerformed(evt);
			}
		});
		
		
		// layout menu panel
		GroupLayout menuPanelLayout = new GroupLayout(menuPanel);
		menuPanel.setLayout(menuPanelLayout);
		menuPanelLayout.setHorizontalGroup(menuPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(menuPanelLayout.createSequentialGroup().addContainerGap().addComponent(addItemBtn)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(addListBtn)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(editItemBtn)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(editListButton)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(deleteItemBtn)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(deleteListBtn)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(searchField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(searchBtn,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		menuPanelLayout.setVerticalGroup(menuPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(menuPanelLayout.createSequentialGroup().addContainerGap().addGroup(menuPanelLayout
						.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addGroup(menuPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(searchField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(addItemBtn).addComponent(addListBtn).addComponent(editItemBtn)
								.addComponent(editListButton).addComponent(deleteItemBtn)
								.addComponent(deleteListBtn))
						.addComponent(searchBtn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
						.addContainerGap(14, Short.MAX_VALUE)));

		getContentPane().add(menuPanel, BorderLayout.PAGE_START);

		instrumentPanel.setBackground(new Color(69, 73, 74));
		instrumentPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
		instrumentPanel.setForeground(new Color(69, 73, 74));
		instrumentPanel.setToolTipText("");
		instrumentPanel.setPreferredSize(new Dimension(300, 482));
		instrumentPanel.setLayout(new GridBagLayout());

		logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		logoLabel.setIcon(new ImageIcon(getClass().getResource("/com/any/store/icons/logo_file[313].png"))); // NOI18N
		logoLabel.setVerticalAlignment(SwingConstants.TOP);
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.ipadx = 76;
		gridBagConstraints.ipady = 68;
		gridBagConstraints.weighty = 0.5;
		instrumentPanel.add(logoLabel, gridBagConstraints);

		mottoLabel.setText("Store anything");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;
		instrumentPanel.add(mottoLabel, gridBagConstraints);

		jScrollPane2.setBackground(new Color(69, 73, 74));
		jScrollPane2.setForeground(new Color(69, 73, 74));

		
		listList.setBorder(new LineBorder(new Color(1, 198, 83), 2, true));
		listList.setFont(new Font("Segoe UI Semibold", 1, 14)); // NOI18N
		listList.setForeground(new Color(69, 73, 74));
		listList.setModel(new AbstractListModel<String>() {
			String[] strings = list;

			public int getSize() {
				return strings.length;
			}

			public String getElementAt(int i) {
				return strings[i];
			}
		});
		listList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listList.setToolTipText("");
		listList.setFocusCycleRoot(true);
		listList.setFocusable(false);
		listList.setPreferredSize(new Dimension(135, 500));
		listList.setSelectionBackground(new Color(1, 198, 83));
		listList.setBackground(new Color(69, 73, 74));
		listList.setForeground(new Color(226, 226, 226));
		listList.setFixedCellHeight(25);
		listList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					cnt.fillTable(listList.getSelectedValue(), tableModel, mainTable);
					mainTable.updateUI();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		jScrollPane2.setViewportView(listList);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		instrumentPanel.add(jScrollPane2, gridBagConstraints);

		getContentPane().add(instrumentPanel, BorderLayout.LINE_START);

		tablePanel.setBackground(new Color(59, 59, 59));
		tablePanel.setForeground(new Color(60, 63, 65));
		tablePanel.setLayout(new BorderLayout());

		tableScrollPane.setBackground(new Color(226, 226, 226));
		tableScrollPane.setBorder(null);
		tableScrollPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		tableScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED );
//		mainTable.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		mainTable.setForeground(new Color(51, 51, 51));
		tableModel = new DefaultTableModel() {
			private static final long serialVersionUID = 3876987434893583461L;
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		mainTable.setModel(tableModel);

		mainTable.setGridColor(new Color(255, 255, 255));
		mainTable.setInheritsPopupMenu(true);
		mainTable.setRowHeight(22);
		mainTable.setSelectionBackground(new Color(1, 198, 83));
		mainTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mainTable.setShowHorizontalLines(true);
		mainTable.getTableHeader().setReorderingAllowed(false);
		tableScrollPane.setViewportView(mainTable);

		if (!listList.getModel().getElementAt(0).isEmpty()) {
			try {
				cnt.fillTable(listList.getModel().getElementAt(0), tableModel,mainTable);
				mainTable.updateUI();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		tablePanel.add(tableScrollPane, BorderLayout.CENTER);

		getContentPane().add(tablePanel, BorderLayout.CENTER);

		pack();
	}

	private void searchButtonActionPerformed(ActionEvent evt) {

	}

	private void deleteListButtonActionPerformed(ActionEvent evt) {

	}

	private void deleteItemButtonActionPerformed(ActionEvent evt) {

	}

	private void editListButtonActionPerformed(ActionEvent evt) {

	}

	private void editItemButtonActionPerformed(ActionEvent evt) {

	}

	private void addListButtonActionPerformed(ActionEvent evt) {

	}

	private void addItemButtonActionPerformed(ActionEvent evt) {
		System.out.println("AddItemButton clicked");
		listList.getSelectedValue();
		setItemFrame();
	}

	private void setItemFrame() {
		itemFrame = new ItemFrame(cnt.getAttributes(), listList.getSelectedValue());
		itemFrame.setItemListener(new ItemListener() {
			
			@Override
			public void eventOccured(ItemEvent ie) {
				ie.getData(); // holds the data from ItemPanel
				System.out.println("Save Button Clicked");
				cnt.addItem(ie.getData());
			}
		});
	}

	private void searchFieldKeyPressed(KeyEvent evt) {

	}

	private void searchFieldFocusGained(FocusEvent evt) {

	}

	private void searchFieldFocusLost(FocusEvent evt) {

	}

}
