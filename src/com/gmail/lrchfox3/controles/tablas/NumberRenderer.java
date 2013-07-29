/*
 * NumberRenderer.java
 *
 * Created on January 13, 2007, 6:48 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.gmail.lrchfox3.controles.tablas;

import javax.swing.JTable;
import java.awt.Component;

/**
 *
 * @author Luis R. Chinchilla H.
 */
public class NumberRenderer extends javax.swing.table.DefaultTableCellRenderer
 {
    public NumberRenderer()
    {        
        setHorizontalAlignment(javax.swing.JLabel.RIGHT);
        setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
    }
    
   public Component getTableCellRendererComponent(JTable table,
            Object value, boolean isSelected, boolean hasFocus, int row, int column)
   {      
     return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
   }
 }