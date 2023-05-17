
package com.searchandsort;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JRadioButton;


public class SearchAndSort {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        new Main().LoadMainForm();
        
        Database db = new Database();
        db.connect();        
    }
}
