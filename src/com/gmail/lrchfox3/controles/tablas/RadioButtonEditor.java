/*
 * RadioButtonEditor.java
 *
 * Created on January 6, 2007, 2:05 AM
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
public class RadioButtonEditor extends javax.swing.DefaultCellEditor implements java.awt.event.ItemListener {  
        
    public RadioButtonEditor(javax.swing.JCheckBox checkBox, boolean radioButton) {    
        super(checkBox);  
        this.radioButton = radioButton;
    }   
    
    public Component getTableCellEditorComponent(JTable table, Object value,  boolean isSelected, int row, int column) {    
        if (value == null) return null;  

        if (radioButton) {
            button1 = (javax.swing.JRadioButton)value;    
            button1.addItemListener(this);    
        }
        else {
            button2 = (javax.swing.JCheckBox)value;    
            button2.addItemListener(this);
        }
        return (Component)value;  
    }   
    
    public Object getCellEditorValue() { 
        if (radioButton) {
            button1.removeItemListener(this);    
            return button1;  
        }
        else {
            button2.removeItemListener(this);    
            return button2;  
        }        
    }   
    
    public void itemStateChanged(java.awt.event.ItemEvent e) {    
        super.fireEditingStopped();  
    }
    
    private javax.swing.JRadioButton button1;  
    private javax.swing.JCheckBox  button2;  
    private boolean radioButton;
}