/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.ludwen;

import com.gmail.lrchfox3.basedatos.CnnMySql;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 *
 * @author LuisR
 */
public class Inicio {

 private static final ResourceBundle cnnConfig = ResourceBundle.getBundle("com/outlook/ludwen/Resources/cnnConfig");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String mensaje = "";

        try {

            CnnMySql conMySql = new CnnMySql();
            conMySql.setServidor(cnnConfig.getString("NombreServidor"));
            conMySql.setBD(cnnConfig.getString("NombreBaseDatos"));            
            conMySql.setUsuario(cnnConfig.getString("Usuario"));
            conMySql.setContrasenya(cnnConfig.getString("Contrasenya"));
            conMySql.setPuerto(Integer.valueOf(cnnConfig.getString("Puerto")));
            conMySql.iniciarConexion();

        } catch (SQLException ex) {
            mensaje = "Error de conexión: " + ex.getMessage() + ". ref: Inicio.Main";
            System.out.println(mensaje);
        } catch (Exception ex) {
            mensaje = "Error Desconocido: " + ex.getMessage() + ". ref: Inicio.Main";
            System.out.println(mensaje);
        }
 /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistraCierreDiario().setVisible(true);
            }
        });
    }
}
    

