/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.lrchfox3.model.dto;


import com.gmail.lrchfox3.basedatos.Campo;
import java.lang.reflect.Field;

/**
 *
 * @author chinchillal
 */
@Deprecated
public class Base  {

    private String tabla = null;
    private String titulo = null;
    private String signosParametros = "";

    public Base() {
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNombreCampos() throws IllegalArgumentException, IllegalAccessException {
        String nombres = "";
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getType().toString().toLowerCase().equals("class " + Campo.class.getName().toLowerCase())) {
                Campo c = (Campo) field.get(this);
                nombres = nombres + c.getNombre() + ",";
            }
        }
        if (nombres.length() > 0) {
            nombres = nombres.substring(0, nombres.length() - 1);
        }
        return nombres;
    }

    public String getNombreCampo(int indice) throws IllegalArgumentException, IllegalAccessException {
        String name = "";
        int j = 1;
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getType().toString().toLowerCase().equals("class " + Campo.class.getName().toLowerCase())) {
                if (j == indice) {
                    Campo c = (Campo) field.get(this);
                    name = c.getNombre();
                    break;
                }
                j++;
            }
        }
        return name;
    }

    public String getNombreCampos(int[] indices) throws IllegalArgumentException, IllegalAccessException {
        String nombres = "";
        signosParametros = "";
        int j = 1;
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getType().toString().toLowerCase().equals("class " + Campo.class.getName().toLowerCase())) {
                if (contieneIndice(indices, j)) {
                    Campo c = (Campo) field.get(this);
                    nombres = nombres + c.getNombre() + ",";
                    signosParametros = signosParametros + "?,";
                }
                j++;
            }
        }
        if (nombres.length() > 0) {
            nombres = nombres.substring(0, nombres.length() - 1);
            signosParametros = signosParametros.substring(0, signosParametros.length() - 1);
        }
        return nombres;
    }
    
    public String getSignosParametros(){        
        return signosParametros;
    }

    private boolean contieneIndice(int[] indices, int idx) {
        for (int i : indices) {
            if (idx == i) {
                return true;
            }
        }
        return false;
    }
}
