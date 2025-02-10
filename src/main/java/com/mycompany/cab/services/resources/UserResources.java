/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cab.services.resources;

import com.google.gson.Gson;
import db.DBUtils;  // Import DBUtils
import db.User;  // Assuming the User class is in the db package
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
@Path("/user")// The base path for user operations
public class UserResources {
    // POST method to add a new user (username and password only)
   // POST method to add a new user (username and password only)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    
    public Response addUser(String json) {
       // Gson gson = new Gson();
        //User us = gson.fromJson(json, User.class);
        //DBUtils utils = new DBUtils();
        //boolean res = utils.addUser(us);
        
        //if (res) {
           // return Response
              //  .status(201)
               // .build();
      //  } else {
           // return Response
               // .status(500)
               // .build();
        //}
        
         Gson gson = new Gson();
        User us = gson.fromJson(json, User.class);
        DBUtils utils = new DBUtils();
        
        boolean res = utils.addUser(us);
        
        if (res) {
            return Response.status(201).build(); // Success
        } else {
            return Response.status(500).entity("Sign Up Failed! Please try again.").build(); // Failure with message
        }
    }
 
  @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginUser(String json) {
        try {
            // Parse JSON input into a User object
            Gson gson = new Gson();
            User user = gson.fromJson(json, User.class);
            
            DBUtils dbUtils = new DBUtils();
            boolean isValidUser = dbUtils.validateUserLogin(user);
            
            if (isValidUser) {
                // If user is valid, return success response
                return Response.status(200).entity("Login successful!").build();
            } else {
                // If invalid credentials, return failure response
                return Response.status(401).entity("Invalid username or password").build();
            }
        } catch (Exception e) {
            e.printStackTrace(); // Add error logging
            return Response.status(500).entity("Server error, please try again later").build();
        }
    }



    
     @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(){
        
        DBUtils utils = new DBUtils();
        List<User> user = utils.getUser();
        
        Gson gson = new Gson();
        return Response
                .status(200)
                .entity(gson.toJson(user))
                .build();
    
  
    }
}



    

 

