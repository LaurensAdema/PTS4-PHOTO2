/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domain.photo;

import java.io.Serializable;

/**
 *
 * @author NesciO
 */
public class Photo implements Serializable{

    private String name;
    private int price;
    private String pathlowres;
    private String pathhighres;
    private int id;
    private int quantity;

    public Photo(String name, int price, int id, String pathlowres, String pathhighres) {
        this.name = name;
        this.price = price;
        this.id = id;
        this.pathlowres = pathlowres;
        this.pathhighres = pathhighres;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getPathlowres() {
        return pathlowres;
    }

    public String getPathhighres() {
        return pathhighres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Photo.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Photo other = (Photo) obj;
        if (id != other.getId()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + id;
        return hash;
    }
}
