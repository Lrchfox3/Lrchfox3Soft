/*
 * ParametrosBusqueda.java
 *
 * Created on December 17, 2006, 8:50 PM
 */

package com.gmail.lrchfox3.controles.tablas;

import java.beans.*;
import java.io.Serializable;

/**
 * @author Luis R. Chinchilla H.
 */
public class ParametrosBusqueda extends Object implements Serializable {
    
    public ParametrosBusqueda() {
   
    }

    private int numeroCampos;
    private String consulta;
    private String[] titulos;
    private String[] campos;
    private int[] tamanyos;   
    private int defaultTamanyo = 80;
    private Class[] tiposDatos;
    private int[] posCamposRetorno;
    private Object[] valoresRetorno;

    public int getNumeroCampos() {
        return numeroCampos;
    }

    /**
     * Se utiliza para definir el n�mero de campos que se crear�n y utilizar�n en la pantalla de b�squeda.
     * Estos campos se desplegar�n visualmente y permitir�n realizar filtro de datos.
     * @param numeroCampos Entero que representa el n�mero de campos.
     */
    public void setNumeroCampos(int numeroCampos) {
        this.numeroCampos = numeroCampos;
    }

    public String getConsulta() {
        return consulta;
    }

    /**
     * Utilizada para definir la sentencia de sql que genera la pantalla de b�squeda.
     * @param consulta String que contiene la sentencia sql.
     */
    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public String getTitulo(int indice) {
        if (indice < titulos.length) return titulos[indice];
        return "";
    }
    
    public String[] getTitulos() {
        return titulos;
    }
    
    /**
     * Define los t�tulos que tendr� cada campo en la pantalla de b�squeda.
     * @param titulos Arreglo de String que contiene los t�tulos de cada campo que se genera en la b�squeda.
     */
    public void setTitulos(String[] titulos) {
        this.titulos = titulos;
    }
    
    public String getCampo(int indice) {
        return campos[indice];
    }
    
    public String[] getCampos() {
        return campos;
    }

    public void setCampos(String[] campos) {
        this.campos = campos;
    }
    
    public int[] getTamanyos() {
        return tamanyos;
    }

    public int getTamanyo(int indice) {
        if (indice < tamanyos.length) return tamanyos[indice];
        return getDefaultTamanyo();
    }
    
    public int getTotalTamanyos()
    {
        int i, totalTamanyo = 0;
        for (i=0; i < tamanyos.length; i++) {
            totalTamanyo = totalTamanyo + getTamanyo(i);
        }
        
        return totalTamanyo;
    }
    
    public void setTamanyos(int[] tamanyos) {
        this.tamanyos = tamanyos;
    }

    public int getDefaultTamanyo() {
        return defaultTamanyo;
    }

    public void setDefaultTamanyo(int defaultTamanyo) {
        this.defaultTamanyo = defaultTamanyo;
    }

    public Class[] getTiposDatos() {
        return tiposDatos;
    }

    public void setTiposDatos(Class[] tiposDatos) {
        this.tiposDatos = tiposDatos;
    }

    public int[] getPosCamposRetorno() {
        return posCamposRetorno;
    }

    public void setPosCamposRetorno(int[] posCamposRetorno) {
        this.posCamposRetorno = posCamposRetorno;
    }

    public Object[] getValoresRetorno() {
        return valoresRetorno;
    }

    public void setValoresRetorno(Object[] valoresRetorno) {
        this.valoresRetorno = valoresRetorno;
    }

    
}
