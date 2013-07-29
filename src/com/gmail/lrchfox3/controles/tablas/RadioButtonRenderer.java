/*
 * RadioButtonRenderer.java
 *
 * Created on January 6, 2007, 2:02 AM
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
public class RadioButtonRenderer implements javax.swing.table.TableCellRenderer {  
    
    public Component getTableCellRendererComponent( JTable table, Object value, boolean isSelected, 
                                                    boolean hasFocus, int row, int column ) {   
        if (value == null) return null;
        return (Component)value;  
    }
}