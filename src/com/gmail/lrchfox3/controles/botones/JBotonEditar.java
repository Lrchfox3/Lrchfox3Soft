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

import com.gmail.lrchfox3.basedatos.BD;
import javax.swing.ImageIcon ;
import com.gmail.lrchfox3.utilitarios.Propiedades;

/**
 *
 * @author Luis R. Chinchilla H.
 */
public class JBotonEditar extends javax.swing.JButton {
    
    // Variables declaration - do not modify                     
    // End of variables declaration                   
    private Propiedades propiedades = new Propiedades();    
    
    /** Creates a new instance of JBotonAgregar */
    public JBotonEditar() {
        inicializar();
    }
    
    public void inicializar() {           
        setText(null);
        setToolTipText(Propiedades.appBundle.getString("EDITAR"));
        setFont(propiedades.getFontBotones());        
        setIcon(new ImageIcon(getClass().getResource(propiedades.getImgEditar())));        
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
        setText(Propiedades.appBundle.getString("EDITAR"));
        setSize(26, 106);        
   }    
   
      public int getAccion(){
       return BD.ACCION_EDITAR;
   }
}
