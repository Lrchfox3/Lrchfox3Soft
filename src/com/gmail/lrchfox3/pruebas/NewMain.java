/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gmail.lrchfox3.pruebas;

/**
 * @author Luis R. Chinchilla H.
 * NewMain.java
 * Created on 07-25-2009, 07:49:49 AM
 */

// <editor-fold defaultstate="collapsed" desc=" Librerias ">
import com.gmail.lrchfox3.basedatos.BD;
import java.sql.*;
import java.sql.ResultSet;
import java.util.ArrayList;
// </editor-fold>

public class NewMain {

// <editor-fold defaultstate="collapsed" desc=" Declaración de Variables ">
public static BD bd = null;
private static ArrayList lstCampos = new ArrayList();
// </editor-fold>

    private static void iniciarConexion() throws Exception {
        bd.iniciarConexion( "Adeltos",bd.DBMSMSACCESS,"Adeltos","","");
    }

    public static String obtenerTipo(String tabla, String codigo){
        Statement st = null;
        ResultSet rs = null;
        String strTipo = null;

        try{
            lstCampos.clear();
            lstCampos.add("descripcion");
            st = bd.crearSentencia();
            rs = bd.ejecutarSentencia(st, bd.selectSQL(tabla, lstCampos, tabla + ".\"codigo\" = " + codigo) );
            while (rs.next()){
                strTipo = rs.getString("descripcion");
            }
        }
        catch(Exception e){
            System.out.println("No se logro Obtener el tipo, error: " + e.getMessage());
        }
        finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
            }
            catch(Exception nex) {
                System.out.println("Error: " + nex.getMessage());
            }

        }
        return strTipo;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        bd = new BD();

        try {
            iniciarConexion();
            bd.setConexion("Adeltos");
            System.out.println(obtenerTipo("tipoPuntos","5"));
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
