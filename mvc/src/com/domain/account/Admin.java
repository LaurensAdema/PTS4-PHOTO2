/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domain.account;

import com.domain.account.Account;

/**
 *
 * @author Laurens Adema
 */
public class Admin extends Account {
    public Admin(int id, String username, String first_name, String last_name, String postal_code, String nr, String email)
    {
        this.id = id;
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.postal_code = postal_code;
        this.nr = nr;
        this.email = email;
    }
}
