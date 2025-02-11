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
    
    
    public User getUser(int id) throws SQLException {
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
    try {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
             Statement stmt = conn.createStatement()) {
            
            // Update both the username and password
            String sql = "UPDATE signin SET username = '" + us.getUsername() + "', password = '" + us.getPassword() + "' WHERE id = '" + us.getId() + "';";
            
            // Execute the update query
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}
   
   
   public boolean deleteUser(int id) {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                    Statement stmt = conn.createStatement(); 
                    ) {
                stmt.executeUpdate("DELETE FROM signin WHERE (id = '"+ id + "');");
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {

        }
        return false;
}
}
