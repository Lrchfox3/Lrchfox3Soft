/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.lrchfox3.utilitarios;

/**
 *
 * @author LuisR
 */
public class Info {

    private String instancia;
    private String servidor;
    private String baseDatos;
    private String aplicacion;
    private String usuario;
    private String contrasenya;
    private int puerto;

    public String getInstancia() {
        return instancia;
    }

    public void setInstancia(String instancia) {
        this.instancia = instancia;
    }

    
    
    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public String getBaseDatos() {
        return baseDatos;
    }

    public void setBaseDatos(String baseDatos) {
        this.baseDatos = baseDatos;
    }

    public String getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(String aplicacion) {
        this.aplicacion = aplicacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String Usuario) {
        this.usuario = Usuario;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String Contrasenya) {
        this.contrasenya = Contrasenya;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int Puerto) {
        this.puerto = Puerto;
    }

    public void setPuerto(long Puerto) {
        this.puerto = (int)Puerto;
    }
    
}
