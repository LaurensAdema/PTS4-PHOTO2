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
    private Map<Product, Integer> products;
    private int price;

    public ShoppingCart() {
        products = new HashMap<>();
        price = 0;
    }
    
    public int getPrice() {
        price = 0;
        for(Entry<Product, Integer> e : products.entrySet()) {
            price += e.getKey().getPrice() * e.getValue();
        }
        return price;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    
    
    
}
