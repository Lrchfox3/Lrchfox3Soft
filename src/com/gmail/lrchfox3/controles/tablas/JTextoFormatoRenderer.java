/*
 * JTextoFormatoRenderer.java
 *
 * Created on April 7, 2007, 1:55 AM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.gmail.lrchfox3.controles.tablas;

import javax.swing.JTable;
import java.awt.Component;
import java.text.NumberFormat;
import javax.swing.table.TableCellRenderer;
import com.gmail.lrchfox3.utilitarios.Propiedades;

/**
 *
 * @author Luis R. Chinchilla H.
 */ //textos.JTextoFormato 
public class JTextoFormatoRenderer extends javax.swing.table.DefaultTableCellRenderer {
    
    private com.gmail.lrchfox3.utilitarios.Numeros numeros = new com.gmail.lrchfox3.utilitarios.Numeros();
    private Propiedades propiedades = new Propiedades();
    
    /**
     * Creates a new instance of JTextoFormatoRenderer 
     */
    public JTextoFormatoRenderer() {
        super();
        setFont(propiedades.getFontTextos());
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 0));
        setHorizontalAlignment(this.RIGHT);             
        setOpaque(true);        
    }
    
     public Component getTableCellRendererComponent(JTable table,
            Object value, boolean isSelected, boolean hasFocus, int row, int column)   {     
         
         final NumberFormat nf = NumberFormat.getNumberInstance();         
         
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            super.setBackground(table.getSelectionBackground());
        }
        else {
            setForeground(table.getForeground());
            setBackground(table.getBackground());
        }
         
        String valor = "";        
        if (value != null && !value.equals("")) {
            Double d = Double.valueOf(value.toString());
            valor = nf.format(d);
        }      
            
        setText(valor);

       return this;
   }           
}
