/*
 * Evaluar.java
 *
 * Created on April 29, 2007, 6:51 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.gmail.lrchfox3.utilitarios;

import com.gmail.lrchfox3.basedatos.BD;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author Luis R. Chinchilla H.
 */
public class Evaluar {
    
    /** Creates a new instance of Evaluar */
    public Evaluar() {
    }
    
    /**
     * Permite evaluar
     */
    public boolean validar(BD bd, String expresion) throws Exception {
        boolean resultado = false;        
        Statement st = bd.crearSentencia();  
        String sql = "SELECT CASE WHEN " + expresion + " THEN 1 ELSE 0 END";
        ResultSet rs = bd.ejecutarSentencia(st, sql);
        
        if (rs.next())
            if (rs.getInt(1) == 1) resultado = true;
        
        return resultado;
    }
    
    /**
     * Ejecuta el calculo de la expresion y devuelve el valor calculado.
     */
    public Object calcular(BD bd, String expresion) throws Exception {
        Object valor = null;
        Statement st = bd.crearSentencia();  
        String sql = "SELECT " + expresion;
        ResultSet rs = bd.ejecutarSentencia(st, sql);        
        if (rs.next()) valor = rs.getObject(1);
        
        return valor;
    }
}
