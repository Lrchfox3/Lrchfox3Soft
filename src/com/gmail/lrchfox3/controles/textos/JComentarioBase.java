/*
 * jTextoBase.java
 *
 * Created on January 14, 2007, 7:01 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */
package com.gmail.lrchfox3.controles.textos;

import com.gmail.lrchfox3.utilitarios.Propiedades;
import java.awt.event.*;

/**
 *
 * @author Luis R. Chinchilla H.
 */
public class JComentarioBase extends javax.swing.JTextArea {

    private Propiedades propiedades = new Propiedades();

    /**
     * Creates a new instance of jTextoBase
     */
    public JComentarioBase() {
        inicializar();
    }

    public JComentarioBase(int rows, int columns) {
        inicializar();
        this.setRows(rows);
        this.setColumns(columns);
    }

    private void inicializar() {
        setFont(propiedades.getFontTextos());        
        popupMenu = new JPopupTextoBase();
        this.setComponentPopupMenu(popupMenu);       
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                validar(evt);
            }
        });

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (getText().length() >= getMaxCaracteres() + 1) {
                    getToolkit().beep();
                    requestFocus();
                    errorMaxCaracteres(getText());

                }
            }
        });
    }

    @Override
    public void setEditable(boolean value) {
        super.setEditable(value);           
    }

    private void validar(KeyEvent evt) {
        int max = getMaxCaracteres();
        int l = getText().length();
        String textoSel = getSelectedText();
        if (textoSel != null) {
            l = l - textoSel.length();
        }

        if (l >= max) {
            getToolkit().beep();
            evt.consume();
        }
    }

    public void setMaxCaracteres(int numeroMaximoCaracteres) {
        this.numeroMaximoCaracteres = numeroMaximoCaracteres;
    }

    public int getMaxCaracteres() {
        return numeroMaximoCaracteres;
    }

    public void errorMaxCaracteres(String texto) {
        setText("");
        javax.swing.JOptionPane.showMessageDialog(this, "Máximo Caracteres: " + getMaxCaracteres() + "\n" + "texto: " + texto + " \n(" + texto.length() + " caracteres)", "Elemento no encontrado", javax.swing.JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void setComponentPopupMenu(javax.swing.JPopupMenu popup) {
        if (popup != null) {
            super.setComponentPopupMenu(popup);
        }
    }
    
    public void setCurrentLineHighlighter(boolean value){
        if (value)
        CurrentLineHighlighter.install(this);       
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Declaración de variables ">
    private JPopupTextoBase popupMenu;
    public int numeroMaximoCaracteres = 32000;
    // </editor-fold>
}
