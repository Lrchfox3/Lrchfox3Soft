/*
 * JBotonBuscarEnTabla.java
 *
 * Created on 23 de julio de 2007, 10:02 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.gmail.lrchfox3.controles.botones;

import javax.swing.ImageIcon ;
import com.gmail.lrchfox3.utilitarios.Propiedades;

/**
 *
 * @author Luis Chinchilla
 */
public class JBotonBuscarEnTabla extends javax.swing.JButton {
    
 
   private Propiedades propiedades = new Propiedades();    
    

    public JBotonBuscarEnTabla() {
        inicializar();
    }
    
    public void inicializar() {                 
        setText(Propiedades.appBundle.getString("BUSCAR"));
        setToolTipText(Propiedades.appBundle.getString("BUSCAR_EN_TABLA"));
        setFont(propiedades.getFontBotones());        
        setIcon(new ImageIcon(getClass().getResource(propiedades.getImgBuscarEnTabla())));         
        setAlignmentX(0);
        setAlignmentY(0);
        setPreferredSize(new java.awt.Dimension(98, 26));
        setSize(getPreferredSize());  
        setMnemonic('F');
    }
    
   /**
    * Muestra el texto del boton y aumenta el tama�o del mismo para presentar
    * el texto.
    */
   public void mostrarTexto() {
        setText("Buscar");
        setSize(26, 106);        
   }    
}
