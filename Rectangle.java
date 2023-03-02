
package com.mycompany.javapainter;

import java.awt.Color;
import java.awt.Stroke;


public class Rectangle 
{
    private int x;
    private int y;
    private int width;
    private int hight;
    private Color color;
    private Stroke stroke;
    private boolean isFilled;

        
    public Rectangle(int x , int y, int width , int hight, Color color, Stroke stroke, boolean isFilled)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.hight = hight;
        this.color = color;
        this.stroke = stroke;
        this.isFilled = isFilled;
    }

    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHight() {
        return hight;
    }

    public Color getColor() {
        return color;
    }

    public Stroke getStroke() {
        return stroke;
    }

    public boolean isIsFilled() {
        return isFilled;
    }
  
    
}
