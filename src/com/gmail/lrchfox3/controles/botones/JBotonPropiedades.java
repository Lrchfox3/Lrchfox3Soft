/*
 * JBotonPropiedades.java
 *
 * Created on 07 de mayo de 2007, 09:03 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.gmail.lrchfox3.controles.botones;

/**
 *
 * @author Luis Chinchilla
 */

import javax.swing.ImageIcon ;
import com.gmail.lrchfox3.utilitarios.Propiedades;

public class JBotonPropiedades extends javax.swing.JButton {
    
    private Propiedades propiedades = new Propiedades(); 
    
    
    public JBotonPropiedades() {
        inicializar();
    }
    
    public void inicializar() {           
        setText(null);
        setToolTipText(Propiedades.appBundle.getString("PROPIEDADES"));
        setFont(propiedades.getFontBotones());        
        setIcon(new ImageIcon(getClass().getResource(propiedades.getImgPropiedades())));        
        setAlignmentX(0);
        setAlignmentY(0);        
        setPreferredSize(new java.awt.Dimension(24, 26));
        setSize(24, 26); 
    }
    
    /**
    * Muestra el texto del boton y aumenta el tama�o del mismo para presentar
    * el texto.
    */
   public void mostrarTexto() {
        setText(Propiedades.appBundle.getString("PROPIEDADES"));
        setSize(26, 106);        
   }
}
