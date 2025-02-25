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
   
   // Get Vehicle by ID
    public Vehicle getVehicleById(int id) throws SQLException {
        Vehicle vehicle = null;
        String query = "SELECT * FROM vehicle WHERE vehicle_id = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                vehicle = new Vehicle();
                vehicle.setVehicleId(rs.getInt("vehicle_id"));
                vehicle.setPlate(rs.getString("plate"));
                vehicle.setType(rs.getString("type"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setColour(rs.getString("colour"));
                vehicle.setBaseFare(rs.getInt("basefare"));
                vehicle.setStatus(rs.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return vehicle;
    }

    // Add a new Vehicle
    public boolean addVehicle(Vehicle vehicle) {
        String query = "INSERT INTO vehicle (plate, type, model, colour, basefare, status) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, vehicle.getPlate());
            stmt.setString(2, vehicle.getType());
            stmt.setString(3, vehicle.getModel());
            stmt.setString(4, vehicle.getColour());
            stmt.setInt(5, (int) vehicle.getBaseFare());
            stmt.setString(6, vehicle.getStatus());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Update an existing Vehicle
    public boolean updateVehicle(Vehicle vehicle) {
        String query = "UPDATE vehicle SET plate = ?, type = ?, model = ?, colour = ?, basefare = ?, status = ? WHERE vehicle_id = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, vehicle.getPlate());
            stmt.setString(2, vehicle.getType());
            stmt.setString(3, vehicle.getModel());
            stmt.setString(4, vehicle.getColour());
            stmt.setInt(5, (int) vehicle.getBaseFare());
            stmt.setString(6, vehicle.getStatus());
            stmt.setInt(7, vehicle.getVehicleId());
            
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete a Vehicle
    public boolean deleteVehicle(int vehicleId) {
        String query = "DELETE FROM vehicle WHERE vehicle_id = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, vehicleId);
            
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
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
   
// Get Driver by ID
    public Driver getDriverById(int id) throws SQLException {
        Driver driver = null;
        String query = "SELECT * FROM drivers WHERE driver_id = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                driver = new Driver();
                driver.setDriverId(rs.getInt("driver_id"));
                driver.setName(rs.getString("name"));
                driver.setAddress(rs.getString("address"));
                driver.setTele(rs.getString("tele"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return driver;
    }

    // Add a new Driver
    public boolean addDriver(Driver driver) {
        String query = "INSERT INTO drivers (name, address, tele) VALUES (?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, driver.getName());
            stmt.setString(2, driver.getAddress());
            stmt.setString(3, driver.getTele());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Update an existing Driver
    public boolean updateDriver(Driver driver) {
        String query = "UPDATE drivers SET name = ?, address = ?, tele = ? WHERE driver_id = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, driver.getName());
            stmt.setString(2, driver.getAddress());
            stmt.setString(3, driver.getTele());
            stmt.setInt(4, driver.getDriverId());
            
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
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
     
     // Fetch help instructions by ID
    public help getHelpById(int id) throws SQLException {
        help help = null;
        String query = "SELECT * FROM help WHERE help_id = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                help = new help();
                help.setHelpId(rs.getInt("help_id"));
                help.setInstructions(rs.getString("instructions"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return help;
    }

    // Add a new Help instruction
    public boolean addHelp(help help) {
        String query = "INSERT INTO help (instructions) VALUES (?)";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, help.getInstructions());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Update an existing Help instruction
    public boolean updateHelp(help help) {
        String query = "UPDATE help SET instructions = ? WHERE help_id = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, help.getInstructions());
            stmt.setInt(2, help.getHelpId());
            
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}


