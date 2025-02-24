/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cab.services.resources;

import com.google.gson.Gson;
import db.DBUtils;  // Import DBUtils
import db.Billing;  // Assuming the User class is in the db package
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
 @Path("/billing")
public class BillingService {
    
    private DBUtils billingDAO = new DBUtils();
    
    private static final double FIXED_TAX = 5.0;
    private static final double FIXED_DISCOUNT = 2.0;
    private static final double FIXED_DRIVER_FEES = 50.0;
    
    

    @GET
    @Path("/latestBooking")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLatestBooking() {
        try {
            Billing bill = billingDAO.getbLatestBooking();

            if (bill != null) {
                // Apply fixed tax, discount, and driver fees
                bill.setTax(FIXED_TAX);
                bill.setDiscount(FIXED_DISCOUNT);
                bill.setDriverFees(FIXED_DRIVER_FEES);
                bill.setTotalAmount(bill.getKmAmount() + FIXED_TAX + FIXED_DRIVER_FEES - FIXED_DISCOUNT);

                return Response.ok(bill).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\":\"No bookings found\"}").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\":\"" + e.getMessage() + "\"}").build();
        }
    }

    @POST
@Path("/processPayment")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response processPayment(Billing bill) {
    try {
        // Set fixed values
        bill.setTax(FIXED_TAX);
        bill.setDiscount(FIXED_DISCOUNT);
        bill.setDriverFees(FIXED_DRIVER_FEES);
        bill.setTotalAmount(bill.getKmAmount() + FIXED_TAX + FIXED_DRIVER_FEES - FIXED_DISCOUNT);

        // Try inserting into the database
        boolean success = billingDAO.insertBill(bill);

        if (success) {
            return Response.ok("{\"message\":\"Payment Successful!\"}").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\":\"Failed to insert bill into the database.\"}")
                    .build();
        }
    } catch (Exception e) {
        e.printStackTrace(); // Print exact error in logs
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("{\"error\":\"" + e.getMessage() + "\"}")
                .build();
    }
    }
    
    
   @GET
    @Path("/all")
    public Response getAllBill() {
       
        DBUtils billingDAO = new DBUtils();
        List<Billing>  billList = billingDAO.getAllBill();
        
        Gson gson = new Gson();
        return Response
                .status(200)
                .entity(gson.toJson( billList))
                .build();
    }
}


