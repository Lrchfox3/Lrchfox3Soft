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
import javax.swing.JSeparator;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.text.JTextComponent;


//Boton limpiar texto
/**
 *
 * @author Luis R. Chinchilla H.
 */
public class JTextoConBoton extends javax.swing.JTextField  {

    private Propiedades propiedades = new Propiedades();
    JComponentBorder cb = new JComponentBorder(new JBotonLimpiarTexto(this));

    /**
     * Creates a new instance of jTextoBase
     */
    public JTextoConBoton() {
        inicializar();
    }

    public void inicializar() {
        setFont(propiedades.getFontTextos());
        //setBorder(new javax.swing.border.EtchedBorder());
        popupMenu = new JPopupTextoBase();
        this.setComponentPopupMenu(popupMenu);
        //setPreferredSize(new java.awt.Dimension(90, 20));
        //setSize(getPreferredSize());

        cb.install(this);

        addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent evt) {
                validar(evt);
            }
        });

        addFocusListener(new FocusAdapter() {

            /*@Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (isEditable()) {
                    setBackground(java.awt.SystemColor.inactiveCaptionText); //R216,G228,B248
                }
                if (isEnabled() && isEditable()) {
                    setBorder(javax.swing.BorderFactory.createEtchedBorder());
                }
            }*/

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (getText().length() >= getMaxCaracteres() + 1) {
                    getToolkit().beep();
                    requestFocus();
                    errorMaxCaracteres(getText());

                }
                /*if (isEditable()) {
                    setBackground(java.awt.SystemColor.activeCaptionText);
                }*/
            }
        });

       /* this.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SombrearBordeMouseEntered(evt);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SombrearBordeMouseExited(evt);
            }
        });*/
    }

    @Override
    public void setEditable(boolean value) {
        if (!value) {
            setBackground(javax.swing.UIManager.getDefaults().getColor("TextPane.disabledBackground"));
        }
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

    /*private void SombrearBordeMouseEntered(java.awt.event.MouseEvent evt) {
        if (this.isEnabled() && this.isEditable()) {
            this.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 204)));
        }
    }*/

    /*private void SombrearBordeMouseExited(java.awt.event.MouseEvent evt) {
        if (this.isEnabled() && this.isEditable()) {
            this.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        }
    }*/
    // <editor-fold defaultstate="collapsed" desc=" Declaración de variables ">
    private JPopupTextoBase popupMenu;
    private JSeparator separator;
    public int numeroMaximoCaracteres = 32000;
    // </editor-fold>
}
class JBotonLimpiarTexto extends javax.swing.JButton {

    // Variables declaration - do not modify
    // End of variables declaration
    private Propiedades propiedades = new Propiedades();
    private JTextComponent texto = null;

    /** Creates a new instance of JBotonAgregar */
    public JBotonLimpiarTexto(JTextComponent texto) {
        inicializar();
        this.texto = texto;
    }

    public void inicializar() {
        setText(null);
        setToolTipText("Limpiar texto");
        setFont(propiedades.getFontBotones());
        setIcon(new ImageIcon(propiedades.getImgLimpiarTexto()));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setAlignmentX(0);
        setAlignmentY(0);
        setPreferredSize(new java.awt.Dimension(19, 19));
        setSize(getPreferredSize());
        setMnemonic('L');

        addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        this.texto.setText("");
        this.texto.requestFocusInWindow();
    }
}
