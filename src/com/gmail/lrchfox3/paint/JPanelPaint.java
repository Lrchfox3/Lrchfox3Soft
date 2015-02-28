package com.gmail.lrchfox3.paint;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.LinkedList;

/**
 * Panel similar a un paintbrush de windows, que permite dibujar trazos 
 * arrastrando el ratón.
 * @author Chuidiang.
 *
 */
public class JPanelPaint extends Canvas implements Serializable {

    /**
     * serial uid
     */
    private static final long serialVersionUID = 3978706198935583032L;
    /** Acci�n de pintar trazo mientrasa se arrastra el ratón */
    private PintaTrazo pintaTrazo = null;
    /** Clase suscriptora de los arrastres de ratón */
    private ListenerArrastre listener = null;
    /** Lista de trazos dibujados */
    private LinkedList<Trazo> trazos = new LinkedList<Trazo>();
    /** Acci�n de mover un trazo cuando se le arrastra con el ratón. */
    private ArrastraTrazo arrastraTrazo = null;
    /** Indica si estamos en modo de arrastre de trazos o de dibujo de los 
     * mismos.
     */
    private boolean modoArrastrar = false;
    private static int SIZE = 5;
    private Color xorColor = new Color(255, 255, 255);

    /**
     * Pone el modo de arrastre de trazos.
     *
     */
    public void modoArrastrar() {
        listener.setAccion(arrastraTrazo);
        setModoArrastrar(true);
        drawHighlightSquares();
    }

    /** 
     * Pone el modo de dibujo de trazos.
     *
     */
    public void modoPintar() {
        listener.setAccion(pintaTrazo);
        setModoArrastrar(false);
        removeHighlightSquares();
        rehacerFormas();
    }

    /**
     * Crea una intancia de esta clase, inicializando todo.
     *
     */
    public JPanelPaint() {
        pintaTrazo = new PintaTrazo(trazos, this);
        listener = new ListenerArrastre(pintaTrazo);
        arrastraTrazo = new ArrastraTrazo(trazos, this);
        addMouseMotionListener(listener);
        addMouseListener(listener);
        this.setPreferredSize(new java.awt.Dimension(50, 50));
    }

    /**
     * Si el modo es arrastre, borra el canvas entero. Si no, no lo hace.
     * Luego llama a paint(g)
     */
    @Override
    public void update(Graphics g) {
        if (isModoArrastrar()) {
            super.update(g);
        }
        paint(g);
    }

    /**
     * Dibuja los trazos en este componente
     */
    @Override
    public void paint(Graphics g) {
        drawHighlightSquares();
        rehacerFormas();
    }

    /**
     * Dibuja un trazo en este componente.
     * @param trazo Trazo a dibujar.
     * @param g Graphics para dibujo.
     */
    private void dibujaTrazo(Trazo trazo, Graphics g) {
        g.setColor(trazo.getColor());
        Point2D p0 = trazo.getPunto(0);
        for (int i = 0; i < trazo.getNumeroPuntos() - 1; i++) {
            Point2D p1 = trazo.getPunto(i + 1);
            g.drawLine((int) p0.getX(), (int) p0.getY(), (int) p1.getX(),
                    (int) p1.getY());
            p0 = p1;
        }
    }

    public void drawHighlightSquares() {
        Graphics2D g2 = (Graphics2D) this.getGraphics();
        for (int i = 0; i < trazos.size(); i++) {
            Trazo trazo = trazos.get(i);
            for (int j = 0; j < trazo.getNumeroPuntos(); j++) {
                Point2D p = trazo.getPunto(j);
                double x = p.getX() - SIZE / 2;
                double y = p.getY() - SIZE / 2;
                g2.setColor(trazo.getColor());
                g2.fill(new Rectangle2D.Double(x, y, SIZE, SIZE));
            }
        }
    }

    public void removeHighlightSquares() {
        Graphics2D g2 = (Graphics2D) this.getGraphics();
        for (int i = 0; i < trazos.size(); i++) {
            Trazo trazo = trazos.get(i);
            for (int j = 0; j < trazo.getNumeroPuntos(); j++) {
                Point2D p = trazo.getPunto(j);
                double x = p.getX() - SIZE / 2;
                double y = p.getY() - SIZE / 2;
                //g2.setXORMode(this.getBackground());
                g2.setColor(this.getBackground());
                g2.fill(new Rectangle2D.Double(x, y, SIZE, SIZE));
            }
        }
    }

    public void rehacerFormas() {
        Graphics2D g2 = (Graphics2D) this.getGraphics();
        for (int i = 0; i < trazos.size(); i++) {
            Trazo trazo = trazos.get(i);
            int n = trazo.getNumeroPuntos();
            Point2D[] points = new Point2D[n];
            for (int j = 0; j < n; j++) {
                points[j] = trazo.getPunto(j);
            }
            Formas f = trazo.getForma();
            g2.setColor(trazo.getColor());
            g2.draw(f.makeShape(points));
        }
    }

    public void dibujarForma(){
        if (!isModoArrastrar()) {
            pintaTrazo.setShapeMaker();
        }

    }
    /**
     * Cambia el color de dibujo del trazo.
     * @param colorActual
     */
    public void setColorActual(Color colorActual) {
        pintaTrazo.setColorActual(colorActual);
    }

    public void setOpcionDibujo(int opcionDibujo) {
        pintaTrazo.setOpcionDibujo(opcionDibujo);
    }

    /**
     * @return the modoArrastrar
     */
    public boolean isModoArrastrar() {
        return modoArrastrar;
    }

    /**
     * @param modoArrastrar the modoArrastrar to set
     */
    public void setModoArrastrar(boolean modoArrastrar) {
        this.modoArrastrar = modoArrastrar;
    }
}
