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
    
    static final String DB_URL = "jdbc:mysql://localhost:3306/cab"; // Replace with your DB details
    static final String USER = "root"; // Your MySQL username
    static final String PASS = ""; // Your MySQL password
    
    
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
                    us.setId( rs.getInt("id"));
                    us.setUsername(rs.getString("username"));
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
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                    Statement stmt = conn.createStatement(); 
                    ) {
                stmt.executeUpdate("INSERT INTO signin (id, username,password) "
                        + "VALUES ('"+ us.getId()+"', '"+ us.getUsername() +"','"+ us.getPassword() +"');");
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {

        }
        return false;
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
