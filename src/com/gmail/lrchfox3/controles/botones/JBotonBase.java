/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.lrchfox3.controles.botones;

import com.gmail.lrchfox3.utilitarios.Propiedades;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import javax.xml.soap.Node;

/**
 *
 * @author Administrador
 */
public class JBotonBase extends javax.swing.JButton implements Serializable {

    private Propiedades propiedades = new Propiedades();
    private int opcion;
    public static final int BTN_BASE = 3;
    public static final int BTN_ACEPTAR = 1;
    public static final int BTN_CANCELAR = 2;
    public static final int BTN_BUSCAR = 3;
    

    public JBotonBase() {
        inicializar();
    }

    private void inicializar() {
        setText(Propiedades.config.getString("BTN_ACEPTAR"));
        setToolTipText(Propiedades.config.getString("BTN_ACEPTAR"));
        setFont(propiedades.getFontBotones());
        setIcon(new javax.swing.ImageIcon(propiedades.getImgAceptar()));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setAlignmentX(0);
        setAlignmentY(0);
        setPreferredSize(new java.awt.Dimension(100, 26));
        setSize(106, 26);
        setMnemonic('A');



    }

    public int getOpcion() {
        return opcion;
    }

    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }
}
