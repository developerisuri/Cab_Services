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
public class DBUtils {
    
   
   static final String DB_URL = "jdbc:mysql://localhost:3306/Cab?autoReconnect=true&useSSL=false"; // Replace with your DB details
    static final String USER = "root"; // Your MySQL username
    static final String PASS = "ID24@isuri"; // Your MySQL password
    
    
    
    
    
    
    
    // Add User method corresponding to the 'signIn' table
    public boolean addUser(User us) {
        //try {
           // DriverManager.registerDriver(new com.mysql.jdbc.Driver());

           // try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                 //   Statement stmt = conn.createStatement(); 
                   // ) {
               // stmt.executeUpdate("INSERT INTO signin (username, password) "
                    //    + "VALUES ('"+ us.getUsername()+"', '"+ us.getPassword() +"');");
              //  return true;
          //  } catch (SQLException e) {
              //  e.printStackTrace();
          //  }

       // } catch (Exception e) {

       // }
       // return false;
       
        // Add User method corresponding to the 'signIn' table
    
        
    
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

 public boolean validateUserLogin(User user) {
    try {
        // Register MySQL driver
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            // Query to check if the username and password match
            String query = "SELECT * FROM signin WHERE username = ? AND password = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, user.getUsername());
                stmt.setString(2, user.getPassword()); // Remember: Plain text comparison here
                
                ResultSet rs = stmt.executeQuery();
                
                if (rs.next()) {
                    return true; // Valid login
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false; // Invalid login if no match found
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



// Query to check if the sign_id exists in the customer table
    private static final String CUSTOMER_CHECK_QUERY = "SELECT COUNT(*) FROM customer WHERE sign_id = ?";

    // Query to insert a new customer into the customer table
    private static final String INSERT_CUSTOMER_QUERY = "INSERT INTO customer (sign_id, name, address, nic, telephone) VALUES (?, ?, ?, ?, ?)";

    // Method to get a database connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    // Method to check if customer exists and register if not
    public static boolean isCustomerExists(int signId) {
        boolean exists = false;

        // Check if the sign_id exists in the customer table
        try (Connection conn = getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(CUSTOMER_CHECK_QUERY)) {

            checkStmt.setInt(1, signId);

            // Execute the query to check if sign_id exists in customer table
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    exists = true;  // Customer exists
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }

    // Method to insert a new customer into the customer table
    public static boolean registerCustomer(int signId, String name, String address, String nic, String telephone) {
        boolean isRegistered = false;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_CUSTOMER_QUERY)) {

            ps.setInt(1, signId);  // Foreign Key
            ps.setString(2, name);
            ps.setString(3, address);
            ps.setString(4, nic);
            ps.setString(5, telephone);

            // Execute the insert query
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                isRegistered = true;  // Registration successful
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isRegistered;
    }
}