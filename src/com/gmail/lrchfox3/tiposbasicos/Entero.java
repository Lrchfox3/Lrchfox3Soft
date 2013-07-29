/*
 * Entero.java
 *
 * Created on March 11, 2007, 8:09 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.gmail.lrchfox3.tiposbasicos;

/** 
 * <code>Entero</code> clase primitiva que se utiliza para manipular tipos de datos enteros.
 * 
 * @author Luis R. Chinchilla H.
 */

public class Entero {
    
    private Integer entero = null;
            
    /** Creates a new instance of Entero */
    public Entero() {
    }

    public Integer getEntero() {
        return entero;
    }

    public void setEntero(Integer entero) {
        this.entero = entero;
    }
    
    public String toString() {
        return entero.toString();
    }
}
