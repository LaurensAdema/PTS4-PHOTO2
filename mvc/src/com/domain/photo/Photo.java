/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domain.photo;

/**
 *
 * @author NesciO
 */
public class Photo {
    
    private String name;
    private int price;
    private String pathlowres;
    private String pathhighres;

    public Photo(String name, int price, String pathlowres, String pathhighres)
    {
        this.name = name;
        this.price = price;
        this.pathlowres = pathlowres;
        this.pathhighres = pathhighres;
    }

    public String getName()
    {
        return name;
    }

    public int getPrice()
    {
        return price;
    }

    public String getPathlowres()
    {
        return pathlowres;
    }

    public String getPathhighres()
    {
        return pathhighres;
    }
    
    
    
}
