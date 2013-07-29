/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gmail.lrchfox3.controles.botones;

import com.gmail.lrchfox3.utilitarios.Propiedades;
import java.io.Serializable;


/**
 *
 * @author Luis R. chinchilla H. (Administrador)
 * Programación Avanzada 1, UTH
 * Período 02, 2010
 */
public class JBotonCerrar extends javax.swing.JButton implements Serializable {

    private Propiedades propiedades = new Propiedades();

    public JBotonCerrar() {
        inicializar();
    }

    private void inicializar() {        
        setText(Propiedades.appBundle.getString("CERRAR"));
        setToolTipText(Propiedades.appBundle.getString("CERRAR_VENTANA"));
        setFont(propiedades.getFontBotones());
        setIcon(new javax.swing.ImageIcon(propiedades.getImgCerrar()));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setAlignmentX(0);
        setAlignmentY(0);
        setPreferredSize(new java.awt.Dimension(83, 25));
        setSize(83, 25);
        setMnemonic('C');
    }
}

