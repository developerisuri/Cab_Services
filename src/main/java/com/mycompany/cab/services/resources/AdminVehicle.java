/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cab.services.resources;

import com.google.gson.Gson;
import db.ADBUtils;
import db.Vehicle ;
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
@Path("/avehicles")
public class AdminVehicle {
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVehicleById(@PathParam("id") int id) {
        ADBUtils utils = new ADBUtils();
        try {
            Vehicle vehicle = utils.getVehicleById(id);
            if (vehicle == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            } else {
                Gson gson = new Gson();
                return Response
                        .status(Response.Status.OK)
                        .entity(gson.toJson(vehicle))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addVehicle(String json) {
        Gson gson = new Gson();
        Vehicle vehicle = gson.fromJson(json, Vehicle.class);
        ADBUtils utils = new ADBUtils();
        boolean res = utils.addVehicle(vehicle);

        if (res) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateVehicle(String json) {
        try {
            Gson gson = new Gson();
            Vehicle vehicle = gson.fromJson(json, Vehicle.class);

            if (vehicle == null || vehicle.getVehicleId() == 0 || vehicle.getPlate() == null || vehicle.getType() == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\": \"Invalid vehicle data\"}")
                        .build();
            }

            ADBUtils utils = new ADBUtils();
            boolean res = utils.updateVehicle(vehicle);

            if (res) {
                return Response.status(Response.Status.OK)
                        .entity("{\"message\": \"Vehicle updated successfully\"}")
                        .build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("{\"error\": \"Failed to update vehicle in the database\"}")
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Exception occurred: " + e.getMessage() + "\"}")
                    .build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteVehicle(@PathParam("id") int id) {
        ADBUtils utils = new ADBUtils();
        boolean res = utils.deleteVehicle(id);

        if (res) {
            return Response.status(Response.Status.OK)
                    .entity("{\"message\": \"Vehicle deleted successfully.\"}")
                    .build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Failed to delete vehicle.\"}")
                    .build();
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getALLVehicles(){
        
        ADBUtils utils = new ADBUtils();
        List<Vehicle> vehicle = utils.getAllVehicles();
        
        Gson gson = new Gson();
        return Response
                .status(200)
                .entity(gson.toJson(vehicle))
                .build();
    
    }
}

