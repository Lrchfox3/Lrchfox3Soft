/*
 * jComboBase.java
 *
 * Created on January 15, 2007, 12:34 AM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.gmail.lrchfox3.controles.listas;

import java.util.Vector;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.gmail.lrchfox3.basedatos.BD;
import com.gmail.lrchfox3.utilitarios.Item;
import com.gmail.lrchfox3.utilitarios.Propiedades;

/**
 *
 * @author Luis R. Chinchilla H.
 */
public class JComboBoxBase extends javax.swing.JComboBox {
    
    private BD bd = null;
    private Propiedades propiedades = new Propiedades();        
    
    /** Creates a new instance of jComboBase */
    public JComboBoxBase() {
        inicializar();
    }
    
    private void inicializar() {
        setFont(propiedades.getFontTextos());
        setBorder(new javax.swing.border.LineBorder(java.awt.SystemColor.activeCaption, 1, true));
        setPreferredSize(new java.awt.Dimension(110, 20));
        setSize(getPreferredSize());
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
                item.setItemData( rs.getObject(1));
                item.setItem(rs.getString(2));                
                vector.add(item);
            }                        
        }
        catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
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
        int filas = 0;
        removeAllItems();
        Vector vector = cargarVector(sql);
        filas = vector.size();
        for (int i = 0; i < filas; i++)  addItem(vector.get(i));            
        
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
        if (valor != null) {
            int numeroLista = getItemCount();
            for (int i = 0; i < numeroLista; i++)   {
                Item item = (Item)getItemAt(i);
                if (item.getItemData().toString().equals(valor.toString()))  {
                    setSelectedIndex(i);
                    return i;
                }
            }
        } 
        return -1;
    }
}
