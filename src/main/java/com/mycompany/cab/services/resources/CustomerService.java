/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cab.services.resources;

import com.google.gson.Gson;
import db.DBUtils; 
import db.Customer;  
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * RESTful service for Customer operations
 * @author iband
 */

@Path("/customers")
public class CustomerService {

    // Endpoint to check if the customer exists and redirect accordingly
    @GET
    @Path("/check")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkCustomerExists(String json) {
        try {
            // Initialize Gson and DBUtils objects inside the method
            Gson gson = new Gson();
            DBUtils dbService = new DBUtils();

            // Parse the JSON string into a SignIdRequest object
            SignIdRequest signIdRequest = gson.fromJson(json, SignIdRequest.class);

            // Get the sign_id from the parsed object
            int signId = signIdRequest.getSignId();

            // Check if the sign_id exists in the signin table
            boolean isValidSignId = dbService.isSignIdValid(signId);

            if (isValidSignId) {
                // If sign_id exists, redirect to customer menu
                return Response.status(Response.Status.OK)
                        .entity(gson.toJson(new ResponseMessage("Customer exists, redirecting to customer menu.", "/customerMenu")))
                        .build();
            } else {
                // If sign_id does not exist, redirect to customer registration
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(gson.toJson(new ResponseMessage("Customer does not exist, proceed with registration.", "/customerRegister")))
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Gson().toJson(new ResponseMessage("An error occurred while checking customer existence.")))
                    .build();
        }
    }

    // Endpoint for registering a customer
    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerCustomer(String json) {
        try {
            // Initialize Gson and DBUtils objects inside the method
            Gson gson = new Gson();
            DBUtils dbService = new DBUtils();

            // Parse the JSON string into a Customer object
            Customer customer = gson.fromJson(json, Customer.class);

            // Ensure that the sign_id exists in the signin table
            boolean isValidSignId = dbService.isSignIdValid(customer.getSignId());
            if (!isValidSignId) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(gson.toJson(new ResponseMessage("Invalid sign_id. Please provide a valid sign_id.")))
                        .build();
            }

            // Proceed with customer registration
            boolean isRegistered = dbService.registerCustomer(
                    customer.getSignId(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getNic(),
                    customer.getTelephone()
            );

            if (isRegistered) {
                return Response.status(Response.Status.CREATED)
                        .entity(gson.toJson(new ResponseMessage("Registration successful!")))
                        .build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(gson.toJson(new ResponseMessage("Registration failed!")))
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(gson.toJson(new ResponseMessage("An error occurred during registration.")))
                    .build();
        }
    }

    // Inner class to wrap the message response as a JSON structure
    private static class ResponseMessage {
        private String message;
        private String redirectUrl;

        public ResponseMessage(String message) {
            this.message = message;
        }

        public ResponseMessage(String message, String redirectUrl) {
            this.message = message;
            this.redirectUrl = redirectUrl;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getRedirectUrl() {
            return redirectUrl;
        }

        public void setRedirectUrl(String redirectUrl) {
            this.redirectUrl = redirectUrl;
        }
    }

    // Inner class to wrap the sign_id for check operation
    private static class SignIdRequest {
        private int signId;

        public int getSignId() {
            return signId;
        }

        public void setSignId(int signId) {
            this.signId = signId;
        }
    }

    private static class gson {

        private static Object toJson(ResponseMessage responseMessage) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public gson() {
        }
    }
}
