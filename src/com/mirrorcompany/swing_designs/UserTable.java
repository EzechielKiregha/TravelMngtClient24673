package com.mirrorcompany.swing_designs;

import com.mirrorcompany.swing_designs.shadow.ShadowRenderer;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author ekire
 */
public class UserTable extends JTable {

    BufferedImage imageShadow;
    
    public UserTable() {
        setRowHeight(40);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (isSelected) {
                    cell.setBackground(new Color(51, 153, 255)); // Set your desired color
                } else {
                    cell.setBackground(Color.white);
                }

                return cell;
            }
        });
        
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent ce) {
                createShadow();
            }
            
        });
    }
    
    private void createShadow(){
        int width = getWidth();
        int height = rowHeight;
        int space = 12;
        int margin = space / 2;
        imageShadow = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = imageShadow.createGraphics();
        g2.fillRect(0,0, width - margin, height - margin);
        g2.drawImage(new ShadowRenderer(6, 0.5f, new Color(160, 160, 160)).createShadow(imageShadow), -4, -4, null);
        g2.fillRect(margin, margin, width - space, height - space);
        g2.setComposite(AlphaComposite.Clear);
        g2.fillRect(margin, margin, width - space, height - space);
        g2.dispose();
    }

    @Override
    public Component prepareRenderer(TableCellRenderer tcr, int i, int i1) {
        switch (i1) {
            case 6:
            {
                TableCell_Status1 cell = new TableCell_Status1(getValueAt(i, 6).toString()); 
                return cell;
            }
            default:
            {
                String values = "";
                if (getValueAt(i, i1) != null) {
                    values = getValueAt(i, i1).toString();
                }
                TableCell cell = new TableCell(values);
                return cell;
            }
        }
    }
    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int space = 12;
        int margin = space / 2;
        for (int i = 0; i < getRowCount(); i++){
            int row = i;
            Rectangle r = getCellRect(row, 0, true);
            g2.setColor(new Color(255, 109, 109));
            g2.fillRect(margin, r.getLocation().y + margin, 3, rowHeight - space);
        }
        g2.dispose();
    }
    public void fixTable(JScrollPane scroll) {
        scroll.setBorder(null);
        scroll.getViewport().setBackground(new Color(250, 250, 250));
        ScrollBar sb = new ScrollBar();
        sb.setBackground(new Color(250, 250, 250));
        scroll.setVerticalScrollBar(sb);
        JPanel p = new JPanel();
        p.setBackground(new Color(250, 250, 250));
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
    }
    
}
