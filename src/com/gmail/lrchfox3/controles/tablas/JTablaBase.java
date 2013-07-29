/*
 * cargarTabla.java
 *
 * Created on December 18, 2006, 9:48 PM
 *
 */
package com.gmail.lrchfox3.controles.tablas;

import java.awt.Color;
import java.awt.Component;
import java.util.Map;
import java.util.ArrayList;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.swing.table.TableColumnModel;
import javax.swing.table.DefaultTableModel;

import javax.swing.table.TableCellRenderer;
import com.gmail.lrchfox3.basedatos.BD;
import com.gmail.lrchfox3.controles.textos.JTextoBase;
import com.gmail.lrchfox3.utilitarios.Excel;
import com.gmail.lrchfox3.utilitarios.FiltrosImportar;
import com.gmail.lrchfox3.utilitarios.Mensajes;
import com.gmail.lrchfox3.utilitarios.Item;
import com.gmail.lrchfox3.utilitarios.Propiedades;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import javax.swing.JFileChooser;

/**
 *
 * @author Luis R. Chinchilla H.
 */
public class JTablaBase extends javax.swing.JTable { //tablas.JTabla {

    private boolean aceptaTecla = false;            // indica si se puede teclas dentro de la tabla.
    private int altoFila = 18;                      // altura de cada fila
    private int filasDesplegar = 1;                 // numero de filas a desplegar en la tabla
    private int alto = getAlto();                   // altura de la tabla
    private Vector clasesColumnas = new Vector();   // vector q tiene a q clase pertenece cada columna
    private boolean actualizacionBD = false;       //
    private boolean autoInsertar = false;       // Indica que se genera una fila para insercion automaticamente cuando es necesario
    private Vector filasAccionBD = new Vector();    // Indica alguna accion que se debe ejecutar en la BD de una fila (agregar, editar, etc)
    private Object[][] valoresExternos;             // definicion de los valores externos que se despliegan en una tabla
    // la primer columna contiene el indice del campo del valor externo
    // el segundo campo es la sentencia sql de donde se obtiene el valor externo.
    private Map condicionesEdicionCelda = new Hashtable();  //formulas con condiciones para indicar si una celda es editable
    private Map formulasCelda = new Hashtable();  //formulas que calculan el valor de una celda
    private Map formulasValidacionCelda = new Hashtable();  //formula que valida si el valor de una celda cumple una condicion
    private String[] titulos;
    private int[] celdasZoom;
    private int[] celdasEdicionZoom;
    private int[] tamanyos;
    private int[] columnasInvisibles;
    private boolean jScroll;
    private Mensajes msg = new Mensajes();
    BD bd = null;

    public JTablaBase() {
        inicializar();
    }

    private void inicializar() {
        addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                celdaMouseClicked(evt);
            }
        });

        addKeyListener(new java.awt.event.KeyAdapter() {

            public void keyTyped(java.awt.event.KeyEvent evt) {
                keyTablaTyped(evt);
            }
        });

        addFocusListener(new java.awt.event.FocusAdapter() {

            public void focusLost(java.awt.event.FocusEvent evt) {
                loseFocus(evt);
            }
        });

        if (getTableHeader() != null) {
            getTableHeader().setBackground(new java.awt.Color(236, 233, 216));
        }
        if (getRowCount() > 0) {
            setRowSelectionInterval(0, 0);
        }
        setSelectionMode(0);
        setAltoFila(getAltoFila());
        setFont(new Propiedades().getFontTextos());
        getTableHeader().setFont(new Propiedades().getFontEtiquetas());
        setAutoResizeMode(AUTO_RESIZE_OFF);
        setRowSelectionAllowed(true);
    }

    private void celdaMouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() >= 2) {
            int fila = getSelectedRow();
            if (fila >= 0) {
                int columna = getSelectedColumn();
                if (columna >= 0) {
                    Object valor = getValueAt(fila, columna);
                    String valorCelda;

                    if (valor == null) {
                        valorCelda = "";
                    } else {
                        valorCelda = valor.toString();
                    }

                    if (isCeldaZoom(columna)) {
                        com.gmail.lrchfox3.formularios.FrmZoomCelda celda = new com.gmail.lrchfox3.formularios.FrmZoomCelda(new javax.swing.JFrame(), true, getColumnName(columna), valorCelda, isCeldaEdicionZoom(columna));
                        celda.setVisible(true);
                        setValorCelda(fila, columna, celda.valor);
                    }
                }
            }
        }
    }

    /**
     * Define la BD que se va a utilizar para manipular los datos relacionados
     * al Arbol.
     *
     * @param BD Base de Datos que se utilizar�.
     */
    public void setBD(BD bd) {
        this.bd = bd;
    }

    public void keyTablaTyped(java.awt.event.KeyEvent evt) {
        int fila = getSelectedRow();
        int columna = getSelectedColumn();

        if (!isCellEditable(fila, columna)) {
            evt.consume();
        } else {
            char caracter = evt.getKeyChar();
            //int codigo = evt.getKeyCode();  //8 backspace //18 alt

            if (caracter == '\b') {
                editCellAt(fila, columna);
            }
        }
    }

    /**
     *
     */
    public void loseFocus(java.awt.event.FocusEvent evt) {
        javax.swing.table.TableCellEditor cellEditor = getCellEditor();
        if (cellEditor != null) {
            cellEditor.getCellEditorValue();
        }
    }

    /**
     *
     */
    public void crearModelo(String sql, String[] titulos) throws SQLException, Exception {
        setTitulos(titulos);
        crearModelo(sql);
    }

    public void crearModelo(String sql) throws SQLException, Exception {
        Statement st = bd.crearSentencia();
        ResultSet rs = bd.ejecutarSentencia(st, sql);
        int columnas = rs.getMetaData().getColumnCount();
        ArrayList registros = new ArrayList();
        while (rs.next()) {
            Object[] registro = new Object[columnas];
            for (int i = 0; i < columnas; i++) {
                registro[i] = rs.getObject(i + 1);
            }

            registros.add(registro);
        }

        Object[][] celdas = new Object[registros.size()][columnas];
        Object[] celda = registros.toArray();
        for (int i = 0; i < celdas.length; i++) {
            celdas[i] = (Object[]) celda[i];
        }

        crearModelo(celdas);
    }

    public void crearModelo(Object[][] celdas, String[] titulos) throws SQLException, Exception {
        setTitulos(titulos);
        crearModelo(celdas);
    }

    public void crearModelo(int filas, int columnas) throws SQLException, Exception {
        Object[][] celdas = new Object[filas][columnas];
        crearModelo(celdas);
    }

    public void crearModelo(Object[][] celdas) throws SQLException, Exception {
        setTitulosDefault(celdas);
        setClasesDefault(celdas);
        celdas = getValoresExternos(celdas);
        ModeloTabla modelo = new ModeloTabla(this, celdas, getTitulos());
        modelo.setBD(bd);
        setModel(modelo);
        inicializarModelo();
        this.getRowCount();
    }

    public void inicializarModelo() {
        if (columnasInvisibles != null) {
            setColumnasVisible(columnasInvisibles, false);
        }
        desplegarTitulos();
        if (getRowCount() > 0) {
            setRowSelectionInterval(0, 0);
        }
        setAnchoColumnas();
        setColumnasEditables(false);

        if (getActualizacionBD()) {
            int fila = getRowCount();
            filasAccionBD.setSize(fila);
            setAccionBDFilas(BD.ACCION_NINGUNA);
            insertarFilaAutomatica(fila);
            seleccionarFila(fila);
        }
    }

    public Class getColumnClass(int c) {
        int l = clasesColumnas.size();
        if ((l == 0) && ((l - 1) < c)) {
            return String.class;
        }
        return (Class) clasesColumnas.get(c);
    }

    public void setColumnClass(int indice, Class clase) {
        clasesColumnas.set(indice, clase);
    }

    public void setClasesDefault(Object[][] celdas) {
        //tipo de objetos y datos de columnas
        if (celdas != null && celdas.length > 0) {
            for (int i = 0; i < celdas[0].length; i++) {
                clasesColumnas.add(String.class);
            }
        }
    }

    public void setActualizacionBD(boolean actualizacionBD) {
        this.actualizacionBD = actualizacionBD;
    }

    /**
     * Indica si los valores que se han modificado en las celdas de una tabla deben 
     * ser actualizados en la Base de Datos.
     *
     * @return True si permite que se actualizen valores en la BD, False si no se salvaran en la BD.
     */
    public boolean getActualizacionBD() {
        return actualizacionBD;
    }

    public int getAccionBDFila(int fila) {
        int accion = BD.ACCION_NINGUNA;
        if (fila >= filasAccionBD.size()); else {
            Object o = filasAccionBD.get(fila);
            if (o != null) {
                accion = (Integer) o;
            }
        }
        return accion;
    }

    public void setAccionBDFila(int fila, int accion) {
        if (getActualizacionBD()) {
            if (getAccionBDFila(fila) == BD.ACCION_AGREGAR); else {
                filasAccionBD.set(fila, accion);
            }
        }
    }

    public void setAccionBDFilas(int accion) {
        if (!getActualizacionBD()) {
            return;
        }

        int filas = filasAccionBD.size();
        for (int i = 0; i < filas; i++) {
            setAccionBDFila(i, accion);
        }
    }

    public boolean isAutoInsertar() {
        return autoInsertar;
    }

    public void setAutoInsertar(boolean autoInsertar) {
        this.autoInsertar = autoInsertar;
    }

    /**
     * @param fila Fila en la que se encuentra posicionado actualmente
     */
    private void insertarFilaAutomatica(int fila) {
        if (getActualizacionBD()) {
            if (fila == getRowCount()) {
                if (isAutoInsertar()) {
                    insertarFila(fila);
                }
            }
        }
    }

    /**
     * Inserta una nueva fila en la tabla.
     */
    public void insertarFila(int fila) {
        Object[] objeto = null;
        javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) getModel();

        modelo.insertRow(fila, objeto);
        filasAccionBD.setSize(fila + 1);
        setAccionBDFila(fila, BD.ACCION_NUEVO_REGISTRO);
        //seleccionarFila(fila);
    }

    /**
     * Elimina una fila de la tabla.
     */
    public void eliminarFila(int fila) {
        javax.swing.table.TableCellEditor cellEditor = getCellEditor();
        if (cellEditor != null) {
            cellEditor.stopCellEditing();
        }
        javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) getModel();
        modelo.removeRow(fila);
        filasAccionBD.remove(fila);

        int filasTabla = getRowCount() - 1;
        if (fila <= filasTabla) {
            seleccionarFila(fila);
        } else {
            seleccionarFila(0);
        }
    }

    /**
     *
     */
    public Object[] getValoresFila(int fila) {
        Object[] valores = new Object[getColumnCount()];
        com.gmail.lrchfox3.utilitarios.Objetos obj = new com.gmail.lrchfox3.utilitarios.Objetos();

        for (int i = 0; i < valores.length; i++) {
            Object valor = getValueAt(fila, i);
            if (valor != null) {
                if (obj.tipoObjeto(valor) == obj.TIPO_ITEM) {
                    valor = ((Item) valor).getItemData();
                }
                if (valor.equals("")) {
                    valor = null;
                }
            }
            valores[i] = valor;
        }
        return valores;
    }

    /**
     * Setea los valores de un objeto en una fila de una tabla
     * @param fila Fila en la cual se desean colocar los valores
     * @param valores arreglo de objetos que contiene los valores que se colocaran en la fila
     */
    public void setValoresFila(int fila, Object[] valores) throws SQLException, Exception {
        for (int columna = 0; columna < valores.length; columna++) {
            int indice = isValorExterno(columna);

            if (indice > -1) {
                String sql = valoresExternos[indice][1].toString();
                Object[] reemplazos = getReemplazos(sql);

                for (int j = 0; j < reemplazos.length; j++) {
                    int c = Integer.valueOf(reemplazos[j].toString()).intValue();
                    String val = "";
                    if (valores[c] != null) {
                        val = valores[c].toString();
                    }
                    if (getColumnClass(c).equals(String.class)) {
                        val = "'" + val + "'";
                    }
                    sql = sql.replaceAll("\\?" + c, val);

                    Item item = new Item();
                    item.cargarItem(bd, sql);
                    valores[columna] = item;
                }
            }
            setValorCelda(fila, columna, valores[columna]);
        }
    }

    /**
     * Cambia el valor de una celda espec�fica.
     *
     * @param fila Fila a la que pertenece la celda.
     * @param columna Columna a la que pertenece la celda.
     * @param valor Valor que se asigna a la celda.
     */
    public void setValorCelda(int fila, int columna, Object valor) {
        Object valorActual = getValueAt(fila, columna);
        int indice = isValorExterno(columna);

        if (valorActual == null && (valor == null || valor.equals(""))); else if ((valorActual == null && valor != null) || (valorActual != null && valor == null) || (!valorActual.equals(valor))) {

            setValueAt(valor, fila, columna);
            int accionBD = BD.ACCION_EDITAR;
            if (getAccionBDFila(fila) == BD.ACCION_NUEVO_REGISTRO) {
                accionBD = BD.ACCION_AGREGAR;
                insertarFilaAutomatica(fila + 1);
            }
            setAccionBDFila(fila, accionBD);
            calcularValorCelda(fila);
        }
    }

    /**
     * Define una columna especifica como editable o no.
     *
     * @param columna Columna a definir si permite o no edicion.
     * @param editable Booleano que define si la columna es o no editable.
     */
    public void setColumnaEditable(int columna, boolean editable) {
        if (editable && (isFormulaColumnaEditable(columna) || isFormulaCalculoColumna(columna))) {
            return;
        }
        ModeloTabla modelo = (ModeloTabla) this.getModel();
        modelo.setCellEditable(-1, columna, editable);
    }

    /**
     * Define las columnas visibles como editables o no
     *
     * @param editable Booleano que indica si las columnas son o no editables.
     */
    public void setColumnasEditables(boolean editable) {
        int columnas = getColumnCount();
        for (int i = 0; i < columnas; i++) {
            setColumnaEditable(i, editable);
        }
    }

    /**
     * Agrega o elimina una f�rmula a una columna, en base a dicha f�rmula se determinar�
     * si una celda ser� o no editable.
     *
     * @param columna Columna para la cual se agrega una f�rmula.
     * @param formula F�rmula a agregar.  Si este valor es nulo, entonces elimina la f�rmula.
     */
    public void setColumnaEditable(int columna, String formula) {
        if (isFormulaColumnaEditable(columna)) {
            condicionesEdicionCelda.remove(columna);
        }
        if (formula == null) {
            return;
        }

        condicionesEdicionCelda.put(columna, formula);
        setColumnaEditable(columna, false);
    }

    /**
     *  Indica si una columna tiene una f�rmula que indica si es editable o no
     *
     * @param columna Columna para la cual se desea saber si es o no editable.
     *
     * @return True si existe una f�rmula, False si no existe.
     */
    protected boolean isFormulaColumnaEditable(int columna) {
        boolean resultado = false;
        if (condicionesEdicionCelda == null); else if (condicionesEdicionCelda.containsKey(columna)) {
            resultado = true;
        }

        return resultado;
    }

    /**
     * Indica si la celda es editable en base a la ejecuci�n de la f�rmula asociada a ella.
     *
     * @param fila Fila de la celda.
     * @param columna Columna de la celda.
     *
     * @return True si la celda es editable, False si no lo es.
     */
    protected boolean calcularEdicionCelda(int fila, int columna) {
        boolean resultado = false;
        try {
            if (isFormulaColumnaEditable(columna)) {
                String expresion = condicionesEdicionCelda.get(columna).toString().toLowerCase();
                expresion = prepararExpresion(fila, columna, expresion);
                com.gmail.lrchfox3.utilitarios.Evaluar eval = new com.gmail.lrchfox3.utilitarios.Evaluar();
                resultado = eval.validar(bd, expresion);
            }
        } catch (Exception ex) {
            msg.mensajesErrores(null, ex.getMessage(), "Edici�n");
        }
        return resultado;
    }

    /**
     * Indica si los valores de una columna se obtienen en base al c�lculo de una f�rmula.
     *
     * @param columna Columna para la que se desea saber si existe una f�rmula.
     *
     * @return True si existe una f�rmula, False si no existe.
     */
    protected boolean isFormulaCalculoColumna(int columna) {
        boolean resultado = false;
        if (formulasCelda == null); else if (formulasCelda.containsKey(columna)) {
            resultado = true;
        }

        return resultado;
    }

    /**
     * Agrega o elimina una f�rmula a una columna, en base a dicha f�rmula se determinar� el valor de una celda.
     *
     * @param columna Columna para la cual se agrega una f�rmula.
     * @param formula F�rmula a agregar.  Si este valor es nulo, entonces elimina la f�rmula.
     */
    public void setFormulaCalculoColumna(int columna, String formula) {
        if (isFormulaCalculoColumna(columna)) {
            formulasCelda.remove(columna);
        }
        if (formula == null) {
            return;
        }

        formulasCelda.put(columna, formula);
        setColumnaEditable(columna, false);
    }

    /**
     * Calcula el valor de una celda en base a la f�rmula asignada a la columna a la que pertenece.  Una vez calculado el valor lo asigna a la celda.
     *
     * @param fila Fila de la celda.
     * @param columna Columna de la celda.
     */
    private void calcularValorCelda(int fila, int columna) {
        Object valor = null;
        try {
            if (isFormulaCalculoColumna(columna)) {
                String expresion = formulasCelda.get(columna).toString().toLowerCase();
                expresion = prepararExpresion(fila, columna, expresion);
                com.gmail.lrchfox3.utilitarios.Evaluar eval = new com.gmail.lrchfox3.utilitarios.Evaluar();
                valor = eval.calcular(bd, expresion);
                setValorCelda(fila, columna, valor);
            }
        } catch (Exception ex) {
            msg.mensajesErrores(null, ex.getMessage(), "F�mula");
        }
    }

    /**
     * Calcula el valor de las celdas de una fila en base a la f�rmula asignada a las columna de la tabla.  
     * El valor calculado para cada celda es asignado a la misma.
     *
     * @param fila Fila de la celda.
     */
    private void calcularValorCelda(int fila) {
        int columnas = getColumnCount();
        for (int i = 0; i < columnas; i++) {
            calcularValorCelda(fila, i);
        }
    }

    /**
     * Agrega o elimina una f�rmula a una columna para determinar si el valor digitado en esta
     * cumple la condicion de la formula.  Si no se cumple la condicon entonces se rechaza dicho valor.
     *
     * @param columna Columna para la cual se agrega una f�rmula.
     * @param formula F�rmula a agregar.  Si este valor es nulo, entonces elimina la f�rmula.
     */
    public void setColumnaValidacion(int columna, String formula) {
        if (isFormulaColumnaEditable(columna)) {
            formulasValidacionCelda.remove(columna);
        }
        if (formula == null) {
            return;
        }
        formulasValidacionCelda.put(columna, formula);
    }

    /**
     * Indica si los valores de una columna deben ser validados contra una f�rmula.
     *
     * @param columna Columna para la que se desea saber si existe una f�rmula.
     *
     * @return True si existe una f�rmula, False si no existe.
     */
    public boolean isFormulaValidacionColumna(int columna) {
        boolean resultado = false;
        if (formulasValidacionCelda == null); else if (formulasValidacionCelda.containsKey(columna)) {
            resultado = true;
        }

        return resultado;
    }

    /**
     * Ejecuta una f�rmula asignada a las columna de la tabla para determinar si el valor
     * ingresado en una celda especifica cumple la validacion de dicha formula.
     *
     * @param fila Fila de la celda.
     * @param columna Columna de la celda.
     */
    public boolean validacionCelda(int fila, int columna) {
        Object valor = null;
        boolean resultado = false;

        try {
            if (isFormulaValidacionColumna(columna)) {
                String expresion = formulasValidacionCelda.get(columna).toString().toLowerCase();
                expresion = prepararExpresion(fila, columna, expresion);
                com.gmail.lrchfox3.utilitarios.Evaluar eval = new com.gmail.lrchfox3.utilitarios.Evaluar();
                resultado = eval.validar(bd, expresion);
            }
        } catch (Exception ex) {
            msg.mensajesErrores(null, ex.getMessage(), "F�mula");
        }
        return resultado;
    }

    /**
     *
     */
    private String prepararExpresion(int fila, int columna, String expresion) throws Exception {
        com.gmail.lrchfox3.utilitarios.calculadora.Calc calc = new com.gmail.lrchfox3.utilitarios.calculadora.Calc();

        expresion = expresion.toUpperCase();
        //expresion = expresion.replaceAll(" ", "");
        expresion = expresion.replaceAll("F.ACTUAL", String.valueOf(fila));
        expresion = expresion.replaceAll("C.ACTUAL", String.valueOf(columna));

        while (true) {
            int ini = expresion.indexOf("CELDA");
            if (ini < 0) {
                break;
            }
            int medio = expresion.indexOf(",", ini);
            int fin = expresion.indexOf(")", medio);

            fila = (int) calc.calc(expresion.substring(ini + 6, medio));
            columna = (int) calc.calc(expresion.substring(medio + 1, fin));
            Object valor = getValueAt(fila, columna);

            Class c = getColumnClass(columna);
            if (c == Integer.class || c == Number.class) {
                if (valor == null || valor.toString().trim().equals("")) {
                    valor = "0";
                }
            } else {
                if (valor == null) {
                    valor = "''";
                }
            }

            expresion = expresion.substring(0, ini) + valor.toString() + expresion.substring(fin + 1);
        }

        return expresion;
    }

    /**
     * Define una columna especifica como editable utilizando un combo box.
     */
    public void setEditorColumnaJComboBox(int columna, String sql) throws SQLException, Exception {
        com.gmail.lrchfox3.controles.tablas.JComboBoxEditor jcbe = new com.gmail.lrchfox3.controles.tablas.JComboBoxEditor();
        jcbe.setBD(bd);
        jcbe.cargarLista(sql);
        getColumnModel().getColumn(columna).setCellEditor(jcbe);
    }

    /**
     * Define una columna especifica como editable utilizando un check box.
     */
    public void setEditorColumnaJCheckBox(int columna) throws Exception {
        com.gmail.lrchfox3.controles.tablas.JCheckBoxEditor jcbe = new com.gmail.lrchfox3.controles.tablas.JCheckBoxEditor();
        com.gmail.lrchfox3.controles.tablas.JCheckBoxRenderer jcbr = new com.gmail.lrchfox3.controles.tablas.JCheckBoxRenderer();

        javax.swing.table.TableColumn tc = getColumnModel().getColumn(columna);
        tc.setCellEditor(jcbe);
        tc.setCellRenderer(jcbr);
    }

    /**
     *  Define una columna especifica como editable utilizando un texto regular.
     *
     * @param columna Columna a la cual se aplicar� el texto.
     * @param maxCaracteres N�mero m�ximo de caracteres que aceptar� la columna.
     */
    public void setEditorColumnaJTexto(int columna, int maxCaracteres) {
        com.gmail.lrchfox3.controles.tablas.JTextoEditor jte = new com.gmail.lrchfox3.controles.tablas.JTextoEditor();
        jte.setMaxCaracteres(maxCaracteres);
        getColumnModel().getColumn(columna).setCellEditor(jte);
    }

    /**
     * Define una columna especifica como editable utilizando un texto con formato.
     * Los formatos solo puede ser de los siguientes tipos:
     * 
     * FORMATO_DEFAULT
     * FORMATO_SOLO_LETRAS
     * FORMATO_SOLO_NUMEROS
     * FORMATO_SOLO_LETRAS_NUMEROS
     *  
     * @param columna Columna a la cual se aplicar� el formato.
     * @param formato Formato que se aplicar� a la columna.
     * @param maxCaracteres N�mero m�ximo de caracteres que aceptar� la columna.
     *
     */
    public void setEditorColumnaJTextoFormato(int columna, int formato, int maxCaracteres) throws Exception {
        com.gmail.lrchfox3.controles.tablas.JTextoFormatoEditor jte = new com.gmail.lrchfox3.controles.tablas.JTextoFormatoEditor();
        if (formato == jte.FORMATO_ENTERO) {
            throw new Exception("Formato Entero no permitido en esta funci�n.");
        }
        if (formato == jte.FORMATO_DECIMAL) {
            throw new Exception("Formato Decimal no permitido en esta funci�n.");
        }

        jte.setFormato(formato);
        jte.setMaxCaracteres(maxCaracteres);
        getColumnModel().getColumn(columna).setCellEditor(jte);
    }

    /**
     * Define una columna especifica como editable utilizando un texto con formato entero.
     *  
     * @param columna N�mero de columna que se editar�.
     * @param maxEntero Valor m�ximo entero permitido.
     *  
     */
    public void setEditorColumnaJTextoFormatoEntero(int columna, int maxEntero) {
        com.gmail.lrchfox3.controles.tablas.JTextoFormatoEditor jte = new com.gmail.lrchfox3.controles.tablas.JTextoFormatoEditor();
        com.gmail.lrchfox3.controles.tablas.JTextoFormatoRenderer jtr = new com.gmail.lrchfox3.controles.tablas.JTextoFormatoRenderer();

        jte.setFormato(jte.FORMATO_ENTERO);
        jte.setLimitesEntero(maxEntero);

        javax.swing.table.TableColumn tc = getColumnModel().getColumn(columna);
        tc.setCellEditor(jte);
        tc.setCellRenderer(jtr);
    }

    /**
     * Define una columna especifica como editable utilizando un texto con formato decimal.
     *  
     * @param columna N�mero de columna que se editar�.
     * @param maxEntero Valor m�ximo entero permitido.
     * @param maxDecimal Valor m�ximo decimal permitido.
     *  
     */
    public void setEditorColumnaJTextoFormatoDecimal(int columna, int maxEntero, int maxDecimal) {
        com.gmail.lrchfox3.controles.tablas.JTextoFormatoEditor jte = new com.gmail.lrchfox3.controles.tablas.JTextoFormatoEditor();
        com.gmail.lrchfox3.controles.tablas.JTextoFormatoRenderer jtr = new com.gmail.lrchfox3.controles.tablas.JTextoFormatoRenderer();

        jte.setFormato(jte.FORMATO_DECIMAL);
        jte.setLimitesNumero(maxEntero, maxDecimal);

        javax.swing.table.TableColumn tc = getColumnModel().getColumn(columna);
        tc.setCellEditor(jte);
        tc.setCellRenderer(jtr);
    }

    /**
     *
     */
    public void seleccionarFila(int fila) {
        seleccionarRangoFilas(fila, fila);
    }

    public void seleccionarRangoFilas(int filaInicio, int filaFin) {
        int filas = getRowCount();
        if (filas > 0) {
            if (filaInicio >= filas) {
                return;
            }
            if (filaFin >= filas) {
                filaFin = filas;
            }
            setRowSelectionInterval(filaInicio, filaFin);
        }
    }

    public String[] getTitulos() {
        return titulos;
    }

    public void setTitulos(String[] titulos) {
        this.titulos = titulos;
    }

    public void setTitulosDefault(Object[][] celdas) {
        String[] titulos = getTitulos();

        if (titulos == null) {
            if (celdas != null && celdas.length > 0) {
                int tcelda = celdas[0].length;
                titulos = new String[tcelda];

                for (int i = 0; i < tcelda; i++) {
                    titulos[i] = "";
                }
            } else {
                titulos = new String[1];
            }

            setTitulos(titulos);
        }
    }

    public void desplegarTitulos() {
        if (titulos != null) {
            TableColumnModel modelo = getColumnModel();
            for (int i = 0; i < titulos.length; i++) {
                modelo.getColumn(i).setHeaderValue(titulos[i]);
            }
        }
    }

    /**
     *
     */
    private Object[] getReemplazos(String sql) {
        int columnas = getColumnCount();
        ArrayList reemplazos = new ArrayList();
        for (int i = 0; i < columnas; i++) {
            if (sql.contains("?" + i)) {
                reemplazos.add(i);
            }
        }
        return reemplazos.toArray();
    }

    /**
     * Cuando el valor de despliegue de una columna de una tabla debe ser obtenido
     * de una sentencia sql diferente a la sentencia original, esta funcion se encarga
     * de leer dicho valor y agregarlo junto con el valor de almacenamiento (en forma de lista).
     */
    public Object[][] getValoresExternos(Object[][] celdas) throws SQLException, Exception {
        if (celdas == null || celdas.length <= 0) {
            return celdas;
        }

        for (int columna = 0; columna < celdas[0].length; columna++) {
            int indice = isValorExterno(columna);

            if (indice > -1) {
                String consulta = valoresExternos[indice][1].toString();
                Object[] reemplazos = getReemplazos(consulta);

                for (int fila = 0; fila < celdas.length; fila++) {
                    String sql = consulta;
                    for (int i = 0; i < reemplazos.length; i++) {
                        int c = Integer.valueOf(reemplazos[i].toString()).intValue();
                        setColumnClass(columna, celdas[fila][c].getClass());
                        String valor = celdas[fila][c].toString();
                        if (getColumnClass(c).equals(String.class)) {
                            valor = "'" + valor + "'";
                        }
                        sql = sql.replaceAll("\\?" + c, valor);
                    }
                    Item item = new Item();
                    item.cargarItem(bd, sql);
                    celdas[fila][columna] = item;
                }
            }
        }
        return celdas;
    }

    /**
     * Define las columnas que tienen valores externos, asi como las sentencias 
     * sql de donde se obtienen.
     *
     * @param valoresExternos arreglo bidimensional que contiene
     * el numero de filas nos dice el numero de campos que tienen valores externos.
     * las columnas deben ser 2:
     * La primera indica el indice del campo con el que se relaciona el valore externo.
     * La segunda contiene la sentencia sql de la cual obtenemos el valor externo para 
     * el indice definido en la columna 1
     */
    public void setValoresExternos(Object[][] valoresExternos) {
        this.valoresExternos = valoresExternos;
    }

    /** 
     * Indica si la columna de la tabla obtiene el valor de un origen de BD
     * diferente al que alimenta la tabla de despliegue.
     * 
     * @param columna La columna para la cual se desea saber si obtiene su valor de un origen externo.
     *
     * @return Verdadero si obtiene el valor de un origen externo, falso si no es el caso.
     */
    public int isValorExterno(int columna) {
        if (valoresExternos != null) {
            for (int i = 0; i < valoresExternos.length; i++) {
                if (Integer.valueOf(valoresExternos[i][0].toString()).intValue() == columna) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int[] getCeldasZoom() {
        return celdasZoom;
    }

    public void setCeldasZoom(int[] celdasZoom) {
        this.celdasZoom = celdasZoom;
    }

    public boolean isCeldaZoom(int indiceCelda) {
        if (celdasZoom != null) {
            for (int i = 0; i < celdasZoom.length; i++) {
                if (indiceCelda == celdasZoom[i]) {
                    return true;
                }
            }
        }
        return false;
    }

    public int[] getCeldasEdicionZoom() {
        return celdasEdicionZoom;
    }

    public void setCeldasEdicionZoom(int[] celdasEdicionZoom) {
        this.celdasEdicionZoom = celdasEdicionZoom;
    }

    public boolean isCeldaEdicionZoom(int indiceCelda) {
        if (celdasEdicionZoom != null) {
            for (int i = 0; i < celdasEdicionZoom.length; i++) {
                if (indiceCelda == celdasEdicionZoom[i]) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setTamanyos(int[] tamanyos) {
        this.tamanyos = tamanyos;
    }

    public int[] getTamanyos() {
        return tamanyos;
    }

    public void setColumnasInvisibles(int[] columnasInvisibles) {
        this.columnasInvisibles = columnasInvisibles;
    }

    /**
     * Define una columna como visible o invisible.
     * @param columna N�mero de la columna que se desea declarar como visible o invisible.
     * @param visible Valor booleano que indica si es visible o invisibel.
     */
    public void setColumnaVisible(int indice, boolean visible) {
        int minimoAncho = 0;
        int ancho = 0;

        TableColumnModel modelo = getColumnModel();
        if (visible) {
            minimoAncho = 10;
            ancho = getTamanyos()[indice];
        }

        modelo.getColumn(indice).setMinWidth(minimoAncho);
        modelo.getColumn(indice).setPreferredWidth(ancho);
        modelo.getColumn(indice).setWidth(ancho);
        modelo.getColumn(indice).setResizable(visible);
    }

    public void setColumnasVisible(int[] indices, boolean visible) {
        for (int i = 0; i < indices.length; i++) {
            setColumnaVisible(indices[i], visible);
        }
    }

    /*********************************************************************/
    public void asignarFrame(javax.swing.JComponent frame) {
        frame.add(getJScroll());
    }

    public void asignarFrame(javax.swing.JComponent frame, Object constraints) {
        frame.add(getJScroll(), constraints);
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto() {
        int altoHeader = 0;
        alto = (getAltoFila() * getFilasDesplegar()) + 1;
        if (getTableHeader() != null) {
            altoHeader = 17;
        }
        //if (getTableHeader().isShowing()) altoHeader = 16;

        setSize(getAncho(), getAlto());
        setTamanyoScroll(getAncho(), getAlto() + altoHeader);
    }

    public int getAltoFila() {
        return altoFila;
    }

    public void setAltoFila(int altoFila) {
        this.altoFila = altoFila;
        setRowHeight(getAltoFila());
        setAlto();
    }

    public int getFilasDesplegar() {
        return filasDesplegar;
    }

    public void setFilasDesplegar(int filasDesplegar) {
        this.filasDesplegar = filasDesplegar;
        setAlto();
    }

    public int getAncho() {
        int ancho = 0;
        TableColumnModel modelo = getColumnModel();
        int columnas = modelo.getColumnCount();

        for (int i = 0; i < columnas; i++) {
            ancho = ancho + modelo.getColumn(i).getPreferredWidth();
        }

        return ancho;
    }

    /**
     *Define el ancho de cada una de las columnas.
     *llama a la funcion de ajustar el tamanyo del scroll.
     *
     */
    public void setAnchoColumnas() {
        TableColumnModel modelo = getColumnModel();
        int numeroColumnas = modelo.getColumnCount();
        for (int i = 0; i < tamanyos.length; i++) {
            if (i >= numeroColumnas) {
                break;
            }
            modelo.getColumn(i).setPreferredWidth(tamanyos[i]);
        }
        /*
         * Comentado: 26-07-2009
         * ya que afecta el tamaño al moneto de cargar varias veces el grid
         */
        //setTamanyoScroll(getAncho(), getAlto());
    }

    public void setTipoScrollHorizontal(int tipoScroll) {
        JScrollTablaBase jsc = getJScroll();
        if (jsc != null) {
            jsc.setHorizontalScrollBarPolicy(tipoScroll);
        }
    }

    public void setUbicacionScroll(int posX, int posY) {
        JScrollTablaBase jsc = getJScroll();
        if (jsc != null) {
            jsc.setPosX(posX);
            jsc.setPosY(posY);
        }
    }

    public int getUbicacionScrollX() {
        JScrollTablaBase jsc = getJScroll();
        if (jsc != null) {
            return jsc.getPosX();
        }
        return 0;
    }

    public int getUbicacionScrollY() {
        JScrollTablaBase jsc = getJScroll();
        if (jsc != null) {
            return jsc.getPosY();
        }
        return 0;
    }

    public void setTamanyoScroll(int ancho, int alto) {
        JScrollTablaBase jsc = getJScroll();
        if (jsc != null) {
            jsc.setTamanyo(ancho, alto);
        }
    }

    public int getAnchoScroll() {
        JScrollTablaBase jsc = getJScroll();
        if (jsc != null) {
            return jsc.getWidth();
        }
        return 0;
    }

    public int getAltoScroll() {
        JScrollTablaBase jsc = getJScroll();
        if (jsc != null) {
            return jsc.getHeight();
        }
        return 0;
    }

    public JScrollTablaBase getJScroll() {
        java.awt.Container ct = getParent();
        if (ct != null) {
            if (ct.getClass().getName().equals("javax.swing.JViewport")) {
                return (JScrollTablaBase) ct.getParent();
            }
        }
        return null;
    }

    public void setJTablesSincronizar(JTablaBase[] jTablesSincronizar) {
        JScrollTablaBase[] jScrollsSincronizar = new JScrollTablaBase[jTablesSincronizar.length];

        for (int i = 0; i < jTablesSincronizar.length; i++) {
            jScrollsSincronizar[i] = jTablesSincronizar[i].getJScroll();
        }

        getJScroll().setJScrollsSincronizar(jScrollsSincronizar);
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int colIndex) {
        Component component = super.prepareRenderer(renderer, rowIndex, colIndex);
        if ((rowIndex % 2) == 0) {
            component.setBackground( new Color(204,204,255));            
        } else {
            component.setBackground(Color.WHITE);            
        }
        return component;
    }
    
    
    /**
     * Permite abrir una ventana con la información contenida en la celda seleccionada de la tabla,
     * se agrega un botón en la celda, al presionar se abre un "Zoom" de lo contenido del la celda.
     * @param columnas indica que columnas van a poder contar con este comportamiento
     */
    public void setZoomCelda(int[] columnas) {
        JTextoBase textField = new JTextoBase();
        textField.setEditable(false);
        textField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        javax.swing.DefaultCellEditor editor = new javax.swing.DefaultCellEditor(textField);
        editor.setClickCountToStart(1);
        for (int i = 0; i < columnas.length; i++) {
            getColumn(getColumnName(columnas[i])).setCellEditor(new StringActionTableCellEditor(editor));
        }
    }

    public void setZoomCelda(int columna) {
        JTextoBase textField = new JTextoBase();
        textField.setEditable(false);
        textField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        javax.swing.DefaultCellEditor editor = new javax.swing.DefaultCellEditor(textField);
        editor.setClickCountToStart(1);
        
        getColumn(getColumnName(columna)).setCellEditor(new StringActionTableCellEditor(editor));
        
    }

    public boolean importarExcelTabla() throws IOException, Exception {
        boolean retValue = false;


        final JFileChooser fc = new JFileChooser();
        fc.addChoosableFileFilter(new FiltrosImportar());
        fc.setAcceptAllFileFilterUsed(false);

        int returnVal = fc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            Excel excel = new Excel();
            DefaultTableModel dtm = excel.importarExcel(this, file.getPath());
            this.setModel(dtm);
        }

        return retValue;
    }

    /**
     * permite exportar toda la información contenida de la tabla a una archivo de Excel,
     * este genera una hoa de excel y carga la información de la tabla en ella.
     * @exception IOException devuelve error por escritura o lectura de archivos.
     * @exception Exception captura excepciones genericas.
     */
    public boolean exportarTablaExcel() throws IOException, Exception {
        boolean retValue = false;
        final JFileChooser fc = new JFileChooser();
        fc.addChoosableFileFilter(new FiltrosImportar());
        fc.setAcceptAllFileFilterUsed(false);
        int returnVal = fc.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            String fileName = file.getPath();
            int b = file.getName().indexOf(".xls");
            if (b < 0) {
                fileName += ".xls";
            }
            retValue = new Excel().exportarJtable(this, fileName, this.getName());
        }
        return retValue;
    }
}



