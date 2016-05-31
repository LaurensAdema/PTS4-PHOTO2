/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shoppingcart;

import com.domain.photo.Photo;
import java.util.List;

/**
 *
 * @author markb
 */
public class ShoppingCart {
    
        private List<Photo> shoppingcartitems;

    /**
     * Get the value of shoppingcartitems
     *
     * @return the value of shoppingcartitems
     */
    public List<Photo> getShoppingcartitems() {
        return shoppingcartitems;
    }

    /**
     * Set the value of shoppingcartitems
     *
     * @param shoppingcartitems new value of shoppingcartitems
     */
    public void setShoppingcartitems(List<Photo> shoppingcartitems) {
        this.shoppingcartitems = shoppingcartitems;
    }

    private int NumberOfItems;

    /**
     * Get the value of NumberOfItems
     *
     * @return the value of NumberOfItems
     */
    public int getNumberOfItems() {
//        numberOfItems = 0;
//
//        for (ShoppingCartItem scItem : items) {
//
//            numberOfItems += scItem.getQuantity();
//        }

        return 0;
    }

    /**
     * Set the value of NumberOfItems
     *
     * @param NumberOfItems new value of NumberOfItems
     */
    public void setNumberOfItems(int NumberOfItems) {
        this.NumberOfItems = NumberOfItems;
    }

    private double total;

    /**
     * Get the value of total
     *
     * @return the value of total
     */
    public double getTotal() {
        return total;
    }

    /**
     * Set the value of total
     *
     * @param total new value of total
     */
    public void setTotal(double total) {
        this.total = total;
    }

}
