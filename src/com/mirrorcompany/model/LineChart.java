package com.mirrorcompany.model;

import java.awt.Color;

/**
 *
 * @author ekire
 */
public class LineChart {
    
    private String name;
    private double value;

    public LineChart(String name, double value) {
        this.name = name;
        this.value = value;
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

    public LineChart() {
    }
    
}
