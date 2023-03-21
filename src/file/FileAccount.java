/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Order;

/**
 *
 * @author Long Nguyen
 */
public class FileAccount {
    public static String abc;
    public ArrayList<Account> readfile(String path) {
        ArrayList<Account> listAcc = new ArrayList<Account>();
        FileReader reader = null;

        File file = new File(path);
        try {
            reader = new FileReader(file);
            BufferedReader buffer = new BufferedReader(reader);
            String line;

            line = buffer.readLine();
            while (line != null) {
                String[] arr = line.split(";");
                String username = arr[0];
                String password = arr[1];
                Account acc = new Account(username, password);
                listAcc.add(acc);
                line = buffer.readLine();
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileAccount.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileAccount.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(FileAccount.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return listAcc;
        
    }

    public void writrFile(String fileName, Order order) {

    FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(fileName);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.printf("Order ID: %d; Customer ID: %d; TotalPrice: %f\n", order.getId(),order.getCustomer().getId(),order.getTotal());
            printWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(FileAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    
    
//        BufferedWriter bw = null;
//        FileWriter fw = null;
//
//        File file = new File(fileName);
//
//        try {
//            if (!file.exists()) {
//                file.createNewFile();
//                fw = new FileWriter(file.getAbsoluteFile(), true);
//                bw = new BufferedWriter(fw);
//
//                bw.write("Order ID: " + order.getId() + "; CustomerID: " + order.getCustomer().getId() + "; TotalPrice: " + order.getTotal());
//                System.out.println("Write file order done!!");
//            }
//        } catch (IOException ex) {
//            Logger.getLogger(FileAccount.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//
//            try {
//
//                if (bw != null) {
//                    bw.close();
//                }
//
//                if (fw != null) {
//                    fw.close();
//                }
//
//            } catch (IOException ex) {
//
//                ex.printStackTrace();
//
//            }
//        }

}
