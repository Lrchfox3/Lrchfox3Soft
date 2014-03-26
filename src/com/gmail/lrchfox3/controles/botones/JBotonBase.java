/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.lrchfox3.controles.botones;

import com.gmail.lrchfox3.utilitarios.Propiedades;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.Map;
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
    private boolean flatButton = false;

    public JBotonBase() {
        inicializar();
    }

    private void inicializar() {
        setText(Propiedades.appBundle.getString("BTN_ACEPTAR"));
        setToolTipText(Propiedades.appBundle.getString("BTN_ACEPTAR"));
        setFont(propiedades.getFontBotones());
        setIcon(new javax.swing.ImageIcon(propiedades.getImgAceptar()));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setAlignmentX(0);
        setAlignmentY(0);
        setPreferredSize(new java.awt.Dimension(100, 26));
        setSize(106, 26);
        setMnemonic('A');

        addMouseListener(new java.awt.event.MouseAdapter() {
            Font originalFont = null;

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                originalFont = getFont();
                Map attributes = originalFont.getAttributes();
                attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                setFont(originalFont.deriveFont(attributes));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                setFont(originalFont);
            }
        });

    }

    public int getOpcion() {
        return opcion;
    }

    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }

    public void setFlatButton(boolean value) {
        flatButton = value;
        setBorderPainted(!value);
        setFocusPainted(!value);
        setContentAreaFilled(!value);
    }
}
