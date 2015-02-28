/**
 * Clase que se suscribe a los movimientos de ratón y lleva las coordenadas
 * por las que se va arrastrando, avisando al InterfaceArrastrarRaton 
 * correspondiente.
 */
package com.gmail.lrchfox3.paint;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Clase que se suscribe a los movimientos de ratón y lleva las coordenadas por
 * las que se va arrastrando, avisando al InterfaceArrastrarRaton
 * correspondiente.
 * Implementa MouseMotionListener para que la pueda añadir como
 * addMouseMotionListener() de un Component java.
 * 
 * @author Chuidiang
 */
public class ListenerArrastre implements MouseListener, MouseMotionListener {

    /** Clase encargada de hacer algo con el arrastre del rat�n */
    private InterfaceArrastrarRaton accion;

    /** Construye una instancia de esta clase y se guarda la accion que se le
     * pasa.
     * @param accion Clase a la que se irá avisando del arrastre del ratón.
     */
    public ListenerArrastre(InterfaceArrastrarRaton accion) {
        this.accion = accion;
    }
    /** Si actualmente se está arrastrando o no el ratón */
    boolean arrastrando = false;
    /** x del ratón antes de producirse el último arrastre */
    int xAntigua;
    /** y del ratón antes de producirse el último arrastre */
    int yAntigua;

    private int[] current;

    /**
     * Se mueve el ratón sin arrastre. Se marca como que no se est� arrastrando
     */
    public void mouseMoved(MouseEvent e) {
        if (accion.isModoArrastrar()) {
            if (arrastrando == true) {
                accion.finalizaArrastra(xAntigua, yAntigua);
            }
            arrastrando = false;
            xAntigua = e.getX();
            yAntigua = e.getY();
        }
        //System.out.println( e.getX() + ":" + e.getY() );
    }

    /**
     * Se está arrastrando el rat�n. Se avisa a la accion correspondiente y
     * se actualizan todas las coordenadas.
     */
    public void mouseDragged(MouseEvent e) {



        if (!accion.isModoArrastrar()) {
            if (accion.getOpcionDibujo() == OpcionesDibujo.MANO_ALZADA || accion.getOpcionDibujo() == OpcionesDibujo.MANO_ALZADA_CONTINUA){
                accion.manoAlzada(e);
            }
            else if (accion.getOpcionDibujo() == OpcionesDibujo.LINEA) {
                accion.simularLinea(e);
            }
            else if (accion.getOpcionDibujo() == OpcionesDibujo.RECTANGULO) {
                accion.simularRectangulo(e);
            }
            else if (accion.getOpcionDibujo() == OpcionesDibujo.RECTANGULO_REDONDO) {
                accion.simularRectanguloRedondo(e);
            }
            else if (accion.getOpcionDibujo() == OpcionesDibujo.OVALO) {
                accion.simularOvalo(e);
            }
        }
        else {
            if (current[0]==-1){
                return;
            }
            else {
                accion.actualizarTrazo(current, e.getPoint());
            }
//            if (arrastrando == false) {
//                accion.comienzaArrastra(e.getX(), e.getY());
//                arrastrando = true;
//            }
//            accion.arrastra(xAntigua, yAntigua, e.getX(), e.getY());
//            xAntigua = e.getX();
//            yAntigua = e.getY();
//            accion.simularLinea(e);
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (!accion.isModoArrastrar()) {
            if (accion.getOpcionDibujo() == OpcionesDibujo.MANO_ALZADA){
               accion.soltarManoAlzada();
            }
            else if (accion.getOpcionDibujo() == OpcionesDibujo.LINEA) {
                accion.soltarManoAlzada();
                accion.crearLinea();
            }
            else if (accion.getOpcionDibujo() == OpcionesDibujo.RECTANGULO) {
                accion.soltarManoAlzada();
                accion.crearRectangulo();
            }
            else if (accion.getOpcionDibujo() == OpcionesDibujo.RECTANGULO_REDONDO) {
                accion.soltarManoAlzada();
                accion.crearRectanguloRedondo();
            }
            else if (accion.getOpcionDibujo() == OpcionesDibujo.OVALO) {
                accion.soltarManoAlzada();
                accion.crearOvalo();
            }
        }
        else {
            current = new int[1];
            current[0]= -1;
        }
    }

    /**
     * Permite cambiar la acción a realizar cuando se arrastre el rat�n.
     * @param accion La nueva acci�n a realizar.
     */
    public void setAccion(InterfaceArrastrarRaton accion) {
        this.accion = accion;
    }

    public void mouseClicked(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mousePressed(MouseEvent e) {
        if (accion.isModoArrastrar()) {
            current = accion.trazoActual(e.getPoint() );
            System.out.println(current[0] + " : " + current[1]);
        }
    }

    public void mouseEntered(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }
}
