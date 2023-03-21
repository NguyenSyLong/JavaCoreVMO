/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Long Nguyen
 */
public class Order {
    private int id;    
    private Customer customer;
    private String payment;
    private ArrayList<OrderDetail> orderDetail= new ArrayList<>();

    public Order() {
    }

    public Order(int id, Customer customer, String payment) {
        this.id = id;
        this.customer = customer;
        this.payment = payment;

    }
    public float getTotal()
    {
        float sum = 0;
        for (OrderDetail detail : orderDetail) {
            sum += detail.getTotal();
        }
        return sum;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public ArrayList<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(ArrayList<OrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }
    
    
    
}
