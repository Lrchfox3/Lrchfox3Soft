/*
 * frmDialogBase.java
 *
 * Created on January 10, 2007, 10:56 PM
 */

package com.gmail.lrchfox3.formularios;

import com.gmail.lrchfox3.utilitarios.Propiedades;

/**
 *
 * @author  Luis R. Chinchilla H.
 */
public class FrmZoomCelda extends javax.swing.JDialog {
    
    /**
     * Crea una nueva forma de zoom para una celda de Tablas
     *
     * @param parent La ventana padre que ha llamado a esta ventana.
     * @param modal Indica si la ventana es modal o no.
     * @param valor Contiene el valor de la celda del zoom.
     */
    public FrmZoomCelda(java.awt.Frame parent, boolean modal, String campo, String valor, boolean editar) {
        super(parent, modal);
        initComponents();
        inicializarVentana (campo, valor, editar);
    }
    
    /** Creates new form frmDialogBase */
    public FrmZoomCelda(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public void inicializarVentana (String campo, String valor, boolean editar) {
        lblCampo.setText(lblCampo.getText() + campo);
        this.valor = valor;
        this.editar = editar;
        txtInformacion.setText(this.valor);
        btnEditarCampo.setEnabled(editar);                
    }
        
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanelZoom = new javax.swing.JPanel();
        lblCampo = new javax.swing.JLabel();
        btnEditarCampo = new javax.swing.JButton();
        txtInformacion = new javax.swing.JTextArea();
        btnAceptar = new com.gmail.lrchfox3.controles.botones.JBotonAceptar();
        btnCancelar = new com.gmail.lrchfox3.controles.botones.JBotonCancelar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Zoom Informaci\u00f3n");
        jPanelZoom.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelZoom.setPreferredSize(new java.awt.Dimension(390, 330));
        lblCampo.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12));
        lblCampo.setText("Campo: ");
        jPanelZoom.add(lblCampo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 26, -1, -1));

        btnEditarCampo.setIcon(new javax.swing.ImageIcon(getClass().getResource(propiedades.getImgEditarCampo())));
        btnEditarCampo.setEnabled(false);
        btnEditarCampo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarClicked(evt);
            }
        });

        jPanelZoom.add(btnEditarCampo, new org.netbeans.lib.awtextra.AbsoluteConstraints(353, 10, 24, 26));

        txtInformacion.setEditable(false);
        txtInformacion.setWrapStyleWord(true);
        txtInformacion.setBorder(new javax.swing.border.EtchedBorder());
        jPanelZoom.add(txtInformacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 46, 358, 238));

        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarClicked(evt);
            }
        });

        jPanelZoom.add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 288, -1, -1));

        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarClicked(evt);
            }
        });

        jPanelZoom.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 288, -1, -1));

        getContentPane().add(jPanelZoom, java.awt.BorderLayout.CENTER);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-406)/2, (screenSize.height-365)/2, 406, 365);
    }
    // </editor-fold>//GEN-END:initComponents

    private void btnEditarClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarClicked
        if (editar)  {
            txtInformacion.setEditable(editar);
            txtInformacion.requestFocus();
            txtInformacion.selectAll();            
        }
    }//GEN-LAST:event_btnEditarClicked

    private void btnCancelarClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarClicked
        dispose();
    }//GEN-LAST:event_btnCancelarClicked

    private void btnAceptarClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarClicked
        if (txtInformacion.getText() == null) valor = null;
        else {
            if (!valor.equals(txtInformacion.getText())) 
                valor = txtInformacion.getText();
        }            
        dispose();
    }//GEN-LAST:event_btnAceptarClicked
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmZoomCelda(new javax.swing.JFrame(), true).setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.gmail.lrchfox3.controles.botones.JBotonAceptar btnAceptar;
    private com.gmail.lrchfox3.controles.botones.JBotonCancelar btnCancelar;
    private javax.swing.JButton btnEditarCampo;
    private javax.swing.JPanel jPanelZoom;
    private javax.swing.JLabel lblCampo;
    private javax.swing.JTextArea txtInformacion;
    // End of variables declaration//GEN-END:variables
    private Propiedades propiedades = new Propiedades(); 
    private boolean editar = false;
    public String valor;   
}
