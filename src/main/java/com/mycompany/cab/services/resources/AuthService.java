/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cab.services.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;


/**
 *
 * @author iband
 */

@Path("/auth")
public class AuthService {
    
    @GET
    @Path("/logout")
    @Produces("application/json")
    public Response logout() {
        // Here we don't need to manage sessions; just return a success message
        return Response.ok("{\"message\": \"Logout successful\"}").build();
    }
}
    

