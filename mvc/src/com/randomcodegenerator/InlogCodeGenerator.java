/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.randomcodegenerator;

import java.util.Random;

/**
 *
 * @author sinclair
 */
public class InlogCodeGenerator {
    
     public String GenerateCode() {
         
    char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
StringBuilder sb = new StringBuilder();
Random random = new Random();


for (int i = 0; i < 30; i++) {
    char c = chars[random.nextInt(chars.length)];
    sb.append(c);
}
String output = sb.toString();

    
return output;
     }

    
}
