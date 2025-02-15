/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

/**
 *
 * @author iband
 */
public class Customer {
    
    private int regid;
    private String name;
    private String address;
    private String nic;
    private String tele;
    
    
    
     public Customer() {
        this.regid = -1;
        this.name = "";
        this.address = "";
        this.nic = "";
         this.tele = "";
    }
    
    // Constructor
    public Customer(int regid, String name, String address, String nic, String tele) {
        this.regid= regid;
        this.name = name;
        this.address = address;
        this.nic = nic;
        this.tele = tele;
    }
    

    
    // Getters and setters
    public int getRegId() {
        return regid;
    }

    public void setRegId(int regid) {
        this.regid = regid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getTelephone() {
        return tele;
    }

    public void setTelephone(String tele) {
        this.tele = tele;
    }
}