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

import javax.swing.ImageIcon;
import com.gmail.lrchfox3.utilitarios.Propiedades;

/**
 *
 * @author Luis R. Chinchilla H.
 */
public class JBotonSalir extends javax.swing.JButton {
    
    // Variables declaration - do not modify                     
    // End of variables declaration                   
    private Propiedades propiedades = new Propiedades();        
    
    /** Creates a new instance of JBotonAgregar */
    public JBotonSalir() {
        inicializar();
    }
    
    public void inicializar() {           
        setText(null);
        setToolTipText(Propiedades.appBundle.getString("SALIR"));
        setFont(propiedades.getFontBotones());        
        setIcon(new ImageIcon(getClass().getResource(propiedades.getImgSalirSistema())));        
        setAlignmentX(0);
        setAlignmentY(0);        
        setPreferredSize(new java.awt.Dimension(24, 26));
        setSize(24, 26); 
        setMnemonic('S');
        
        addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBotonSalirClicked(evt);
            }
        });
    }
    
    private void JBotonSalirClicked(java.awt.event.ActionEvent evt) {
        String nombre = "";
        Class claseBase = null;
        java.awt.Container clase = getParent();
                
        while (clase != null) {
            claseBase = clase.getClass().getSuperclass();
            if (claseBase.getName().equals("javax.swing.JFrame")) {
                ((java.awt.Window)clase).dispose();
            }       
            
            clase = clase.getParent();
        }
    }
    
   /**
    * Muestra el texto del boton y aumenta el tama�o del mismo para presentar
    * el texto.
    */
   public void mostrarTexto() {
        setText(Propiedades.appBundle.getString("SALIR"));
        setSize(26, 106);        
   }    
}
