/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author iband
 */
public class ADBUtils {
    
    static final String DB_URL = "jdbc:mysql://localhost:3306/Cab?autoReconnect=true&useSSL=false"; // Replace with your DB details
    static final String USER = "root"; // Your MySQL username
    static final String PASS = "ID24@isuri"; // Your MySQL password
    
    
    public User getUseri(int id) throws SQLException {
        User us = null;
         try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                    Statement stmt = conn.createStatement(); 
                    ResultSet rs = stmt.executeQuery("SELECT * FROM signin WHERE id="+ id);) {
                while (rs.next()) {
                    us = new User();
                    us.setId( rs.getInt("id"));
                    us.setUsername(rs.getString("username"));
                    us.setPassword(rs.getString("password"));

                    break;
                }
            } catch (SQLException e) {
                System.err.print(e);
                throw e;
            }

        } catch (SQLException e) {
            System.err.print(e);
            throw e;
        }

        return us;
    }
    
    public List<User> getUser() {
        List<User> user = new ArrayList<>();
         try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                    Statement stmt = conn.createStatement(); 
                    ResultSet rs = stmt.executeQuery("SELECT * FROM signin");) {
                while (rs.next()) {
                    User us = new User();
                    us.setId( rs.getInt("sign_id"));
                    us.setUsername(rs.getString("username"));
                     us.setPassword(rs.getString("password"));

                    user.add(us);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {

        }

        return user;
    }
     
      public boolean addUser(User us) {
       
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            // Insert query without the auto-incremented 'id' field
            String query = "INSERT INTO signin (username, password) VALUES (?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, us.getUsername());
            stmt.setString(2, us.getPassword());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;  // Returns true if the insert was successful
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Ensure resources are closed
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false; // Return false in case of error
    }

    
  public boolean updateUser(User us) {
    String sql = "UPDATE signin SET username = ?, password = ? WHERE sign_id = ?";

    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, us.getUsername());
        pstmt.setString(2, us.getPassword());
        pstmt.setInt(3, us.getId());

        int rowsUpdated = pstmt.executeUpdate();
        return rowsUpdated > 0;

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}




   
   
  public boolean deleteUser(int id) {
    try {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver()); // Using the latest MySQL driver

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM signin WHERE sign_id = ?")) { // Use prepared statement to prevent SQL injection
            stmt.setInt(1, id);  // Setting the id parameter in the query
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;  // Return true if at least one row is deleted
        } catch (SQLException e) {
            e.printStackTrace(); // Log any SQL exception for debugging
        }

    } catch (Exception e) {
        e.printStackTrace(); // Log any general exception for debugging
    }
    return false;  // Return false if deletion fails
}

}
