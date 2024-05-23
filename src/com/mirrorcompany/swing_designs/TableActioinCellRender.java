

package com.mirrorcompany.swing_designs;

import com.mirrorcompany.component.DeviceListPanelAction;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author ekire
 */
public class TableActioinCellRender extends DefaultTableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean isSelected, boolean bln1, int row, int column) {
        Component com =  super.getTableCellRendererComponent(jtable, o, isSelected, bln1, row, column); //To change body of generated methods, choose Tools | Templates.
        
        DeviceListPanelAction action = new DeviceListPanelAction();
        if (isSelected == false && row % 2 == 0){
            action.setBackground(Color.BLUE);
        } else {
            action.setBackground(com.getBackground());
        }
        action.setBackground(com.getBackground());
        return action;
    }
    
    
    
}
