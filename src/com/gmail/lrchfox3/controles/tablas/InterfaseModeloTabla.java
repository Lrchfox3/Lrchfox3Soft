/*
 * InterfaseModeloTabla.java
 *
 * Created on February 7, 2007, 2:32 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.gmail.lrchfox3.controles.tablas;

// <editor-fold defaultstate="collapsed" desc=" Librerias">  
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
// </editor-fold>

/**
 * Interface defining methods for extending {@link javax.swing.table.TableModel} 
 * to support tooltips for columns, returning class, cell editor and cell renderer
 * for specific cell (not only column).
 * 
 * @version $Name:  $ - $Revision: 1.1.1.1 $ - $Date: 2005/04/07 18:36:21 $
 */
public interface InterfaseModeloTabla extends TableModel {
  /**
   * Returns class for a given cell.
   * 
   * @param row cell's row
   * @param col cell's column
   * @return
   */
  public Class getCellClass(int row, int col);
  
  /**
   * Returns tooltip for a given column.
   * 
   * @param col
   * @return
   */
  public String getColumnToolTip(int col);
  
  /**
   * Returns editor for a given cell
   * 
   * @param row cell's row
   * @param col cell's column
   * @return
   */
  public TableCellEditor getCellEditor(int row, int col);
  
  /**
   * Returns renderer for the given cell.
   * 
   * @param row cell's row
   * @param col cell's column
   * @return
   */
  public TableCellRenderer getCellRenderer(int row, int col);
  
}