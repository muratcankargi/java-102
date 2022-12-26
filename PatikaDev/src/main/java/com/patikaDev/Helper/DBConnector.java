package com.patikaDev.Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private Connection connection=null;

    private Connection ConnectDB(){
        try {
            this.connection= DriverManager.getConnection(Config.DB_URL,Config.DB_USERNAME,Config.DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.connection;
    }

    public static  Connection getInstance(){
        DBConnector db= new DBConnector();
        return db.ConnectDB();
    }
}
