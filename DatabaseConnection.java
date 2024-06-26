
//package com.keyin.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    
    private static final String url = "jdbc:postgresql://localhost:2705/HealthMonitoringSystem";
    private static final String user = "postgres";
    private static final String password = "Malachiw1!";

    /**
     * retrieves a connection to the database.
     *
     * @return         	the database connection
     */
    public static Connection getCon(){
        Connection connection = null;
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException  e) {
            e.printStackTrace();
        }
        return connection;
    }


}
