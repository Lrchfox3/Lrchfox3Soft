/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.lrchfox3.controles.tablas;


import com.gmail.lrchfox3.controles.textos.JComentarioBase;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author chinchillal
 */
public class StringActionTableCellEditor extends ActionTableCellEditor {

    public StringActionTableCellEditor(TableCellEditor editor) {
        super(editor);
    }

    protected void editCell(JTable table, int row, int column) {
        JComentarioBase textArea = new JComentarioBase(10, 50);

        Object value = table.getValueAt(row, column);
        textArea.setEditable(false);
        if (value != null) {
            textArea.setText((String) value);
            textArea.setCaretPosition(0);
        }
        int result = JOptionPane.showOptionDialog(table, new JScrollPane(textArea), (String) table.getColumnName(column), JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
        if (result == JOptionPane.OK_OPTION) {
            table.setValueAt(textArea.getText(), row, column);
        }
    }
}
