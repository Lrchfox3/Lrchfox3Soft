/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.lrchfox3.basedatos;

import com.gmail.lrchfox3.utilitarios.Utileria;

/**
 *
 * @author chinchillal
 */
public class Campo {

    private String nombre = null;
    private String etiqueta = null;
    private Object value = null;
    private boolean llave = false;
    private boolean visible = true;
    private boolean nulo = true;
    private int length = -1;
    private int tipo = SqlTipos.NONE;

    public Campo() {
    }

    public Campo(String _nombre) {
        this.nombre = _nombre;
    }

    public Campo(String _nombre, int _tipo) {
        this.nombre = _nombre;
        this.tipo = _tipo;
    }

    public Campo(String _nombre, String _etiqueta, int _tipo) {
        this.nombre = _nombre;
        this.etiqueta = _etiqueta;
        this.tipo = _tipo;
    }
    
    public Campo(String _nombre, String _etiqueta, int _tipo, int _length) {
        this.nombre = _nombre;
        this.etiqueta = _etiqueta;
        this.tipo = _tipo;
        this.length = _length;
    }
    
    public Campo(String _nombre, String _etiqueta, int _tipo, int _length, boolean _nulo) {
        this.nombre = _nombre;
        this.etiqueta = _etiqueta;
        this.tipo = _tipo;
        this.length = _length;
        this.nulo = _nulo;
    }

    public Campo(String _nombre, String _etiqueta, int _tipo, boolean _esllave) {
        this.nombre = _nombre;
        this.etiqueta = _etiqueta;
        this.tipo = _tipo;
        this.llave = _esllave;
    }

    
    public Campo(String _nombre, String _etiqueta, int _tipo, boolean _esllave, int _length, boolean _nulo) {
        this.nombre = _nombre;
        this.etiqueta = _etiqueta;
        this.tipo = _tipo;
        this.llave = _esllave;
        this.length = _length;
        if (this.llave){
            this.nulo = false;
        } else {     
            this.nulo = _nulo;
        }
    }
    
    

    
    public Campo(String _nombre, String _etiqueta, int _tipo, boolean _esllave, boolean _visible) {
        this.nombre = _nombre;
        this.etiqueta = _etiqueta;
        this.tipo = _tipo;
        this.llave = _esllave;
        this.visible = _visible;
    }

    public Campo(String _nombre, String _etiqueta, int _tipo, boolean _esllave, boolean _visible, boolean _nulo, int _length) {
        this.nombre = _nombre;
        this.etiqueta = _etiqueta;
        this.tipo = _tipo;
        this.llave = _esllave;
        this.visible = _visible;
        this.nulo = _nulo;
        this.length = _length;
    }

    public Campo(String _nombre, Object _valor, String _etiqueta, int _tipo) {
        this.nombre = _nombre;
        this.etiqueta = _etiqueta;
        this.value = _valor;
        this.tipo = _tipo;
    }
    
     public Campo(String _nombre, String _etiqueta, boolean _nulo, int _tipo) {
        this.nombre = _nombre;
        this.etiqueta = _etiqueta;
        this.tipo = _tipo;
    }

    public Campo(String _nombre, Object _valor, String _etiqueta, int _tipo, int _length, boolean _nulo) {
        this.nombre = _nombre;
        this.etiqueta = _etiqueta;
        this.value = _valor;
        this.tipo = _tipo;
        this.nulo = _nulo;
        this.length = _length;
    }

    public Campo(String _nombre, Object _valor) {
        this.nombre = _nombre;
        this.value = _valor;
        this.tipo = SqlTipos.VARCHAR;
    }

    public void setLlave(boolean value) {
        this.llave = value;
    }

    public boolean esLlave() {

        return this.llave;
    }

    public void setVisible(boolean value) {
        this.visible = value;
    }

    public boolean esVisible() {

        return this.visible;
    }

    public int getIntValue() {
        return (getValue() != null ? ((Integer) getValue()).intValue() : -999999);
    }

    public double getDoubleValue() {        
        return (getValue()!= null ? Double.parseDouble(getValue().toString())  : -999999);
    }
        
    public String getStringValue() {
        return (getValue() != null ? getValue().toString() : "");
    }

    public java.sql.Date getDateValue() {
        java.sql.Date now = Utileria.getFechaActual();
        if (getValue() != null) {
            now = (java.sql.Date) getValue();
        }
        return now;
    }

    public void setValue(Object valor) {
        if (getTipo() == SqlTipos.INTEGER) {
            value = (valor != null ? ((Integer) valor).intValue() : -999999);
        } else if (getTipo() == SqlTipos.VARCHAR) {
            value = (valor != null ? valor.toString() : "");
        } 
        else if (getTipo() == SqlTipos.DECIMAL) {
            value = (valor != null ? ((Double) valor).intValue() : -999999);
        }else if (getTipo() == SqlTipos.DATE_TIME) {
            java.sql.Date now = new java.sql.Date(System.currentTimeMillis());
            value = (valor != null ? valor : now);
        }
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the etiqueta
     */
    public String getEtiqueta() {
        return etiqueta;
    }

    /**
     * @param etiqueta the etiqueta to set
     */
    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    /**
     * @return the value
     */
    public Object getValue() {
        return value;
    }

    /**
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public boolean isNulo() {
        return nulo;
    }

    public void setNulo(boolean nulo) {
        this.nulo = nulo;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
    
    
}
