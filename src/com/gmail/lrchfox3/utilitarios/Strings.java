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

// <editor-fold defaultstate="collapsed" desc=" Librerias">  
import java.util.ArrayList;
import java.util.Vector;
// </editor-fold>  

/**
 * Provee funciones para manipulacion de Strings
 *
 * @author Luis R. Chinchilla H.
 */
public class Strings {
    
    /**
     * Creates a new instance of Strings 
     */
    public Strings() {}
    
    /**
     * Convierte un ArrayList en un string.
     *
     *@param arreglo ArrayLista que se desea convertir a String.
     *@param separador Caracter(es) que separan cada elemento en el String resultante.
     *
     *@return El string convertido
     */
    public String listaArreglosAString(ArrayList arreglo, String separador) {
        String valor = "";
        for (int i = 0; i < arreglo.size(); i++) {
            if (!valor.equals("")) valor = valor + separador;
            Object v = arreglo.get(i);
            if (v == null) v = "null";
            valor = valor + v.toString();
        }
        
        return valor;
    }

    public String vectorAString(Vector vector, String separador) {
        String valor = "";
        for (int i = 0; i < vector.size(); i++) {
            if (!valor.equals("")) valor = valor + separador;
            valor = valor + vector.get(i).toString();
        }
        
        return valor;
    }
    
        public String arrayListAString(ArrayList list, String separador) {
        String valor = "";
        for (int i = 0; i < list.size(); i++) {
            if (!valor.equals("")) valor = valor + separador;
            valor = valor + list.get(i).toString();
        }
        
        return valor;
    }
}
