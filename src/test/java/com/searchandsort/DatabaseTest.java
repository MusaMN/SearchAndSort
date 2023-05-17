package com.searchandsort;

import java.sql.SQLException;

public class DatabaseTest {
    
    public static void main(String[] args) {
        DatabaseTest test = new DatabaseTest();
        
        // Run test cases
        test.testConnect();
        test.testInsertData();
        test.testGetData();
        test.testGetDataWithOrder();
        test.testGetDataWithSearch();
    }
    
    public void testConnect() {
        Database database = new Database();
        
        try {
            database.connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public void testInsertData() {
        Database database = new Database();
        
        try {
            database.connect();
            String[] data = { "1", "Sgegede", "Mabena", "245 eStradini, eNdaweni" };
            database.insertData(data);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void testGetData() {
        Database database = new Database();
        
        try {
            database.connect();
            String[][] data = database.getData();
            
            //Print retrieved data
            for (String[] row : data) {
                for (String value : row) {
                    System.out.print(value + " ");
                }
                System.out.println();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public void testGetDataWithOrder() {
        Database database = new Database();
        
        try {
            database.connect();
            String[][] data = database.getData("DESC");
            
            //Print retrieved data
            for (String[] row : data) {
                for (String value : row) {
                    System.out.print(value + " ");
                }
                System.out.println();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void testGetDataWithSearch() {
        Database database = new Database();
        
        try {
            database.connect();
            String[][] data = database.getData("name", "Mthizo");
            
            //Print retrieved data
            for (String[] row : data) {
                for (String value : row) {
                    System.out.print(value + " ");
                }
                System.out.println();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
