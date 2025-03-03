/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cab.services.resources;

import com.google.gson.Gson;
import db.ADBUtils;
import db.User;
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
 *
 * @author iband
 */
@Path("adminUser")
public class AdminUser {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(){
        
        ADBUtils utils = new ADBUtils();
        List<User> user = utils.getUser();
        
        Gson gson = new Gson();
        return Response
                .status(200)
                .entity(gson.toJson(user))
                .build();
    }
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUseri(@PathParam("id") int id) {
         ADBUtils utils = new ADBUtils();
         
         try {
            User us = utils.getUseri(id);
            if (us == null) {
                return Response
                    .status(404)
                    .build(); 
            } else {
                Gson gson = new Gson();
                return Response
                       .status(200)
                       .entity(gson.toJson(us))
                       .build();  
            }
         } catch(SQLException e) {
             return Response
                    .status(500)
                    .build(); 
         } 
    
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(String json) {
        Gson gson = new Gson();
        User us = gson.fromJson(json, User.class);
        ADBUtils utils = new ADBUtils();
        boolean res = utils.addUser(us);
        
        if (res) {
            return Response
                .status(201)
                .build();
        } else {
            return Response
                .status(500)
                .build();
        }
    }
    
   @PUT
@Path("/update")  // Ensure the path is correct
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response updateUser(String json) {
    try {
        Gson gson = new Gson();
        User us = gson.fromJson(json, User.class);

        if (us == null || us.getId() == 0 || us.getUsername() == null || us.getPassword() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity("{\"error\": \"Invalid user data\"}")
                .build();
        }

        ADBUtils utils = new ADBUtils();
        boolean res = utils.updateUser(us);

        if (res) {
            return Response
                .status(Response.Status.OK)
                .entity("{\"message\":\"User updated successfully\"}")
                .build();
        } else {
            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("{\"error\": \"Failed to update user in the database\"}")
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

   
   @DELETE
@Path("{id}")
   @Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response deleteUser(@PathParam("id") int id) {
    ADBUtils utils = new ADBUtils();
    boolean res = utils.deleteUser(id); // Try deleting the user based on the ID
    if (res) {
        // Return success status if deletion is successful
        return Response
                .status(Response.Status.OK) // HTTP 200
                .entity("User deleted successfully.") // Optional: Provide response body
                .build();
    } else {
        // Return internal server error if deletion fails
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR) // HTTP 500
                .entity("Failed to delete user.") // Optional: Provide response body
                .build();
    }
}

    
}
