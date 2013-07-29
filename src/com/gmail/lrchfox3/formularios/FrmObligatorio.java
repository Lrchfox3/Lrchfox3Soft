/*
 * frmObligatorio.java
 *
 * Created on February 3, 2007, 7:29 PM
 */

package com.gmail.lrchfox3.formularios;

/**
 *
 * @author  Luis R. Chinchilla H.
 */
public class FrmObligatorio extends javax.swing.JDialog {
    
    /** Creates new form frmObligatorio */
    public FrmObligatorio(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
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
        btnCancelar = new com.gmail.lrchfox3.controles.botones.JBotonCancelar();
        btnAceptar = new com.gmail.lrchfox3.controles.botones.JBotonAceptar();
        lblObligatorios = new com.gmail.lrchfox3.controles.textos.JEtiquetaObligatorios();
        lblTitulo = new com.gmail.lrchfox3.controles.textos.JEtiquetaBase();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(java.awt.Color.white);
        JPanelVentana.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarClicked(evt);
            }
        });

        JPanelVentana.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(268, 250, -1, -1));

        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarClicked(evt);
            }
        });

        JPanelVentana.add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 250, -1, -1));

        JPanelVentana.add(lblObligatorios, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 256, -1, -1));

        JPanelVentana.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 10, 216, 26));

        getContentPane().add(JPanelVentana, java.awt.BorderLayout.WEST);

        pack();
    }
    // </editor-fold>//GEN-END:initComponents

    private void btnCancelarClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarClicked
        
    }//GEN-LAST:event_btnCancelarClicked

    private void btnAceptarClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarClicked
        
    }//GEN-LAST:event_btnAceptarClicked
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmObligatorio(new javax.swing.JFrame(), true).setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.gmail.lrchfox3.controles.botones.JBotonAceptar btnAceptar;
    private com.gmail.lrchfox3.controles.botones.JBotonCancelar btnCancelar;
    private com.gmail.lrchfox3.controles.paneles.JPanelVentana JPanelVentana;
    private com.gmail.lrchfox3.controles.textos.JEtiquetaObligatorios lblObligatorios;
    private com.gmail.lrchfox3.controles.textos.JEtiquetaBase lblTitulo;
    // End of variables declaration//GEN-END:variables
    
}
