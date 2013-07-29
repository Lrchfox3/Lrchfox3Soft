/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gmail.lrchfox3.basedatos;

/**
 * @author Luis R. Chinchilla H.
 * BDMicrosoftAccess.java
 * Created on 07-25-2009, 07:28:28 AM
 */

// <editor-fold defaultstate="collapsed" desc=" Librerias ">
import java.sql.Connection;
import java.sql.DriverManager;
// </editor-fold>

public class CnnAccess {

// <editor-fold defaultstate="collapsed" desc=" Declaración de Variables ">

// </editor-fold>

    public CnnAccess(){
        
    }
        /**
     * Inicia una conexion a Base de Datos Microsoft SQL Server.
     *     
     * @param dataSourceName Nombre de la base de datos. DSN
     * @param usuario Usuario que inicia la conexion.
     * @param contrasenya Contrase�a del usuario que inicia la conexion.     
     *
     * @return El objeto que contiene la conexion a la Base de Datos.
     */
    public Connection iniciarConexion(String dataSourceName, String usuario, String contrasenya ) throws Exception {

        String dbURL = "jdbc:odbc:"+dataSourceName;
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        return DriverManager.getConnection(dbURL, usuario, contrasenya);

    }
}
