/*
 * JTextoScrollBase.java
 *
 * Created on 10 de febrero de 2007, 10:58 AM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.gmail.lrchfox3.controles.textos;

/**
 *
 * @author Luis Chinchilla
 */
import com.gmail.lrchfox3.utilitarios.Propiedades;

public class JTextoScrollBase extends javax.swing.JScrollPane {
    
    /**
     * Creates a new instance of JTextoScrollBase 
     */
    public JTextoScrollBase() {
        inicializar();
    }
    
    public void inicializar() {
        setBackground( new Propiedades().getColorFondo() );
        setBorder(new javax.swing.border.EtchedBorder(javax.swing.border.EtchedBorder.LOWERED));        
    }    
    
//    public void setText(){
//        
//    }
}