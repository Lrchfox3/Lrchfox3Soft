/*
 * JTextoRenderer.java
 *
 * Created on January 13, 2007, 6:51 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.gmail.lrchfox3.controles.tablas;

import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.table.TableCellRenderer;
//
import com.gmail.lrchfox3.utilitarios.Propiedades;

/**
 *
 * @author Luis R. Chinchilla H.
 */
//extends javax.swing.table.DefaultTableCellRenderer
public class JTextoRenderer extends JTextField implements TableCellRenderer { 
    
    private Propiedades propiedades = new Propiedades();        
    
    public JTextoRenderer() {
        super();
        setBorder(null);
        setFont(propiedades.getFontTextos());
    }
    
   public java.awt.Component getTableCellRendererComponent(JTable table,
            Object value, boolean isSelected, boolean hasFocus, int row, int column)
   {     
       return this;       
        //return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
   }
 } 