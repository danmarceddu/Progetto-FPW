package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    static Connection con;
    static String url;

    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url1 = "jdbc:mysql://ec2-52-47-198-123.eu-west-3.compute.amazonaws.com:443/fpw18_marceddudaniele";
            con = DriverManager.getConnection(url1, "fpw18_marceddudaniele", "danmarceddu");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return con;
    }
}
