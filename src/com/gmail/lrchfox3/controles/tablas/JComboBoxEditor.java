/*
 * JComboBoxEditor.java
 *
 * Created on February 7, 2007, 6:52 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.gmail.lrchfox3.controles.tablas;


 /** $Id: ComboBoxTableEditor.java,v 1.1.1.1 2005/04/07 18:36:18 pocho Exp $  
 */
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.EventObject;
import java.util.IdentityHashMap;
import java.util.List;

import java.sql.*;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.gmail.lrchfox3.controles.tablas.JComboBoxRenderer;
import com.gmail.lrchfox3.utilitarios.Item;
import com.gmail.lrchfox3.utilitarios.UnmodifiableVectorAdapter;

/**
 * <p>
 *  <code>ComboBoxTableEditor</code> is editor for table cells that needs to
 *  choose their new value from a list of possible values (JComboBox)
 * </p>
 * 
 * @version $Name:  $ - $Revision: 1.1.1.1 $ - $Date: 2005/04/07 18:36:18 $
 * @see TableCellEditor
 */
public class JComboBoxEditor extends com.gmail.lrchfox3.controles.listas.JComboBoxBase implements TableCellEditor, TableCellRenderer {
 
  private static final JComboBoxRenderer RENDERER = new JComboBoxRenderer();
  
  protected EventListenerList listenerList = new EventListenerList();
  protected ChangeEvent changeEvent = new ChangeEvent(this);
  
  private UnmodifiableVectorAdapter items;
  
  private ComboBoxModel model;
  private boolean isEditable;
  
  public JComboBoxEditor() {
    super();
    items = new UnmodifiableVectorAdapter(Collections.EMPTY_LIST);
    model = new DefaultComboBoxModel(items);
    setEditable(false);
    
    addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        stopCellEditing();
      }
    });
  }
  
  public void setItems(List items) {
    this.items.setAdaptedList(items);
  }
  
  /**
     * Lee los registros de una tabla de la Base de Datos y alimenta la lista
     * con los campos de estos.
     *
     * @param sql Sentencia sql que contiene la informacion a cargar en la lista.
     *
     * @return numero de registros cargados a la Lista.
     */
    public int cargarLista(String sql) throws SQLException, Exception {
        java.util.List  lista = cargarVector(sql);
        setItems(lista);
        
        return lista.size();
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
    JTablaBase tabla = (JTablaBase)getParent();
    tabla.setValorCelda(tabla.getEditingRow(), tabla.getEditingColumn(), getSelectedItem());        
    return getSelectedItem();
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
    setModel(model);
    setEditable(isEditable());
    setForeground(table.getForeground());
    setBackground(table.getBackground());
    Item item = (Item)value;
    
    try {
        if (item != null) seleccionarDato(item.getItemData());
    }
    catch (Exception ex) {
        new com.gmail.lrchfox3.utilitarios.Mensajes().mensajesErrores(null, ex.getMessage(), "Error");
    }
    return this;
  }
  
  public Component getTableCellRendererComponent(JTable table, Object value,
                                                 boolean isSelected,
                                                 boolean hasFocus, int row,
                                                 int column) {
    return RENDERER.getTableCellRendererComponent(table, value, isSelected, 
                                                  hasFocus, row, column);
  }
}

