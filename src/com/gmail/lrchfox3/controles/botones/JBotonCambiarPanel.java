/*
 * JBotonCambiarPanel.java
 *
 * Created on 23 de abril de 2007, 10:12 PM
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

import javax.swing.ImageIcon;
import com.gmail.lrchfox3.utilitarios.Propiedades;

public class JBotonCambiarPanel extends javax.swing.JButton {
    
    private Propiedades propiedades = new Propiedades();
    private int estado = 1;
    
    public JBotonCambiarPanel() {
        inicializar();
        addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBotonCambiarPanelActionPerformed(evt);
            }
        });
    }
            
    public void inicializar() {           
        setText(null);
        setToolTipText(Propiedades.appBundle.getString("CAMBIAR_PANEL"));
        setFont(propiedades.getFontBotones());        
        setIcon(new ImageIcon(getClass().getResource(propiedades.getImgCambiarPanel())));        
        setAlignmentX(0);
        setAlignmentY(0);        
        setPreferredSize(new java.awt.Dimension(24, 26));
        setSize(24, 26); 
    }
    
    private void JBotonCambiarPanelActionPerformed(java.awt.event.ActionEvent evt) {
        if (estado != 0) {
            this.estado = 0;
        }
        else {
            this.estado = 1;
        }
    }    
    
    public void setEstado( int estado ) {
        this.estado = estado;
    }
    
    public int getEstado() {
        return estado;
    }
}
