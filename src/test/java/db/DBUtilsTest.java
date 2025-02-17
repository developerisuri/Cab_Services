/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package db;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author iband
 */
public class DBUtilsTest {
     private DBUtils dbUtils;
    
    public DBUtilsTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
       
    }
    
    @AfterAll
    public static void tearDownClass() {
        
    }
    
    @BeforeEach
    public void setUp  () {
       
        
    }
    
    @AfterEach
    public void tearDown() {
        
        // Cleanup: Ensure test users are deleted after each test
        //dbUtils.deleteUser(9999);
    }
}

    /**
     * Test of addUser method, of class DBUtils.
     */
    
    // @Test
   // public void testGetUserl() throws Exception {
        //User expResult = new User(6, "Carlos Sainz","6554");
      //  DBUtils instance = new DBUtils();
         
        // Test the adding
      //  boolean added = instance.addUser(expResult);
        //assertEquals(added, true);
        
   // }
    
    
  // @Test
  //  public void testvalidateUserLogin_ValidCredentials() {
      //  User user = new User();
       // user.setUsername("hi");
       // user.setPassword("222");

       // boolean result = dbUtils.validateUserLogin(user);
      //  assertTrue(result, "The login should be valid for correct credentials.");
   // }

   // @Test
   // public void testvalidateUserLogin_InvalidCredentials() {
        //User user = new User();
      //  user.setUsername("invalidUsername");
      //  user.setPassword("invalidPassword");

      //  boolean result = dbUtils.validateUserLogin(user);
       // assertFalse(result, "The login should be invalid for incorrect credentials.");
   // }

   // @Test
   // public void testAddUser() {
       // System.out.println("addUser");
      //  User us = null;
      //  DBUtils instance = new DBUtils();
      //  boolean expResult = false;
      //  boolean result = instance.addUser(us);
       // assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
      //  fail("The test case is a prototype.");
   // }

    /**
     * Test of validateUserLogin method, of class DBUtils.
     */
    // @Test
   // public void testValidateUserLogin() {
      //  System.out.println("validateUserLogin");
      //  User us = new User();
      // DBUtils instance = new DBUtils();
      // boolean expResult = instance.validateUserLogin(us);
        //boolean result = instance.validateUserLogin(us);
     // assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
      //  fail("The test case is a prototype.");
   // }
    
    //}
    
    /**
     * Test of getUser method, of class DBUtils.
     */
    
  //  @Test
   // public void testGetUser() {
        
      // System.out.println("getUser");
     //  DBUtils instance = new DBUtils();
      // List<User> expResult =instance.getUser();
      //  List<User> result = instance.getUser();
     //  assertEquals(true, true);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
 // }


    /**
     * Test of addCustomer method, of class DBUtils.
     */
    //@Test
  // public void testAddCustomer() {
       // System.out.println("addCustomer");
       // Customer customer = new Customer();
       // DBUtils instance = new DBUtils();
      // boolean expResult = instance.addCustomer(customer);
       // boolean result = instance.addCustomer(customer);
       // assertEquals(true, true);
        // TODO review the generated test code and remove the default call to fail.
      //  fail("The test case is a prototype.");
   // }


    /**
     * Test of getCustomer method, of class DBUtils.
     */
   // @Test
   // public void testGetCustomer() {
      //  System.out.println("getCustomer");
      //  DBUtils instance = new DBUtils();
       // List<Customer> expResult = instance.getCustomer();
      //  List<Customer> result = instance.getCustomer();
      // assertEquals(true, true);
        // TODO review the generated test code and remove the default call to fail.
     //   fail("The test case is a prototype.");
   // }
//}

    /**
     * Test of getAllVehicles method, of class DBUtils.
     */
   // @Test
  //  public void testGetAllVehicles() {
     //   System.out.println("getAllVehicles");
     //   DBUtils instance = new DBUtils();
      //  List<Vehicle> expResult = null;
      //  List<Vehicle> result = instance.getAllVehicles();
      //  assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
      //  fail("The test case is a prototype.");
  //  }

    /**
     * Test of getAllDrivers method, of class DBUtils.
     */
   // @Test
   // public void testGetAllDrivers() {
       // System.out.println("getAllDrivers");
      //  DBUtils instance = new DBUtils();
      //  List<Driver> expResult = null;
      //  List<Driver> result = instance.getAllDrivers();
       // assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
      //  fail("The test case is a prototype.");
  //  }

    /**
     * Test of getAllHelpInstructions method, of class DBUtils.
     */
  //  @Test
  //  public void testGetAllHelpInstructions() {
    //    System.out.println("getAllHelpInstructions");
     //   DBUtils instance = new DBUtils();
     //   List<help> expResult = null;
      //  List<help> result = instance.getAllHelpInstructions();
      //  assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
     //   fail("The test case is a prototype.");
   // }
    

