/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.editphoto;

import java.awt.image.BufferedImage;

/**
 *
 * @author sinclair
 */
public class ToSepia {
    public BufferedImage toSepia(BufferedImage image, int sepiaIntensity) {

    int width = image.getWidth();
    int height = image.getHeight();
    int sepiaDepth = 20;

    int[] imagePixels = image.getRGB(0, 0, width, height, null, 0, width);

    for (int i = 0; i < imagePixels.length; i++) {
        int color = imagePixels[i];

        int r = (color >> 8) & 0xff;
        int g = (color >> 8) & 0xff;
        int b = (color) & 0xff;
        int gry = (r + g + b) / 3;

        r = g = b = gry;
        r = r + (sepiaDepth * 2);
        g = g + sepiaDepth;

        if (r > 255) {
            r = 255;
        }
        if (g > 255) {
            g = 255;
        }
        if (b > 255) {
            b = 255;
        }

        // Darken blue color to increase sepia effect
        b -= sepiaIntensity;

        // normalize if out of bounds
        if (b < 0) {
            b = 0;
        }
        if (b > 255) {
            b = 255;
        }

        imagePixels[i] = (color & 0xff000000) + (r << 16) + (g << 8) + b;
    }

    BufferedImage res = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    res.setRGB(0, 0, width, height, imagePixels, 0, width);
    return res;
}
}
