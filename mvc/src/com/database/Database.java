package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author soufyan
 */
public class Database {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://www.ict4events.nl:3306/photoshop";

    //  Database credentials
    static final String USER = "photoshop";
    static final String PASS = "pts4";

    private static Database database;

    public static Database getDatabase()
    {
        if (database == null)
        {
            database = new Database();
        }
        return database;
    }

    Connection conn = null;

    public void openConnection()
    {
        try
        {
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e)
            {
                System.out.println("Where is your MySQL JDBC Driver?");
            }
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connection Successful");
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
            System.out.println("Connection Failed!");
        }
    }

    public ResultSet executeUnsafeQuery(String sql) throws SQLException
    {
        //Execute a query
        Statement statement;
        ResultSet resultSet = null;
        try
        {
            if (conn == null || conn.isClosed())
            {
                openConnection();
            }
            statement = conn.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return resultSet;
    }

    public void closeConnection()
    {
        try
        {
            if (conn != null || !conn.isClosed())
            {
                conn.close();
                System.out.println("Connection closed");
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
