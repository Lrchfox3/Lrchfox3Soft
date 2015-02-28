/**
 * Javier Abell�n, 24 Mayo 2006
 * Interface para las clases encargadas de hacer algo cuando se arrastre el
 * rat�n.
 */
package com.gmail.lrchfox3.paint;

// <editor-fold defaultstate="collapsed" desc=" Librerias ">
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
// </editor-fold>

/**
 * Interface para las clases encargadas de hacer algo cuando se arrastre el
 * rat�n.

 * @author Chuidiang
 *
 */
public interface InterfaceArrastrarRaton {

    /**
     * Se llama a este m�todo cuando se empieza a arrastrar el rat�n.
     * @param x del rat�n.
     * @param y del rat�n.
     */
    public void comienzaArrastra(int x, int y);

    /**
     * Se llama a este m�todo cada vez que se arrastra el rat�n.
     * @param xAntigua x del rat�n antes del arrastre
     * @param yAntigua y del rat�n antes del arrastre
     * @param xNueva x actual del rat�n
     * @param yNueva y actual del rat�n
     */
    public void arrastra(int xAntigua, int yAntigua, int xNueva, int yNueva);

    /**
     * Se llama a este método cuando se termina de arrastrar el rat�n.
     * @param x del ratón.
     * @param y del ratón.
     */
    public void finalizaArrastra(int x, int y);

    /* Opción Mano Alzada*/
    public void manoAlzada(MouseEvent e);

    public void soltarManoAlzada();

    /* Opción Línea*/
    public void simularLinea(MouseEvent e);

    public void crearLinea();

    /* Opción Rectángulo*/
    public void simularRectangulo(MouseEvent e);

    public void crearRectangulo();

    /* Opción Rectángulo Redondo*/
    public void simularRectanguloRedondo(MouseEvent e);

    public void crearRectanguloRedondo();

    /* Opción Ovalo*/
    public void simularOvalo(MouseEvent e);

    public void crearOvalo();

    public void setShapeMaker();

    public int getOpcionDibujo();

    public boolean isModoArrastrar();

    public int[] trazoActual(Point2D p);

    public void actualizarTrazo(int[] current, Point2D eventPoint);

    public void Alejar();
}
