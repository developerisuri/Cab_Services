/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cab.services.resources;

import com.google.gson.Gson;
import db.DBUtils;  // Import DBUtils
import db.Customer;  // Assuming the User class is in the db package
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.sql.SQLException;
import java.util.List;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import static javax.ws.rs.client.Entity.json;

/**
 *
 * @author iband
 */

@Path("/customers")
public class CustomerService {
    
      // Create a Gson instance to convert objects to JSON
    private static final Gson gson = new Gson();

    // Inject the database service (assuming DBUtils is a class you have for DB operations)
    private final DBUtils dbService = new DBUtils();

    // Endpoint to check if the customer exists
    @GET
    @Path("/exists/{signId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkCustomerExists(@PathParam("signId") int signId) {
        try {
            // Check if the customer exists using DBUtils
            boolean exists = dbService.isCustomerExists(signId);

            if (exists) {
                // Return response with a message
                return Response.status(Response.Status.OK)
                        .entity(gson.toJson(new ResponseMessage("Customer exists, redirecting to customer menu.")))
                        .build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(gson.toJson(new ResponseMessage("Customer does not exist, proceed with registration.")))
                        .build();
            }
        } catch (Exception e) {
            // Log and handle any unexpected errors
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(gson.toJson(new ResponseMessage("An error occurred while checking customer existence.")))
                    .build();
        }
    }

    // Endpoint to register a new customer
    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerCustomer(Customer customer) {
        try {
            // Register the customer using DBUtils
            boolean isRegistered = dbService.registerCustomer(
                    customer.getSignId(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getNic(),
                    customer.getTelephone()
            );

            if (isRegistered) {
                return Response.status(Response.Status.CREATED)
                        .entity(gson.toJson(new ResponseMessage("Registration successful!")))
                        .build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(gson.toJson(new ResponseMessage("Registration failed!")))
                        .build();
            }
        } catch (Exception e) {
            // Log and handle unexpected errors
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(gson.toJson(new ResponseMessage("An error occurred during registration.")))
                    .build();
        }
    }

    // Inner class to wrap the message response as a JSON structure
    private static class ResponseMessage {
        private String message;

        public ResponseMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}