/*
 * Objetos.java
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

/**
 *
 * @author Luis R. Chinchilla H.
 */
public class Objetos {

    public static final int TIPO_TEXTO = 1;
    public static final int TIPO_TEXTO_FORMATEADO = 2;
    public static final int TIPO_TEXTO_FECHA = 3;
    public static final int TIPO_FECHA = 4;
    public static final int TIPO_COMBO = 5;
    public static final int TIPO_TABLA = 6;
    public static final int TIPO_HORA = 7;
    public static final int TIPO_CHECKBOX = 8;
    public static final int TIPO_ITEM = 11;
    public static final int TIPO_ENTERO = 15;
    public static final int TIPO_STRING = 16;
    public static final int TIPO_OBJETO = 20;

    /**
     * Creates a new instance of Objetos
     */
    public Objetos() {
    }

    /**
     * Identifica el nombre de la clase de la que desciende el objeto.
     *
     * @param objeto el objeto para el cual se desea saber la clase.
     *
     * @return String con el nombre de la clase a la que pertenece el objeto.
     */
    public String nombreClaseObjeto(Object objeto) {
        String nombreClase = objeto.getClass().getName();
        return nombreClase;
    }

    /**
     * Identifica el tipo general de un objeto
     *
     * @param objeto el objeto para el cual se desea saber el tipo.
     *
     * @return Entero con el tipo general del objeto.
     */
    public int tipoObjeto(Object objeto) {
        int tipo = 0;
        String nombreClase = nombreClaseObjeto(objeto);

        if ((nombreClase.equals("textos.JTextoBase"))
                || (nombreClase.equals("textos.JTextoComentarios"))
                || (nombreClase.equals("javax.swing.JTextField"))
                || (nombreClase.equals("javax.swing.JTextArea"))) {
            tipo = TIPO_TEXTO;
        } else if ((nombreClase.equals("javax.swing.JFormattedTextField"))
                || (nombreClase.equals("textos.JTextoFormato"))) {
            tipo = TIPO_TEXTO_FORMATEADO;
        } else if (nombreClase.equals("utilitarios.calendario.JTextFieldDateEditor")) {
            tipo = TIPO_TEXTO_FECHA;
        } else if (nombreClase.equals("utilitarios.calendario.JDateChooser")) {
            tipo = TIPO_FECHA;
        } else if ((nombreClase.equals("listas.JComboBase"))) {
            tipo = TIPO_COMBO;
        } else if ((nombreClase.equals("tablas.JTablaBase"))
                || (nombreClase.equals("javax.swing.JTable"))) {
            tipo = TIPO_TABLA;
        } else if ((nombreClase.equals("textos.JHora"))) {
            tipo = TIPO_HORA;
        } else if ((nombreClase.equals("selecciones.JCheckBoxBase"))) {
            tipo = TIPO_CHECKBOX;
        } else if ((nombreClase.equals("utilitarios.Item"))) {
            tipo = TIPO_ITEM;
        } else if ((nombreClase.equals("tiposBasicos.Entero"))) {
            tipo = TIPO_ENTERO;
        } else if ((nombreClase.equals("tiposBasicos.Caracteres"))) {
            tipo = TIPO_STRING;
        } else if ((nombreClase.equals("java.lang.Object"))) {
            tipo = TIPO_OBJETO;
        }


        return tipo;
    }

    /**
     * Convierte un arreglo de objetos a un ArrayList.
     *
     * @param objetos Arreglo de objetos a convertir.
     *
     * @return ArrayList
     */
    public ArrayList ObjetosAListaArreglos(Object[] objetos) {
        ArrayList lista = new ArrayList();
        for (int i = 0; i < objetos.length; i++) {
            lista.add(objetos);
        }

        return lista;
    }
}
