/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.lrchfox3.controles.botones;


import com.gmail.lrchfox3.utilitarios.Propiedades;
import java.io.Serializable;

/**
 *
 * @author Administrador
 */
public class JBotonAceptar extends JBotonBase implements Serializable {

    private Propiedades propiedades = new Propiedades();
     private boolean flatButton = false;

    public JBotonAceptar() {
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
    }
    
}
