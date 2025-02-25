/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cab.services.resources;
import com.google.gson.Gson;
import db.ADBUtils;  // Import DBUtils
import db.help;  // Assuming the User class is in the db package
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
@Path("/ahelp")
public class AdminHelp {
    
     @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHelpInstructions() {
        ADBUtils utlis = new ADBUtils();
        List<help> helpList = utlis.getAllHelpInstructions();

        Gson gson = new Gson();
        return Response.status(200).entity(gson.toJson(helpList)).build();
    }
    
     @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHelp(@PathParam("id") int id) {
        ADBUtils utils = new ADBUtils();

        try {
            help help = utils.getHelpById(id);
            if (help == null) {
                return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"Help instruction not found\"}")
                    .build();
            } else {
                Gson gson = new Gson();
                return Response
                    .status(Response.Status.OK)
                    .entity(gson.toJson(help))
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addHelp(String json) {
        Gson gson = new Gson();
        help help = gson.fromJson(json, help.class);
        ADBUtils utils = new ADBUtils();
        boolean res = utils.addHelp(help);

        if (res) {
            return Response
                .status(Response.Status.CREATED)
                .entity("{\"message\": \"Help instruction added successfully\"}")
                .build();
        } else {
            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("{\"error\": \"Failed to add help instruction\"}")
                .build();
        }
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateHelp(String json) {
        try {
            Gson gson = new Gson();
            help help = gson.fromJson(json, help.class);

            if (help == null || help.getHelpId() == 0 || help.getInstructions() == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"Invalid help data\"}")
                    .build();
            }

            ADBUtils utils = new ADBUtils();
            boolean res = utils.updateHelp(help);

            if (res) {
                return Response
                    .status(Response.Status.OK)
                    .entity("{\"message\":\"Help instruction updated successfully\"}")
                    .build();
            } else {
                return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Failed to update help instruction\"}")
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
