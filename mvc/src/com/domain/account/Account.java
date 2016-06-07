/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domain.account;

import com.database.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Laurens Adema
 */
abstract public class Account {
    protected int id;
    protected String username;
    protected String first_name;
    protected String last_name;
    protected String postal_code;
    protected String nr;
    protected String email;

    public int getID()
    {
        return id;
    }

    public String getUsername()
    {
        return username;
    }

    public String getFirst_name()
    {
        return first_name;
    }

    public String getLast_name()
    {
        return last_name;
    }

    public String getPostal_code()
    {
        return postal_code;
    }

    public String getNr()
    {
        return nr;
    }

    public String getEmail()
    {
        return email;
    }

    public String getType()
    {
        //return this.getClass().toString();
        return this.getClass().getSimpleName();
    }
}
