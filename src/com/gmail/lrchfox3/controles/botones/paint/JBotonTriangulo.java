package com.gmail.lrchfox3.controles.botones.paint;

/**
 * @author Luis R. Chinchilla H.
 * jBotonRectangulo.java
 * Created on 03-16-2009, 10:31:42 AM
 */

// <editor-fold defaultstate="collapsed" desc=" Librerias ">
import javax.swing.ImageIcon ;
import com.gmail.lrchfox3.utilitarios.Propiedades;
import java.io.Serializable;
import javax.swing.JButton;
import com.gmail.lrchfox3.paint.OpcionesDibujo;
// </editor-fold>

public class JBotonTriangulo extends JButton implements Serializable {

// <editor-fold defaultstate="collapsed" desc=" Declaración de Variables ">
    private static final long serialVersionUID = 3978706198935583032L;
    private Propiedades propiedades = new com.gmail.lrchfox3.utilitarios.Propiedades();
    private int opcionDibujo = OpcionesDibujo.TRIANGULO;
// </editor-fold>

    public JBotonTriangulo() {
        inicializar();
    }

    public void inicializar() {
        //setText("Rectángulo");
        setToolTipText("Triángulo");
        setFont(propiedades.getFontBotones());
        setIcon(new ImageIcon(propiedades.getImgTriangulo()));
        setAlignmentX(0);
        setAlignmentY(0);
        setPreferredSize(new java.awt.Dimension(30, 26));
        setSize(30, 26);
        setMnemonic('T');
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
