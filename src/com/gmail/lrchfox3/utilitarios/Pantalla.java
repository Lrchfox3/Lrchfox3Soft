/*
 * Pantalla.java
 *
 * Created on December 26, 2006, 4:52 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.gmail.lrchfox3.utilitarios;

/**
 *
 * @author Luis R. Chinchilla H.
 */
public class Pantalla {
    
    /**
     * Creates a new instance of Pantalla 
     */
    private int alto = 0;
    private int ancho = 0;
    public static final int RESOLUCION_1280X800 = 1;
    public static final int RESOLUCION_1024X768 = 2;
        
    public Pantalla() {
    }
    
    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }
    
    public int resolucion()
    {       
        int resolucion = 0;
        java.awt.GraphicsEnvironment ge = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment();        
        java.awt.GraphicsDevice gd[] = ge.getScreenDevices();                
        java.awt.DisplayMode dm = gd[0].getDisplayMode();
        
        int alto = dm.getHeight();        
        int ancho = dm.getWidth();
        
        switch (ancho) {
            case 1280: 
                resolucion = RESOLUCION_1280X800;
                setAncho(1280);
                setAlto(800);
                break;
            case 1024: 
                resolucion = RESOLUCION_1024X768;
                setAncho(1024);
                setAlto(768);
                break;
            case 800: 
                break;
        }
        
        return resolucion;        
    }
    
}
