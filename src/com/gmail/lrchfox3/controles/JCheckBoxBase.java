/*
 * jCheckBoxBase.java
 *
 * Created on January 31, 2007, 9:49 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.gmail.lrchfox3.controles;

import com.gmail.lrchfox3.utilitarios.Propiedades;
/**
 *
 * @author Luis R. Chinchilla H.
 */
public class JCheckBoxBase extends javax.swing.JCheckBox {
     
     private Propiedades propiedades = new Propiedades();    
     
    /** Creates a new instance of jCheckBoxBase */
    public JCheckBoxBase() {
        inicializar();
    }
    
    public void inicializar() {
        setBorder(new javax.swing.border.EtchedBorder());
        setBackground(propiedades.getColorFondo());
        setFont(propiedades.getFontTextos());
    }
    
    /**
     *  Dependiendo del valor selecciona o no el checkbox
     * 
     *  param valor Valor que indica si seleccionar o no el checkbox
     *  El valor aceptado para seleccionar es 'S'
     *  El valor aceptado para deseleccionar es 'N'
     */
    public void seleccionar(Object valor) throws Exception {
        boolean v = false;
        
        if (valor != null) {            
            if (valor.equals("S")) v = true;            
            else if (valor.equals("N"))    ;
            else
                throw new Exception("El valor es incorrecto.  Valores aceptados ('S', 'N')");                        
        } 
        
        setSelected(v);
    }
    
    public String getSeleccionado() {
        String valor = "N";
        if (isSelected()) valor = "S";
        
        return valor;        
    }
}
