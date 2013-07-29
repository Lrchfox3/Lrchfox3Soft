/*
 * JPopupTextoBase.java
 *
 * Created on 10 de febrero de 2007, 06:43 PM
 *
 */
package com.gmail.lrchfox3.controles.tablas;

/**
 *
 * @author Luis Chinchilla
 */

import com.gmail.lrchfox3.controles.textos.JComentarioBase;
import com.gmail.lrchfox3.controles.textos.JTextoBase;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.text.JTextComponent;

public class JPopupTablaBase extends JPopupMenu {

    public JPopupTablaBase() {
        inicializar();
    }

    private void inicializar() {
        mnuItemImportar = new JMenuItem();
        mnuItemExportar = new JMenuItem();

        mnuItemImportar.setText("Importar");
        mnuItemImportar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        mnuItemImportar.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemCortarActionPerformed(evt);
            }
        });
        add(mnuItemImportar);
        mnuItemExportar.setText("Exportar");
        mnuItemExportar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        mnuItemExportar.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemCopiarActionPerformed(evt);
            }
        });
        add(mnuItemExportar);
    }

    private void mnuItemCortarActionPerformed(java.awt.event.ActionEvent evt) {
        if ((this.getInvoker() instanceof JTextoBase) || (this.getInvoker() instanceof JComentarioBase)) {
            JTextComponent texto = (JTextComponent) this.getInvoker();
            texto.cut();
        }
    }

    private void mnuItemCopiarActionPerformed(java.awt.event.ActionEvent evt) {
        if ((this.getInvoker() instanceof JTextoBase) || (this.getInvoker() instanceof JComentarioBase)) {
            JTextComponent texto = (JTextComponent) this.getInvoker();
            texto.copy();
        }
    }   

    // <editor-fold defaultstate="collapsed" desc=" Declaración de Variables ">
    private JMenuItem mnuItemImportar;
    private JMenuItem mnuItemExportar;
    // </editor-fold>
}
