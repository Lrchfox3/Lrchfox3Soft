package com.gmail.lrchfox3.controles.paneles;

/**
 * @author Luis R. Chinchilla H.
 */

import com.gmail.lrchfox3.utilitarios.Utileria;

public class JPanelVentana extends javax.swing.JPanel {

    private Utileria util = new Utileria();
    
    public JPanelVentana() {
        inicializar();
    }
    
    public void inicializar() {
        try {
            util.lookAndFeelSystem();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        setAutoscrolls(true);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
    }
    
}
