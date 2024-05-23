
package com.mirrorcompany.charts;

import com.mirrorcompany.model.PolarAreaModel;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 *
 * @author ekire
 */
public class PolarArea extends javax.swing.JComponent {
 
    private final List<PolarAreaModel> list = new ArrayList<>();
    private double maxValue;
    private double totalValue;
    private final int PADDING_BOTTOM = 30;
    
    private final Animator animator;
    private float animate;
    
    
    public PolarArea() {
        initComponents();
        setBackground(Color.WHITE);
        setForeground(Color.WHITE);
        
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                animate = fraction;
                repaint();
            }
        };
        animator = new Animator(800, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
    }

    @Override
    public void paint(Graphics grphcs) {
        if (isOpaque()){
            grphcs.setColor(getBackground());
            grphcs.fillRect(0, 0, getWidth(), getHeight());
            
        }
        createChart(grphcs);
        super.paint(grphcs); 
    }
    
    private void drawValues(Graphics2D g2, String values, double angle, double rs) {
        int centerx = getWidth() / 2;
        int centerY = (getHeight() - PADDING_BOTTOM) / 2;
        Point p = getLocation(angle, rs);
        g2.setColor(getForeground());
        g2.setFont(getFont());
        FontMetrics ft = g2.getFontMetrics();
        Rectangle2D r2 = ft.getStringBounds(values, g2);
        double x = (centerx + p.x) - (r2.getWidth() / 2);
        double y = (centerY - p.y) + (ft.getAscent() / 2);
        g2.drawString(values, (int) x, (int) y);
    }

    public void createChart(Graphics grphcs){
        int width = getWidth();
        int height = getHeight() - PADDING_BOTTOM;
        int space = 6;
        int size = Math.min(width, height)- space;
        int x = (width - size) / 2;
        int y = (height - size) / 2;
        if (width <= 0){
            width = 1;
        }
        if (height <= 0){
            height = 1;
        }
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (!list.isEmpty()){
            if (!list.isEmpty()) {
            DecimalFormat df = new DecimalFormat("#,##0.##");
            double startAngle = 90;
            for (PolarAreaModel data : list) {
                g2.setColor(data.getColor());
                double angle = valueToAngle(data.getValue());
                double rs = valueToRealSize(data.getValue(), size) * animate;
                Shape s = createShape(startAngle, angle, rs);
                g2.fill(s);
                g2.setComposite(AlphaComposite.Clear);
                g2.setStroke(new BasicStroke(2f));
                g2.draw(s);
                g2.setComposite(AlphaComposite.SrcOver);
                startAngle += angle;
                drawValues(g2, df.format(data.getValue()), startAngle - angle / 2, rs / 4);
            }

        } else {
            g2.setColor(new Color(200, 200, 200));
            g2.drawOval(x, y, size, size);
        }
        g2.dispose();
        grphcs.drawImage(img, 0,0, null);
    }
    }
    private Shape createShape(double start, double end, double values){
        int width = getWidth();
        int height = getHeight() - PADDING_BOTTOM;
        double x = (width - values) / 2;
        double y = (height - values) / 2;
        
        Shape shape = new Arc2D.Double(x, y, values, values, start, end, Arc2D.PIE);
        return shape;
    }
    
    private double valueToRealSize(double value, int size){
        double n = value * 100 / maxValue;
        return n * size / 100;
    }
    
    private double valueToAngle(double values){
        double n = values * 100 / totalValue;
        return n * 360 / 100;
    }
    
    private Point getLocation(double angle, double rs) {
        double x = Math.cos(Math.toRadians(angle)) * rs;
        double y = Math.sin(Math.toRadians(angle)) * rs;
        return new Point((int) x, (int) y);
    }
    public void addItem(PolarAreaModel data){
        list.add(data);
        calculateValue(data);
        repaint();
    }

    public void start() {
        if (!animator.isRunning()) {
            animator.start();
        }
    }

    public void clear() {
        animate = 0;
        list.clear();
        removeAll();
        repaint();
    }
    
    public void calculateValue(PolarAreaModel data){
        maxValue = Math.max(maxValue,data.getValue());
        totalValue = 0;
        for (PolarAreaModel p : list){
            totalValue += p.getValue();
        }
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 330, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
