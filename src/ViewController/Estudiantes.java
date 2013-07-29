/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Estudiantes.java
 *
 * Created on 03-05-2010, 04:35:14 PM
 */
package ViewController;

import com.gmail.lrchfox3.basedatos.BD;
import com.gmail.lrchfox3.utilitarios.FiltrosImportar;
import com.gmail.lrchfox3.utilitarios.Item;
import model.BC.ClaseBC;
import java.awt.Color;
import javax.swing.JOptionPane;
import model.BC.EstudianteBC;
import model.BC.EstudianteClaseBC;
import model.BC.EstudiantePeriodoBC;
import model.BC.MonitoreoBC;
import model.BC.PeriodoBC;
import model.BC.VistaEstudianteBC;

import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.ListModel;
import model.DTO.Clase;
import model.DTO.Estudiante;
import model.DTO.EstudianteClase;
import model.DTO.EstudiantePeriodo;
import model.DTO.Periodo;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

/**
 *
 * @author Administrador
 */
public class Estudiantes extends javax.swing.JInternalFrame {

    public Estudiantes() {
        inicializar();
    }

    public Estudiantes(String mensaje) {
        if (mensaje.length() <= 0) {
            inicializar();
            //Collections.copy(lstPeriodos1, lstPeriodos);
        } else {
            JOptionPane.showMessageDialog(this, mensaje, appBundle.getString("ERROR"), JOptionPane.ERROR_MESSAGE);
        }
    }

    private void inicializar() {
        try {
            initComponents();
            tblEstudiantes.setName(estudianteBase.getTitulo());
            bd.setAccion(BD.ACCION_NINGUNA);
            habilitar(BD.ACCION_NINGUNA);
            cargarTabla(this.listaEstudiantes);            

        } catch (Exception e) {
            System.out.println(appBundle.getString("ERROR") + ": " + e.getMessage());
        }
    }

    private void cargarTabla(final java.util.List lst) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JTableBinding jTableBinding = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE, lst, tblEstudiantes);
                JTableBinding.ColumnBinding columnBinding = null; //jTableBinding.addColumnBinding(ELProperty.create("${codigo}"));

                /*columnBinding.setColumnName("Código");*/
                /*columnBinding.setColumnClass(Integer.class);*/
                columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nombre}"));
                columnBinding.setColumnName(appBundle.getString("NOMBRE"));
                columnBinding.setColumnClass(String.class);
                //columnBinding.setEditable(false);g
                columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${numeroCuenta}"));
                columnBinding.setColumnName(appBundle.getString("NUMERO_CUENTA"));
                columnBinding.setColumnClass(String.class);
                //columnBinding.setEditable(false);
                columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${correo}"));
                columnBinding.setColumnName(appBundle.getString("CORREO_ELECTRONICO"));
                columnBinding.setColumnClass(String.class);
                //columnBinding.setEditable(false);
                columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${comentario}"));
                columnBinding.setColumnName(appBundle.getString("COMENTARIO"));
                columnBinding.setColumnClass(String.class);
                //columnBinding.setEditable(false);
                columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${telefono}"));
                columnBinding.setColumnName(appBundle.getString("TELEFONO"));
                columnBinding.setColumnClass(String.class);
                //columnBinding.setEditable(false);
                jTableBinding.bind();
                bindingGroup.addBinding(jTableBinding);
                tblEstudiantes.requestFocus();
                tblEstudiantes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                int[] cols = new int[]{1, 2, 3, 4};
                tblEstudiantes.setZoomCelda(cols);

                /*JTextField textField = new JTextField();
                 textField.setEditable(false);
                 textField.setBorder(BorderFactory.createEmptyBorder());
                 DefaultCellEditor editor = new DefaultCellEditor(textField);
                 editor.setClickCountToStart(1);
                 tblEstudiantes.getColumnModel().getColumn(1).setCellEditor(new StringActionTableCellEditor(editor));
                 tblEstudiantes.getColumnModel().getColumn(2).setCellEditor(new StringActionTableCellEditor(editor));
                 tblEstudiantes.getColumnModel().getColumn(3).setCellEditor(new StringActionTableCellEditor(editor));
                 tblEstudiantes.getColumnModel().getColumn(4).setCellEditor(new StringActionTableCellEditor(editor));*/
            }
        });
        //System.out.println( tblEstudiantes.getColumnName(1) );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        listaPeriodos1 = null;
        listaClases1 = null;
        try{

            listaEstudiantes = VistaEstudianteBC.getListaEstudiantes();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), appBundle.getString("ERROR"), 0);
        }
        try{
            Periodo p = new Periodo();
            p.setCodigo(-1);
            p.setPeriodo("("+ appBundle.getString("NINGUNO") +")");
            List<Periodo> lstPeriodos = PeriodoBC.getListaPeriodos();
            listaPeriodos1 = PeriodoBC.getListaPeriodos();
            lstPeriodos.add(0, p);
            listaPeriodos = lstPeriodos;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), appBundle.getString("ERROR"), 0);
        }
        try{
            Clase c = new Clase();
            c.setCodigo(-1);
            c.setNombreClase("(Ninguno)");
            c.setReferencia("");
            List<Clase> lstClases = ClaseBC.getListaClases();
            listaClases1 = ClaseBC.getListaClases();
            lstClases.add(0, c);
            listaClases = lstClases;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), appBundle.getString("ERROR"), 0);
        }
        jPanel2 = new javax.swing.JPanel();
        lblTitulo = new com.gmail.lrchfox3.controles.textos.JEtiquetaTitulo();
        txtCodigo = new com.gmail.lrchfox3.controles.textos.JTextoBase();
        txtCodigo.setVisible(false);
        jEtiquetaBase1 = new com.gmail.lrchfox3.controles.textos.JEtiquetaBase();
        cbxClases = new com.gmail.lrchfox3.controles.listas.JComboBoxBase();
        cbxPeriodos = new com.gmail.lrchfox3.controles.listas.JComboBoxBase();
        jEtiquetaBase2 = new com.gmail.lrchfox3.controles.textos.JEtiquetaBase();
        txtCodigoPeriodo = new com.gmail.lrchfox3.controles.textos.JTextoBase();
        txtCodigoPeriodo.setVisible(false);
        txtCodigoClase = new com.gmail.lrchfox3.controles.textos.JTextoBase();
        txtCodigoClase.setVisible(false);
        imgFiltroActivo = new javax.swing.JLabel();
        imgFiltroActivo.setVisible(false);
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEstudiantes = new com.gmail.lrchfox3.controles.tablas.JTablaBase();/*{
            public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }
        };*/;
        btnAgregar = new com.gmail.lrchfox3.controles.botones.JBotonAgregar();
        btnEditar = new com.gmail.lrchfox3.controles.botones.JBotonEditar();
        btnEliminar = new com.gmail.lrchfox3.controles.botones.JBotonEliminar();
        btnBuscar = new com.gmail.lrchfox3.controles.botones.JBotonBuscar();
        lblBuscar = new com.gmail.lrchfox3.controles.textos.JEtiquetaBase();
        jPanel4 = new javax.swing.JPanel();
        lblNombre = new com.gmail.lrchfox3.controles.textos.JEtiquetaBase();
        txtNoCuenta = new com.gmail.lrchfox3.controles.textos.JTextoBase();
        lblNoCuenta = new com.gmail.lrchfox3.controles.textos.JEtiquetaBase();
        txtNombre = new com.gmail.lrchfox3.controles.textos.JTextoBase();
        lblCorreo = new com.gmail.lrchfox3.controles.textos.JEtiquetaBase();
        txtCorreo = new com.gmail.lrchfox3.controles.textos.JTextoBase();
        lblComentario = new com.gmail.lrchfox3.controles.textos.JEtiquetaBase();
        lblTitulo1 = new com.gmail.lrchfox3.controles.textos.JEtiquetaTitulo();
        lblImgEstudianteInfo = new com.gmail.lrchfox3.controles.textos.JEtiquetaBase();
        lblTelefono = new com.gmail.lrchfox3.controles.textos.JEtiquetaBase();
        txtTelefono = new com.gmail.lrchfox3.controles.textos.JTextoBase();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtComentario = new com.gmail.lrchfox3.controles.textos.JComentarioBase();
        jScrollPane3 = new javax.swing.JScrollPane();
        chkLstClases = new com.gmail.lrchfox3.controles.listas.JCheckBoxList();
        jScrollPane4 = new javax.swing.JScrollPane();
        chkLstPeriodos = new com.gmail.lrchfox3.controles.listas.JCheckBoxList();
        chkClases = new javax.swing.JCheckBox();
        chkPeriodos = new javax.swing.JCheckBox();
        btnImportar = new com.gmail.lrchfox3.controles.botones.JBotonImportar();
        btnExportar = new com.gmail.lrchfox3.controles.botones.JBotonExportar();
        txtBuscar = new com.gmail.lrchfox3.controles.textos.JTextoConBoton();
        btnAceptar = new com.gmail.lrchfox3.controles.botones.JBotonAceptar();
        btnCancelar = new com.gmail.lrchfox3.controles.botones.JBotonCancelar();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setTitle(estudianteBase.getTitulo());
        setAutoscrolls(true);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setPreferredSize(new java.awt.Dimension(781, 65));

        lblTitulo.setText("Catalogo: " + estudianteBase.getTitulo());

        txtCodigo.setEditable(false);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, tblEstudiantes, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.codigo}"), txtCodigo, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        txtCodigo.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                txtCodigoCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("ViewController/Resources/application"); // NOI18N
        jEtiquetaBase1.setText(bundle.getString("CLASE")); // NOI18N

        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, listaClases, cbxClases);
        bindingGroup.addBinding(jComboBoxBinding);

        cbxClases.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxClasesActionPerformed(evt);
            }
        });

        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, listaPeriodos, cbxPeriodos);
        bindingGroup.addBinding(jComboBoxBinding);

        cbxPeriodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxPeriodosActionPerformed(evt);
            }
        });

        jEtiquetaBase2.setText(bundle.getString("PERIODO")); // NOI18N

        txtCodigoPeriodo.setEditable(false);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, cbxPeriodos, org.jdesktop.beansbinding.ELProperty.create("${selectedItem.codigo}"), txtCodigoPeriodo, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        txtCodigoClase.setEditable(false);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, cbxClases, org.jdesktop.beansbinding.ELProperty.create("${selectedItem.codigo}"), txtCodigoClase, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        imgFiltroActivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ViewController/imagenes/filter.png"))); // NOI18N
        imgFiltroActivo.setToolTipText("Quitar Filtro");
        imgFiltroActivo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        imgFiltroActivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                imgFiltroActivoMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(txtCodigoClase, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCodigoPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(558, 558, 558))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jEtiquetaBase1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxClases, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jEtiquetaBase2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxPeriodos, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(imgFiltroActivo)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodigoPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodigoClase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imgFiltroActivo)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jEtiquetaBase1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbxClases, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jEtiquetaBase2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbxPeriodos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        tblEstudiantes.setAutoCreateRowSorter(true);
        tblEstudiantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblEstudiantes.setEditingColumn(0);
        tblEstudiantes.setEditingRow(0);
        tblEstudiantes.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblEstudiantes);

        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnBuscar.setText("");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        lblBuscar.setText("Buscar");

        lblNombre.setText(bundle.getString("NOMBRE")); // NOI18N

        txtNoCuenta.setMaxCaracteres(64);
        txtNoCuenta.setRoundedCornerBorder(true);

        lblNoCuenta.setText(bundle.getString("NUMERO_CUENTA")); // NOI18N

        lblCorreo.setText(bundle.getString("CORREO_ELECTRONICO")); // NOI18N

        lblComentario.setText(bundle.getString("COMENTARIO")); // NOI18N

        lblTitulo1.setText("Detalle " + estudianteBase.getTitulo());

        lblImgEstudianteInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ViewController/imagenes/Clientes.gif"))); // NOI18N

        lblTelefono.setText(bundle.getString("TELEFONO")); // NOI18N

        txtComentario.setColumns(20);
        txtComentario.setRows(5);
        jScrollPane2.setViewportView(txtComentario);

        org.jdesktop.swingbinding.JListBinding jListBinding = org.jdesktop.swingbinding.SwingBindings.createJListBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, listaClases1, chkLstClases);
        bindingGroup.addBinding(jListBinding);

        jScrollPane3.setViewportView(chkLstClases);

        jListBinding = org.jdesktop.swingbinding.SwingBindings.createJListBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, listaPeriodos1, chkLstPeriodos);
        bindingGroup.addBinding(jListBinding);

        jScrollPane4.setViewportView(chkLstPeriodos);

        chkClases.setText(bundle.getString("CLASES")); // NOI18N
        chkClases.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkClasesStateChanged(evt);
            }
        });

        chkPeriodos.setText(bundle.getString("PERIODOS")); // NOI18N
        chkPeriodos.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkPeriodosStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(lblImgEstudianteInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTitulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNoCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblComentario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)
                            .addComponent(txtNoCuenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkClases))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkPeriodos))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblTitulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNoCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNoCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblImgEstudianteInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblComentario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkClases)
                    .addComponent(chkPeriodos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnImportar.setText("Importar");
        btnImportar.setToolTipText("Importar Hoja de Excel a Tabla...");
        btnImportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportarActionPerformed(evt);
            }
        });

        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnImportar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnImportar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.add(jPanel3);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        bd.setAccion(this.btnAgregar.getAccion());
        habilitar(bd.getAccion());
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (this.txtCodigo.getText().length() <= 0) {
            JOptionPane.showMessageDialog(this, "Debe Seleccionar un registro de la tabla. " + this.txtCodigo.getText(), "Editar Registro", 1);
        } else {
            bd.setAccion(this.btnEditar.getAccion());
            habilitar(bd.getAccion());
            try {
                setEstudiante();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), appBundle.getString("ERROR"), 0);
            }
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (this.txtCodigo.getText().length() <= 0) {
            JOptionPane.showMessageDialog(this, "Debe Seleccionar un registro de la tabla", "Eliminar Registro", 1);
        } else {
            bd.setAccion(this.btnEliminar.getAccion());
            habilitar(bd.getAccion());
            try {
                setEstudiante();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), appBundle.getString("ERROR"), 0);
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        habilitar(BD.ACCION_NINGUNA);
        limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        guardar();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnImportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportarActionPerformed
        importar();
    }//GEN-LAST:event_btnImportarActionPerformed

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        exportar();
    }//GEN-LAST:event_btnExportarActionPerformed

    private void cbxClasesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxClasesActionPerformed
        buscar();
    }//GEN-LAST:event_cbxClasesActionPerformed

    private void cbxPeriodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxPeriodosActionPerformed
        buscar();
    }//GEN-LAST:event_cbxPeriodosActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            buscar();            
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void chkClasesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkClasesStateChanged
        if (chkClases.isSelected()) {
            chkLstClases.selectAll();
        } else {
            chkLstClases.selectNone();
        }
    }//GEN-LAST:event_chkClasesStateChanged

    private void chkPeriodosStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkPeriodosStateChanged
        if (chkPeriodos.isSelected()) {
            chkLstPeriodos.selectAll();
        } else {
            chkLstPeriodos.selectNone();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_chkPeriodosStateChanged

    private void txtCodigoCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtCodigoCaretPositionChanged
        System.out.println("..............");        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoCaretPositionChanged

    private void imgFiltroActivoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgFiltroActivoMousePressed
     this.txtBuscar.setText(null);
     this.cbxClases.setSelectedIndex(0);
     this.cbxPeriodos.setSelectedIndex(0);
     this.buscar();
    }//GEN-LAST:event_imgFiltroActivoMousePressed

    private void importar() {
        try {
            ImageIcon icon = createImageIcon("/ViewController/imagenes/fileImport.png");
            List<model.DTO.Periodo> lstPeriodos = PeriodoBC.getListaPeriodos();
            Object[] possibilities = new Object[lstPeriodos.size()];
            int i = 0;
            for (Periodo periodo : lstPeriodos) {
                Item item = new Item();
                item.setItem(periodo.getPeriodo());
                item.setItemData(periodo.getCodigo());
                possibilities[i] = item;
                i++;
            }
            Item s = (Item) JOptionPane.showInputDialog(this, "Seleccione el Período Educativo:\n", "Período Educativo", JOptionPane.PLAIN_MESSAGE, icon, possibilities, "");

//If a string was returned, say so.
            if ((s != null) && (s.getItem().length() > 0)) {
                //Create a file chooser
                final JFileChooser fc = new JFileChooser();
                fc.addChoosableFileFilter(new FiltrosImportar());
                fc.setAcceptAllFileFilterUsed(false);
//In response to a button click:
                int returnVal = fc.showOpenDialog(this);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    boolean retValue = this.tblEstudiantes.importarExcelTabla();
                    //EstudianteBC.importarEstudiantesFromExcel(file.getPath());
                    if (retValue) {
                        refrescarListaEstudiantes();
                        cargarTabla(this.listaEstudiantes);
                        JOptionPane.showMessageDialog(this, "Se ha importado satisfactoriamente el archivo", "Estudiantes", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), appBundle.getString("ERROR"), 0);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), appBundle.getString("ERROR"), 0);
        }
    }

    private void exportar() {
        try {
            boolean retValue = tblEstudiantes.exportarTablaExcel();
            if (retValue) {
                JOptionPane.showMessageDialog(this, "La tabla se ha exportado exitosamente", "Exportar Tabla", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No se logro exportar la tabla", "Exportar Tabla", JOptionPane.ERROR_MESSAGE);
            }        
        } catch (Exception ex) {
            MonitoreoBC.registrarMovimiento("ERROR", "Exportar Excel", "No se logro exportar la tabla de Estudiantes, error: " + ex.getMessage() + ". ref: Estudiantes.exportar", "ADMIN");
        }

    }

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     * @param path
     * @return 
     */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Estudiantes.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    private void setEstudiante() throws Exception {
        Estudiante dto = new Estudiante();
        dto = EstudianteBC.getEstudiante(new Integer(txtCodigo.getText()).intValue());
        this.txtNombre.setText(dto.getNombre());
        this.txtNoCuenta.setText(dto.getNumeroCuenta());
        this.txtCorreo.setText(dto.getCorreo());
        this.txtComentario.setText(dto.getComentario());
        this.txtTelefono.setText(dto.getTelefono());
        seleccionarClases(dto.getCodigoEstudiante());
        seleccionarPeriodos(dto.getCodigoEstudiante());
    }

    private void seleccionarClases(int _codigo) throws SQLException, Exception {
        List<Integer> lst = EstudianteClaseBC.getEstudianteClases(_codigo);
        ListModel dlst = chkLstClases.getModel();
        int[] indices = new int[lst.size()];
        int j = 0;
        for (Integer integer : lst) {
            for (int i = 0; i < dlst.getSize(); i++) {
                if (dlst.getElementAt(i) instanceof Clase) {
                    if (((Clase) dlst.getElementAt(i)).getCodigo() == integer.intValue()) {
                        indices[j] = i;
                        break;
                    }
                }
            }
            j++;
        }
        chkLstClases.setCheckBoxListSelectedIndices(indices);
        if (indices.length == dlst.getSize()) {
            chkClases.setSelected(true);
        }
    }

    private void seleccionarPeriodos(int _codigo) throws SQLException, Exception {
        List<Integer> lst = EstudiantePeriodoBC.getEstudiantePeriodos(_codigo);
        ListModel dlst = chkLstPeriodos.getModel();
        int[] indices = new int[lst.size()];
        int j = 0;
        for (Integer integer : lst) {
            for (int i = 0; i < dlst.getSize(); i++) {
                if (dlst.getElementAt(i) instanceof Periodo) {
                    if (((Periodo) dlst.getElementAt(i)).getCodigo() == integer.intValue()) {
                        indices[j] = i;
                        break;
                    }
                }
            }
            j++;
        }
        chkLstPeriodos.setCheckBoxListSelectedIndices(indices);
        if (indices.length == dlst.getSize()) {
            chkPeriodos.setSelected(true);
        }
    }

    private void buscar() {
        txtCodigo.setText(null);
        Integer codigoClase = null, codigoPeriodo = null;
        if (txtCodigoClase.getText().length() > 0) {
            codigoClase = Integer.valueOf(txtCodigoClase.getText());
            if (codigoClase.intValue() < 0) {
                codigoClase = null;
            }
        }
        if (txtCodigoPeriodo.getText().length() > 0) {
            codigoPeriodo = Integer.valueOf(txtCodigoPeriodo.getText());
            if (codigoPeriodo.intValue() < 0) {
                codigoPeriodo = null;
            }
        }
        if (this.txtBuscar.getText().length() > 0) {
            try {

                this.listaEstudiantes = VistaEstudianteBC.buscarPorCriterio(this.txtBuscar.getText(), codigoClase, codigoPeriodo);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), appBundle.getString("ERROR"), 0);
            }
        } else {
            try {
                this.listaEstudiantes = VistaEstudianteBC.buscarPorCriterio(this.txtBuscar.getText(), codigoClase, codigoPeriodo);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), appBundle.getString("ERROR"), 0);
            }
        }
        cargarTabla(this.listaEstudiantes);

        if (this.txtBuscar.getText().length() > 0 || txtCodigoClase.getText().length() > 0 || txtCodigoPeriodo.getText().length() > 0) {
            imgFiltroActivo.setVisible(true);
        } else {
            imgFiltroActivo.setVisible(false);

        }
    }

    private void guardar() {
        if (validar()) {
            Estudiante dto = new Estudiante();
            if (txtCodigo.getText().length() > 0) {
                dto.setCodigoEstudiante(new Integer(txtCodigo.getText()).intValue());
            }
            dto.setNumeroCuenta(this.txtNoCuenta.getText());
            dto.setNombre(this.txtNombre.getText());
            dto.setCorreo(this.txtCorreo.getText());
            dto.setComentario(this.txtComentario.getText());
            dto.setTelefono(this.txtTelefono.getText());
            boolean retValue;
            try {
                retValue = EstudianteBC.editar(bd.getAccion(), dto);
                if (retValue) {
                    EstudianteClaseBC.editar(BD.ACCION_ELIMINAR, dto.getCodigoEstudiante());
                    for (Object item : chkLstClases.getCheckBoxListSelectedValues()) {
                        if (item instanceof Clase) {
                            EstudianteClase ec = new EstudianteClase();
                            ec.setCodigoEstudiante(dto.getCodigoEstudiante());
                            ec.setCodigoClase(((Clase) item).getCodigo());
                            EstudianteClaseBC.editar(BD.ACCION_AGREGAR, ec);
                        }
                    }

                    EstudiantePeriodoBC.editar(BD.ACCION_ELIMINAR, dto.getCodigoEstudiante());
                    for (Object item : chkLstPeriodos.getCheckBoxListSelectedValues()) {
                        if (item instanceof Periodo) {
                            EstudiantePeriodo ep = new EstudiantePeriodo();
                            ep.setCodigoEstudiante(dto.getCodigoEstudiante());
                            ep.setCodigoPeriodo(((Periodo) item).getCodigo());
                            EstudiantePeriodoBC.editar(BD.ACCION_ELIMINAR, ep);
                        }
                    }

                    refrescarListaEstudiantes();
                    cargarTabla(this.listaEstudiantes);
                    JOptionPane.showMessageDialog(this, "Operación realizada con Exito", "Estudiantes", 1);
                    bd.setAccion(BD.ACCION_NINGUNA);
                    habilitar(bd.getAccion());
                    limpiar();
                    try {
                        this.listaEstudiantes = EstudianteBC.getListaEstudiantes();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, ex.getMessage(), appBundle.getString("ERROR"), 0);
                        MonitoreoBC.registrarMovimiento("ERROR", "Buscar", "Error al obtener lista de Estudiantes, error: " + ex.getMessage() + ". ref: Estudiantes.guardar", "ADMIN");
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), appBundle.getString("ERROR"), 0);
                MonitoreoBC.registrarMovimiento("ERROR", bd.getDescripcionAccion(), bd.getDescripcionAccion() + " Estudiante, error: " + ex.getMessage() + ". ref: Estudiantes.guardar", "ADMIN");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Existen campos que son obligatorios ingresar", "Estudiantes", 0);
        }
    }

    private boolean validar() {
        boolean value = true;
        this.lblNoCuenta.inicializar();
        this.lblNombre.inicializar();
        if (this.txtNoCuenta.getText().length() <= 0) {
            this.lblNoCuenta.setForeground(Color.red);
            value = false;
        }
        if (this.txtNoCuenta.getText().length() <= 0) {
            this.lblNombre.setForeground(Color.red);
            value = false;
        }
        return value;
    }

    private void habilitar(int accion) {
        this.btnAgregar.setEnabled(false);
        this.btnEditar.setEnabled(false);
        this.btnEliminar.setEnabled(false);
        if (accion == BD.ACCION_NINGUNA || accion == BD.ACCION_ELIMINAR) {
            if (accion == BD.ACCION_ELIMINAR) {
                this.btnAceptar.setEnabled(true);
                this.btnCancelar.setEnabled(true);
            } else {
                this.btnEliminar.setEnabled(true);
                this.btnEditar.setEnabled(true);
                this.btnAgregar.setEnabled(true);
                this.btnAceptar.setEnabled(false);
                this.btnCancelar.setEnabled(false);
            }
            this.txtNoCuenta.setEditable(false);
            this.txtNombre.setEditable(false);
            this.txtCorreo.setEditable(false);
            this.txtComentario.setEditable(false);
            this.txtTelefono.setEditable(false);
            chkLstClases.setEnabled(false);
            chkLstPeriodos.setEnabled(false);
            chkClases.setEnabled(false);
            chkPeriodos.setEnabled(false);
        } else if (accion == BD.ACCION_AGREGAR || accion == BD.ACCION_EDITAR) {
            this.txtNoCuenta.setEditable(true);
            this.txtNombre.setEditable(true);
            this.txtCorreo.setEditable(true);
            this.txtComentario.setEditable(true);
            this.txtTelefono.setEditable(true);
            chkLstClases.setEnabled(true);
            chkLstPeriodos.setEnabled(true);
            chkClases.setEnabled(true);
            chkPeriodos.setEnabled(true);
            this.btnAceptar.setEnabled(true);
            this.btnCancelar.setEnabled(true);
            this.txtNoCuenta.requestFocus();
        }
    }

    private void refrescarListaEstudiantes() {
        try {
            listaEstudiantes.clear();
            listaEstudiantes = VistaEstudianteBC.getListaEstudiantes();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), appBundle.getString("ERROR"), 0);
        }
    }

    private void limpiar() {
        this.txtNoCuenta.setText(null);
        this.txtNombre.setText(null);
        this.txtCorreo.setText(null);
        this.txtComentario.setText(null);
        this.txtTelefono.setText(null);
        this.txtCodigo.setText(null);
        this.lblNombre.setForeground(Color.black);
        this.lblNoCuenta.setForeground(Color.black);
        this.chkClases.setSelected(false);
        this.chkPeriodos.setSelected(false);
        this.chkLstClases.selectNone();
        this.chkLstPeriodos.selectNone();
    }

    private void cargarPeriodos() {
    }
    /**
     * @param args the command line arguments
     */
    /*public static void main(String args[]) {
     java.awt.EventQueue.invokeLater(new Runnable() {

     public void run() {
     new Estudiantes().setVisible(true);
     }
     });
     }*/
    
    private final  BD bd = new BD();
    java.util.ResourceBundle appBundle = java.util.ResourceBundle.getBundle("ViewController/Resources/application"); // NOI18N
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.gmail.lrchfox3.controles.botones.JBotonAceptar btnAceptar;
    private com.gmail.lrchfox3.controles.botones.JBotonAgregar btnAgregar;
    private com.gmail.lrchfox3.controles.botones.JBotonBuscar btnBuscar;
    private com.gmail.lrchfox3.controles.botones.JBotonCancelar btnCancelar;
    private com.gmail.lrchfox3.controles.botones.JBotonEditar btnEditar;
    private com.gmail.lrchfox3.controles.botones.JBotonEliminar btnEliminar;
    private com.gmail.lrchfox3.controles.botones.JBotonExportar btnExportar;
    private com.gmail.lrchfox3.controles.botones.JBotonImportar btnImportar;
    private com.gmail.lrchfox3.controles.listas.JComboBoxBase cbxClases;
    private com.gmail.lrchfox3.controles.listas.JComboBoxBase cbxPeriodos;
    private javax.swing.JCheckBox chkClases;
    private com.gmail.lrchfox3.controles.listas.JCheckBoxList chkLstClases;
    private com.gmail.lrchfox3.controles.listas.JCheckBoxList chkLstPeriodos;
    private javax.swing.JCheckBox chkPeriodos;
    private javax.swing.JLabel imgFiltroActivo;
    private com.gmail.lrchfox3.controles.textos.JEtiquetaBase jEtiquetaBase1;
    private com.gmail.lrchfox3.controles.textos.JEtiquetaBase jEtiquetaBase2;
    private javax.swing.JPanel jPanel1;
    private model.DTO.Estudiante estudianteBase = new model.DTO.Estudiante();
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private com.gmail.lrchfox3.controles.textos.JEtiquetaBase lblBuscar;
    private com.gmail.lrchfox3.controles.textos.JEtiquetaBase lblComentario;
    private com.gmail.lrchfox3.controles.textos.JEtiquetaBase lblCorreo;
    private com.gmail.lrchfox3.controles.textos.JEtiquetaBase lblImgEstudianteInfo;
    private com.gmail.lrchfox3.controles.textos.JEtiquetaBase lblNoCuenta;
    private com.gmail.lrchfox3.controles.textos.JEtiquetaBase lblNombre;
    private com.gmail.lrchfox3.controles.textos.JEtiquetaBase lblTelefono;
    private com.gmail.lrchfox3.controles.textos.JEtiquetaTitulo lblTitulo;
    private com.gmail.lrchfox3.controles.textos.JEtiquetaTitulo lblTitulo1;
    private java.util.List listaClases;
    private java.util.List listaClases1;
    private java.util.List listaEstudiantes;
    private java.util.List listaPeriodos;
    private java.util.List listaPeriodos1;
    private com.gmail.lrchfox3.controles.tablas.JTablaBase tblEstudiantes;
    private com.gmail.lrchfox3.controles.textos.JTextoConBoton txtBuscar;
    private com.gmail.lrchfox3.controles.textos.JTextoBase txtCodigo;
    private com.gmail.lrchfox3.controles.textos.JTextoBase txtCodigoClase;
    private com.gmail.lrchfox3.controles.textos.JTextoBase txtCodigoPeriodo;
    private com.gmail.lrchfox3.controles.textos.JComentarioBase txtComentario;
    private com.gmail.lrchfox3.controles.textos.JTextoBase txtCorreo;
    private com.gmail.lrchfox3.controles.textos.JTextoBase txtNoCuenta;
    private com.gmail.lrchfox3.controles.textos.JTextoBase txtNombre;
    private com.gmail.lrchfox3.controles.textos.JTextoBase txtTelefono;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    private void setLocationRelativeTo(Object object) {
    }
}
