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



//Customer Registration

   // Check if the sign_id exists in the signin table
    public boolean isSignIdValid(int signId) {
        String query = "SELECT COUNT(*) FROM signin WHERE sign_id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            
            stmt.setInt(1, signId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Register a customer with the provided details
    public boolean registerCustomer(int signId, String name, String address, String nic, String telephone) {
        String query = "INSERT INTO customer (sign_id, name, address, nic, telephone) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            
            stmt.setInt(1, signId);
            stmt.setString(2, name);
            stmt.setString(3, address);
            stmt.setString(4, nic);
            stmt.setString(5, telephone);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Check if a customer exists based on the sign_id (Optional - If needed)
    public boolean isCustomerExists(int signId) {
        String query = "SELECT COUNT(*) FROM customer WHERE sign_id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            
            stmt.setInt(1, signId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



//Vehicle Display

// Fetch all vehicles from the database
   public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicle = new ArrayList<>();
         try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                    Statement stmt = conn.createStatement(); 
                    ResultSet rs = stmt.executeQuery("SELECT * FROM vehicle");) {
                while (rs.next()) {
                    Vehicle ve = new Vehicle();
                    ve.setVehicleId( rs.getInt("vehicle_id"));
                    ve.setPlate(rs.getString("plate"));
                    ve.setType(rs.getString("type"));
                    ve.setModel(rs.getString("model"));
                    ve.setColour(rs.getString("colour"));
                     ve.setBaseFare(rs.getInt("basefare"));
                     ve.setStatus(rs.getString("status"));

                    


                    vehicle.add(ve);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {

        }

        return vehicle;
    }
   
   
   
   //Driver Display

// Fetch all drivers from the database
   public List<Driver> getAllDrivers() {
        List<Driver> driver = new ArrayList<>();
         try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                    Statement stmt = conn.createStatement(); 
                    ResultSet rs = stmt.executeQuery("SELECT * FROM drivers ");) {
                while (rs.next()) {
                    Driver dr = new Driver();
                    dr.setDriverId( rs.getInt("driver_id"));
                    dr.setName(rs.getString("name"));
                    dr.setAddress(rs.getString("address"));
                    dr.setTele(rs.getString("tele"));
                   

                    


                    driver.add(dr);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {

        }

        return driver;
    }
   
   // Help instructions
   
   public List<help> getAllHelpInstructions() {
        List<help> helpList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM help");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                help help = new help();
                help.setHelpId(rs.getInt("help_id"));
                help.setInstructions(rs.getString("instructions")); // LONGTEXT retrieval
                helpList.add(help);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return helpList;
    }
}
   
