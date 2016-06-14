/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domain.bestelling;

import com.domain.photo.Photo;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author NesciO
 */
public class ShoppingCart {
    private Map<Photo, Integer> photos;
    private int price;

    public ShoppingCart() {
        photos = new HashMap<>();
        price = 0;
    }
    
    public int getPrice() {
        price = 0;
        for(Entry<Photo, Integer> e : photos.entrySet()) {
            price += e.getKey().getPrice() * e.getValue();
        }
        return price;
    }

    public Map<Photo, Integer> getPhotos() {
        return photos;
    }

    public void setPhotos(Map<Photo, Integer> photos) {
        this.photos = photos;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    
    
    
}
