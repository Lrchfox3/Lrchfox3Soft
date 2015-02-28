/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.lrchfox3.basedatos;



/**
 * @author Luis R. Chinchilla H.
 * cnnSqlServer.java
 * Created on 02-12-2010, 08:46:43 PM
 */
// <editor-fold defaultstate="collapsed" desc=" Librerias ">
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
// </editor-fold>
public class CnnSqlServer {

// <editor-fold defaultstate="collapsed" desc=" Declaración de Variables ">
    private String nombreServidor = "";
    private String nombreBaseDatos = "";
    private String usuario = "";
    private String contraseyna = "";
    private int puerto = 0;
    private String nombreAplicacion = "";
    public static Connection cnn = null;
    private static int accion = 0;
    public static final int ACCION_NINGUNA = 0;
    public static final int ACCION_INSERT = 1;
    public static final int ACCION_UPDATE = 2;
    public static final int ACCION_DELETE = 3;
// </editor-fold>

    public CnnSqlServer() {
    }

    public CnnSqlServer(String servidor, String bdd, String app, String user, String pass, int puerto) throws SQLServerException, Exception {

        this.setNombreServidor(servidor);
        this.setNombreBaseDatos(bdd);
        this.setNombreAplicacion(app);
        this.setUsuario(user);
        this.setContraseyna(pass);
        this.setPuerto(puerto);
        iniciarConexion();
    }

    public Connection iniciarConexion() throws SQLServerException, Exception {
        /*this.nombreServidor = "localhost";//LCHINCHILLAPC2\\SQLEXPRESS";
        this.nombreBaseDatos = "CONTROL_CLASES";
        this.usuario = "sa";
        this.contraseyna = "123456";
        this.puerto = 1433;*/

        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName(this.getNombreServidor());
        ds.setDatabaseName(this.getNombreBaseDatos());
        ds.setUser(this.getUsuario());
        ds.setPassword(this.getContraseyna());
        ds.setPortNumber(this.getPuerto());
        ds.setApplicationName(this.getNombreAplicacion());
        cnn = ds.getConnection();
        return cnn;
    }

    public Connection iniciarConexion(String servidor, String bdd, String app, String user, String pass, int puerto) throws SQLServerException, Exception {
        this.setNombreServidor(servidor);
        this.setNombreBaseDatos(bdd);
        this.setNombreAplicacion(app);
        this.setUsuario(user);
        this.setContraseyna(pass);
        this.setPuerto(puerto);
        return iniciarConexion();
    }

    public String getContraseyna() {
        return contraseyna;
    }

    public void setContraseyna(String contraseyna) {
        this.contraseyna = contraseyna;
    }

    public String getNombreBaseDatos() {
        return nombreBaseDatos;
    }

    public void setNombreBaseDatos(String nombreBaseDatos) {
        this.nombreBaseDatos = nombreBaseDatos;
    }

    public String getNombreServidor() {
        return nombreServidor;
    }

    public void setNombreServidor(String nombreServidor) {
        this.nombreServidor = nombreServidor;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public String getNombreAplicacion() {
        return nombreAplicacion;
    }

    public void setNombreAplicacion(String nombreAplicacion) {
        this.nombreAplicacion = nombreAplicacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public static Connection getCnn() {
        return cnn;
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
    public static void setAccion(int aAccion) {
        accion = aAccion;
    }

    public static String getDescripcionAccion() {
        int action = getAccion();
        String descripcion = "";
        switch (action) {
            case ACCION_NINGUNA:
                descripcion = "Ninguna";
                break;
            case ACCION_INSERT:
                descripcion = "Agregar";
                break;
            case ACCION_UPDATE:
                descripcion = "Editar";
                break;
            case ACCION_DELETE:
                descripcion = "Eliminar";
                break;
            default:
                descripcion = "Ninguna";
                break;

        }
        return descripcion;
    }


    public static String getDescripcionAccion(int action) {
        String descripcion = "";
        switch (action) {
            case ACCION_NINGUNA:
                descripcion = "Ninguna";
                break;
            case ACCION_INSERT:
                descripcion = "Agregar";
                break;
            case ACCION_UPDATE:
                descripcion = "Editar";
                break;
            case ACCION_DELETE:
                descripcion = "Eliminar";
                break;
            default:
                descripcion = "Ninguna";
                break;

        }
        return descripcion;
    }
}
