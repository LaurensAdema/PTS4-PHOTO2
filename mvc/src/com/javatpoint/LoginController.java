package com.javatpoint;

import com.database.Database;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author NesciO
 */
public class LoginController {

    public String getPassword(String username) {

        return Database.getDatabase().query("password", "user", "username", username);
    }
}
