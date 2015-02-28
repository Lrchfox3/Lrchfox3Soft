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
public class JBotonComentario extends javax.swing.JButton implements Serializable {

    private Propiedades propiedades = new Propiedades();

    public JBotonComentario() {
        inicializar();
    }

    private void inicializar() {
        setText(Propiedades.appBundle.getString("COMENTARIO"));
        setToolTipText(Propiedades.appBundle.getString("COMENTARIO"));
        setFont(propiedades.getFontBotones());        
        setIcon(new javax.swing.ImageIcon(propiedades.getImgComentario()));
        setAlignmentX(0);
        setAlignmentY(0);
        setPreferredSize(new java.awt.Dimension(100, 26));
        setSize(106, 26);
        setMnemonic('A');
    }
}
