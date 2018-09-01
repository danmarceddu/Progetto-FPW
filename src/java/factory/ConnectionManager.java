package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionManager {
    
    private static ConnectionManager singleton;
    
    private ConnectionManager(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch(ClassNotFoundException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ConnectionManager getInstance(){
        //Non ancora istanziato
        if(singleton == null)
        {
            singleton =  new ConnectionManager();
        }
        return singleton;
    }
    
    public static Connection getConnection(){
        Connection conn = null;
        String str_conn = "jdbc:mysql://ec2-52-47-198-123.eu-west-3.compute.amazonaws.com:443/fpw18_marceddudaniele?zeroDateTimeBehavior=convertToNull";
        try
        {
            conn = DriverManager.getConnection(str_conn, "fpw18_marceddudaniele", "danmarceddu");
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
         return conn;
    }
}