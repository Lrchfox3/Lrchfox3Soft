/*
 * frmEdicion.java
 *
 * Created on February 3, 2007, 5:58 PM
 */

package com.gmail.lrchfox3.formularios;

/**
 *
 * @author  Luis R. Chinchilla H.
 */
public class FrmEdicion extends javax.swing.JFrame {
    
    /**
     * Creates new form frmEdicion 
     */
    public FrmEdicion() {
        initComponents();
        lblObligatorios.setVisible(false);
    }
    
    /**
     * Define el icono y el texto del titulo.
     *
     * @param icono Imagen del titulo.
     * @param texto el tecto del titulo.
     *
     */
    public void setTitulo(javax.swing.Icon icono, String texto) {
        lblTitulo.setIcon(icono);
        lblTitulo.setText(texto);
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        JPanelVentana = new com.gmail.lrchfox3.controles.paneles.JPanelVentana();
        lblTitulo = new com.gmail.lrchfox3.controles.textos.JEtiquetaBase();
        lblObligatorios = new com.gmail.lrchfox3.controles.textos.JEtiquetaObligatorios();
        btnAceptar = new com.gmail.lrchfox3.controles.botones.JBotonAceptar();
        btnCancelar = new com.gmail.lrchfox3.controles.botones.JBotonCancelar();
        btnAgregar = new com.gmail.lrchfox3.controles.botones.JBotonAgregar();
        btnEditar = new com.gmail.lrchfox3.controles.botones.JBotonEditar();
        btnEliminar = new com.gmail.lrchfox3.controles.botones.JBotonEliminar();
        btnSalir = new com.gmail.lrchfox3.controles.botones.JBotonSalir();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new com.gmail.lrchfox3.utilitarios.Propiedades().getColorFondo());
        JPanelVentana.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JPanelVentana.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 10, 216, 26));

        JPanelVentana.add(lblObligatorios, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 256, -1, -1));

        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarClicked(evt);
            }
        });

        JPanelVentana.add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 250, -1, -1));

        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarClicked(evt);
            }
        });

        JPanelVentana.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(268, 250, -1, -1));

        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarClicked(evt);
            }
        });

        JPanelVentana.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 16, -1, -1));

        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarClicked(evt);
            }
        });

        JPanelVentana.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 16, -1, -1));

        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarClicked(evt);
            }
        });

        JPanelVentana.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(274, 16, -1, -1));

        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirClicked(evt);
            }
        });

        JPanelVentana.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(358, 16, -1, -1));

        getContentPane().add(JPanelVentana, java.awt.BorderLayout.CENTER);

        pack();
    }
    // </editor-fold>//GEN-END:initComponents

    private void btnSalirClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirClicked
        dispose();
    }//GEN-LAST:event_btnSalirClicked

    private void btnCancelarClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarClicked
        
    }//GEN-LAST:event_btnCancelarClicked

    private void btnAceptarClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarClicked
        
    }//GEN-LAST:event_btnAceptarClicked

    private void btnEliminarClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarClicked
        
    }//GEN-LAST:event_btnEliminarClicked

    private void btnEditarClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarClicked
        
    }//GEN-LAST:event_btnEditarClicked

    private void btnAgregarClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarClicked
        
    }//GEN-LAST:event_btnAgregarClicked
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmEdicion().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.gmail.lrchfox3.controles.botones.JBotonAceptar btnAceptar;
    private com.gmail.lrchfox3.controles.botones.JBotonAgregar btnAgregar;
    private com.gmail.lrchfox3.controles.botones.JBotonCancelar btnCancelar;
    private com.gmail.lrchfox3.controles.botones.JBotonEditar btnEditar;
    private com.gmail.lrchfox3.controles.botones.JBotonEliminar btnEliminar;
    private com.gmail.lrchfox3.controles.botones.JBotonSalir btnSalir;
    private com.gmail.lrchfox3.controles.paneles.JPanelVentana JPanelVentana;
    private com.gmail.lrchfox3.controles.textos.JEtiquetaObligatorios lblObligatorios;
    private com.gmail.lrchfox3.controles.textos.JEtiquetaBase lblTitulo;
    // End of variables declaration//GEN-END:variables
   
}
