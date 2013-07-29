package com.gmail.lrchfox3.paint;

// <editor-fold defaultstate="collapsed" desc=" Librerias ">
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.LinkedList;

// </editor-fold>
/**
 * Construye trazos según se le avisa de arrastre de ratón.
 */
public class PintaTrazo implements InterfaceArrastrarRaton {

    /** Lista de trazos */
    private LinkedList<Trazo> trazos;
    /** Trazo que se está construyendo actualmente */
    private Trazo trazoActual = null;
    /** Lienzo de dibujo, necesario para llamar a repaint() según se va
     * construyendo un nuevo trazo.
     */
    private Component lienzo;
    /** Color del que se está dibujando el trazo actual */
    private Color colorActual = Color.black;
    private OpcionesDibujo optDibujo;

    /** Construye una instancia de esta clase, guardandose los parámetros que
     * le pasan.
     * @param trazos Lista donde ir� metiendo los nuevos trazos que se creen.
     * @param lienzo Llamar� a repaint() de este componente seg�n se va
     * construyendo un trazo.
     */
    public PintaTrazo(LinkedList<Trazo> trazos, Component lienzo) {
        this.trazos = trazos;
        this.lienzo = lienzo;
        optDibujo = new OpcionesDibujo(trazos, lienzo);
    }

    /**
     * Crea un trazo nuevo y le pone como primer punto x,y.
     */
    public void comienzaArrastra(int x, int y) {
        trazoActual = new Trazo();
        trazoActual.setColor(colorActual);
        trazoActual.addPunto(x, y);
        trazos.add(trazoActual);
        lienzo.repaint();
    }

    /** A�ade un nuevo punto al trazo actual */
    public void arrastra(int xAntigua, int yAntigua, int xNueva, int yNueva) {
        trazoActual.addPunto(xNueva, yNueva);
        lienzo.repaint();
    }

    /** Marca que ya no hay trazo actual */
    public void finalizaArrastra(int x, int y) {
        trazoActual = null;
    }

    /** Guarda el color para el pr�ximo trazo que se dibuje */
    public void setColorActual(Color colorActual) {
        this.colorActual = colorActual;
    }

    public int getOpcionDibujo() {
        return optDibujo.getOpcionDibujo();
    }

    public void setOpcionDibujo(int opcionDibujo) {
        optDibujo.setOpcionDibujo(opcionDibujo);
    }

    public void Alejar() {
    }

    public boolean isModoArrastrar() {
        return ((JPanelPaint) lienzo).isModoArrastrar();
    }

    /*Opción Mano Alzada*/
    public void manoAlzada(MouseEvent e) {
        optDibujo.manoAlzada(e);
    }

    public void soltarManoAlzada() {
        optDibujo.soltarManoAlzada();
    }

    /* Opción Línea*/
    public void simularLinea(MouseEvent e) {
        optDibujo.simularLinea(e);
    }

    public void crearLinea() {
        optDibujo.linea();
    }

    /* Opción Rectángulo */
    public void simularRectangulo(MouseEvent e) {
        optDibujo.simularRectangulo(e);
    }

    public void crearRectangulo() {
        optDibujo.rectangulo();
    }

    public void simularRectanguloRedondo(MouseEvent e) {
        optDibujo.simularRectanguloRedondo(e);
    }

    public void crearRectanguloRedondo() {
        optDibujo.rectanguloRedondo();
    }

    /* Opción Ovalo*/
    public void simularOvalo(MouseEvent e) {
        optDibujo.simularOvalo(e);
    }

    public void crearOvalo() {
        optDibujo.ovalo();
    }

    public void setShapeMaker() {        
        optDibujo.setShapeMaker();
    }

    public int[] trazoActual(Point2D p) {
        int r[] = new int[2];
        r[0] = -1;
        r[1] = -1;
        return r;
    }
    /*No hace Nada*/

    public void actualizarTrazo(int[] current, Point2D eventPoint) {
    }
}
