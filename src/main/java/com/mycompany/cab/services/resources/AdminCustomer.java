/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cab.services.resources;

import com.google.gson.Gson;
import db.ADBUtils;
import db.Customer;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
/**

/**
 *
 * @author iband
 */
@Path("/acustomer")
public class AdminCustomer {
    
     @POST
    @Consumes(MediaType.APPLICATION_JSON)
    

  public Response addCustomer(String json) {
      
       Gson gson = new Gson();
        Customer customer = gson.fromJson(json, Customer.class);
        ADBUtils utils = new ADBUtils();
        
        boolean res = utils.addCustomer(customer);
        
        if (res) {
            return Response.status(201).build(); // Success
        } else {
            return Response.status(500).entity("Registration Failed! Please try again.").build(); // Failure with message
        }
  }
  
  
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomer(){
        
        ADBUtils utils = new ADBUtils();
        List<Customer> customer = utils.getCustomer();
        
        Gson gson = new Gson();
        return Response
                .status(200)
                .entity(gson.toJson(customer))
                .build();
    
  
    }
    
    @GET
@Path("{id}")
@Produces(MediaType.APPLICATION_JSON)
public Response getCustomerById(@PathParam("id") int id) {
    ADBUtils utils = new ADBUtils();

    try {
        Customer customer = utils.getCustomerById(id);
        if (customer == null) {
            return Response
                .status(Response.Status.NOT_FOUND)
                .entity("{\"error\": \"Customer not found\"}")
                .build();
        } else {
            Gson gson = new Gson();
            return Response
                .status(Response.Status.OK)
                .entity(gson.toJson(customer))
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
public Response updateCustomer(String json) {
    try {
        Gson gson = new Gson();
        Customer customer = gson.fromJson(json, Customer.class);

        if (customer == null || customer.getRegId() == 0 || customer.getName() == null || 
            customer.getAddress() == null || customer.getNic() == null || customer.getTelephone() == null) {
            return Response
                .status(Response.Status.BAD_REQUEST)
                .entity("{\"error\": \"Invalid customer data\"}")
                .build();
        }

        ADBUtils utils = new ADBUtils();
        boolean res = utils.updateCustomer(customer);

        if (res) {
            return Response
                .status(Response.Status.OK)
                .entity("{\"message\":\"Customer updated successfully\"}")
                .build();
        } else {
            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("{\"error\": \"Failed to update customer in the database\"}")
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
