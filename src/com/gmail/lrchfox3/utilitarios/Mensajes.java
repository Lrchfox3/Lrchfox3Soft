/*
 * Mensajes.java
 *
 * Created on January 20, 2007, 6:05 PM
 *
 * Conexiones Base de Datos Microsot SQL Server.
 *
 * Copyright 2007 GO Consultores, Inc. All rights reserved.
 * Use is subject to license terms.
 *
 * @author Luis R. Chinchilla H.
 */
package com.gmail.lrchfox3.utilitarios;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import com.gmail.lrchfox3.basedatos.BD;
import com.gmail.lrchfox3.model.dto.Message;

/**
 *
 * @author Luis R. Chinchilla H.
 */
public class Mensajes {

    private int accionDatos = 0;

    /**
     * Creates a new instance of Mensajes
     */
    public Mensajes() {
    }

    /**
     * Despliega Mensajes de advertencia, confirmaci�n, informaci�n y error
     *
     * @param bd Base de Datos con la tabla que contiene el mensaje a desplegar.
     * @param padre Ventana que llama a la ventana de error.
     * @param codigoMensaje Identifica el tipo del mensaje (Informaci�n,
     * Confirmaci�n, Advertencia, Error)
     * @param titulo El titulo del mensaje.
     * @param datosMensaje informaci�n que se desea desplegar en el mensaje.
     *
     */
    public String mensajes(BD bd, int codigoMensaje, String[] datosMensaje) throws SQLException, Exception {
        String resultado = "";
        Message message = new Message();        
        Statement st = null;
        ResultSet rs = null;
        
        try {

            String consulta;
            consulta = bd.selectSQL(message.getTabla(),
                    message.getNombreCampos(new int[]{2, 3, 4}),
                    message.getNombreCampo(1) + " = " + Integer.valueOf(codigoMensaje).toString());
            st = bd.crearSentencia();
            rs = bd.ejecutarSentencia(st, consulta);

            if (rs.next()) {
                //Object tipo = rs.getObject(1);                
                //Object tituloO = rs.getObject(2);
                Object mensaje = rs.getObject(3);
               
                if (datosMensaje != null) {
                    for (String item : datosMensaje) {
                        mensaje = mensaje.toString().replaceFirst("@param", item.toString());
                    }
                }  
                if (mensaje!=null) {
                    resultado = mensaje.toString() ;
                }
            }
        } catch (Exception ex) {
            String err = "";
            if (ex.getMessage() == null) {
                err = Propiedades.appBundle.getString("DESCONOCIDO") + ".";
            } else {
                err = ex.getMessage();
            }

            mensajesErrores(null, err, "");
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
        }
        
        return "";
    }
    
    public int mensajes(java.awt.Component padre, BD bd, int codigoMensaje, String titulo, String[] datosMensaje) throws SQLException, Exception {
        int resultado = JOptionPane.YES_OPTION;
        Message message = new Message();

        //ArrayList campos = new ArrayList();
        Statement st = null;
        ResultSet rs = null;

        try {

            String consulta;
            consulta = bd.selectSQL(message.getTabla(),
                    message.getNombreCampos(new int[]{2, 3, 4}),
                    message.getNombreCampo(1) + " = " + Integer.valueOf(codigoMensaje).toString());
            st = bd.crearSentencia();
            rs = bd.ejecutarSentencia(st, consulta);

            if (rs.next()) {
                Object tipo = rs.getObject(1);                
                Object tituloO = rs.getObject(2);
                Object mensaje = rs.getObject(3);

                if (tituloO == null) {
                    tituloO = titulo;
                }
                if (tituloO == null) {
                    tituloO = Propiedades.appBundle.getString("MENSAJE");
                }

                if (datosMensaje != null) {
                    for (String item : datosMensaje) {
                        mensaje = mensaje.toString().replaceFirst("@param", item.toString());
                    }
                }

                if (tipo.equals("INF")) {
                    JOptionPane.showMessageDialog(padre, mensaje, tituloO.toString() + " (" + Propiedades.appBundle.getString("INFORMACION") + ")", JOptionPane.INFORMATION_MESSAGE);
                } else if (tipo.equals("ADV")) {
                    JOptionPane.showMessageDialog(padre, mensaje, tituloO.toString() + " (" + Propiedades.appBundle.getString("ADVERTENCIA") + ")" , JOptionPane.WARNING_MESSAGE);
                } else if (tipo.equals("ERR")) {
                    JOptionPane.showMessageDialog(padre, mensaje, tituloO.toString() + " (" + Propiedades.appBundle.getString("ERROR") + ")", JOptionPane.ERROR_MESSAGE);
                } else if (tipo.equals("CFM")) {
                    resultado = JOptionPane.showConfirmDialog(padre, mensaje, tituloO.toString() + " (" + Propiedades.appBundle.getString("CONFIRMACION") + ")", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                }
            }
        } catch (Exception ex) {
            String err = "";
            if (ex.getMessage() == null) {
                err = Propiedades.appBundle.getString("DESCONOCIDO") + ".";
            } else {
                err = ex.getMessage();
            }

            mensajesErrores(padre, err, "");
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
        }

        return resultado;
    }

    /**
     * Ventana de mensaje de error.
     *
     * @param padre Ventana que llama a la ventana de error.
     * @param mensaje Mensaje a desplegar.
     * @param titulo El titulo del mensaje.
     */
    public void mensajesErrores(java.awt.Component padre, Object mensaje, String titulo) {
        if (mensaje == null) {
            mensaje = Propiedades.appBundle.getString("MENSAJE_ERROR_DESCONOCIDO");
        }
        JOptionPane.showMessageDialog(padre, mensaje, titulo + " (Error)", JOptionPane.ERROR_MESSAGE);
    }

}
