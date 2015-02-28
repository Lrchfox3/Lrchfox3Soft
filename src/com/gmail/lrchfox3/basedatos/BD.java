/**
 * ConexionDB.java
 *
 * Created on January 4, 2007, 1:42 AM
 *
 * Copyright 2007 GO Consultores, Inc. All rights reserved. Use is subject to
 * license terms.
 *
 * @author Luis R. Chinchilla H.
 */
package com.gmail.lrchfox3.basedatos;

// <editor-fold defaultstate="collapsed" desc=" Librerias">  

import java.io.Serializable;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import com.gmail.lrchfox3.utilitarios.Strings;
import java.util.logging.Level;
import java.util.logging.Logger;
// </editor-fold>  

/**
 * <code>BD</code> se utiliza para manipular conexiones con Bases de Datos.
 *
 * @author Luis R. Chinchilla H.
 */
public  class BD extends Object implements Serializable {

    // <editor-fold defaultstate="collapsed" desc=" Declaración de Variables ">
    /**
     * Manejador de Base de Datos SQL
     */
    public static final int DBMSMSSQLSERVER = 1;
    public static final int DBMSMSACCESS = 2;
    public static final int DBMSMSMYSQL = 3;
    public static final int DBMSDB2 = 5;
    /**
     * Objeto de conexion a la Base de Datos.
     */
    public int DBMS = 0;
    private static int dbms[] = {0, 0, 0, 0, 0};
    private static Connection[] conBD = {null, null, null, null, null};
    private static String[] nombreConBD = {null, null, null, null, null};
    private static Connection conBDActual = null;
    public Statement stmtActual = null;
    // acciones a la BD 
    private int accion = 0;
    public static final int ACCION_NINGUNA = 0;
    public static final int ACCION_AGREGAR = 1;
    public static final int ACCION_EDITAR = 2;
    public static final int ACCION_ELIMINAR = 3;
    public static final int ACCION_ACEPTAR_AGREGAR = 4;
    public static final int ACCION_ACEPTAR_EDITAR = 5;
    public static final int ACCION_ACEPTAR_ELIMINAR = 6;
    public static final int ACCION_CANCELAR = 7;
    public static final int ACCION_NUEVO_REGISTRO = 8;
    //
    private static final String SELECT = "SELECT";
    private static final String INSERT = "INSERT INTO";
    private static final String UPDATE = "UPDATE";
    private static final String DELETE = "DELETE FROM";
    private static final String WHERE = "WHERE";
    // </editor-fold>  

    public BD() {
    }
    
    public BD(String nombreBD)  {
        try {
            this.setConexion(nombreBD);
        } catch (Exception ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     */
    public int getAccion() {
        return accion;
    }

    public void setAccion(int accion) {
        this.accion = accion;
    }
    
     public  String getDescripcionAccion() {
        int action = getAccion();
        String descripcion = "";
        switch (action) {
            case ACCION_NINGUNA:
                descripcion = "Ninguna";
                break;
            case ACCION_AGREGAR:
                descripcion = "Agregar";
                break;
            case ACCION_EDITAR:
                descripcion = "Editar";
                break;
            case ACCION_ELIMINAR:
                descripcion = "Eliminar";
                break;
            default:
                descripcion = "Ninguna";
                break;

        }
        return descripcion;
    }

    public  String getDescripcionAccion(int action) {
        String descripcion = "";
        switch (action) {
            case ACCION_NINGUNA:
                descripcion = "Ninguna";
                break;
            case ACCION_AGREGAR:
                descripcion = "Agregar";
                break;
            case ACCION_EDITAR:
                descripcion = "Editar";
                break;
            case ACCION_ELIMINAR:
                descripcion = "Eliminar";
                break;
            default:
                descripcion = "Ninguna";
                break;

        }
        return descripcion;
    }


    /**
     * Encuentra el indice del slot donde se encuentra la conexion que se esta
     * utilizando.
     *
     * @return numero del indice del slot de la conexion. Si es -1 no encontro
     * la conexion.
     */
    private int indiceNombreConBD(String nombre) {
        int max = conBD.length;
        for (int i = 0; i < max; i++) {
            if (nombreConBD[i] != null) {
                if (nombreConBD[i].equals(nombre)) {
                    return i;
                }
            }
        }

        return -1;
    }

    /**
     * Determina si existe un slot (espacio) en el pool de conexiones para
     * asignar una nueva conexion. El maximo de conexiones es 5.
     *
     * @return Numero de espacio libre para conexion. Si devuelve -1, ya no hay
     * espacio para crear mas conexiones.
     */
    private int conexionLibre() {
        int max = conBD.length;
        for (int i = 0; i < max; i++) {
            if (conBD[i] == null) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Lee una conexion abierta de Base de Datos.
     *
     * @return Un objecto que contiene la conexion a la Base de Datos.
     */
    private Connection getConexion(String nombre) throws Exception {
        int indice = indiceNombreConBD(nombre);
        if (indice == -1) {
            return null;
        }

        return conBD[indice];
    }

    /**
     * Inicia una conexion a la Base de Datos.
     *
     * @param DBMS manejador de Base de Datos que se utiliza en la conexion.
     * @param servidor Servidor al cual se establece la conexion.
     * @param BD Nombre de la Base de Datos.
     * @param usuario Usuario que inicia la conexion.
     * @param contrasenya Contraseï¿½a del usuario que inicia la conexion.
     * @param aplicacion Nombre de la aplicacion que establece la conexion a la
     * Base de Datos
     * @param puerto Puerto a traves del cual se establece la conexion con la
     * Base de Datos.
     */
    public void iniciarConexion(String nombreConexion, int DBMS, String servidor, String BD, String usuario, String contrasenya, String aplicacion, int puerto) throws SQLException, Exception {
        this.DBMS = DBMS;
        Connection con = null;

        int indice = conexionLibre();
        if (indice == -1) {
            throw new Exception("No Existen Conexiones Disponibles.");
        }

        switch (DBMS) {
            case DBMSMSSQLSERVER:
                con = new CnnSqlServer().iniciarConexion(servidor, BD, aplicacion, usuario, contrasenya, puerto);
                break;
            case DBMSMSACCESS:
                con = new CnnAccess().iniciarConexion(BD, usuario, contrasenya);
                break;

            case DBMSMSMYSQL:
                con = new CnnMySql().iniciarConexion(servidor, BD, usuario, contrasenya, puerto);
                break;
            case DBMSDB2:

//                con = new BDDB2().iniciarConexion(servidor, BD, usuario, contrasenya);
                break;
        }

        con.setAutoCommit(false);
        conBD[indice] = con;
        nombreConBD[indice] = nombreConexion;
        setConexion(nombreConexion);
    }

    public void iniciarConexion(String nombreConexion, int DBMS, String BD, String usuario, String contrasenya) throws SQLException, Exception {
        iniciarConexion(nombreConexion, DBMS, "", BD, usuario, contrasenya, "", 0);
    }
    
    
    public void iniciarConexion(String nombreConexion, int DBMS, String BD, String usuario, String contrasenya, int puerto) throws SQLException, Exception {
        iniciarConexion(nombreConexion, DBMS, "", BD, usuario, contrasenya, "", puerto);
    }

    /**
     * Setea la conexion que la BD va a utilizar.
     */
    public void setConexion(String nombreConBD) throws Exception {
        conBDActual = getConexion(nombreConBD);
    }

    public Connection getConexion() throws Exception {
        return conBDActual;
    }

    /**
     *
     */
    public int getDBMS() {
        return DBMS;
    }

    /**
     * Finaliza una conexion a la Base de Datos.
     *
     * @param conn Conexion de Base de Datos que se desea cerrar.
     */
    public void cerrarConexion(Connection con) throws SQLException {
        con.close();
    }

    public void cerrarConexion() throws SQLException {
        conBDActual.close();
    }

    /**
     * Crea un objeto para enviar sentencias SQL a la Base de Datos.
     */
    public Statement crearSentencia() throws SQLException, Exception {
        return conBDActual.createStatement();
    }

    public Statement createStatement() throws SQLException, Exception {
        return conBDActual.createStatement();
    }

    /**
     * Cierra un objeto de setencia SQL.
     */
    public void cerrarSetencia(Statement sentenciaBD) throws SQLException {
        sentenciaBD.close();
    }

    /**
     * Commit de transacciones.
     */
    public void commit() throws SQLException, Exception {
        conBDActual.commit();

        int accion = getAccion();
        if (accion == ACCION_AGREGAR) {
            setAccion(ACCION_ACEPTAR_AGREGAR);
        } else if (accion == ACCION_EDITAR) {
            setAccion(ACCION_ACEPTAR_EDITAR);
        } else if (accion == ACCION_ELIMINAR) {
            setAccion(ACCION_ACEPTAR_ELIMINAR);
        }
    }

    /**
     * Rollback de transacciones.
     */
    public void rollback() throws SQLException, Exception {
        conBDActual.rollback();
    }

    /**
     * Prepara sentencias SQL que todavia no tienen valores asignados. Los
     * valores seran asignados posteriormente.
     *
     * @param sentenciaSQL Sentencia SQL que se prepara para ejecutar.
     *
     * @return Objeto con sentencia SQL preparada para ejecutarse.
     *
     * @author Chinchilla 19/12/2006
     */
    public PreparedStatement prepararSentencia(String sentenciaSQL) throws SQLException, Exception {
        return conBDActual.prepareStatement(sentenciaSQL);
    }

    /**
     * Ejecuta una sentencia SQL preparada con la funcion prepararSentencia.
     *
     * @return devuelve un objeto con el resultado de la sentencia ejecutado.
     */
    public boolean ejecutarSentencia(PreparedStatement sentenciaBD) throws SQLException {
        sentenciaBD.execute();
        return true;
    }

    /**
     * Ejecuta una sentencia SQL.
     *
     * @return devuelve un objeto con el resultado de la sentencia ejecutado.
     */
    public ResultSet ejecutarSentencia(Statement sentenciaBD, String sentenciaSQL) throws SQLException {
        return sentenciaBD.executeQuery(sentenciaSQL);
    }

    /**
     * Ejecuta una sentencia SQL.
     *
     * @return devuelve un objeto con el resultado de la sentencia ejecutado.
     */
    public ResultSet ejecutarSentencia(String sentenciaSQL) throws SQLException, Exception {

        this.stmtActual = crearSentencia();
        ResultSet rs = this.stmtActual.executeQuery(sentenciaSQL);
        return rs;
    }

    /**
     *
     * @param campos
     * @param valores
     * @param tipoDatos
     * @return
     */
    public String condicionesSQL(ArrayList<Object> campos, ArrayList<Object> valores, int[] tipoDatos) {
        String where = "";
        int n = campos.size();
        if (n > 0) {
            for (int i = 0; i < n; i++) {
                if (!where.equals("")) {
                    where = where + " AND ";
                }
                Object val = delimitarValor(valores.get(i), tipoDatos[i]);
                where = where + campos.get(i).toString() + " = " + val + " ";
            }
        }
        return where;
    }

    /**
     * Crea un string con la sentencia de select SQL.
     *
     * @param tablas Nombre de la tabla o tablas (separadas por comas)para el
     * select.
     * @param campos Lista de campos en el select.
     * @param where Subsentencia Where del select.
     *
     * @return devuelve un string conteniendo la sentencia select de SQL.
     */
    public String selectSQL(String tablas, ArrayList<Object> campos, String where) throws Exception {
        String sql = null;
        String strCampos = "";
        String strWhere = "";
        Strings strings = new Strings();

        if (tablas != null) {
            if (campos == null) {
                strCampos = " * ";
            } else {
                strCampos = strings.listaArreglosAString(campos, ", ");
            }
            if (where != null && !where.trim().equals("")) {
                strWhere = " " + WHERE + " " + where;
            }
        }
        sql = SELECT + " " + strCampos + " FROM " + tablas + strWhere;

        return sql;
    }

    /**
     * Crea un string con la sentencia de select SQL.
     *
     * @param tablas Nombre de la tabla o tablas (separadas por comas)para el
     * select.
     * @param campos Lista de campos en el select.
     * @param where Subsentencia Where del select.
     *
     * @return devuelve un string conteniendo la sentencia select de SQL.
     */
    public String selectSQL(String tablas, String campos, String where) throws Exception {
        String sql = null;
        String strCampos = "";
        String strWhere = "";
        Strings strings = new Strings();

        if (tablas != null) {

            if (campos.length() <= 0) {
                campos = " * ";
            }
            if (where != null && !where.trim().equals("")) {
                strWhere = " " + WHERE + " " + where;
            }
        }
        sql = SELECT + " " + campos + " FROM " + tablas + strWhere;

        return sql;
    }

    /**
     * Crea un string con la sentencia de insert SQL
     *
     * @param tabla Nombre de la tabla para el insert.
     * @param campos Lista de campos en el insert.
     * @param valores Lista de valores a insertar.
     *
     * @return devuelve un string conteniendo la sentencia insert de SQL.
     */
    public String insertSQL(String tabla, ArrayList<Object> campos, ArrayList<String> valores) throws Exception {
        String strCampos = "";
        String strValores = "";
        String sql = null;
        Strings strings = new Strings();

        if (tabla != null && campos != null) {
            if (valores == null) {
                valores = new ArrayList<>();
                for (int i = 0; i < campos.size(); i++) {
                    valores.add("?");
                }
            } else {
                for (int i = 0; i < campos.size(); i++) {
                    if (valores.get(i) == null) {
                        valores.set(i, "null");
                    }
                }
            }

            strCampos = strings.listaArreglosAString(campos, ", ");
            strValores = strings.listaArreglosAString(valores, ", ");
            sql = INSERT + " " + tabla + " (" + strCampos + ") VALUES (" + strValores + ")";
        }

        return sql;
    }

    /**
     * Crea un string con la sentencia de update SQL
     *
     * @param tabla Nombre de la tabla para el update.
     * @param campos Lista de campos en el update.
     * @param valores Lista de valores a update.
     * @param where Subsentencia where del update.
     *
     * @return devuelve un string conteniendo la sentencia update de SQL.
     */
    public String updateSQL(String tabla, ArrayList campos, ArrayList valores, String where) throws Exception {
        String strUpdates = "";
        String sql = null;
        Strings strings = new Strings();
        String valor = "";

        if (tabla != null && campos != null) {
            if (valores == null) {
                valores = new ArrayList();
                for (int i = 0; i < campos.size(); i++) {
                    valores.add("?");
                }
            } else {
                for (int i = 0; i < campos.size(); i++) {
                    if (valores.get(i) == null) {
                        valores.set(i, "null");
                    }
                }
            }

            for (int i = 0; i < campos.size(); i++) {
                if (!strUpdates.equals("")) {
                    strUpdates = strUpdates + ", ";
                }
                strUpdates = strUpdates + campos.get(i).toString() + " = " + valores.get(i).toString();
            }

            if (where == null) {
                where = "";
            } else if (!where.trim().equals("")) {
                where = " " + WHERE + " " + where;
            }

            sql = UPDATE + " " + tabla + " SET " + strUpdates + where;
        }

        return sql;
    }

    /**
     * Crea un string con la sentencia de delete SQL
     *
     * @param tabla Nombre de la tabla para el delete.
     * @param where Subsentencia where del delete.
     *
     * @return devuelve un string conteniendo la sentencia delete de SQL.
     */
    public String deleteSQL(String tabla, String where) {
        String sql = null;

        if (tabla != null || where != null) {
            if (where != null) {
                sql = DELETE + " " + tabla + " " + WHERE + " " + where;
            }
        }

        return sql;
    }

    /**
     * Coloca entre apostrofes un dato que por el tipo requiere ser delimitado
     * por estas.
     *
     * @param valor Valor al cual se le revisa la necesidad de colocar entre
     * apostrofes.
     * @param tipoDato el Tipo de dato (Varchar, Date) indica si el valor se
     * debe colocar entre apostrofes.
     *
     * @return El valor delimitado o no dependiendo si fuera necesario.
     */
    public Object delimitarValor(Object valor, int tipoDato) {
        if (valor != null) {
            if (tipoDato == Types.VARCHAR || tipoDato == Types.DATE) {
                valor = "'" + valor + "'";
            }
        }
        return valor;
    }

    /**
     * permite obtener varios datos de una registro tabla
     *
     * @author Chinchilla 29/07/06
     *
     * @param tabla: tabla a buscar los datos
     * @param campos: lista de campos que seran buscados
     * @param codigo: codigo unico de la tabla
     * @param compare: valor de busqueda
     */
    public ArrayList getDatosTabla(String tabla, ArrayList campos, String whereStatement) throws Exception {
        Statement st = null;
        ResultSet rs = null;
        ArrayList datos = new ArrayList();
        String strCampos = "";
        try {
            st = crearSentencia();
            rs = ejecutarSentencia(st, selectSQL(tabla, campos, whereStatement));
            if (rs != null) {
                while (rs.next()) {
                    for (int i = 0; i < campos.size(); i++) {
                        datos.add(rs.getObject(i + 1));
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (Exception nex) {
                throw nex;
            }
        }
        return datos;
    }

    /**
     * Toma un registro de BD y lo convierta a un ArrayList.
     *
     * @param rs ResultSet conteniendo el registro a convertir.
     *
     * @return Retorna el ArrayList con los valores del registro.
     */
    public ArrayList registroAListaArreglos(ResultSet rs) throws SQLException {
        int campos = rs.getMetaData().getColumnCount();
        ArrayList lista = new ArrayList();

        for (int i = 0; i < campos; i++) {
            lista.add(rs.getObject(i + 1));
        }

        return lista;
    }

    public Object[] registroAArregloObjetos(ResultSet rs) throws SQLException {
        int campos = rs.getMetaData().getColumnCount();
        Object[] arreglo = new Object[campos];

        for (int i = 0; i < campos; i++) {
            arreglo[i] = rs.getObject(i + 1);
        }

        return arreglo;
    }
}
