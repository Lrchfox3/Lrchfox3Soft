/*
 * ModeloTabla.java
 *
 * Created on February 7, 2007, 2:40 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.gmail.lrchfox3.controles.tablas;

// <editor-fold defaultstate="collapsed" desc=" Librerias">  
import java.awt.Component;
import java.util.Map;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Hashtable;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.gmail.lrchfox3.basedatos.BD;
// </editor-fold>

/**
 * <p>
 * Default implementation of {@link org.javautils.swing.ImprovedTableModel} interface.
 * This model extends {@link javax.swing.table.DefaultTableModel}.
 * </p>
 * <p>
 *   -1 value for row or column in <code>setXxx(int row, int col, ...)</code> methods means
 *   setting for whole row or column respectivelly.
 * </p>
 * 
 * @version $Name:  $ - $Revision: 1.1.1.1 $ - $Date: 2005/04/07 18:36:19 $
 */
public class ModeloTabla extends DefaultTableModel implements InterfaseModeloTabla {
  
   private JTablaBase tabla;
   
  /** 
   * Vector of uneditable cells - cells are indicated using keys created 
   * by {@link #createKey(int, int)}method 
   */
  private Vector uneditableCells;
  
  /** Map of tooltips added to columns' headers. */
  private Map columnTooltips;
  
  /** Map of cell editors for specific cells */
  private Map cellEditors;
  
  /** Map of cell renderers for specific cells */
  private Map cellRenderers;
  
  private BD bd = null;
  
  public ModeloTabla() {
    super();
    inicializar();    
  }
  
   public ModeloTabla(JTablaBase tabla, Object[][] celdas, Object[] titulos) {
        super(celdas, titulos);        
        this.tabla = tabla;
        inicializar();
    }
  
   private void inicializar() {
       uneditableCells = new Vector();
       columnTooltips = new Hashtable();
       cellEditors = new Hashtable();
       cellRenderers = new Hashtable();
   }
   
   /**
     * Define la BD que se va a utilizar para manipular los datos relacionados
     * al Arbol.
     *
     * @param BD Base de Datos que se utilizar�.
     */
    public void setBD(BD bd) throws Exception {
        this.bd = bd;
    }
    
  /**
   * Creates key for row and column.
   * 
   * @param row
   * @param col
   * @return
   */
  private String createKey(int row, int col) {
    return row + "," + col;
  }
  
  /**
   * Sets cell as editable/uneditable.
   * 
   * @param row
   * @param col
   * @param editable
   */
  public void setCellEditable(int row, int col, boolean editable) {
    String key = createKey(row, col);
    if (editable)
      uneditableCells.remove(key);
    else if (!uneditableCells.contains(key))
      uneditableCells.add(key);
  }
  
  /**
   * Sets column editable
   * 
   * @param col
   * @param editable
   */
  public void setColumnEditable(int col, boolean editable) {
    setCellEditable(-1, col, editable); 
  }
  
  /**
   * Returns whether the column is editable.
   * 
   * @see javax.swing.table.TableModel#isCellEditable(int, int)
   */
  public boolean isCellEditable(int row, int col) {
    boolean resultado = false;
    
    if (tabla.isFormulaCalculoColumna(col)) resultado = false;
    else if (tabla.isFormulaColumnaEditable(col)) resultado = tabla.calcularEdicionCelda(row, col);    
    else {
        String key = createKey(-1, col);
        if (uneditableCells.contains(key)) ;
        else {
            key = createKey(row, col);
            resultado = !(uneditableCells.contains(key));
        }
    }
    
    return resultado;
  }
  
  /**
   * Returns cell's class -> usually returns the class of value in the given
   * cell.
   * 
   * @see org.javautils.swing.ImprovedTableModel#getCellClass(int, int)
   */
  public Class getCellClass(int row, int col) {
    Object value = getValueAt(row, col);
    if (value != null)
      return value.getClass();
    else
      return Object.class; 
  }
  
  /**
   * Sets tooltip for column's header.
   * 
   * @param column
   * @param tooltip
   */
  public void setColumnToolTip(int column, String tooltip) {
    columnTooltips.put(new Integer(column), tooltip);
  }
  
  /**
   * Returns tooltip for column.
   * 
   * @see org.javautils.swing.ImprovedTableModel#getColumnToolTip(int)
   */
  public String getColumnToolTip(int col) {
    return (String) columnTooltips.get(new Integer(col));
  }
  
  /**
   * Sets cell editor for given cell.
   * 
   * @param row
   * @param col
   * @param editor
   */
  public void setCellEditor(int row, int col, TableCellEditor editor) {
    String key = createKey(row, col);
    if (editor == null)
      cellEditors.remove(key);
    else
      cellEditors.put(key, editor);
  }
  
  /**
   * Returns cell editor for given cell.
   * 
   * @see org.javautils.swing.ImprovedTableModel#getCellEditor(int, int)
   */
  public TableCellEditor getCellEditor(int row, int col) {
    return (TableCellEditor) cellEditors.get(createKey(row, col));
  }
  
  /**
   * Sets cell renderer for given cell. 
   * 
   * @param row
   * @param col
   * @param renderer
   */
  public void setCellRenderer(int row, int col, TableCellRenderer renderer) {
    String key = createKey(row, col);
    if (renderer == null)
      cellRenderers.remove(key);
    else
      cellRenderers.put(key, renderer);
  }
  
  /**
   * Returns cell renderer for given cell.
   * 
   * @see org.javautils.swing.ImprovedTableModel#getCellRenderer(int, int)
   */
  public TableCellRenderer getCellRenderer(int row, int col) {
    return (TableCellRenderer) cellRenderers.get(createKey(row, col));

  }

}