/*
 * jEtiquetaBase.java
 *
 * Created on January 14, 2007, 7:21 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */
package com.gmail.lrchfox3.controles.textos;

import com.gmail.lrchfox3.basedatos.Base;
import com.gmail.lrchfox3.utilitarios.Propiedades;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis R. Chinchilla H.
 */
public class JEtiquetaBase extends javax.swing.JLabel  { //implements MouseListener

    private Propiedades propiedades = new Propiedades();
    private Base bean = new Base();
    private int index = -1;
//    private URI url = null;

    /**
     * Creates a new instance of jEtiquetaBase
     */
    public JEtiquetaBase() {
        inicializar();
    }

    public void inicializar() {
        setFont(propiedades.getFontEtiquetas());
        setForeground(super.getForeground());
        //setPreferredSize(90, 15));
        //setSize(getPreferredSize());
    }

    public void setBindingBean( Base bean ){
        this.bean = bean;
        
    }
    
    public void setIndexBindingBean( int index ){
        this.index = index;        
    }
    
    public String getText(){
        String text = super.getText();
        if (this.bean!=null){        
            try {
                if (this.index>-1){
                text = this.bean.getEtiquetaCampo(this.index);
                }
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(JEtiquetaBase.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(JEtiquetaBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return text;
    }
    /**
     * Metodo para asignar una direccion web
     *
     * @param url URL de pagina web
     */
//    public void setURL(String url) {
//        try {
//            if (url != null) {
//                this.url = new URI(url);
//                this.setToolTipText(url);
//                this.setCursor(new Cursor(Cursor.HAND_CURSOR));
//            }
//        } catch (URISyntaxException ex) {
//            System.err.println(ex.getMessage());
//        }
//    }

    /**
     * Retorna la direccion web que este asignado al objeto
     *
     * @return String Direccion URL
     */
//    public String getURL() {
//        String value = ( url!=null ? this.url.toString() : "");
//        return value;
//    }

    /**
     * Cuando se realice un clic sobre el componente JLabel, se abre el enlace
     * en el navegador predefinido del sistema operativo
     */
//    public void mouseClicked(MouseEvent e) {
//        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
//        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
//            try {
//                if (url != null) {
//                    desktop.browse(url);
//                }
//            } catch (Exception ex) {
//                System.err.println(ex.getMessage());
//            }
//        }
//    }
//
//    @Override
//    public void mousePressed(MouseEvent e) {
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent e) {
//    }
//
//    @Override
//    public void mouseEntered(MouseEvent e) {
//    }
//
//    @Override
//    public void mouseExited(MouseEvent e) {
//    }
}
