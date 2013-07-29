/*
 * CatalogosBase.java
 *
 * Created on 09-08-2010, 04:20:28 PM
 */
package ViewController.catalogos;

import com.gmail.lrchfox3.basedatos.BD;
import com.gmail.lrchfox3.basedatos.Campo;
import com.gmail.lrchfox3.controles.paneles.JPanelInformacion;
import model.BC.CatalogoBC;
import model.BC.MonitoreoBC;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import com.gmail.lrchfox3.model.dto.Catalogo;
import com.gmail.lrchfox3.utilitarios.Utileria;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

/**
 *
 * @author chinchillal
 */
public class CatalogosBase extends javax.swing.JInternalFrame {

    private Catalogo catalogoBase;
    private CatalogoBC catalogoBaseBC;

    public CatalogosBase(Catalogo catalogo, CatalogoBC catalogoBC) {
        catalogoBase = catalogo;
        catalogoBaseBC = catalogoBC;
        inicializar();
    }

    private void inicializar() {
        try {
            Utileria.lookAndFeelSystem();//"Nimbus"
            initComponents();
            this.lblTitulo.setText(this.lblTitulo.getText() + catalogoBase.getTitulo());
            this.setTitle(this.lblTitulo.getText());
            cargarTabla(lstCatalogo);

        } catch (Exception ex) {
            MonitoreoBC.registrarMovimiento("ERROR", "Iniciar Catalogo: " + catalogoBase.getTitulo(), "Erro al iniciar la ventana de Catalogos:" + catalogoBase.getTitulo() + ", error: " + ex.getMessage() + ". ref: CatalogosBase.inicializar", "ADMIN");
        }
    }

    private void cargarTabla(final java.util.List lst) {
        this.tblCatalogo.setName(catalogoBase.getTitulo());

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                JTableBinding jTableBinding = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE, lst, tblCatalogo);
                JTableBinding.ColumnBinding columnBinding = null;
                try {

                    for (Campo c : catalogoBase.getCampos()) {
                        if (c.esVisible()) {
                            columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${" + c.getNombre() + "}"));
                            columnBinding.setColumnName(c.getEtiqueta());
                            columnBinding.setColumnClass(String.class);
                        } else {
                            columnBinding = null;
                        }

                    }
                    jTableBinding.bind();
                    bindingGroup.addBinding(jTableBinding);
                    tblCatalogo.requestFocus();
                    tblCatalogo.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                    int col = 0;
                    for (Campo c : catalogoBase.getCampos()) {
                        if (c.esVisible()) {
                            tblCatalogo.setZoomCelda(col);
                            col++;
                        }
                    }

                } catch (Exception ex) {
                    MonitoreoBC.registrarMovimiento("ERROR", "Cagar Catalogo: " + catalogoBase.getTitulo(), "No se logro obtener la información de la tabla:" + catalogoBase.getNombreTabla() + ", error: " + ex.getMessage() + ". ref: CatalogosBase.cargarTabla", "ADMIN");
                }
            }
        });
    }

    private void refrescarListaCatalogo() {
        try {
            this.lstCatalogo.clear();
            lstCatalogo = catalogoBaseBC.getListaCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", 0);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();
        try {
            lstCatalogo = catalogoBaseBC.getListaCampos();
        } catch (Exception ex) {                 JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", 0);                                     }
        pnlCatalogo = new javax.swing.JPanel();
        lblTitulo = new com.gmail.lrchfox3.controles.textos.JEtiquetaTitulo();
        btnAgregar = new com.gmail.lrchfox3.controles.botones.JBotonAgregar();
        btnEditar = new com.gmail.lrchfox3.controles.botones.JBotonEditar();
        btnEliminar = new com.gmail.lrchfox3.controles.botones.JBotonEliminar();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCatalogo = new com.gmail.lrchfox3.controles.tablas.JTablaBase();
        btnCerrar = new com.gmail.lrchfox3.controles.botones.JBotonCerrar();
        jEtiquetaBase1 = new com.gmail.lrchfox3.controles.textos.JEtiquetaBase();
        btnBuscar = new com.gmail.lrchfox3.controles.botones.JBotonBuscar();
        txtCodigo = new javax.swing.JTextField();
        txtCodigo.setVisible(false);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this.tblCatalogo, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.codigo}"), txtCodigo, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);
        bindingGroup.bind();
        txtBuscar = new com.gmail.lrchfox3.controles.textos.JTextoConBoton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("ViewController/Resources/application"); // NOI18N
        lblTitulo.setText(bundle.getString("CATALOGO")); // NOI18N

        btnAgregar.setText("");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEditar.setText("");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setText("");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        tblCatalogo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblCatalogo);

        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        jEtiquetaBase1.setText(bundle.getString("BUSCAR")); // NOI18N

        btnBuscar.setText("");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        txtCodigo.setEditable(false);

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlCatalogoLayout = new javax.swing.GroupLayout(pnlCatalogo);
        pnlCatalogo.setLayout(pnlCatalogoLayout);
        pnlCatalogoLayout.setHorizontalGroup(
            pnlCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCatalogoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCatalogoLayout.createSequentialGroup()
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCatalogoLayout.createSequentialGroup()
                        .addComponent(jEtiquetaBase1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlCatalogoLayout.setVerticalGroup(
            pnlCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCatalogoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jEtiquetaBase1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        getContentPane().add(pnlCatalogo, java.awt.BorderLayout.CENTER);

        setBounds(0, 0, 405, 446);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        mostrarInformacion(catalogoBase, BD.ACCION_AGREGAR, true);
    }//GEN-LAST:event_btnAgregarActionPerformed


    /*
     * abrea la ventana de captura de información, la información que se presente depende de la información
     */
    private void mostrarInformacion(Catalogo cat, int accion, boolean editable) {
        JPanelInformacion infoPersona = new JPanelInformacion(cat, 150);
        infoPersona.setEditable(editable);
        int result = JOptionPane.showOptionDialog(this, infoPersona, bd.getDescripcionAccion(accion) + " Info. " + cat.getTitulo(), JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

        if (result == JOptionPane.OK_OPTION) {

            try {
                infoPersona.getInformacion();
                /*for (Campo c : catalogoBase.getCampos()) {
                System.out.println(c.getValue());
                }*/
                catalogoBaseBC.editar(accion, cat);
                refrescarListaCatalogo();
                cargarTabla(this.lstCatalogo);
            } catch (Exception ex) {
                Logger.getLogger(CatalogosBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    protected ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = getClass().getResource("");
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed

        int rows = this.tblCatalogo.getSelectedRow();
        int accion = BD.ACCION_EDITAR;
        if (rows > 0) {
            Catalogo dto;
            try {
                dto = catalogoBaseBC.getCatalogo(new Integer(txtCodigo.getText()).intValue());
                mostrarInformacion(dto, accion, true);
            } catch (SQLException ex) {
                Logger.getLogger(CatalogosBase.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(CatalogosBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un elemento de la tabla", bd.getDescripcionAccion(accion) + " Info. " + catalogoBase.getTitulo(), JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int rows = this.tblCatalogo.getSelectedRow();
        int accion = BD.ACCION_ELIMINAR;
        if (rows > 0) {
            Catalogo dto;
            try {
                dto = catalogoBaseBC.getCatalogo(new Integer(txtCodigo.getText()).intValue());
                mostrarInformacion(dto, accion, false);
            } catch (SQLException ex) {
                Logger.getLogger(CatalogosBase.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(CatalogosBase.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un elemento de la tabla", bd.getDescripcionAccion(accion) + " Info. " + catalogoBase.getTitulo(), JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotonBuscar1ActionPerformed
        buscar();
    }//GEN-LAST:event_jBotonBuscar1ActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
 if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            buscar();
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void buscar() {
        txtCodigo.setText(null);
        if (this.txtBuscar.getText().length() > 0) {
            try {
                this.lstCatalogo = catalogoBaseBC.buscarEnCatalogo(this.txtBuscar.getText());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", 0);
            }
        } else {
            try {
                this.lstCatalogo = catalogoBaseBC.getListaCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", 0);
            }
        }
        cargarTabla(this.lstCatalogo);
    }
    
    BD bd = new BD();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.gmail.lrchfox3.controles.botones.JBotonAgregar btnAgregar;
    private com.gmail.lrchfox3.controles.botones.JBotonBuscar btnBuscar;
    private com.gmail.lrchfox3.controles.botones.JBotonCerrar btnCerrar;
    private com.gmail.lrchfox3.controles.botones.JBotonEditar btnEditar;
    private com.gmail.lrchfox3.controles.botones.JBotonEliminar btnEliminar;
    private com.gmail.lrchfox3.controles.textos.JEtiquetaBase jEtiquetaBase1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.gmail.lrchfox3.controles.textos.JEtiquetaTitulo lblTitulo;
    private java.util.List lstCatalogo;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    private javax.swing.JPanel pnlCatalogo;
    private com.gmail.lrchfox3.controles.tablas.JTablaBase tblCatalogo;
    private com.gmail.lrchfox3.controles.textos.JTextoConBoton txtBuscar;
    private javax.swing.JTextField txtCodigo;
    // End of variables declaration//GEN-END:variables
}
