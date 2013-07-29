package com.gmail.lrchfox3.controles.listas;

import java.util.Vector;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import com.gmail.lrchfox3.basedatos.BD;
import com.gmail.lrchfox3.utilitarios.Item;
import com.gmail.lrchfox3.utilitarios.Propiedades;

/**
 *
 * @author Luis R. Chinchilla H.
 */
public class JListaBase extends javax.swing.JList {

    private BD bd = null;
    private Propiedades propiedades = new Propiedades();

    /** Creates a new instance of jComboBase */
    public JListaBase() {
        inicializar();
    }

    private void inicializar() {
        setFont(propiedades.getFontTextos());
        setBorder(new javax.swing.border.LineBorder(java.awt.SystemColor.activeCaption, 1, true));
        setBackground(propiedades.getColorListas());
    }

    /**
     * Define la BD que se va a utilizar para manipular los datos relacionados
     * al Arbol.
     *
     * @param BD Base de Datos que se utilizar�.
     */
    public void setBD(BD bd) throws Exception {
        this.bd = bd;
    }

    /**
     * Lee los registros de una tabla de la Base de Datos y alimenta un vector
     * con los campos de estos.
     *
     * @param sql Sentencia sql que contiene la informacion a cargar en el vector.
     *
     * @return numero de registros cargados en el vector.
     */
    protected Vector cargarVector(String sql) throws SQLException, Exception {
        Statement st = null;
        ResultSet rs = null;
        Vector vector = new java.util.Vector();

        try {
            st = bd.crearSentencia();
            rs = bd.ejecutarSentencia(st, sql);
            while (rs.next()) {
                Item item = new Item();
                item.setItemData(rs.getObject(1));
                item.setItem(rs.getString(2));
                vector.add(item);
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
        }

        return vector;
    }

    /**
     * Carga una lista con la informacion que toma de un vector generado a partir de la sentencia SQL
     * enviada como parametro.
     *
     * @param sql Sentencia sql que contiene la informacion a cargar en la lista.
     *
     * @return numero de registros cargados a la Lista.
     */
    public int cargarLista(String sql) throws SQLException, Exception {
        DefaultListModel listModel = new DefaultListModel();

        int filas = 0;
        this.removeAll();
        Vector vector = cargarVector(sql);
        filas = vector.size();
        for (int idx = 0; idx < filas; idx++) {
            listModel.addElement(vector.get(idx));
        }
        this.setModel(listModel);
        return filas;
    }

    /**
     * Carga una lista con la informacion que toma de una lista de objetos
     * enviada como parametro.
     *
     * @param items Lista de Objetos
     *
     * @return numero de registros cargados a la Lista.
     */
    public int cargarLista(Object[] items) throws Exception {
        DefaultListModel listModel = new DefaultListModel();
        int filas = 0;
        this.removeAll();
        filas = items.length;
        for (int idx = 0; idx < filas; idx++) {
            listModel.addElement(items[idx]);
        }
        this.setModel(listModel);
        return filas;
    }

    /**
     * Seleccionar un elemento del combo para desplegarlo basado en un dato.
     *
     * @param valor Valor que se desea buscar y seleccionar.
     *
     * @return Entero con el indice del objeto seleccionado.  Si el valor es nulo, no se seleccion� ningun elemento.
     */
    public int seleccionarDato(Object valor) throws Exception {
        ListModel listModel = this.getModel();
        if (valor != null) {
            int numeroLista = listModel.getSize();
            for (int i = 0; i < numeroLista; i++) {
                Item item = (Item) listModel.getElementAt(i);
                if (item.getItemData().toString().equals(valor.toString())) {
                    setSelectedIndex(i);
                    return i;
                }
            }
        }
        return -1;
    }

    public void moverElementoA(int index, JListaBase lst) {
        ListModel listModelOrigen = this.getModel();
        ListModel listModelDestino = lst.getModel();
        DefaultListModel newListModel = new DefaultListModel();
        DefaultListModel newModelDestino = new DefaultListModel();

        if (index >= 0) {
            int numeroLista = listModelDestino.getSize();
            for (int i = 0; i < numeroLista; i++) {
                Item item = (Item) listModelDestino.getElementAt(i);
                newModelDestino.addElement(item);
            }

            numeroLista = listModelOrigen.getSize();
            for (int i = 0; i < numeroLista; i++) {
                Item item = (Item) listModelOrigen.getElementAt(i);
                if (index == i) {
                    newModelDestino.addElement(item);
                } else {
                    newListModel.addElement(item);
                }
            }
            lst.setModel(newModelDestino);
            this.removeAll();
            this.setModel(newListModel);
        }

    }

    public void moverElementoA(Object valor, JListaBase lst) {
        ListModel listModelOrigen = this.getModel();
        ListModel listModelDestino = lst.getModel();
        DefaultListModel newListModel = new DefaultListModel();
        DefaultListModel newModelDestino = new DefaultListModel();
        if (valor != null) {
            int numeroLista = listModelDestino.getSize();
            for (int i = 0; i < numeroLista; i++) {
                Item item = (Item) listModelDestino.getElementAt(i);
                newModelDestino.addElement(item);
            }

            numeroLista = listModelOrigen.getSize();
            for (int i = 0; i < numeroLista; i++) {
                Item item = (Item) listModelOrigen.getElementAt(i);
                if (item.getItemData().toString().equals(valor.toString())) {
                    newModelDestino.addElement(item);
                } else {
                    newListModel.addElement(item);
                }
            }
            lst.setModel(newModelDestino);
            this.removeAll();
            this.setModel(newListModel);
        }
    }
}
