/*
 * frmBusca.java
 *
 * Created on December 18, 2006, 12:00 AM
 */

package com.gmail.lrchfox3.formularios;
import java.sql.ResultSet;
import com.gmail.lrchfox3.utilitarios.Pantalla;
import com.gmail.lrchfox3.utilitarios.Propiedades;
import com.gmail.lrchfox3.controles.tablas.JTablaBase;
import com.gmail.lrchfox3.controles.tablas.JScrollTablaBase;
import com.gmail.lrchfox3.controles.tablas.ParametrosBusqueda;
import javax.swing.JTable;
import com.gmail.lrchfox3.basedatos.BD;

/**
 *
 * @author  Luis R. Chinchilla H.
 */
public class FrmBuscar extends javax.swing.JDialog {
    
    private int defaultPosX = 15;
    private int defaultPosY = 55;
    private int defaultAlto = 20;
    private int espacioCampos = 0;
    
    private int altoMaximoVentana = 0;
    private int anchoMaximoVentana = 0;       
            
    private void inicializar() throws Exception {        
        try {
            crearBuscadores(params.getTitulos(), params.getTiposDatos());
            tblBusqueda.setFilasDesplegar(1);                
            tblBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    tblBusquedakeyPressed(evt);
                }
            });
        
            
            crearLista(params.getConsulta());                    
            
            jFormattedTextFieldNoFilasChanged(null);
            jFormattedTextFieldNoFilas.setVisible(true);
            jFormattedTextFieldNoFilas.repaint();                
            repaint(); 
            tblLista.seleccionarFila(0);
        }
        catch (Exception ex) {
            com.gmail.lrchfox3.utilitarios.Mensajes msg = new com.gmail.lrchfox3.utilitarios.Mensajes();
            msg.mensajesErrores(null, ex.getMessage(), "Búsqueda");
        } 
    }
    
    public FrmBuscar(BD bd, java.awt.Frame parent, boolean modal, ParametrosBusqueda params) {
        super(parent, modal);
        try {
                this.bd = bd;
                this.params = params;
                initComponents();                
                inicializar();                                
                
                if (tblLista.getRowCount() <= 0) {
                    String datos[] = {"para Búsqueda."};
                    new com.gmail.lrchfox3.utilitarios.Mensajes().mensajesErrores(this, "No Existen Registros para B�squeda.", "B�squeda");
                    btnAceptar.setVisible(false);
                    btnCancelarClicked(null);
                }
        }
        catch (Exception ex){
            com.gmail.lrchfox3.utilitarios.Mensajes msg = new com.gmail.lrchfox3.utilitarios.Mensajes();
            msg.mensajesErrores(null, ex.getMessage(), "Búsqueda");
        }        
    }
    
    /** Creates new form frmBusca */
    public FrmBuscar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();        
    } 
            
    public int getDefaultPosX() {        
        return defaultPosX;
    }

    public void setDefaultPosX(int posXDefault) {
        this.defaultPosX = posXDefault;
    }

    public int getDefaultPosY() {
        return defaultPosY;
    }

    public void setDefaultPosY(int posYDefault) {
        this.defaultPosY = posYDefault;
    }

    public int getAltoDefault() {
        return defaultAlto;
    }
    
    public int getEspacioCampos() {
        return espacioCampos;
    }

    public void setEspacioCampos(int espacioCampos) {
        this.espacioCampos = espacioCampos;
    }
    
    public Pantalla getResolucionPantalla() {
        Pantalla p = new Pantalla();        
        p.resolucion();
        return p;
    }
        
    public void setTamanyos() {
        Pantalla p = getResolucionPantalla();
        int anchoMax = p.getAncho();
        int altoMax = p.getAlto();
        int altoWindow = tblLista.getAltoScroll() + tblLista.getUbicacionScrollY() + getDefaultPosY() + 20;
        int anchoWindow = tblBusqueda.getAnchoScroll() + ((getDefaultPosX() + 2) * 2);
                        
        btnCancelar.setLocation(tblBusqueda.getUbicacionScrollX() + tblBusqueda.getAnchoScroll() - btnCancelar.getWidth(), 
                                tblLista.getAltoScroll() + tblLista.getUbicacionScrollY() + 5);
        btnAceptar.setLocation(btnCancelar.getX() - (btnAceptar.getWidth() + 10), btnCancelar.getY());
                
        setMaximumSize(new java.awt.Dimension(anchoMax, altoMax));                
        if (anchoMax <= anchoWindow) anchoWindow =  anchoMax;        
        setSize(anchoWindow, altoWindow);                
    }
        
    private void crearBuscadores(String[] titulos, Class[] tiposDatos) throws Exception {
        Object[][] celdas = new Object[1][titulos.length];
        for (int i = 0; i < titulos.length; i++) celdas[0][i] = "";
        
        tblBusqueda.setTamanyos(params.getTamanyos());  
        tblBusqueda.crearModelo(celdas, titulos);        
        tblBusqueda.setColumnasEditables(true);
        tblBusqueda.setUbicacionScroll(getDefaultPosX(), getDefaultPosY());
        tblBusqueda.setTipoScrollHorizontal(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);        
        tblBusqueda.setRowSelectionAllowed(false);            
    }
    
    
    private void crearLista(String consulta) throws Exception {                    
        tblLista.setBD(bd);
        tblLista.setTamanyos(params.getTamanyos());  
        tblLista.crearModelo(consulta);        
        tblLista.setUbicacionScroll(getDefaultPosX(), tblBusqueda.getUbicacionScrollY() + tblBusqueda.getAltoScroll() + 2);
        tblLista.setTableHeader(null);        
        JTablaBase[] jt = {tblBusqueda};
        tblLista.setJTablesSincronizar(jt);        
    }            
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jDesktopPaneBuscar = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        jFormattedTextFieldNoFilas = new javax.swing.JFormattedTextField();
        btnAceptar = new com.gmail.lrchfox3.controles.botones.JBotonAceptar();
        btnCancelar = new com.gmail.lrchfox3.controles.botones.JBotonCancelar();
        scrllTblLista = new com.gmail.lrchfox3.controles.tablas.JScrollTablaBase();
        tblLista = new com.gmail.lrchfox3.controles.tablas.JTablaBase();
        scrllTblBusqueda = new com.gmail.lrchfox3.controles.tablas.JScrollTablaBase();
        tblBusqueda = new com.gmail.lrchfox3.controles.tablas.JTablaBase();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("B\u00fasqueda");
        setName("busqueda");
        setResizable(false);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                resize(evt);
            }
        });

        jDesktopPaneBuscar.setBackground(new java.awt.Color(236, 233, 216));
        jDesktopPaneBuscar.setAutoscrolls(true);
        jLabel1.setText("No. Filas");
        jLabel1.setToolTipText("No. de Filas Visibles");
        jLabel1.setBounds(15, 10, 60, 18);
        jDesktopPaneBuscar.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLabel1.getAccessibleContext().setAccessibleName(null);

        jFormattedTextFieldNoFilas.setBorder(new javax.swing.border.EtchedBorder());
        jFormattedTextFieldNoFilas.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextFieldNoFilas.setText("20");
        jFormattedTextFieldNoFilas.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11));
        jFormattedTextFieldNoFilas.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jFormattedTextFieldNoFilasChanged(evt);
            }
        });

        jFormattedTextFieldNoFilas.setBounds(14, 26, 48, 20);
        jDesktopPaneBuscar.add(jFormattedTextFieldNoFilas, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarClicked(evt);
            }
        });

        btnAceptar.setBounds(402, 408, 106, 26);
        jDesktopPaneBuscar.add(btnAceptar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarClicked(evt);
            }
        });

        btnCancelar.setBounds(526, 412, 112, 26);
        jDesktopPaneBuscar.add(btnCancelar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        tblLista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrllTblLista.setViewportView(tblLista);

        scrllTblLista.setBounds(36, 148, 272, 116);
        jDesktopPaneBuscar.add(scrllTblLista, javax.swing.JLayeredPane.DEFAULT_LAYER);

        tblBusqueda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrllTblBusqueda.setViewportView(tblBusqueda);

        scrllTblBusqueda.setBounds(18, 56, 198, 48);
        jDesktopPaneBuscar.add(scrllTblBusqueda, javax.swing.JLayeredPane.DEFAULT_LAYER);

        getContentPane().add(jDesktopPaneBuscar, java.awt.BorderLayout.CENTER);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-800)/2, (screenSize.height-500)/2, 800, 500);
    }
    // </editor-fold>//GEN-END:initComponents

    private void btnCancelarClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarClicked
        dispose();        
    }//GEN-LAST:event_btnCancelarClicked

    private void btnAceptarClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarClicked
        int posCamposRetorno = params.getPosCamposRetorno().length;
        Object[] valoresRetorno = new Object[posCamposRetorno];
        
        int fila = tblLista.getSelectedRow();
        if (fila > -1 ) {
            for (int i = 0; i < posCamposRetorno; i ++)
                valoresRetorno[i] = tblLista.getValueAt(fila, i); 
            
            params.setValoresRetorno(valoresRetorno);
        }           
        
        dispose();
    }//GEN-LAST:event_btnAceptarClicked

    private void jFormattedTextFieldNoFilasChanged(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jFormattedTextFieldNoFilasChanged
        String noFilas = jFormattedTextFieldNoFilas.getText();
        if ((noFilas != null) && (!noFilas.equals("")))
            tblLista.setFilasDesplegar(new Integer(noFilas).intValue()); 
            setTamanyos();
            tblLista.repaint();
            repaint();
    }//GEN-LAST:event_jFormattedTextFieldNoFilasChanged

    private void resize(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_resize
        Pantalla p = getResolucionPantalla();
        setLocation((p.getAncho() - getWidth()) / 2,  (p.getAlto() - getHeight()) / 2);
        
    }//GEN-LAST:event_resize
    
    private void tblBusquedakeyPressed(java.awt.event.KeyEvent evt) {
        int keyCode = evt.getKeyCode();
        String consulta = params.getConsulta(); 
        
        if ((keyCode == evt.VK_ENTER) || (keyCode == evt.VK_TAB))
            try {
                int noColumnas = tblBusqueda.getColumnCount();
                
                for (int i = 0; i < noColumnas; i ++) {
                    Object valor = null;

                    if (i == tblBusqueda.getEditingColumn()) {
                        if (javax.swing.JTextField.class.equals(tblBusqueda.getEditorComponent().getClass())) {
                            javax.swing.JTextField jtf = (javax.swing.JTextField)tblBusqueda.getEditorComponent();
                            valor = jtf.getText();
                        }
                    }
                    else valor = tblBusqueda.getValueAt(0, i);
                    
                    if (valor != null && !valor.equals("")) {
                        if (tblLista.getColumnClass(i).equals(Number.class)) valor = " = " + valor;
                        else                                                 valor = " like '%" + valor + "%' ";
                        
                        if (consulta.toUpperCase().contains(" WHERE"))
                                consulta = consulta + " AND ";                        
                        else    consulta = consulta + " WHERE ";
                        
                        consulta = consulta + params.getCampo(i) + valor.toString();
                    }
                }
                
                tblLista.crearModelo(consulta);
            }
            catch (Exception e) {
                System.out.println("error :" + e.getMessage());
            }
            
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmBuscar(new javax.swing.JFrame(), true).setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.gmail.lrchfox3.controles.botones.JBotonAceptar btnAceptar;
    private com.gmail.lrchfox3.controles.botones.JBotonCancelar btnCancelar;
    private javax.swing.JDesktopPane jDesktopPaneBuscar;
    private javax.swing.JFormattedTextField jFormattedTextFieldNoFilas;
    private javax.swing.JLabel jLabel1;
    private com.gmail.lrchfox3.controles.tablas.JScrollTablaBase scrllTblBusqueda;
    private com.gmail.lrchfox3.controles.tablas.JScrollTablaBase scrllTblLista;
    private com.gmail.lrchfox3.controles.tablas.JTablaBase tblBusqueda;
    private com.gmail.lrchfox3.controles.tablas.JTablaBase tblLista;
    // End of variables declaration//GEN-END:variables
    private BD bd = null;
    public com.gmail.lrchfox3.controles.tablas.ParametrosBusqueda params;
}
