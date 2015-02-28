package com.gmail.lrchfox3.controles.botones.paint;

/**
 * @author Luis R. Chinchilla H.
 * jBotonBorrador.java
 * Created on 03-16-2009, 10:25:05 AM
 */

// <editor-fold defaultstate="collapsed" desc=" Librerias ">
import javax.swing.ImageIcon ;
import com.gmail.lrchfox3.utilitarios.Propiedades;
import java.io.Serializable;
import javax.swing.JButton;
import com.gmail.lrchfox3.paint.OpcionesDibujo;
// </editor-fold>

public class JBotonBorrador extends JButton implements Serializable {

// <editor-fold defaultstate="collapsed" desc=" Declaración de Variables ">
    private static final long serialVersionUID = 3978706198935583032L;
    private Propiedades propiedades = new com.gmail.lrchfox3.utilitarios.Propiedades();
    private int opcionDibujo = OpcionesDibujo.BORRADOR;
// </editor-fold>

    public JBotonBorrador() {
        inicializar();
    }

    public void inicializar() {
        //setText("Borrador");
        setToolTipText("Borrador");
        setFont(propiedades.getFontBotones());
        setIcon(new ImageIcon(propiedades.getImgBorrador()));
        setAlignmentX(0);
        setAlignmentY(0);
        setPreferredSize(new java.awt.Dimension(30, 26));
        setSize(30, 26);
        setMnemonic('B');
    }

    @Override
    public String getText() {
        return "";
    }

    /**
     * @return the opcionDibujo
     */
    public int getOpcionDibujo() {
        return opcionDibujo;
    }
}
