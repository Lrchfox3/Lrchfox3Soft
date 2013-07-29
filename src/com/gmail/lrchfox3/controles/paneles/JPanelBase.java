package com.gmail.lrchfox3.controles.paneles;

/**
 * @author Luis R. Chinchilla H.
 */

import java.awt.Color;
import com.gmail.lrchfox3.utilitarios.Propiedades;
import com.gmail.lrchfox3.utilitarios.Utileria;

public class JPanelBase extends javax.swing.JPanel {

    private Propiedades propiedades = new Propiedades();
    private Utileria util = new Utileria();

    public JPanelBase() {
        inicializar();
    }

    public void inicializar() {
                try {
            util.lookAndFeelSystem();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        //setBackground(propiedades.getColorFondo());
        setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(), null,
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                propiedades.getFontBotones(), Color.BLUE));

        setFont(propiedades.getFontEtiquetas());
        setPreferredSize(new java.awt.Dimension(50, 50));
        setSize(getPreferredSize());
        setAutoscrolls(true);
    }
}
