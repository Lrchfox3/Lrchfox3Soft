/*
 * JPopupTextoBase.java
 *
 * Created on 10 de febrero de 2007, 06:43 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.gmail.lrchfox3.controles.textos;

/**
 *
 * @author Luis Chinchilla
 */
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;
        
public class JPopupTextoBase extends JPopupMenu {
    
    public JPopupTextoBase() {
        inicializar();
    }

    private void inicializar() {
        mnuItemCortar = new JMenuItem();
        mnuItemCopiar = new JMenuItem();
        mnuItemPegar = new JMenuItem();
        separator1 = new Separator();
        mnuItemSelecAll = new JMenuItem();

        this.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {

            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }

            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }

            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                PopupMenuWillBecomeVisible(evt);
            }
        });


        mnuItemCortar.setText("Cortar");
        mnuItemCortar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        mnuItemCortar.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemCortarActionPerformed(evt);
            }
        });
        add(mnuItemCortar);
        mnuItemCopiar.setText("Copiar");
        mnuItemCopiar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        mnuItemCopiar.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemCopiarActionPerformed(evt);
            }
        });
        add(mnuItemCopiar);
        mnuItemPegar.setText("Pegar");
        mnuItemPegar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        mnuItemPegar.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemPegarActionPerformed(evt);
            }
        });
        add(mnuItemPegar);
        add(separator1);
        mnuItemSelecAll.setText("Seleccionar Todo");
        mnuItemSelecAll.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        mnuItemSelecAll.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemSelecAllActionPerformed(evt);
            }
        });
        add(mnuItemSelecAll);
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

    private void mnuItemPegarActionPerformed(java.awt.event.ActionEvent evt) {
        if ((this.getInvoker() instanceof JTextoBase) || (this.getInvoker() instanceof JComentarioBase)) {
            JTextComponent texto = (JTextComponent) this.getInvoker();
            texto.paste();
        }
    }

    private void mnuItemSelecAllActionPerformed(java.awt.event.ActionEvent evt) {
        if ((this.getInvoker() instanceof JTextoBase) || (this.getInvoker() instanceof JComentarioBase)) {
            JTextComponent texto = (JTextComponent) this.getInvoker();
            texto.selectAll();
        }
    }

    private void PopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
        if ((this.getInvoker() instanceof JTextoBase) || (this.getInvoker() instanceof JComentarioBase)) {
            JTextComponent texto = (JTextComponent) this.getInvoker();
            if (texto.isEnabled()) {
                mnuItemCortar.setEnabled(true);
                mnuItemCopiar.setEnabled(true);
                mnuItemPegar.setEnabled(true);
                mnuItemSelecAll.setEnabled(true);
                if (!texto.isEditable()) {
                    mnuItemCortar.setEnabled(false);
                    mnuItemPegar.setEnabled(false);
                }
            } else {
                mnuItemCortar.setEnabled(false);
                mnuItemCopiar.setEnabled(false);
                mnuItemPegar.setEnabled(false);
                mnuItemSelecAll.setEnabled(false);
            }

            if (texto.getText().length()<=0){
                mnuItemSelecAll.setEnabled(false);
            }
            else {
                mnuItemSelecAll.setEnabled(true);
            }
        }
    }
    // <editor-fold defaultstate="collapsed" desc=" Declaración de Variables ">
    private JMenuItem mnuItemCortar;
    private JMenuItem mnuItemCopiar;
    private JMenuItem mnuItemPegar;
    private Separator separator1;
    private JMenuItem mnuItemSelecAll;
    // </editor-fold>
}
