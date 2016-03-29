package com.javatpoint;

import java.util.HashMap;
import java.util.Map;

public class LoginBean {

    private String name, password;
    LoginController controller;

    public LoginBean() {
        controller = new LoginController();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean validate() {
        if (password.equals(controller.getPassword(this.name))) {
            return true;
        } else {
            return false;
        }
    }
}
