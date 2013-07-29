/*
 * JEtiquetaBase.java
 *
 * Created on January 14, 2007, 7:21 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.gmail.lrchfox3.controles.textos;

import com.gmail.lrchfox3.utilitarios.Propiedades;

/**
 *
 * @author Luis R. Chinchilla H.
 */
public class JEtiquetaObligatorios extends javax.swing.JLabel {
    
    private Propiedades propiedades = new Propiedades();    
    
    /** Creates a new instance of JEtiquetaBase */
    public JEtiquetaObligatorios() {
        inicializar();        
    }
    
    public void inicializar() {
        setText("Campos Obligatorios");
        setFont(propiedades.getFontEtiquetas());        
        setForeground(propiedades.getColorEtiquetaObligatorios());
        setIcon(new javax.swing.ImageIcon(getClass().getResource(propiedades.getImgObligatorio())));
    }

}
