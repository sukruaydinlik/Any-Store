package com.any.store.gui;

import com.any.store.controller.Controller;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = -1583374613377445225L;

    private ItemFrame itemFrame;
    private JButton addItemBtn;
    private JButton addListBtn;
    private JButton deleteItemBtn;
    private JButton deleteListBtn;
    private JButton editItemBtn;
    private JButton editListBtn;
    private JPanel instrumentPanel;
    private JScrollPane listJSP;
    private JList<String> listList;
    private JLabel logoLabel;
    private JTable mainTable;
    private JPanel menuPanel;
    private JLabel mottoLabel;
    private JButton searchBtn;
    private JTextField searchField;
    private JPanel tablePanel;
    private JScrollPane tableJSP;
    private DefaultTableModel tableModel;
    private Controller cnt;
    private String[] list;
    private String selectedList;
    private AbstractListModel<String> listModel;
    private GridBagConstraints gbc;
    private ListFrame listFrame;
    private ListListener listListener;
    private ItemListener itemListener;

    public MainFrame() {
        initComponents();
    }

    private void initComponents() {

        cnt = new Controller();
        menuPanel = new JPanel();
        searchBtn = new JButton();
        searchField = new JTextField();
        addItemBtn = new JButton();
        addListBtn = new JButton();
        editItemBtn = new JButton();
        editListBtn = new JButton();
        deleteItemBtn = new JButton();
        deleteListBtn = new JButton();
        instrumentPanel = new JPanel();
        logoLabel = new JLabel();
        mottoLabel = new JLabel();
        listJSP = new JScrollPane();
        listList = new JList<>();
        tablePanel = new JPanel();
        tableJSP = new JScrollPane();
        mainTable = new JTable();
        list = cnt.getList();


        // setting frame
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Any Store");
        setBackground(new Color(1, 198, 83));
        setBounds(new Rectangle(100, 100, 0, 0));


        setListeners();

        layoutMenuPanel();
        layoutTablePanel();
        layoutInstrumentPanel();

        getContentPane().add(tablePanel, BorderLayout.CENTER);
        getContentPane().add(instrumentPanel, BorderLayout.LINE_START);
        getContentPane().add(menuPanel, BorderLayout.PAGE_START);

        pack();
    }

    public void setListeners() {
        listListener = new ListListener() {
            @Override
            public void saveAddBtnClicked(ListEvent listEvent) {
                ArrayList<String> atts = listEvent.getAtts();
                String listName = listEvent.getListName();
                cnt.addList(listName, atts);
                refresh();
            }

            @Override
            public void saveEditBtnClicked(ListEvent listEvent) {
                ArrayList<String> atts = listEvent.getAtts();
                String listName = listEvent.getListName();
                cnt.editList(listName, atts);
                refresh();
            }
        };

        itemListener = new ItemListener() {
            @Override
            public void addEventOccured(ItemEvent ie) {
                //ie.getData(); // holds the data from ItemPanel

                cnt.addItem(ie.getData(), ie.getListName());
                refresh();
            }

            @Override
            public void editEventOccured(ItemEvent ie) {
                cnt.editItem(ie.getData(), ie.getListName(), ie.getItemId());
                refresh();
            }
        };
    }

    private void layoutTablePanel() {

        tablePanel.setBackground(new Color(59, 59, 59));
        tablePanel.setForeground(new Color(60, 63, 65));
        tablePanel.setLayout(new BorderLayout());

        tableJSP.setBackground(new Color(226, 226, 226));
        tableJSP.setBorder(null);
        tableJSP.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        tableJSP.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // mainTable.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
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
        tableJSP.setViewportView(mainTable);

        tablePanel.add(tableJSP, BorderLayout.CENTER);
    }

    private void layoutInstrumentPanel() {

        listModel = new AbstractListModel<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String getElementAt(int i) {
                return list[i];
            }

            @Override
            public int getSize() {
                return list.length;
            }

        };

        // set instrument panel
        instrumentPanel.setBackground(new Color(69, 73, 74));
        instrumentPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        instrumentPanel.setForeground(new Color(69, 73, 74));
        instrumentPanel.setToolTipText("");
        instrumentPanel.setPreferredSize(new Dimension(300, 482));
        instrumentPanel.setLayout(new GridBagLayout());

        // set logo
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoLabel.setIcon(new ImageIcon(getClass().getResource("/com/any/store/icons/logo_file[313].png"))); // NOI18N
        logoLabel.setVerticalAlignment(SwingConstants.TOP);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 76;
        gbc.ipady = 68;
        gbc.weighty = 0.5;
        instrumentPanel.add(logoLabel, gbc);

        mottoLabel.setText("Store anything");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        instrumentPanel.add(mottoLabel, gbc);

        listJSP.setBackground(new Color(69, 73, 74));
        listJSP.setForeground(new Color(69, 73, 74));
        listJSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        listList.setBorder(new LineBorder(new Color(1, 198, 83), 2, true));
        listList.setFont(new Font("Segoe UI Semibold", 1, 14)); // NOI18N
        listList.setForeground(new Color(69, 73, 74));
        listList.setModel(listModel);

        listList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // listList.setFocusCycleRoot(true);
        listList.setFocusable(false);
        listList.setPreferredSize(new Dimension(135, 500));
        listList.setSelectionBackground(new Color(1, 198, 83));
        listList.setBackground(new Color(69, 73, 74));
        listList.setForeground(new Color(226, 226, 226));
        listList.setFixedCellHeight(25);
        listList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectedList = listList.getSelectedValue();
                // selectedList = "a";
                cnt.fillTable(selectedList, tableModel, mainTable);
                mainTable.updateUI();

            }
        });

        listJSP.setViewportView(listList);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        instrumentPanel.add(listJSP, gbc);

        if (listList.getModel().getSize() != 0) {
            listList.setSelectedIndex(0);

            cnt.fillTable(listList.getModel().getElementAt(0), tableModel, mainTable);
            mainTable.updateUI();
            selectedList = listList.getSelectedValue();

        }
        if (list.length != 0) {
            selectedList = listList.getSelectedValue();

        } else {
            selectedList = "";
        }

    }

    private void layoutMenuPanel() {
        // set menu panel
        menuPanel.setBackground(new Color(99, 99, 99));
        menuPanel.setForeground(new Color(99, 99, 99));
        menuPanel.setPreferredSize(new Dimension(781, 50));

        // set search Btn
        searchBtn.setIcon(new ImageIcon(getClass().getResource("/com/any/store/icons/icons8_search_26px_1.png"))); // NOI18N
        searchBtn.setToolTipText("Search in collection");
        searchBtn.setContentAreaFilled(false);
        searchBtn.setFocusPainted(false);
        searchBtn.setPreferredSize(new Dimension(30, 30));
        searchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        // set search field
        searchField.setText("search...");
        searchField.setBorder(new LineBorder(new Color(1, 198, 83), 2, true));
        searchField.setFont(new Font("Segoe UI Semibold", 1, 14)); // NOI18N
        searchField.setMinimumSize(new Dimension(64, 30));
        searchField.setPreferredSize(new Dimension(190, 30));
        searchField.setSelectionColor(new Color(1, 198, 83));
        searchField.setBackground(new Color(99, 99, 99));
        searchField.setForeground(new Color(226, 226, 226));
        searchField.setCaretColor(new Color(1, 198, 83));
        searchField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                searchFieldFocusGained(evt);
            }

            public void focusLost(FocusEvent evt) {
                searchFieldFocusLost(evt);

            }
        });
        searchField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                searchFieldKeyPressed(evt);
            }
        });

        // set add item Btn
        addItemBtn.setIcon(new ImageIcon(getClass().getResource("/com/any/store/icons/icons8_add_file_26px.png"))); // NOI18N
        addItemBtn.setToolTipText("Add item");
        addItemBtn.setContentAreaFilled(false);
        addItemBtn.setFocusPainted(false);
        addItemBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addItemBtnActionPerformed(evt);
            }
        });

        // set add list Btn
        addListBtn
                .setIcon(new ImageIcon(getClass().getResource("/com/any/store/icons/icons8_add_property_26px_1.png"))); // NOI18N
        addListBtn.setToolTipText("Add list");
        addListBtn.setContentAreaFilled(false);
        addListBtn.setFocusPainted(false);
        addListBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addListBtnActionPerformed(evt);
            }
        });

        // set edit item Btn
        editItemBtn.setIcon(new ImageIcon(getClass().getResource("/com/any/store/icons/icons8_edit_file_26px.png"))); // NOI18N
        editItemBtn.setToolTipText("Edit item");
        editItemBtn.setContentAreaFilled(false);
        editItemBtn.setFocusPainted(false);
        editItemBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                editItemBtnActionPerformed(evt);
            }
        });

        // set edit list Btn
        editListBtn
                .setIcon(new ImageIcon(getClass().getResource("/com/any/store/icons/icons8_edit_property_26px_2.png"))); // NOI18N
        editListBtn.setToolTipText("Edit list");
        editListBtn.setContentAreaFilled(false);
        editListBtn.setFocusPainted(false);
        editListBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                editListBtnActionPerformed(evt);
            }
        });

        // set delete item Btn
        deleteItemBtn
                .setIcon(new ImageIcon(getClass().getResource("/com/any/store/icons/icons8_delete_file_26px.png"))); // NOI18N
        deleteItemBtn.setToolTipText("Delete item");
        deleteItemBtn.setContentAreaFilled(false);
        deleteItemBtn.setFocusPainted(false);
        deleteItemBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                deleteItemBtnActionPerformed(evt);
            }
        });

        // set delete list Btn
        deleteListBtn.setIcon(
                new ImageIcon(getClass().getResource("/com/any/store/icons/icons8_delete_document_26px_2.png"))); // NOI18N
        deleteListBtn.setToolTipText("Delete list");
        deleteListBtn.setContentAreaFilled(false);
        deleteListBtn.setFocusPainted(false);
        deleteListBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                deleteListBtnActionPerformed(evt);
            }
        });

        // layout menu panel
        GroupLayout menuPanelLayout = new GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(menuPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(menuPanelLayout.createSequentialGroup().addContainerGap().addComponent(addItemBtn)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(addListBtn)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(editItemBtn)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(editListBtn)
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
                .addGroup(menuPanelLayout.createSequentialGroup().addContainerGap()
                        .addGroup(menuPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addGroup(menuPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(searchField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(addItemBtn).addComponent(addListBtn).addComponent(editItemBtn)
                                        .addComponent(editListBtn).addComponent(deleteItemBtn)
                                        .addComponent(deleteListBtn))
                                .addComponent(searchBtn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(14, Short.MAX_VALUE)));

    }

    private void searchBtnActionPerformed(ActionEvent evt) {
        refresh();

    }

    private void deleteListBtnActionPerformed(ActionEvent evt) {
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure to delete this list?");
        String listName = listList.getSelectedValue();
        //System.out.println(choice);
        if (!listName.equals("") && choice == 0) {
            cnt.deleteList(listName);
        }
        refresh();

    }

    private void deleteItemBtnActionPerformed(ActionEvent evt) {
        int column = 0;
        int row = mainTable.getSelectedRow();
        if (row != -1) {
            int itemId = Integer.parseInt(mainTable.getModel().getValueAt(row, column).toString());
            cnt.deleteItem(itemId);
            cnt.fillTable(listList.getSelectedValue(), tableModel, mainTable);
            cnt.refresh();
        } else {
            System.out.println("Select row please!");
        }
        refresh();
    }


    private void editListBtnActionPerformed(ActionEvent evt) {


        String listName = listList.getSelectedValue();
        if (!listName.equals("")) {
            //listFrame = new ListFrame(cnt.findAttributes(listName),listName);
            listFrame = new ListFrame(cnt.findAttributes(listName), listName);
            listFrame.setListener(listListener);
        }
        refresh();
    }

    private void editItemBtnActionPerformed(ActionEvent evt) {
        ArrayList<String> colNames = new ArrayList<>();
        ArrayList<String> item = new ArrayList<>();

        int colCount = 0;
        int column = 0;
        int row = mainTable.getSelectedRow();

        // -1 means no row selected
        if (row != -1) {

            System.out.println(item.size());
            colCount = mainTable.getModel().getColumnCount();

            for (int i = 0; i < colCount - 1; i++) {
                colNames.add(mainTable.getColumnName(i));
                if (mainTable.getValueAt(row, column) == null) {
                    item.add("");
                } else {
                    item.add(mainTable.getValueAt(row, column).toString());
                }

                column++;
            }

            //create new itemFrame and pass elements
            int itemId = Integer.parseInt(mainTable.getModel().getValueAt(row, 0).toString());
            ItemFrame itemFrame = new ItemFrame(colNames, listList.getSelectedValue(), itemId, item);
            itemFrame.setItemListener(itemListener);
            cnt.refresh();
        } else {
            System.out.println("Select row please!");
        }
        refresh();
    }

    private void addListBtnActionPerformed(ActionEvent evt) {
        String listName = listList.getSelectedValue();
        if (!listName.equals("")) {
            //listFrame = new ListFrame(cnt.findAttributes(listName),listName);
            listFrame = new ListFrame();
            listFrame.setListener(listListener);
        }
        refresh();
    }

    private void addItemBtnActionPerformed(ActionEvent evt) {
        itemFrame = new ItemFrame(cnt.findAttributes(selectedList), listList.getSelectedValue());
        itemFrame.setItemListener(itemListener);
        refresh();

    }


    private void searchFieldKeyPressed(KeyEvent evt) {
        String filter = searchField.getText();
        filterTable(filter);
        refresh();
    }

    private void searchFieldFocusGained(FocusEvent evt) {
        if(searchField.getText().equals("search...")) {

            searchField.setText("");
            refresh();
        }
    }

    private void searchFieldFocusLost(FocusEvent evt) {
        if(searchField.getText().equals("")) {
            searchField.setText("search...");
            filterTable("");
            refresh();
        }
    }

    public void filterTable(String filter){
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel>(tableModel);
        mainTable.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter("(?i)" + filter));
    }

    public void refresh() {
        cnt.fillTable(listList.getSelectedValue(), tableModel, mainTable);
        mainTable.repaint();
        mainTable.revalidate();

        list = cnt.getList();
        listList.repaint();
        listList.revalidate();
    }

}

