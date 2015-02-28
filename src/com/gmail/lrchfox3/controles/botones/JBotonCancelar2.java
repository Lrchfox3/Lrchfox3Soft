/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.lrchfox3.controles.botones;


import java.io.Serializable;
import com.gmail.lrchfox3.utilitarios.Propiedades;

/**
 *
 * @author Administrador
 */
public class JBotonCancelar2 extends javax.swing.JButton implements Serializable {

    private Propiedades propiedades = new Propiedades();

    public JBotonCancelar2() {
        inicializar();
    }

    private void inicializar() {
        //setText("");
        setToolTipText(Propiedades.appBundle.getString("CANCELAR"));
        //setFont(propiedades.getFontBotones());
        setIcon(new javax.swing.ImageIcon(propiedades.getImgCancelar2()));
        setContentAreaFilled(false);        
        setAlignmentX(0);
        setAlignmentY(0);
        setPreferredSize(new java.awt.Dimension(20, 20));
        setSize(getPreferredSize());
        //setMnemonic('C');
    }

    @Override
    public String getText(){
        return "";
    }
}
