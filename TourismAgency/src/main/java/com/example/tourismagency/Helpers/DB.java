package com.example.tourismagency.Helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

    private DB(){}

    public static Connection getInstance(){
        try {
            return DriverManager.getConnection(Constant.DB_URL, Constant.DB_USER, Constant.DB_PASS);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}