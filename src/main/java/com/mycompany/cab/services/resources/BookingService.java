/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cab.services.resources;

import com.google.gson.Gson;
import db.DBUtils;  // Import DBUtils
import db.Booking;  // Assuming the User class is in the db package
import db.Vehicle; 
import db.Driver; 
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


/**
 *
 * @author iband
 */

@Path("/bookings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookingService {
    
    DBUtils bookingDAO = new DBUtils();

    // Create a new booking
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertBooking(String json) {
        //bookingDAO.insertBooking(booking);
       // return Response.status(Response.Status.CREATED).entity("Booking created successfully!").build();
       
       Gson gson = new Gson();
        Booking booking = gson.fromJson(json, Booking.class);
        DBUtils utils = new DBUtils();
        
        boolean res = utils.insertBooking(booking);
        
        if (res) {
            return Response.status(201).build(); // Success
        } else {
            return Response.status(500).entity("Sign Up Failed! Please try again.").build(); // Failure with message
        }
    }

    // Get all bookings
    @GET
    public Response getAllBookings() {
        DBUtils utils = new DBUtils();
        List<Booking> bookings = utils.getAllBookings();
        
        Gson gson = new Gson();
        return Response
                .status(200)
                .entity(gson.toJson(bookings))
                .build();
    
    }
    
    @GET
@Path("/latest")
@Produces(MediaType.APPLICATION_JSON)
public Booking getLatestBooking() {
    return bookingDAO.getLatestBooking();
}




@GET
   @Path("/dvehicles")
    @Produces(MediaType.APPLICATION_JSON)
   public Response getVehicles() {
     DBUtils utils = new DBUtils();
       List<Vehicle> vehicleList  = utils.getVehicles();
        
       Gson gson = new Gson();
       return Response
                .status(200)
                .entity(gson.toJson(vehicleList))
                .build();
    
    }

    @GET
    @Path("/ddrivers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDrivers() {
      DBUtils utils = new DBUtils();
       List<Driver> driverList  = utils.getDrivers();
        
       Gson gson = new Gson();
     return Response
              .status(200)
              .entity(gson.toJson(driverList))
               .build();
   }
}

    

