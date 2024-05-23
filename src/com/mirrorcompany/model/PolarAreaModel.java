package com.mirrorcompany.model;

import java.awt.Color;
/**
 *
 * @author ekire
 */
public class PolarAreaModel {
   
    Color color;
    private String name;
    private double value;

    public PolarAreaModel() {
    }
    
    public PolarAreaModel(Color color, String name, double value) {
        this.color = color;
        this.name = name;
        this.value = value;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    
    
}
