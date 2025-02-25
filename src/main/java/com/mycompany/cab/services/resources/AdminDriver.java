/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cab.services.resources;

import com.google.gson.Gson;
import db.ADBUtils;
import db.Driver;
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
@Path("/adrivers")
public class AdminDriver {
     @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDrivers() {
        ADBUtils utils = new ADBUtils();
        List<Driver> driver = utils.getAllDrivers();

        Gson gson = new Gson();
        return Response
                .status(200)
                .entity(gson.toJson(driver))
                .build();
    }
    
    @GET
@Path("{id}")
@Produces(MediaType.APPLICATION_JSON)
public Response getDriver(@PathParam("id") int id) {
     ADBUtils utils = new ADBUtils();
     
     try {
        Driver driver = utils.getDriverById(id);
        if (driver == null) {
            return Response
                .status(404)
                .build(); 
        } else {
            Gson gson = new Gson();
            return Response
                   .status(200)
                   .entity(gson.toJson(driver))
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
public Response addDriver(String json) {
    Gson gson = new Gson();
    Driver driver = gson.fromJson(json, Driver.class);
    ADBUtils utils = new ADBUtils();
    boolean res = utils.addDriver(driver);
    
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
@Path("/update")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response updateDriver(String json) {
    try {
        Gson gson = new Gson();
        Driver driver = gson.fromJson(json, Driver.class);

        if (driver == null || driver.getDriverId() == 0 || driver.getName() == null || driver.getAddress() == null || driver.getTele() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity("{\"error\": \"Invalid driver data\"}")
                .build();
        }

        ADBUtils utils = new ADBUtils();
        boolean res = utils.updateDriver(driver);

        if (res) {
            return Response
                .status(Response.Status.OK)
                .entity("{\"message\":\"Driver updated successfully\"}")
                .build();
        } else {
            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("{\"error\": \"Failed to update driver in the database\"}")
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
