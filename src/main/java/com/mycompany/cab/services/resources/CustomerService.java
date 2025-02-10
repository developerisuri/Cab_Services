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
   // Inject the database service
 private final DBUtils dbService = new DBUtils();
    // Endpoint to check if the customer exists
    @GET
    @Path("/exists/{signId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkCustomerExists(@PathParam("signId") int signId) {
        boolean exists = dbService.isCustomerExists(signId);

        if (exists) {
            return Response.status(Response.Status.OK).entity("Customer exists, redirecting to customer menu.").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Customer does not exist, proceed with registration.").build();
        }
    }

    // Endpoint to register a new customer
    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerCustomer(Customer customer) {
        boolean isRegistered = dbService.registerCustomer(
                customer.getSignId(),
                customer.getName(),
                customer.getAddress(),
                customer.getNic(),
                customer.getTelephone()
        );

        if (isRegistered) {
            return Response.status(Response.Status.CREATED).entity("Registration successful!").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Registration failed!").build();
        }
    }
}