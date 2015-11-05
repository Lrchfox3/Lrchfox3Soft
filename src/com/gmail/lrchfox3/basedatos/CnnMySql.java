/*
 * BDMicrosoftSQL.java
 *
 * Created on January 4, 2007, 6:05 PM
 *
 * Conexiones Base de Datos Microsot SQL Server.
 *
 * Copyright 2007 GO Consultores, Inc. All rights reserved.
 * Use is subject to license terms.
 *
 * @author Luis R. Chinchilla H.
 */
package com.gmail.lrchfox3.basedatos;

// <editor-fold defaultstate="collapsed" desc=" Librerias de Importacion">  
import com.gmail.lrchfox3.utilitarios.Info;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// </editor-fold>
/**
 * <code>BDMicrosoft</code> se utiliza para manipular conexiones con Bases de
 * Datos Microsoft SQL Server.
 *
 * @author Luis R. Chinchilla H.
 */
public class CnnMySql {

    private String instancia = "MYSQL";
    private String servidor = "";
    private String BD = "";
    private String usuario = "";
    private String contrasenya = "";
    private int puerto = 0;
    public static Connection cnn = null;
    private String url = "jdbc:mysql://@paramServidor:@paramPuerto/@paramBDD";
    private static int accion = 0;
    public static final int ACCION_NINGUNA = 0;
    public static final int ACCION_INSERT = 1;
    public static final int ACCION_UPDATE = 2;
    public static final int ACCION_DELETE = 3;

    /**
     * Creates a new instance of BDMicrosoftSQL
     */
    public CnnMySql() {
    }

    /**
     * @return the servidor
     */
    public String getServidor() {
        return servidor;
    }

    /**
     * @param servidor the servidor to set
     */
    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    /**
     * @return the puerto
     */
    public int getPuerto() {
        return puerto;
    }

    /**
     * @param puerto the puerto to set
     */
    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    /**
     * @return the BD
     */
    public String getBD() {
        return BD;
    }

    /**
     * @param aBD the BD to set
     */
    public void setBD(String aBD) {
        BD = aBD;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param aUsuario the usuario to set
     */
    public void setUsuario(String aUsuario) {
        usuario = aUsuario;
    }

    /**
     * @return the contrasenya
     */
    public String getContrasenya() {
        return contrasenya;
    }

    /**
     * @param aContrasenya the contrasenya to set
     */
    public void setContrasenya(String aContrasenya) {
        contrasenya = aContrasenya;
    }

    /**
     * @return the accion
     */
    public static int getAccion() {
        return accion;
    }

    /**
     * @param aAccion the accion to set
     */
    public static void setAccion(int _accion) {
        accion = _accion;
    }

    /**
     * Inicia una conexion a Base de Datos MySQL.
     *
     *
     * @param BD Nombre de la Base de Datos
     * @param usuario Usuario que inicia la conexion.
     * @param contrasenya Contraseï¿½a del usuario que inicia la conexion.
     *
     * @return El objeto que contiene la conexion a la Base de Datos.
     */
    public Connection iniciarConexion(String servidor, String BD, String usuario, String contrasenya, int puerto) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        setServidor(servidor);
        setBD(BD);
        setPuerto(puerto);
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        url = url.replaceFirst("@paramServidor", this.getServidor());
        url = url.replaceFirst("@paramPuerto", String.valueOf(this.getPuerto()));
        url = url.replaceFirst("@paramBDD", this.getBD());
        cnn = DriverManager.getConnection(url, usuario, contrasenya);
        if (cnn != null) {
            System.out.println("Conexión a base de datos " + url + " ... Ok");
            cnn.setAutoCommit(false);
        }

        return cnn;
    }

    public Connection iniciarConexion(String servidor, String BD, String usuario, String contrasenya, long puerto) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Connection conBD = iniciarConexion(servidor, BD, usuario, contrasenya, (int) puerto);
        return conBD;
    }

    public Connection iniciarConexion() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Connection conBD = iniciarConexion(getServidor(), getBD(), getUsuario(), getContrasenya(), getPuerto());
        return conBD;
    }

    public Connection iniciarConexion(Info _info) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        Connection conBD = iniciarConexion(_info.getServidor(), _info.getBaseDatos(), _info.getUsuario(), _info.getContrasenya(), _info.getPuerto());
        return conBD;

    }

    public static Connection getCnn() {
        return cnn;
    }

    public String getInstancia() {
        return instancia;
    }

    public void setInstancia(String instancia) {
        this.instancia = instancia;
    }

}
