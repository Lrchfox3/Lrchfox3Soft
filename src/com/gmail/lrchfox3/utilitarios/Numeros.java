/**
 * ConexionDB.java
 *
 * Created on January 4, 2007, 1:42 AM
 *
 * Copyright 2006 GO Consultores, Inc. All rights reserved.
 * Use is subject to license terms.
 *
 * @author Luis R. Chinchilla H.
 */

package com.gmail.lrchfox3.utilitarios;

// <editor-fold defaultstate="collapsed" desc=" Librerias ">            

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.gmail.lrchfox3.basedatos.BD;    
// </editor-fold>
/**
 * Provee funciones para manipulacion de Numeros.
 *
 * @author Luis R. Chinchilla H.
 */
public class Numeros {
    
    /**
     * Creates a new instance of Numeros 
     */
    public Numeros() {}
    
    /**
     * Indica si un caracter es Numero
     *
     * @param c Caracter para el cual se desea determinar si es numero.
     *
     * @return Verdadero si es nï¿½mero, Falso si no lo es.
     */
    public boolean isNumero(char c) {
        boolean isNumber = true;
        String validChars = "0123456789";
        if (validChars.indexOf(c) == -1) isNumber = false;

        return isNumber;
    }
    
    /**
     *
     */
    public boolean isNumber(String s) {
        boolean isNumber = true;
        String validChars = "0123456789";

        for (int i = 0; i < s.length() && isNumber; i++) 
        { 
          char c = s.charAt(i); 
          if (validChars.indexOf(c) == -1)  {
              isNumber = false;
              break;
          }
          else  isNumber = true;
        }
        
        return isNumber;
    }
    
    public boolean isDecimal(String s) {
        int pd = 0;
        boolean isDecimal = true;
        String validChars = "0123456789.";

        for (int i = 0; i < s.length() && isDecimal; i++) 
        { 
          char c = s.charAt(i); 
          if (validChars.indexOf(c) == -1)  {
              isDecimal = false;
              break;
          }
          else  isDecimal = true;
          
          if (isPuntoDecimal(c)) pd++;
        }
        
        if (pd > 1) isDecimal = false;
        
        return isDecimal;
    }
    
    public boolean isPuntoDecimal(char c) {
        boolean isPunto = true;
        String validChars = ".";
        if (validChars.indexOf(c) == -1) isPunto = false;

        return isPunto;
    }
    
    public boolean isSignoMas(char c) {
        boolean isPunto = true;
        String validChars = "+";
        if (validChars.indexOf(c) == -1) isPunto = false;

        return isPunto;
    }
    
    public boolean isSignoMenos(char c) {
        boolean isPunto = true;
        String validChars = "-";
        if (validChars.indexOf(c) == -1) isPunto = false;

        return isPunto;
    }
    
    /**
     * Genera un numero correlativo en base a una sentencia sql.
     *
     * @param bd Base de Datos que se esta utilizando para la generacion.
     * @param sql Sentencia sql para generar el correlativo.
     *
     * @return Entero con el correlativo generado.
     */
    private int generarCorrelativo(BD bd, String sql) throws Exception, SQLException {
        int correlativo = 0;
        Statement st = bd.crearSentencia();
        ResultSet rs = bd.ejecutarSentencia(st, sql);                
        
        if (rs.next()) correlativo = new Integer(rs.getObject("codigo").toString()).intValue();
        else throw new Exception("No se generï¿½ el cï¿½digo correlativo de " + rs.getMetaData().getColumnName(1) + " en " + rs.getMetaData().getTableName(1) + ".");
        
        return correlativo;
    }
    
     /**
     * Genera un numero correlativo en base al campo numerico de una tabla.
     *
     * @param bd Base de Datos que se esta utilizando para la generacion.
     * @param tabla Tabla en la cual se genera el codigo.
     * @param campo Campo que se utiliza para generar el codigo.
     *
     * @return Entero con el correlativo generado.
     */
    public int generarCorrelativo(BD bd, String tabla, String campo) throws Exception {        
        String sql = "SELECT ISNULL(MAX(" + campo + "), 0) + 1 as codigo FROM " + tabla;
        return generarCorrelativo(bd, sql);
    }
    
    /**
     * Genera un numero correlativo en base al campo varchar de una tabla.
     *
     * @param bd Base de Datos que se esta utilizando para la generacion.
     * @param tabla Tabla en la cual se genera el codigo.
     * @param campo Campo que se utiliza para generar el codigo.
     * @param inicio caracter donde comienza el numero.
     * @param tamanyo tamaï¿½o del numero.
     *
     *@return Entero con el correlativo generado.
     */
    public int generarCorrelativo(BD bd, String tabla, String campo, int inicio, int tamanyo) throws Exception {
        
        String sql = "SELECT ISNULL(MAX(CONVERT(NUMERIC, SUBSTRING(" + campo + 
                     ", " +  Integer.valueOf(inicio).toString() + 
                     "," + Integer.valueOf(tamanyo).toString() + "))), 0) + 1 as codigo FROM " + tabla;
        return this.generarCorrelativo(bd, sql);
    }
}
