/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cab.services.resources;

import com.google.gson.Gson;
import db.DBUtils;  // Import DBUtils
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

@Path("/help")
public class HelpService {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHelpInstructions() {
        DBUtils utlis = new DBUtils();
        List<help> helpList = utlis.getAllHelpInstructions();

        Gson gson = new Gson();
        return Response.status(200).entity(gson.toJson(helpList)).build();
    }
}
    

