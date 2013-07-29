/*
 * jBotonCancelar.java
 *
 * Created on January 10, 2007, 10:34 PM
 */

package com.gmail.lrchfox3.controles.botones;


import com.gmail.lrchfox3.utilitarios.Propiedades;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 *
 * @author Luis R. Chinchilla H.
 */
public class JBotonCancelar extends JButton implements Serializable {
        /**
     * serial uid
     */
    private static final long serialVersionUID = 3978706198935583032L;

    /** Creates new form BeanForm */
    public JBotonCancelar() {
        inicializar();       
    }
    
    private void inicializar() {                
        setText(Propiedades.appBundle.getString("CANCELAR"));
        setToolTipText(Propiedades.appBundle.getString("CANCELAR"));
        setFont(propiedades.getFontBotones());   
        try {
        setIcon(new ImageIcon(propiedades.getImgCancelar()));
        } catch (Exception e) {
          e.printStackTrace();
        }
        setAlignmentX(0);
        setAlignmentY(0);
        setPreferredSize(new java.awt.Dimension(100, 26));
        setSize(112, 26);     
        setMnemonic('C');
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    private Propiedades propiedades = new com.gmail.lrchfox3.utilitarios.Propiedades();
}
