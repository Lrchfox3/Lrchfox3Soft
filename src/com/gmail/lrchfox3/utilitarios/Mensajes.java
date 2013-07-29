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

import java.util.ArrayList;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import com.gmail.lrchfox3.basedatos.BD;

/**
 *
 * @author Luis R. Chinchilla H.
 */
public class Mensajes {
    
    private int accionDatos = 0;
    
    /**
     * Creates a new instance of Mensajes 
     */
    public Mensajes() {}

    /**
     * Despliega Mensajes de advertencia, confirmaci�n, informaci�n y error
     *
     * @param bd Base de Datos con la tabla que contiene el mensaje a desplegar.
     * @param padre Ventana que llama a la ventana de error.
     * @param codigoMensaje Identifica el tipo del mensaje (Informaci�n, Confirmaci�n, Advertencia, Error)
     * @param titulo El titulo del mensaje.
     * @param datosMensaje informaci�n que se desea desplegar en el mensaje.
     *
     */
    public int mensajes(java.awt.Component padre, BD bd,  int codigoMensaje, String titulo, String[] datosMensaje) throws SQLException, Exception{        
        int resultado = JOptionPane.YES_OPTION;

        ArrayList campos = new ArrayList();
        Statement st = null;
        ResultSet rs = null;

        try {
                campos.add("type");
                campos.add("title");
                campos.add("message");
                
        
                String consulta = bd.selectSQL("message", campos, "message_cod = " + Integer.valueOf(codigoMensaje).toString());
                st = bd.crearSentencia();
                rs = bd.ejecutarSentencia(st, consulta);
        
                if (rs.next()) {
                    Object tipo = rs.getObject(1);
                    Object mensaje = rs.getObject(2);
                    Object tituloO = rs.getObject(3);

                    if (tituloO == null) tituloO = titulo;
                    if (tituloO == null) tituloO = "Mensaje";

                    if (datosMensaje != null) {
                        for (int i = 0; i < datosMensaje.length; i++) {
                            mensaje = mensaje.toString().replaceFirst("\\?", datosMensaje[i].toString());
                        }
                    }

                    if (tipo.equals("INF")) {  
                        JOptionPane.showMessageDialog(padre, mensaje, tituloO.toString() + " (Información)", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else if (tipo.equals("ADV")) {
                        JOptionPane.showMessageDialog(padre, mensaje, tituloO.toString() + " (Advertencia)", JOptionPane.WARNING_MESSAGE);
                    }
                    else if (tipo.equals("ERR")) {
                        JOptionPane.showMessageDialog(padre, mensaje, tituloO.toString() + " (Error)", JOptionPane.ERROR_MESSAGE);
                    }
                    else if (tipo.equals("CFM")) {
                        resultado = JOptionPane.showConfirmDialog(padre, mensaje, tituloO.toString() + " (Confirmación)", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);                        
                    }
                }                        
        }
        catch (Exception ex) {
            String err ="";
            if (ex.getMessage() == null) err = "Desconocido.";
            else                         err = ex.getMessage();
            
            mensajesErrores(padre, err , "");
        }
        finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
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
        if (mensaje == null) mensaje = "Error Desconocido. Consulte a su Proveedor.";
        JOptionPane.showMessageDialog(padre, mensaje, titulo + " (Error)", JOptionPane.ERROR_MESSAGE);
    }

}
