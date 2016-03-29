/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domain.account;

import com.domain.foto.Foto;
import com.domain.foto.Groep;
import com.domain.bestelling.Product;
import com.domain.account.Account;
import java.util.ArrayList;

/**
 *
 * @author Laurens Adema
 */
public class Klant extends Account{
    private ArrayList<Product> winkelwagen;
    private ArrayList<Foto> mijnFotos;
    private ArrayList<Groep> mijnGroepen;
}
