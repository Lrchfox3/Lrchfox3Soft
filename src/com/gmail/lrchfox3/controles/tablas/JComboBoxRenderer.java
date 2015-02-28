/*
 * JComboBoxRenderer.java
 *
 * Created on February 7, 2007, 6:56 PM
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
 * ComboBox table renderer.
 * 
 * @version $Name:  $ - $Revision: 1.1.1.1 $ - $Date: 2005/04/07 18:36:18 $
 */
public class JComboBoxRenderer  extends com.gmail.lrchfox3.controles.listas.JComboBoxBase implements TableCellRenderer {


  public JComboBoxRenderer() {
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
    
    this.removeAllItems();
    
    if(value != null){
      this.addItem(value.toString());
      this.setSelectedIndex(0);
    }

    // ideal height for rows containing combobox as their renderer 
    int prefHeight = (int) this.getPreferredSize().getHeight() - 4;
    if (table.getRowHeight(row) < prefHeight)
      table.setRowHeight(prefHeight);
    
    return this;
  }

}
