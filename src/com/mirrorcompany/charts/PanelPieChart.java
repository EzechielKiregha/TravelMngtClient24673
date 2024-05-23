package com.mirrorcompany.charts;

import com.mirrorcompany.model.PieChart;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.awt.Shape;
import java.awt.geom.Arc2D;

/**
 *
 * @author ekire
 */
public class PanelPieChart extends javax.swing.JPanel {
    
    private List<PieChart> list;
    
    
    private float chartSize = 0.5f;
 
    public PanelPieChart() {
        list = new ArrayList<>();
        initComponents();
        setOpaque(false);
        setBackground(new Color(255, 255, 255));
        dateTest();
    }
    
    public void dateTest(){
        list.add(new PieChart("Monday", 10, new Color(4, 174, 243)));
        list.add(new PieChart("Tuesday", 150, new Color(215, 39, 250)));
        list.add(new PieChart("Wednesday", 80, new Color(44, 88, 236)));
        list.add(new PieChart("Thursday", 100, new Color(21, 202, 87)));
        list.add(new PieChart("Friday", 125, new Color(127, 63, 255)));
        list.add(new PieChart("Saturday", 80, new Color(238, 167, 35)));
        list.add(new PieChart("Sunday", 200, new Color(245, 79, 99)));
    }
    
    public double getTotal(){
        double total = 0;
        for (PieChart pc : list){
            total += pc.getValue();
        }
        return total;
    }
    
    
    public void addItem(PieChart data){
        list.add(data);
        repaint();
    }
    
    public void removeAllItem(){
        list.clear();
        repaint();  
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        int width = getWidth();
        int height = getHeight();
        int size = Math.min(width, height);
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        double x = (width - size) / 2;
        double y = (height - size) / 2;
        Shape area = new Ellipse2D.Double(x, y, size, size);
        g2.setColor(getBackground());
        g2.fill(area);
        
        double total = getTotal();
        double curvalue = 0;
        
        for(PieChart data : list){
            double startAngle = (curvalue * 360f / total) + 90;
            double angle = (data.getValue() * 360f / total);
            g2.setColor(data.getColor());
            Shape shape = new Arc2D.Double(x, y, size, size, startAngle, angle, Arc2D.PIE);
            g2.fill(shape);
            curvalue += data.getValue();
        }
        
        double inSize = size * chartSize;
        double xl = (width - inSize) / 2;
        double yl = (height - inSize) / 2;
        Shape cut = new Ellipse2D.Double(xl, yl, inSize, inSize);
        g2.setComposite(AlphaComposite.Clear);
        g2.fill(cut);
        grphcs.drawImage(img, 0, 0, null);
        super.paintComponent(grphcs); 
    }
    
    
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 253, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 152, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
