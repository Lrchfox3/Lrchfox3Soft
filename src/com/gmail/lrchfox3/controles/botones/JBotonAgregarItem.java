/*
 * JBotonAgregarItem.java
 *
 * Created on January 14, 2007, 8:08 PM
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
 * @author Luis R. Chinchilla H.
 */
public class JBotonAgregarItem extends javax.swing.JButton {
    
    private Propiedades propiedades = new Propiedades();    
    
    /**
     * Creates a new instance of JBotonAgregarItem 
     */
    public JBotonAgregarItem() {
        inicializar();
    }
    
    public void inicializar() {           
        //setText(null);
        setToolTipText(Propiedades.appBundle.getString("AGREGAR_ELEMENTO"));
        setFont(propiedades.getFontBotones());        
        setIcon(new ImageIcon(propiedades.getImgAgregarItem()));
        setAlignmentX(0);
        setAlignmentY(0);        
        setPreferredSize(new java.awt.Dimension(24, 19));
        setSize(getPreferredSize()); 
    }


    @Override
    public String getText() {
        return "";
    }
    
}
