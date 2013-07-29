/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gmail.lrchfox3.model.dto;


import com.gmail.lrchfox3.basedatos.Campo;
import java.util.List;

/**
 *
 * @author chinchillal
 */
public abstract interface Catalogo {


    public String getNombreTabla();

    public String getTitulo();

    public List<Campo> getCampos() throws Exception;

}
