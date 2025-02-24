/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

/**
 *
 * @author iband
 */
public class Billing {
    private int billId;  // Added billId field
    private String orderNum;
    private String customerName;
    private int vehicleId;
    private int km;
    private int baseFare;
    private double kmAmount;
    private double tax;
    private double discount;
    private double driverFees;
    private double totalAmount;

    // Constructor updated to include billId
    public Billing(int billId, String orderNum, String customerName, int vehicleId, int km, int baseFare, double kmAmount, double tax, double discount, double driverFees, double totalAmount) {
        this.billId = billId;  // Initialize billId
        this.orderNum = orderNum;
        this.customerName = customerName;
        this.vehicleId = vehicleId;
        this.km = km;
        this.baseFare = baseFare;
        this.kmAmount = kmAmount;
        this.tax = tax;
        this.discount = discount;
        this.driverFees = driverFees;
        this.totalAmount = totalAmount;
    }

    // Getter and Setter for billId
    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public int getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(int baseFare) {
        this.baseFare = baseFare;
    }

    public double getKmAmount() {
        return kmAmount;
    }

    public void setKmAmount(double kmAmount) {
        this.kmAmount = kmAmount;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getDriverFees() {
        return driverFees;
    }

    public void setDriverFees(double driverFees) {
        this.driverFees = driverFees;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
