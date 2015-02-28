/*
 * JTextoEditor.java
 *
 * Created on January 13, 2007, 6:52 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.gmail.lrchfox3.controles.tablas;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import java.util.EventObject;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.gmail.lrchfox3.utilitarios.Propiedades;

/**
 *
 * @author Luis R. Chinchilla H.
 */
public class JTextoEditor extends com.gmail.lrchfox3.controles.textos.JTextoBase implements TableCellEditor {
    
    protected Object valorOriginal = null;
    protected JTablaBase tabla =     null;
    protected ChangeEvent changeEvent = new ChangeEvent(this);  
    protected Propiedades propiedades = new Propiedades();
            
    public JTextoEditor() {
        super();        
        setBorder(null);
        setFont(propiedades.getFontTextos());         
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                stopCellEditing();
            }
        });
        
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                loseFocus(evt);
            }
        });
        
    }
           
    public void loseFocus(java.awt.event.FocusEvent evt) {
        copiarValorATabla();
    }
     
     public void copiarValorATabla() {        
        tabla = (JTablaBase)getParent();
        if (tabla != null) {
            String valor = getText().trim();
            if (valorOriginal != null || !valor.equals(""))
                tabla.setValorCelda(tabla.getEditingRow(), tabla.getEditingColumn(), valor);        
        }
    }
     
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        String valor = null;        
        
        valorOriginal = value;
        if (value != null)  valor = value.toString();
        setText(valor);

        return this;
    }
    
    public Object getCellEditorValue() {
        copiarValorATabla();                
        return getText();
    }        
    
    public boolean isCellEditable(EventObject event) {
        return true;
    }
    
    public boolean shouldSelectCell(EventObject event) {
        return true;
    }
    
    public boolean stopCellEditing() {
        //valorOriginal = null;
        fireEditingStopped();
        return true; 
    }
    
    public void cancelCellEditing() {
        fireEditingCanceled();
    }    
    
    /**
    * Fire editing stopped event to all listeners.
    */
    public void fireEditingStopped() {
        CellEditorListener listener;
        Object[] listeners = listenerList.getListenerList();
        
        for (int i = 0; i < listeners.length; i++) {
            if (listeners[i] == CellEditorListener.class) {
                listener = (CellEditorListener) listeners[i + 1];
                listener.editingStopped(changeEvent);
            }
        }
    }
  
    /**
    * Fire editing cancelled event to all listeners 
    */
    public void fireEditingCanceled() {
        CellEditorListener listener;
        Object[] listeners = listenerList.getListenerList();
        
        for (int i = 0; i < listeners.length; i++) {
            if (listeners[i] == CellEditorListener.class) {
                listener = (CellEditorListener) listeners[i + 1];
                listener.editingCanceled(changeEvent);
            }
        }
    }
  
    public void addCellEditorListener(CellEditorListener listener) {
        listenerList.add(CellEditorListener.class, listener);
    }
    
    public void removeCellEditorListener(CellEditorListener listener) {
        listenerList.remove(CellEditorListener.class, listener);
    }
 }