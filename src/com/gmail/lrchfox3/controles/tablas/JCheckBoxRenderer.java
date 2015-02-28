/*
 * JCheckBoxRenderer.java
 *
 * Created on May 20, 2007, 3:13 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.gmail.lrchfox3.controles.tablas;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Luis R. Chinchilla H.
 */
public class JCheckBoxRenderer extends com.gmail.lrchfox3.controles.JCheckBoxBase implements TableCellRenderer {
    
    /** Creates a new instance of JCheckBoxRenderer */
    public JCheckBoxRenderer() {
        super();
    }
    
    public Component getTableCellRendererComponent(JTable table,
          Object value, boolean isSelected, boolean hasFocus,
          int row, int column) {
        
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            super.setBackground(table.getSelectionBackground());
        }
        else {
            setForeground(table.getForeground());
            setBackground(table.getBackground());
        }
        
        try {
            seleccionar(value);
        }
        catch (Exception ex) {
            new com.gmail.lrchfox3.utilitarios.Mensajes().mensajesErrores(null, ex.getMessage(), "Error");
        }
        return this;
    }
}
