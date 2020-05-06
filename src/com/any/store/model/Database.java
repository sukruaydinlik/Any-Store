package com.any.store.model;

import java.sql.*;
import java.util.ArrayList;

public class Database {

    public ArrayList<Item> items;
    public ArrayList<List> lists;
    public ArrayList<Attribute> attributes;

    private String url = "jdbc:sqlite:db.db";
    private Connection con = null;
//	private PreparedStatement prepStat = null;
//	private ResultSet rs = null;

    public boolean isConnected;

    public Database() {
        try {
            con = DriverManager.getConnection(url);
            this.isConnected = true;
        } catch (SQLException e) {
            this.isConnected = false;
            e.printStackTrace();
        }
//        if (isConnected) {
//            System.out.println("Connection successfull!");
//        } else {
//
//            System.out.println("Connection error");
//        }

        initialize();

    }

    private void initialize() {
        items = new ArrayList<Item>();
        lists = new ArrayList<List>();
        attributes = new ArrayList<Attribute>();
    }

    public void disconnect() {
        try {
            con.close();
            this.isConnected = false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Item> readItems() {
        items.clear();

        int id;
        int attId;
        String value;
        int itemId;

        try {
            PreparedStatement prepStat = con.prepareStatement("select * from item order by id asc");

            ResultSet rs = prepStat.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id");
                attId = rs.getInt("att_id");
                value = rs.getString("value");
                itemId = rs.getInt("item_id");
                Item item = new Item(id, attId, value, itemId);
                items.add(item);
            }
            prepStat.close();
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return items;
        }
    }

    public ArrayList<List> readLists() {
        lists.clear();

        int id;
        String name;

        try {
            PreparedStatement prepStat = con.prepareStatement("select * from list order by name asc");
            ResultSet rs = prepStat.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id");
                name = rs.getString("name");

                List list = new List(id, name);
                lists.add(list);
            }
            prepStat.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return lists;
        }
    }

    public ArrayList<Attribute> readAttributes() {
        attributes.clear();

        int id;
        int listId;
        String name;

        try {
            PreparedStatement prepStat = con.prepareStatement("select * from attribute order by id asc");
            ResultSet rs = prepStat.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id");
                listId = rs.getInt("list_id");
                name = rs.getString("name");

                Attribute att = new Attribute(id, listId, name);
                attributes.add(att);
            }
            prepStat.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return attributes;
        }
    }

    public void writeItem(int attId, String value, int itemId) {
        try {
            PreparedStatement prepStat = con.prepareStatement("insert into item(att_id,value,item_id) values(?,?,?)");
            prepStat.setInt(1, attId);
            prepStat.setString(2, value);
            prepStat.setInt(3, itemId);
            prepStat.executeUpdate();
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void writeList(String name) {
        try {
            PreparedStatement prepStat = con.prepareStatement("insert into list(name) values(?)");
            prepStat.setString(1, name);
            prepStat.executeUpdate();
            prepStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void writeAttribute(String listName, String name) {

        try {
            int listId = searchList(listName);
            if (listId != 0) {
                // write attribute
                PreparedStatement prepStat = con.prepareStatement("insert into attribute(list_id,name) values(?,?)");
                prepStat.setInt(1, listId);
                prepStat.setString(2, name);
                prepStat.executeUpdate();
                prepStat.close();
                PreparedStatement prepStat2 = con.prepareStatement("insert into attribute(list_id,name) values(?,?)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int searchList(String listName) {
        lists.clear();
        int id = 0;

        try {
            PreparedStatement prepStat = con.prepareStatement("select id from list where name = ? order by id asc");
            prepStat.setString(1, listName);
            ResultSet rs = prepStat.executeQuery();
            if (!rs.next()) {
            } else {
                do {
                    id = rs.getInt("id");
                } while (rs.next());

            }
            prepStat.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public ArrayList<Item> searchItem(int sattId) {
        items.clear();

        int id;
        int attId;
        String value;
        int itemId;

        try {
            PreparedStatement prepStat = con
                    .prepareStatement("select * from item where att_id =? group by item_id order by item_id asc");
            prepStat.setInt(1, sattId);

            ResultSet rs = prepStat.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id");
                attId = rs.getInt("att_id");
                value = rs.getString("value");
                itemId = rs.getInt("item_id");
                Item item = new Item(id, attId, value, itemId);
                items.add(item);
            }
            prepStat.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public ArrayList<Attribute> searchAttribute(String listName) {
        attributes.clear();

        int id;
        int listId;
        String name;
        try {
            int idList = searchList(listName);
            PreparedStatement prepStat = con.prepareStatement("select * from attribute where list_id = ? order by id asc");
            prepStat.setInt(1, idList);
            ResultSet rs = prepStat.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id");
                listId = rs.getInt("list_id");
                name = rs.getString("name");

                Attribute att = new Attribute(id, listId, name);
                attributes.add(att);
            }
            prepStat.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attributes;
    }

    public ArrayList<Item> searchRow(int sItemId) {
        items.clear();

        int id;
        int attId;
        String value;
        int itemId;

        try {
            PreparedStatement prepStat = con.prepareStatement("select * from item where item_id =?");
            prepStat.setInt(1, sItemId);
            ResultSet rs = prepStat.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id");
                attId = rs.getInt("att_id");
                value = rs.getString("value");
                itemId = rs.getInt("item_id");
                Item item = new Item(id, attId, value, itemId);
                items.add(item);
            }
            prepStat.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;

    }

    public ArrayList<Integer> searchItemIds(int attId) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        int itemId;

        try {
            PreparedStatement prepStat = con
                    .prepareStatement("select item_id from item where att_id = ? group by item_id order by item_id asc");
            prepStat.setInt(1, attId);
            ResultSet rs = prepStat.executeQuery();

            while (rs.next()) {
                itemId = rs.getInt("item_id");
                result.add(itemId);
            }
            prepStat.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    public int readItemId() {
        int itemId = 0;
        try {

            PreparedStatement prepStat = con.prepareStatement("select max(item_id) from item;");
            ResultSet rs = prepStat.executeQuery();

            if (!rs.next()) {
                return 0;
            } else {
                do {
                    itemId = rs.getInt("max(item_id)");
                } while (rs.next());

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemId;
    }

    public void removeAttribute(String name, String listName) {
        try {
            int listId = searchList(listName);
            if (listId != 0) {

                for (int i = 0; i < searchAttribute(listName).size(); i++) {
                    if (searchAttribute(listName).get(i).getName().equals(name)){

                        cleanItem(searchAttribute(listName).get(i).getId());
                    }
                }
                PreparedStatement prepStat = con.prepareStatement("delete from attribute where list_id = ? and name = ? ");
                prepStat.setInt(1, listId);
                prepStat.setString(2, name);
                prepStat.executeUpdate();



            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void cleanItem(int id) {
        try {
            PreparedStatement prepStat = con.prepareStatement("delete from item where att_id = ?;");
            prepStat.setInt(1, id);
            prepStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeItem(int itemId) {
        try {
            PreparedStatement prepStat = con.prepareStatement("delete from item where item_id = ?;");
            prepStat.setInt(1, itemId);
            prepStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeList(String listName) {
        try {
            attributes.clear();
            int id = searchList(listName);
            attributes = searchAttribute(listName);
            for (int i = 0; i < attributes.size(); i++) {
                removeAttribute(attributes.get(i).getName(),listName);
            }
            PreparedStatement prepStat = con.prepareStatement("delete from list where name = ?");
            prepStat.setString(1, listName);
            prepStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateItem(int itemId, ArrayList<Item> tempItem) {
        int attId = 0;
        String value = "";
        PreparedStatement prepStat;
        int rs;
        try {
            for (int i = 0; i < tempItem.size(); i++) {
                attId = tempItem.get(i).getAttId();
                value = tempItem.get(i).getValue();
                prepStat = con.prepareStatement("update item SET value = ? where item_id = ? and att_id = ?");
                prepStat.setString(1, value);
                prepStat.setInt(2, itemId);
                prepStat.setInt(3, attId);
                rs = prepStat.executeUpdate();

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
