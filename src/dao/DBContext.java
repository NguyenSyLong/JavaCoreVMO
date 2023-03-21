package dao;

import file.FileAccount;
import java.util.ArrayList;
import model.Account;
import model.Customer;
import model.Order;
import model.Product;
import sun.security.util.Password;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Long Nguyen
 */
public class DBContext {
    private ArrayList<Order> orders = new ArrayList<>();
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    
    public boolean login(String username,String password){
       FileAccount fa = new FileAccount();
       ArrayList<Account> acc = fa.readfile("C:\\Users\\Long Nguyen\\Desktop\\lyric.txt"); 
        for (Account account : acc) {
            if(account.getUsername().equals(username)&&account.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
    
    
    public void addCustomer(Customer c){
        customers.add(c);        
    }
    public void addProduct(Product p){
        products.add(p);
    }
    public void addOrder(Order d){
        orders.add(d);
    }
    public Product getProductById(int id){
        for (Product product : products) {
            if(product.getId()==id){
                return product;
            }
        }
        return null;
    }
    public Order getOrderById(int id){
        for (Order order : orders) {
            if(order.getId()==id){
                return order;
            }
        }
        return null;
    }
    public ArrayList<Product> getProducts(){
        return products;
    }
}
