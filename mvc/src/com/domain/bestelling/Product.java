/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domain.bestelling;

import com.domain.photo.Photo;

/**
 *
 * @author NesciO
 */
public class Product {

    private String name;
    private String material;
    private String size;
    private String color;

    private Photo photo;
    private int price;
    private int id;

    public Product(int id, String material, String color, String size, Photo photo, int price) {
        this.material = material;
        this.size = size;
        this.photo = photo;
        this.price = price;
        this.id = id;
        this.color = color;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
