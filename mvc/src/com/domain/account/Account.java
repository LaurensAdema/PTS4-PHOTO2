/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domain.account;

import com.database.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.UUID;
/**
 *
 * @author Laurens Adema
 */
public class Account {

    private String naam;
    private String password;

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean validate() {

        try {
            ResultSet rs = Database.getDatabase().query("SELECT password FROM account WHERE username = " + this.naam, Database.QUERY.QUERY);

            while (rs.next()) {
                String foundpassword = rs.getString(1);
                if (password.equals(foundpassword)) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        Database.getDatabase().closeConnection();
        return false;
    }

}
