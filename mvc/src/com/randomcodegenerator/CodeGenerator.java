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
public abstract class CodeGenerator {

    public static String generateAlphanumericCode(int size)
    {
        return generateCode(size, "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray());
    }
    
    public static String generateNumericCode(int size)
    {
            return generateCode(size, "1234567890".toCharArray());
    }
    
    public static String generateCode (int size, char[] chars)
    {
        String output = "";
        if (size > 0)
        {
            StringBuilder sb = new StringBuilder();
            Random random = new Random();

            for (int i = 0; i < size; i++)
            {
                char c = chars[random.nextInt(chars.length)];
                sb.append(c);
            }

            output = sb.toString();
        }
        return output;
    }
}
