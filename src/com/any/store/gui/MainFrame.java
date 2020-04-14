/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.util.Vector;

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
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = -1583374613377445225L;
	
    private JButton addItemButton;
    private JButton addListButton;
    private JButton deleteItemButton;
    private JButton deleteListButton;
    private JButton editItemButton;
    private JButton editListButton;
    private JPanel instrumentPanel;
    private JScrollPane jScrollPane2;
    private JList<String> listList;
    private JLabel logoLabel;
    private JTable mainTable;
    private JPanel menuPanel;
    private JLabel mottoLabel;
    private JButton searchButton;
    private JTextField searchField;
    private JPanel tablePanel;
    private JScrollPane tableScrollPane;
    private DefaultTableModel tableModel;

	public MainFrame() {
        initComponents();
    }
    
    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        menuPanel = new JPanel();
        searchButton = new JButton();
        searchField = new JTextField();
        addItemButton = new JButton();
        addListButton = new JButton();
        editItemButton = new JButton();
        editListButton = new JButton();
        deleteItemButton = new JButton();
        deleteListButton = new JButton();
        instrumentPanel = new JPanel();
        logoLabel = new JLabel();
        mottoLabel = new JLabel();
        jScrollPane2 = new JScrollPane();
        listList = new JList<>();
        tablePanel = new JPanel();
        tableScrollPane = new JScrollPane();
        mainTable = new JTable();
        
        
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Any Store - Store anything");
        setBackground(new Color(1, 198, 83));
        setBounds(new Rectangle(100, 100, 0, 0));

        menuPanel.setBackground(new Color(99, 99, 99));
        menuPanel.setForeground(new Color(99, 99, 99));
        menuPanel.setPreferredSize(new Dimension(781, 50));

        searchButton.setIcon(new ImageIcon(getClass().getResource("/com/any/store/icons/icons8_search_26px_1.png"))); // NOI18N
        searchButton.setToolTipText("Search in collection");
        searchButton.setContentAreaFilled(false);
        searchButton.setFocusPainted(false);
        searchButton.setPreferredSize(new Dimension(30, 30));
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

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

        addItemButton.setIcon(new ImageIcon(getClass().getResource("/com/any/store/icons/icons8_add_file_26px.png"))); // NOI18N
        addItemButton.setToolTipText("Add item");
        addItemButton.setContentAreaFilled(false);
        addItemButton.setFocusPainted(false);
//        addItemButton.setBorder(null);
        addItemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addItemButtonActionPerformed(evt);
            }
        });

        addListButton.setIcon(new ImageIcon(getClass().getResource("/com/any/store/icons/icons8_add_property_26px_1.png"))); // NOI18N
        addListButton.setToolTipText("Add list");
        addListButton.setContentAreaFilled(false);
        addListButton.setFocusPainted(false);
//        addListButton.setBorder(null);
        addListButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addListButtonActionPerformed(evt);
            }
        });

        editItemButton.setIcon(new ImageIcon(getClass().getResource("/com/any/store/icons/icons8_edit_file_26px.png"))); // NOI18N
        editItemButton.setToolTipText("Edit item");
        editItemButton.setContentAreaFilled(false);
        editItemButton.setFocusPainted(false);
//        editItemButton.setBorder(null);
        editItemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                editItemButtonActionPerformed(evt);
            }
        });

        editListButton.setIcon(new ImageIcon(getClass().getResource("/com/any/store/icons/icons8_edit_property_26px_2.png"))); // NOI18N
        editListButton.setToolTipText("Edit list");
        editListButton.setContentAreaFilled(false);
        editListButton.setFocusPainted(false);
//        editListButton.setBorder(null);
        editListButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                editListButtonActionPerformed(evt);
            }
        });

        deleteItemButton.setIcon(new ImageIcon(getClass().getResource("/com/any/store/icons/icons8_delete_file_26px.png"))); // NOI18N
        deleteItemButton.setToolTipText("Delete item");
        deleteItemButton.setContentAreaFilled(false);
        deleteItemButton.setFocusPainted(false);
//        deleteItemButton.setBorder(null);
        deleteItemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                deleteItemButtonActionPerformed(evt);
            }
        });

        deleteListButton.setIcon(new ImageIcon(getClass().getResource("/com/any/store/icons/icons8_delete_document_26px_2.png"))); // NOI18N
        deleteListButton.setToolTipText("Delete list");
        deleteListButton.setContentAreaFilled(false);
        deleteListButton.setFocusPainted(false);
//        deleteListButton.setBorder(null);
        deleteListButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                deleteListButtonActionPerformed(evt);
            }
        });

        GroupLayout menuPanelLayout = new GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addItemButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addListButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editItemButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editListButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteItemButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteListButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(searchField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(menuPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(searchField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(addItemButton)
                        .addComponent(addListButton)
                        .addComponent(editItemButton)
                        .addComponent(editListButton)
                        .addComponent(deleteItemButton)
                        .addComponent(deleteListButton))
                    .addComponent(searchButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

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
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "asdfa", "asf", "qwq", "fgdgvsvf", "qwerde", "ergfsd", "gfrevfcd", "wesfwsefd", "asdf", "sadf", "as", "df", "asdf", "asdf", "d", "asf", " ", " " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
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

        mainTable.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        mainTable.setForeground(new Color(51, 51, 51));
        String[] columnNames = new String[5];
		tableModel = new DefaultTableModel(columnNames, 100);
		mainTable.setModel(tableModel);

        mainTable.setGridColor(new Color(255, 255, 255));
        mainTable.setInheritsPopupMenu(true);
        mainTable.setRowHeight(22);
        mainTable.setSelectionBackground(new Color(1, 198, 83));
        mainTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mainTable.setShowHorizontalLines(true);
        mainTable.getTableHeader().setReorderingAllowed(false);
        tableScrollPane.setViewportView(mainTable);

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

    }

    private void searchFieldKeyPressed(KeyEvent evt) {

    }

    private void searchFieldFocusGained(FocusEvent evt) {

    }

    private void searchFieldFocusLost(FocusEvent evt) {

    }

    

}
