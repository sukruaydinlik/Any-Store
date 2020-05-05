package com.any.store.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListFrame extends JFrame {

    private static final long serialVersionUID = 3520834608783343621L;

    private ArrayList<String> attributes;
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
    private DefaultListModel listModel;
    private ListListener listener;
    private boolean isEdit; // true for edit, false for add


    //////////////////////////////////////// main method to test ///////////////////////////////////////////////////////
    public static void main(String args[]) {
        ArrayList<String> mylist = new ArrayList<String>();
        for (int i = 0; i < 2; i++) {
            mylist.add("Attribute ");
        }
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Motif".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ListFrame.class.getName()).log(Level.SEVERE, null, ex);
        }


        EventQueue.invokeLater(() -> {
            new ListFrame(mylist, "Test List").setVisible(true);
        });
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    // constructor for creating new list
    public ListFrame() {
        setEdit(false);
        initComponents(new ArrayList<>(), "");
        setVisible(true);
    }

    // constructor for editing an existing list
    public ListFrame(ArrayList<String> atts, String listName) {
        setEdit(true);
        initComponents(atts, listName);
        setVisible(true);

    }

    private void initComponents(ArrayList<String> atts, String listName) {


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
        attributes = new ArrayList<String>();
        listModel = new DefaultListModel();

        if (isEdit()) { // check that it is for editing or creating
            for (int i = 0; i < atts.size(); i++) {
                listModel.addElement(atts.get(i));
            }
            System.out.println("added attributes to the list model");
        } else {
            System.out.println("in else");
        }


        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("List");
        setBackground(new Color(69, 73, 74));
        setBounds(new Rectangle(200, 100, 640, 480));
        setPreferredSize(new Dimension(800, 600));

        mainPane.setBackground(new Color(69, 73, 74));
        mainPane.setForeground(new Color(69, 73, 74));
        //System.out.println(atts.size());

        listList.setBorder(new LineBorder(new Color(187, 187, 187), 2, true));
        listList.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        listList.setModel(listModel);
        listList.setSelectionBackground(new Color(1, 198, 83));
        listList.setBackground(new Color(69, 73, 74));
        listList.setForeground(new Color(187, 187, 187));
        listList.setSelectedIndex(0);
        if (listModel.size() == 0) {
            deletePropButton.setEnabled(false);
        }

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
        if (isEdit) {
            nameField.setText(listName);
            nameField.setFocusable(false);
        } else {
            nameField.setText("");
        }
        nameField.setBorder(new LineBorder(new Color(187, 187, 187), 2, true));
        nameField.setSelectionColor(new Color(1, 198, 83));
        nameField.setBackground(new Color(69, 73, 74));
        nameField.setForeground(new Color(187, 187, 187));
        nameField.setColumns(15);

        propertyLabel.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        propertyLabel.setText("Property: ");
        propertyLabel.setForeground(new Color(187, 187, 187));

        propertyField.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        propertyField.setText("");
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

    private void listListValueChanged(ListSelectionEvent evt) {
    }

    private void addPropButtonActionPerformed(ActionEvent evt) {
        String property = propertyField.getText();

        if (property.equals("") || listModel.contains(property)) {
            System.out.println("Item is already in the list or empty");
        } else {
            listModel.addElement(propertyField.getText());
            deletePropButton.setEnabled(true);
        }
        listList.setSelectedIndex(0);
    }

    private void deletePropButtonActionPerformed(ActionEvent evt) {
        int index = listList.getSelectedIndex();
        listModel.remove(index);

        if (listModel.size() == 0) { //Nobody's left, disable deleteBtn.
            deletePropButton.setEnabled(false);

        }
        listList.setSelectedIndex(0);
    }


    private void saveButtonActionPerformed(ActionEvent evt) {
        attributes.clear();
        // get list name
        String listName = nameField.getText();

        if (!listName.equals("")) {
            // add attributes to 'attributes'
            for (int i = 0; i < listModel.size(); i++) {
                attributes.add(listModel.get(i).toString());
            }
            //  call lisEvent
            ListEvent le = new ListEvent(this, attributes, listName);
            if (listener != null) {
                if (isEdit) {
                    listener.saveEditBtnClicked(le);
                } else {
                    listener.saveAddBtnClicked(le);
                }
            }
        }
        this.dispose();
    }


    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }

    public ListListener getListener() {
        return listener;
    }

    public void setListener(ListListener listener) {
        this.listener = listener;
    }

}
