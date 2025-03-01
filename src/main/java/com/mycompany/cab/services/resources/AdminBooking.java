/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cab.services.resources;

import com.google.gson.Gson;
import db.ADBUtils;  // Import DBUtils
import db.Booking;  // Assuming the User class is in the db package
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
@Path("/abookings")
public class AdminBooking {
    
      // Create a new booking
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertBooking(String json) {
        //bookingDAO.insertBooking(booking);
       // return Response.status(Response.Status.CREATED).entity("Booking created successfully!").build();
       
       Gson gson = new Gson();
        Booking booking = gson.fromJson(json, Booking.class);
        ADBUtils utils = new ADBUtils();
        
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
        ADBUtils utils = new ADBUtils();
        List<Booking> bookings = utils.getAllBookings();
        
        Gson gson = new Gson();
        return Response
                .status(200)
                .entity(gson.toJson(bookings))
                .build();
    
    }
    @GET
@Path("{id}")
@Produces(MediaType.APPLICATION_JSON)
public Response getBookingById(@PathParam("id") int id) {
    ADBUtils utils = new ADBUtils();

    try {
        Booking booking = utils.getBookingById(id);
        if (booking == null) {
            return Response
                .status(Response.Status.NOT_FOUND)
                .entity("{\"error\": \"Booking not found\"}")
                .build();
        } else {
            Gson gson = new Gson();
            return Response
                .status(Response.Status.OK)
                .entity(gson.toJson(booking))
                .build();
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return Response
            .status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity("{\"error\": \"Database error occurred\"}")
            .build();
    }
}

@PUT
@Path("/update")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response updateBooking(String json) {
    try {
        Gson gson = new Gson();
        Booking booking = gson.fromJson(json, Booking.class);

        if (booking == null || booking.getBookId() == 0 || booking.getOrdernum() == null || 
            booking.getCname() == null || booking.getCaddress() == null || 
            booking.getCtele() == null || booking.getDestination() == null || 
            booking.getKm() == 0 || booking.getVehicleId() == 0 || booking.getDriverId() == 0) {
            return Response
                .status(Response.Status.BAD_REQUEST)
                .entity("{\"error\": \"Invalid booking data\"}")
                .build();
        }

        ADBUtils utils = new ADBUtils();
        boolean res = utils.updateBooking(booking);

        if (res) {
            return Response
                .status(Response.Status.OK)
                .entity("{\"message\":\"Booking updated successfully\"}")
                .build();
        } else {
            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("{\"error\": \"Failed to update booking in the database\"}")
                .build();
        }
    } catch (Exception e) {
        e.printStackTrace();
        return Response
            .status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity("{\"error\": \"Exception occurred: " + e.getMessage() + "\"}")
            .build();
    }
}

    
}
