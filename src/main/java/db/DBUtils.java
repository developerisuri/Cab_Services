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
    public boolean addCustomer(Customer customer) {
Connection conn = null;
    PreparedStatement stmt = null;
    
    try {
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        
        // Insert query for customer table
        String query = "INSERT INTO customer (name, address, nic, tele) VALUES (?, ?, ?, ?)";
        stmt = conn.prepareStatement(query);
        stmt.setString(1, customer.getName());
        stmt.setString(2, customer.getAddress());
        stmt.setString(3, customer.getNic());
        stmt.setString(4, customer.getTelephone());
        
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;  // Returns true if insert was successful
        
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
    
//Customer List
     
    public List<Customer> getCustomer() {
        List<Customer> customer = new ArrayList<>();
         try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                    Statement stmt = conn.createStatement(); 
                    ResultSet rs = stmt.executeQuery("SELECT * FROM customer");) {
                while (rs.next()) {
                   Customer cu = new Customer();
                   cu.setRegId( rs.getInt("regid"));
                    cu.setName(rs.getString("name"));
                     cu.setAddress(rs.getString("address"));
                      cu.setNic(rs.getString("nic"));
                       cu.setTelephone(rs.getString("tele"));

                    customer .add(cu);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {

        }

        return customer ;
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

   
//Booking class

// Insert a new booking
    public boolean insertBooking(Booking booking) {
    Connection conn = null;
    PreparedStatement stmt = null;

    try {
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        String query  = "INSERT INTO booking2 (cname, caddress, ctele, destination, km, vehicle_id, driver_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        // Set parameters for the SQL query
        stmt = conn.prepareStatement(query);
        stmt.setString(1, booking.getCname());
        stmt.setString(2, booking.getCaddress());
        stmt.setString(3, booking.getCtele());
        stmt.setString(4, booking.getDestination());
        stmt.setInt(5, booking.getKm());
        stmt.setInt(6, booking.getVehicleId());
        stmt.setInt(7, booking.getDriverId());

        // Execute the update and check if insertion was successful
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;  // Returns true if insert was successful

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

    return false;  // Return false in case of error
}





    // Get all bookings
    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        //String sql = "SELECT * FROM booking2 ";
        
         try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());


        try (Connection conn =  DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps  = conn.prepareStatement("SELECT * FROM booking2");
             ResultSet rs = ps .executeQuery()) {

            // Process each row in the result set
            while (rs.next()) {
                bookings.add(new Booking(
                    rs.getInt("book_id"),
                    rs.getString("ordernum"),      // Mapping 'ordernum' from MySQL
                    rs.getString("cname"),
                    rs.getString("caddress"),
                    rs.getString("ctele"),
                    rs.getString("destination"),
                    rs.getInt("km"),
                    rs.getInt("vehicle_id"),
                    rs.getInt("driver_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
         } catch (Exception e) {

        }
        return bookings;
    }
    

    // Get a booking by its Order Number
public Booking getBookingByOrderNum(String orderNum) throws SQLException {
    Booking booking = null;
    
    try {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM booking2 WHERE ordernum = '" + orderNum + "'")) {

            while (rs.next()) {
                booking = new Booking();
                booking.setBookId(rs.getInt("book_id"));
                booking.setOrdernum(rs.getString("ordernum"));
                booking.setCname(rs.getString("cname"));
                booking.setCaddress(rs.getString("caddress"));
                booking.setCtele(rs.getString("ctele"));
                booking.setDestination(rs.getString("destination"));
                booking.setKm(rs.getInt("km"));
                booking.setVehicleId(rs.getInt("vehicle_id"));
                booking.setDriverId(rs.getInt("driver_id"));
                break; // Stop after getting the first match
            }
        } catch (SQLException e) {
            System.err.print(e);
            throw e;
        }

    } catch (SQLException e) {
        System.err.print(e);
        throw e;
    }

    return booking;
}

    // Update a booking
    public void updateBooking(Booking booking) {
        String sql = "UPDATE booking2  SET cname = ?, caddress = ?, ctele = ?, destination = ?, km = ?, vehicle_id = ?, driver_id = ? WHERE book_id = ?";

        try (Connection conn =  DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, booking.getCname());
            stmt.setString(2, booking.getCaddress());
            stmt.setString(3, booking.getCtele());
            stmt.setString(4, booking.getDestination());
            stmt.setInt(5, booking.getKm());
            stmt.setInt(6, booking.getVehicleId());
            stmt.setInt(7, booking.getDriverId());
            stmt.setInt(8, booking.getBookId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a booking by its ID
    public void deleteBooking(int id) {
        String sql = "DELETE FROM booking2  WHERE book_id = ?";

        try (Connection conn =  DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);  // Set the booking ID parameter
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Booking getLatestBooking() {
    Booking booking = null;
     try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());


        try (Connection conn =  DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps  = conn.prepareStatement("SELECT * FROM booking2 ORDER BY book_id DESC LIMIT 1;");
             ResultSet rs = ps .executeQuery()) {
        
        if (rs.next()) {
            booking = new Booking();
            booking.setBookId(rs.getInt("book_id"));
            booking.setOrdernum(rs.getString("ordernum"));
            booking.setCname(rs.getString("cname"));
            booking.setCaddress(rs.getString("caddress"));
            booking.setCtele(rs.getString("ctele"));
            booking.setDestination(rs.getString("destination"));
            booking.setKm(rs.getInt("km"));
            booking.setVehicleId(rs.getInt("vehicle_id"));
            booking.setDriverId(rs.getInt("driver_id"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
        } catch (Exception e) {

        }
    return booking;
}
    
    public List<Vehicle> getVehicles() {
    List<Vehicle> vehicleList = new ArrayList<>();
   // String sql = "SELECT id, model FROM vehicle";

     try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());


        try (Connection conn =  DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps  = conn.prepareStatement("SELECT vehicle_id, model FROM vehicle");
             ResultSet rs = ps .executeQuery()) {

        while (rs.next()) {
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleId(rs.getInt("vehicle_id"));
            vehicle.setModel(rs.getString("model"));
            vehicleList.add(vehicle);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
        } catch (Exception e) {

        }
    return vehicleList;
}

    
    public List<Driver> getDrivers() {
    List<Driver> driverList = new ArrayList<>();
    //String sql = "SELECT id, name FROM drivers";

     try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());


        try (Connection conn =  DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps  = conn.prepareStatement("SELECT driver_id, name FROM driver");
             ResultSet rs = ps .executeQuery()) {
        while (rs.next()) {
            Driver driver = new Driver();
            driver.setDriverId(rs.getInt("driver_id"));
            driver.setName(rs.getString("name"));
            driverList.add(driver);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
        } catch (Exception e) {

        }
    return driverList;
}

}


    