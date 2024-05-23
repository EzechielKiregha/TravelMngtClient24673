/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mirrorcompany.swing_designs;

import com.mirrorcompany.component.DeviceListPanelAction;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author ekire
 */
public class TableActionCellEditor extends DefaultCellEditor {

    private TableActionEvent event;
    
    public TableActionCellEditor(TableActionEvent event) {
        super(new JComboBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {

        DeviceListPanelAction action = new DeviceListPanelAction();
        action.initEvent(event, row);
        action.setBackground(jtable.getSelectionBackground());
        return action;
    }
    
}
