/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Alessandro Pilosu
 */
public class ConnectionManager {

    static Connection con;
    static String url;

    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //String url = "jdbc:mysql://HOST/DATABASE";
            //con = DriverManager.getConnection(url, "username", "password");
            String url = "jdbc:mysql://localhost/FastPressWriter";
            con = DriverManager.getConnection(url, "serverlocale", "DanieleMarceddu");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return con;
    }
}
