/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domain.bestelling;

import com.domain.account.Customer;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 *
 * @author Laurens Adema
 */
public class Bestelling {
    private ArrayList<Product> producten;
    private double prijs;
    private GregorianCalendar datum;
    private Betaalmethode betaalmethode;
    private Customer klant;
}
