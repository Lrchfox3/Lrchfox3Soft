package com.gmail.lrchfox3.controles.botones;


import com.gmail.lrchfox3.utilitarios.Propiedades;
import java.io.Serializable;


/**
 *
 * @author Luis R. Chinchilla H.
 */
public class JBotonImportar extends javax.swing.JButton implements Serializable {

    private Propiedades propiedades = new Propiedades();

    public JBotonImportar() {
        inicializar();
    }

    private void inicializar() {
        setText(Propiedades.appBundle.getString("IMPORTAR"));
        setToolTipText(Propiedades.appBundle.getString("IMPORTAR_DOCUMENTO"));
        setFont(propiedades.getFontBotones());        
        setIcon(new javax.swing.ImageIcon(propiedades.getImgImportar()));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setAlignmentX(0);
        setAlignmentY(0);
        setPreferredSize(new java.awt.Dimension(100, 26));
        setSize(106, 26);
        setMnemonic('I');
    }
}
