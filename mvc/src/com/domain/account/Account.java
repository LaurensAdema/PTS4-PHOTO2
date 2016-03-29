/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domain.account;

import com.database.Database;

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
        if (password.equals(Database.getDatabase().query("password", "user", "username", this.naam))) {
            return true;
        } else {
            return false;
        }
    }
}
