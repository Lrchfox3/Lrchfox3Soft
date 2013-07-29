/*
 * JCheckBoxEditor.java
 *
 * Created on May 20, 2007, 2:47 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.gmail.lrchfox3.controles.tablas;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Luis R. Chinchilla H.
 */
public class JCheckBoxEditor extends com.gmail.lrchfox3.controles.JCheckBoxBase implements TableCellEditor {
    
    private static final JCheckBoxRenderer RENDERER = new JCheckBoxRenderer();  
    protected EventListenerList listenerList = new EventListenerList();
    protected ChangeEvent changeEvent = new ChangeEvent(this);      
    private boolean isEditable;
  
  public JCheckBoxEditor() {
    super();  
    
    addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        stopCellEditing();
      }
    });
  }      
      
  /**
   * Adds {@link CellEditorListener} to this renderer.
   * 
   * @param listener CellEditorListener
   */
  public void addCellEditorListener(CellEditorListener listener) {
    listenerList.add(CellEditorListener.class, listener);
  }
  
  /**
   * Removes {@link CellEditorListener} from this renderer.
   * 
   * @param listener CellEditorListener
   */
  public void removeCellEditorListener(CellEditorListener listener) {
    listenerList.remove(CellEditorListener.class, listener);
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

  /**
   * Cause that editing is cancelled.
   * 
   * @see javax.swing.CellEditor#cancelCellEditing()
   */
  public void cancelCellEditing() {
    fireEditingCanceled();
  }

  /**
   * Cause that editing is stopped
   * 
   * @see javax.swing.CellEditor#stopCellEditing()
   */
  public boolean stopCellEditing() {
    fireEditingStopped();
    return true;
  }
  
  public boolean isCellEditable(EventObject event) {
    return true;
  }
  
  public boolean shouldSelectCell(EventObject event) {
    return true;
  }

  /**
   * Returns selected item
   */
  public Object getCellEditorValue() {
    String v = getSeleccionado();
    JTablaBase tabla = (JTablaBase)getParent();
    tabla.setValorCelda(tabla.getEditingRow(), tabla.getEditingColumn(), v);
    
    return v;
  }
  
  /**
   * Returns editor's component
   * 
   * @param table JTable table that displays this editor
   * @param value Object value that is to be edited
   * @param isSelected boolean
   * @param row int
   * @param column int
   * @return Component
   */
  public Component getTableCellEditorComponent(JTable table,
                                               Object value, boolean isSelected,
                                               int row, int column) {   
    try {
        seleccionar(value);
    }
    catch (Exception ex) {
        new com.gmail.lrchfox3.utilitarios.Mensajes().mensajesErrores(null, ex.getMessage(), "Error");
    }
    return this;
  }
  
  /*
  public Component getTableCellRendererComponent(JTable table, Object value,
                                                 boolean isSelected,
                                                 boolean hasFocus, int row,
                                                 int column) {
            
    return RENDERER.getTableCellRendererComponent(table, value, isSelected, 
                                                 hasFocus, row, column);
  } 
   */
    
    
}
