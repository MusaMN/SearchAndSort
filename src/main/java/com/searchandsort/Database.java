
package com.searchandsort;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    
    private Connection conn = null;
    
    public String connect() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/customer", "root", "");
            System.out.println("Database Connected");
            return "connected";
        } catch (SQLException e) {
            System.out.println("Database connection failed.");
            e.printStackTrace();
            return null;
        }       
    }
    
    //public void insertData(int id, String name, String surname, String address) throws SQLException {
    public void insertData(String[] data) throws SQLException {       
        String qry = "INSERT INTO detail VALUES(?,?,?,?)";        
        PreparedStatement stmt = conn.prepareStatement(qry);        
        
        stmt.setString(1, data[0]);
        stmt.setString(2, data[1]);
        stmt.setString(3, data[2]);
        stmt.setString(4, data[3]);
        stmt.addBatch();
        stmt.executeBatch();
        //return stmt.executeBatch();        
    }
    
    public String[][] getData(String order) throws SQLException {
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        
        ResultSet rs = stmt.executeQuery("SELECT * FROM detail;");
        
        if("DESC".equals(order)){
            rs = stmt.executeQuery("SELECT * FROM detail ORDER BY name DESC;");
        }
        else if("ASC".equals(order)) {
            rs = stmt.executeQuery("SELECT * FROM detail ORDER BY name ASC;");
        }        
            
        rs.last();
        int rowCount = rs.getRow();
        rs.first();
        String[][] data = new String[rowCount][4];         
        
        for(int x = 0; x<rowCount; rs.next()) {
            data[x][0] = rs.getString(1);
            data[x][1] = rs.getString(2);
            data[x][2] = rs.getString(3);
            data[x][3] = rs.getString(4);           
            x++;
        } 
        return data;
    }
    
    public String[][] getData(String searchBy, String value) throws SQLException {        
        
        String qry = "SELECT * FROM detail WHERE " + searchBy + " = '" + value + "';";       
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);        
        ResultSet rs = stmt.executeQuery(qry);
        
        rs.last();
        int rowCount = rs.getRow();
        rs.first();
        String[][] data = new String[rowCount][4];         
        
        for(int x = 0; x<rowCount; rs.next()) {
            data[x][0] = rs.getString(1);
            data[x][1] = rs.getString(2);
            data[x][2] = rs.getString(3);
            data[x][3] = rs.getString(4);           
            x++;
        } 
        return data;
    }

    String[][] getData() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

