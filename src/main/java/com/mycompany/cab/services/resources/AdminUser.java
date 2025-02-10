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
    public Response getUser(@PathParam("id") int id) {
         ADBUtils utils = new ADBUtils();
         
         try {
            User us = utils.getUser(id);
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
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(String json) {
        Gson gson = new Gson();
        User us = gson.fromJson(json, User.class);
        ADBUtils utils = new ADBUtils();
        boolean res = utils.updateUser(us);
        
        if (res) {
            return Response
                .status(200)
                .build();
        } else {
            return Response
                .status(500)
                .build();
        }
    }
    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id") int id) {
        ADBUtils utils = new ADBUtils();
        boolean res = utils.deleteUser(id);
        if (res) {
            return Response
                .status(200)
                .build();
        } else {
            return Response
                .status(500)
                .build();
        }
    }
    
}
