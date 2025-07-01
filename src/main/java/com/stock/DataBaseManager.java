package com.stock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DataBaseManager {
    private final String DB_URL = "jdbc:sqlite:stock_logs.db";
    public DataBaseManager() {
        try (Connection conn = DriverManager.getConnection(DB_URL);

             Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS logs (" +
             "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
             "stock_name TEXT NOT NULL, " +
             "price REAL NOT NULL, " +
             "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP)";
stmt.execute(sql);

           



        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void insertLog( String stock_name, double price) {
        String sql = "INSERT INTO logs (stock_name, price) VALUES (?, ?)";
        try(Connection conn =  DriverManager.getConnection(DB_URL);
            PreparedStatement ppstmt = conn.prepareStatement(sql)){
            ppstmt.setString(1,stock_name);
            ppstmt.setDouble(2,price);
            ppstmt.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    
}
