/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordersystem;

import dao.DBContext;
import file.FileAccount;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;
import model.Account;
import model.Customer;
import model.Order;
import model.OrderDetail;
import model.Product;

/**
 *
 * @author Long Nguyen
 */
public class Ordersystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        
        
        DBContext db = new DBContext();
        Scanner sc = new Scanner(System.in);
        System.out.println("-----Login INFO-----");
         while(true) {
             System.out.print("Enter username: ");
             String username = sc.nextLine();
             System.out.print("Enter password: ");
             String password = sc.nextLine();
             if(db.login(username, password)){
                 System.out.println("Login sucessfully!!");
                 break;
             }
             System.out.println("Login again");
        }
        String option = null;
       
        
        
        do {
            displayMenu();
            System.out.print("Enter option: ");
            option = sc.nextLine();
            
            switch (option) {
                case "1":
                    addProduct(db);
                    break;
                case "2":
                    addOrder(db);
                    break;
                case "3":
                    displayOrder(db);
                    break;
                case "4":
                    sortPriceProduct(db);
                    break;

            }
        } while (!option.equals("0"));
        System.out.println("Bye bye!");
    }
    static void sortPriceProduct(DBContext db){
        Collections.sort(db.getProducts());
        DisplayProduct(db);
    }
    static void addProduct(DBContext db) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter ID: ");
        int id;
        float price;
        String name;

        while (true) {
            try {
                id = Integer.parseInt(sc.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Please re-enter ID: ");
            }
        }

        System.out.print("Enter name: ");
        name = sc.nextLine();
        System.out.print("Enter price: ");
        while (true) {
            try {
                price = Float.parseFloat(sc.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Please re-enter price: ");
            }
        }
        db.addProduct(new Product(id, name, price));
        System.out.println("");
        DisplayProduct(db);
//        System.out.println("Enter dob: ");

    }

    static void addOrder(DBContext db) {
        FileAccount fa = new FileAccount();
        Scanner sc = new Scanner(System.in);
        System.out.println("Customer Info:");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter customer ID: ");
        int cid = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter customer name: ");
        String cname = scanner.nextLine();
        System.out.print("Enter DOB: ");
        String date = sc.nextLine();
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        Date dob = null;
        try {
            dob = DateFor.parse(date);
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        System.out.print("Enter customer phone: ");
        String phone = sc.nextLine();
        System.out.println("Choose customer gender: ");
        boolean gender = true;
        String option = null;
        do {


            System.out.println("1.MALE");
            System.out.println("2.FEMALE");
            System.out.print("Choose 1 or 2: ");
            option = sc.nextLine();
            switch (option) {
                case "1":
                    gender = true;
                    break;
                    
                    
                case "2":
                    gender = false;
                    break;
               
            }
            
        } while (option.equals("1")&&option.equals("2"));

        Customer c = new Customer(cid, cname, dob, phone, gender);
        db.addCustomer(c);
       
        
        System.out.println("-----OrderINFO-----");
        System.out.print("Order Id: ");
        int oid = Integer.parseInt(sc.nextLine());
        System.out.print("Payment Method: ");
        String payment = sc.nextLine();
        String control = null;
        Order o = new Order(cid, c, payment);
        
        System.out.println("");
        System.out.println("Choose product in list:");
        DisplayProduct(db);
        
        do{
            System.out.print("Product ID: ");
            int pID = Integer.parseInt(sc.nextLine());
            Product p = db.getProductById(pID);
            System.out.print("Quantity: ");
            int quantity = Integer.parseInt(sc.nextLine());
            float price = p.getPrice();
            OrderDetail od = new OrderDetail(o, p, quantity);
            o.getOrderDetail().add(od);
            db.addOrder(o);
            
            System.out.println("Enter 0 to stop buying...");
            control = scanner.nextLine();
        }while(!control.equals("0"));
        

        fa.writrFile("C:\\Users\\Long Nguyen\\Desktop\\order.txt", o);
        
    }
        static void displayOrder(DBContext db) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("order Id:");
        int oid = Integer.parseInt(scanner.nextLine());
        Order order = db.getOrderById(oid);
        System.out.println("Order Nubmer: " + order.getId());
        System.out.println("Customer: " + order.getCustomer().getName());
        System.out.println("Payment Method: " + order.getPayment());
        for (OrderDetail detail : order.getOrderDetail()) {
            System.out.println(
                    detail.getProduct().getName() + "\t"
                    + detail.getQuantity() + "\t"
                    + detail.getProduct().getPrice()
            );
        }
        System.out.println("Total: " + order.getTotal());
    }

    static void DisplayProduct(DBContext db) {
        Scanner sc = new Scanner(System.in);
        System.out.println("List Product: ");
        System.out.println("ID Name Price");
        for (Product product : db.getProducts()) {
            System.out.println(product.getId() + "  " + product.getName() + "  " + product.getPrice());
        }
        System.out.println("");

    }
    static void abc(){
        
    }

    static void displayMenu() {
        System.out.println("Please choose option from 0-3");
        System.out.println("1. Add Product ");
        System.out.println("2. Add Order ");
        System.out.println("3. Find Order ");
        System.out.println("4. Sort Product By Price ");
        System.out.println("0. Exit");
    }
}
