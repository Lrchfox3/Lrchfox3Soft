/*
 * JBotonAgregar.java
 *
 * Created on January 14, 2007, 5:31 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.gmail.lrchfox3.controles.botones;

import java.io.Serializable;
import com.gmail.lrchfox3.utilitarios.Propiedades;

/**
 *
 * @author Luis R. Chinchilla H.
 */
public class JBotonGuardar extends javax.swing.JButton implements Serializable{
    
            /**
     * serial uid
     */
    private static final long serialVersionUID = 3978706198935583032L;
    
    // Variables declaration - do not modify                     
    // End of variables declaration                   
    private Propiedades propiedades = new Propiedades();    
    
    /** Creates a new instance of JBotonAgregar */
    public JBotonGuardar() {
        inicializar();
    }
    
    private void inicializar() {           
        setText(Propiedades.appBundle.getString("GUARDAR"));
        setToolTipText(Propiedades.appBundle.getString("EXPORTAR"));
        setFont(propiedades.getFontBotones());                        
        setIcon(new javax.swing.ImageIcon(propiedades.getImgGuardar()));        
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
        setText("Guardar");
        setSize(26, 106);        
   }    
}
