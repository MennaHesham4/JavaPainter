
package com.mycompany.javapainter;

import java.awt.Color;
import java.awt.Stroke;


public class Line 
{
    private int xStart;
    private int yStart;
    private int xEnd;
    private int yEnd;
    private Color color;
    private Stroke stroke;

        
    public Line(int xStart , int yStart, int xEnd , int yEnd, Color color, Stroke stroke)
    {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        this.color = color;
        this.stroke = stroke;
    }

    public int getxStart() {
        return xStart;
    }

    public int getyStart() {
        return yStart;
    }

    public int getxEnd() {
        return xEnd;
    }

    public int getyEnd() {
        return yEnd;
    }
    
    public Color getColor() {
        return color;
    }

    public Stroke getStroke() {
        return stroke;
    }

    
}
