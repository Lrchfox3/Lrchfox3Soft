package com.gmail.lrchfox3.controles.paneles;

/**
 * @author Luis R. Chinchilla H.
 */

import java.io.Serializable;
import com.gmail.lrchfox3.utilitarios.Propiedades;
import com.gmail.lrchfox3.utilitarios.Utileria;

public class JPanelConTitulo extends javax.swing.JPanel implements Serializable {

    private Propiedades propiedades = new Propiedades();
    private String titulo = "Titulo";
    private Utileria util = new Utileria();

    public JPanelConTitulo() {        
        inicializar();
    }

    private void inicializar() {
        try {
            util.lookAndFeelSystem();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        crearBorde();
        this.setPreferredSize(new java.awt.Dimension(50, 50));
        this.setLayout(new java.awt.BorderLayout());
        this.setSize(getPreferredSize());
        this.setAutoscrolls(true);
    }

    private void crearBorde() {
        this.setBorder(javax.swing.BorderFactory.createTitledBorder(
                new javax.swing.border.LineBorder(
                java.awt.SystemColor.activeCaption,
                1, true),
                titulo,
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.ABOVE_TOP,
                propiedades.getFontBotones(),
                new java.awt.Color(204, 102, 0)));
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
        crearBorde();
    }
}
