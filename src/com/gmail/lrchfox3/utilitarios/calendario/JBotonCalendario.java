/*
 * JBotonCalendario.java
 *
 * Created on January 14, 2007, 8:32 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.gmail.lrchfox3.utilitarios.calendario;

import java.util.Date;
import javax.swing.ImageIcon ;
import com.gmail.lrchfox3.utilitarios.calendario.JTextFieldDateEditor;
import com.gmail.lrchfox3.utilitarios.Propiedades;

/**
 *
 * @author Luis R. Chinchilla H.
 */
public class JBotonCalendario extends javax.swing.JButton {

    private Date fecha;
    private Propiedades propiedades = new Propiedades();            
    
    /** Creates a new instance of JBotonCalendario */
    public JBotonCalendario() {
        inicializar();
    }
    
    public void inicializar() {
        setText(null);
        setToolTipText("Calendario");
        setFont(propiedades.getFontBotones());        
        setIcon(new ImageIcon(getClass().getResource(propiedades.getImgCalendario())));
        setAlignmentX(0);
        setAlignmentY(0);        
        setPreferredSize(new java.awt.Dimension(24, 19));
        setSize(getPreferredSize()); 
    }
}
