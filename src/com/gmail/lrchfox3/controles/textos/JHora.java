/*
 * JHora.java
 *
 * Created on January 14, 2007, 7:01 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.gmail.lrchfox3.controles.textos;

import javax.swing.text.MaskFormatter;
import com.gmail.lrchfox3.utilitarios.Numeros;
import com.gmail.lrchfox3.utilitarios.Propiedades;

/**
 *
 * @author Luis R. Chinchilla H.
 */
public class JHora extends javax.swing.JSpinner {
    
    private Propiedades propiedades = new Propiedades();        
    
    /** Creates a new instance of JHora */
    public JHora() {
        inicializar();
    }
    
    public void inicializar() {
        setFont(propiedades.getFontTextos());
        setBorder(new javax.swing.border.EtchedBorder());
        setPreferredSize(new java.awt.Dimension(70, 20));
        setSize(getPreferredSize()); 
        setModel(new javax.swing.SpinnerDateModel());
        javax.swing.JSpinner.DateEditor de = new javax.swing.JSpinner.DateEditor(this, "hh:mm a");        
        setEditor(de);        
    }                    
}
