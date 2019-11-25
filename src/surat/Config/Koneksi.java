/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surat.Config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

/**
 *
 * @author riefist
 */
public class Koneksi {
    private static Connection ConnectionDB = null;
   
   public static Connection getConnection() {
       return ConnectionDB;
   }
   
   public Koneksi() {
       try {
           String db = "pbo_surat";
           String url = "jdbc:mysql://localhost:3306/" + db + "?zeroDateTimeBehavior=convertToNull";
           String user = "root";
           String pass = "";
           
           if (ConnectionDB == null) {
               ConnectionDB = DriverManager.getConnection(url, user, pass);
           }
           
       } catch (SQLException e) {
           System.out.println(e.getMessage());
       }
   }
}
