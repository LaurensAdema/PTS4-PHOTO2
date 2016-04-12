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
public class Account {

    private String naam;

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public boolean validate(String password) {

        try {
            ResultSet rs = Database.getDatabase().query("SELECT * FROM account WHERE username = " + this.naam, Database.QUERY.QUERY);

            while (rs.next()) {
                String foundpassword = rs.getString("password");
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
