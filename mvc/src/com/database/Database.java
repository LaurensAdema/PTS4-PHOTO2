package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
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

    private Map query;

    private enum QUERYTYPES {
        SELECT, FROM, WHERE, IS
    }

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
            if (conn != null)
                    {
                    if(!conn.isClosed())
                        
            {
                conn.close();
                System.out.println("Connection closed");
            }
            } else {
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String query(String selectField, String table, String whereField, String whereValue)
    {
        setQuery(selectField, table, whereField, whereValue);
        ResultSet results;

        try
        {
            if (conn == null || conn.isClosed())
            {
                openConnection();
            }
            Statement statement = conn.createStatement();

            if (conn == null || conn.isClosed()) {
                openConnection();
            }
            
            String SQLquery
                    = "SELECT " + query.get(QUERYTYPES.SELECT)
                    + " FROM " + query.get(QUERYTYPES.FROM)
                    + " WHERE " + query.get(QUERYTYPES.WHERE)
                    + " = " + query.get(QUERYTYPES.IS) + ";";
            results = statement.executeQuery(SQLquery);
            return results.getNString(1);

        } catch (SQLException ex)
        {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            closeConnection();
        }
        finally {
            closeConnection();
        }
        return null;
    }

    private void setQuery(String selectField, String table, String whereField, String whereValue)
    {
        query = new HashMap<String, String>();

        query.put(QUERYTYPES.SELECT, selectField);
        query.put(QUERYTYPES.FROM, table);
        query.put(QUERYTYPES.WHERE, whereField);
        query.put(QUERYTYPES.IS, whereValue);
    }
}
