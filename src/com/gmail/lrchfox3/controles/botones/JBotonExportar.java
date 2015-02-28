package com.gmail.lrchfox3.controles.botones;


import com.gmail.lrchfox3.utilitarios.Propiedades;
import java.io.Serializable;


/**
 *
 * @author Luis R. Chinchilla H.
 */
public class JBotonExportar extends javax.swing.JButton implements Serializable {

    private Propiedades propiedades = new Propiedades();

    public JBotonExportar() {
        inicializar();
    }

    private void inicializar() {
        setText(Propiedades.appBundle.getString("EXPORTAR"));
        setToolTipText(Propiedades.appBundle.getString("EXPORTAR_DOCUMENTO"));
        setFont(propiedades.getFontBotones());        
        setIcon(new javax.swing.ImageIcon(propiedades.getImgExportar()));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setAlignmentX(0);
        setAlignmentY(0);
        setPreferredSize(new java.awt.Dimension(100, 26));
        setSize(106, 26);
        setMnemonic('E');
    }
}
