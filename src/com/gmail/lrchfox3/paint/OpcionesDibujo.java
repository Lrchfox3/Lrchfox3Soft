package com.gmail.lrchfox3.paint;

/**
 * @author Luis R. Chinchilla H.
 * OpcionesDibujo.java
 * Created on 03-13-2009, 12:50:03 PM
 */

// <editor-fold defaultstate="collapsed" desc=" Librerias ">
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
//import lrch.Paint.Formas;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import javax.imageio.ImageIO;

// </editor-fold>
public class OpcionesDibujo {

// <editor-fold defaultstate="collapsed" desc=" Declaración de Variables ">
    /** Lista de trazos */
    private LinkedList<Trazo> trazos;
    private Component lienzo;
    private Color mainColor = new Color(0, 0, 0);
    private Color xorColor = new Color(255, 255, 255);
    private int opcionDibujo = 0;
    /* Current mouse coordinates */
    private int mousex = 0;
    private int mousey = 0;
    /* Previous mouse coordinates */
    private int prevx = 0;
    private int prevy = 0;
    /* Main Mouse X and Y coordiante variables */
    private int Orx = 0;
    private int Ory = 0;
    private int drawX = 0;
    private int drawY = 0;
    private int OrWidth = 0;
    private int OrHeight = 0;
    private final int ArcWidth = 20;
    private final int ArcHeight = 20;
    /* Initial state falgs for operation */
    private boolean initialLine = true;
    private boolean initialPen = true;
    private boolean initialRect = true;
    private boolean initialOval = true;
    /* Indica las opciones de dibujo existentes */
    public static final int ARC_WIDTH = 20;
    public static final int ARC_HEIGHT = 20;
    public static final int MANO_ALZADA = 0;
    public static final int LINEA = 1;
    public static final int RECTANGULO = 2;
    public static final int OVALO = 3;
    public static final int MANO_ALZADA_CONTINUA = 4;
    public static final int CURVA = 5;
    public static final int BORRADOR = 6;
    public static final int POLIGONO = 7;
    public static final int RECTANGULO_REDONDO = 8;
    public static final int CRUVAR = 9;
    public static final int ARCO = 10;
    public static final int TRIANGULO = 11;
    private static int SIZE = 5;
    private Formas shapeMaker;
    private Point2D[] points;
    private static Random generator = new Random();
    private static Hashtable formasHechas = new Hashtable();
// </editor-fold>

    public OpcionesDibujo() {
        cargarFormasHechas();
    }

    public OpcionesDibujo(LinkedList<Trazo> trazos, Component lienzo) {
        this.trazos = trazos;
        this.lienzo = lienzo;
        cargarFormasHechas();
    }

    /*
    Method will emulate a pen style graphic.
    by drawing a line from the previous mouse corrdinates
    to the current mouse coordinates.

    Note: In initial attempt the previous mouse coordinates
    are set to the current mouse coordinates so as
    not to begin the pen graphic from an unwanted
    arbitrary point.
     */
    public void manoAlzada(MouseEvent e) {
        Graphics g = this.lienzo.getGraphics();
        g.setColor(mainColor);

        /*
        In initial state setup default values
        for mouse coordinates
         */
        if (initialPen) {

            setGraphicalDefaults(e);
            initialPen = false;
            g.drawLine(prevx, prevy, mousex, mousey);
        }

        /*
        Make sure that the mouse has actually
        moved from its previous position.
         */
        if (mouseHasMoved(e)) {
            /*
            set mouse coordinates to
            current mouse position
             */
            mousex = e.getX();
            mousey = e.getY();

            /*
            draw a line from the previous mouse coordinates
            to the current mouse coordinates
             */
            g.drawLine(prevx, prevy, mousex, mousey);

            /*
            set the current mouse coordinates to
            previous mouse coordinates for next time
             */
            prevx = mousex;
            prevy = mousey;
        }

    }

    /*
    Method is invoked when mouse has been released
    and current operation is Pen
     */
    public void soltarManoAlzada() {
        initialPen = true;
    }

    /**
     * Method will emulate a line drawing graphic.
     * By drawing a shadow line for an origin mouse
     * coordinate pair to a moving mouse coordinate
     * pair, until the mouse has been release from
     * dragmode.
     */
    public void simularLinea(MouseEvent e) {
        Graphics g = this.lienzo.getGraphics();
        /*
        In initial state setup default values
        for mouse coordinates
         */
        if (initialLine) {
            setGraphicalDefaults(e);
            g.setXORMode(xorColor);
            g.drawLine(Orx, Ory, mousex, mousey);
            initialLine = false;
        }

        if (mouseHasMoved(e)) {
            /*
            Delete previous line shadow
            by xor-ing the graphical object
             */
            g.setXORMode(xorColor);
            g.drawLine(Orx, Ory, mousex, mousey);

            /* Update new mouse coordinates */
            mousex = e.getX();
            mousey = e.getY();

            /* Draw line shadow */
            g.drawLine(Orx, Ory, mousex, mousey);
        }

    }

    /*
    Method is invoked when mouse has been released
    and current operation is Line
     */
    public void linea() {
        //Shape shapeLinea = new Line2D.Double(Orx, Ory, mousex, mousey);
        if ((Math.abs(Orx - mousex) + Math.abs(Ory - mousey)) != 0) {
            System.out.println("Line has been released....");
            initialLine = true;
            Graphics g = this.lienzo.getGraphics();
            Graphics2D g2 = (Graphics2D) g;
            g.setColor(mainColor);
            //g.drawLine(Orx, Ory, mousex, mousey);            
            //g2.draw(shapeLinea);

            Formas shapeMaker = (Formas) new Linea();
            int n = shapeMaker.getPointCount();
            points = new Point2D[n];
            points[0] = new Point2D.Double(Orx, Ory);
            points[1] = new Point2D.Double(mousex, mousey);

            g2.draw(shapeMaker.makeShape(points));

            Trazo trazoLinea = new Trazo();
            trazoLinea.setColor(mainColor);
            trazoLinea.addPunto(points[0]);
            trazoLinea.addPunto(points[1]);
            trazoLinea.addForma(shapeMaker);
            trazos.add(trazoLinea);
            getPixelesForma(shapeMaker, points);
        }

    }


    /*
    Method will emulate a rectangle drawing graphic.
    By drawing a shadow rectangle for an origin mouse
    coordinate pair to a moving mouse coordinate
    pair, until the mouse has been release from
    dragmode.
     */
    public void simularRectangulo(MouseEvent e) {
        Graphics g = this.lienzo.getGraphics();
        g.setColor(mainColor);

        /*
        In initial state setup default values
        for mouse coordinates
         */
        if (initialRect) {
            setGraphicalDefaults(e);
            initialRect = false;
        }

        /*
        Make sure that the mouse has actually
        moved from its previous position.
         */
        if (mouseHasMoved(e)) {
            /*
            Delete previous rectangle shadow
            by xor-ing the graphical object
             */
            g.setXORMode(this.lienzo.getBackground());
            g.drawRect(drawX, drawY, OrWidth, OrHeight);

            /* Update new mouse coordinates */
            mousex = e.getX();
            mousey = e.getY();

            /* Check new mouse coordinates for negative errors */
            setActualBoundry();

            /* Draw rectangle shadow */
            g.drawRect(drawX, drawY, OrWidth, OrHeight);

        }

    }

    /*
    Method is invoked when mouse has been released
    and current operation is Rectangle
     */
    public void rectangulo() {

        initialRect = true;
        Graphics g = this.lienzo.getGraphics();
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(mainColor);

        Formas shapeMaker = (Formas) new Rectangulo();
        int n = shapeMaker.getPointCount();
        points = new Point2D[n];
        points[0] = new Point2D.Double(Orx, Ory);
        points[1] = new Point2D.Double(mousex, mousey);
        //g2.drawRect(drawX, drawY, OrWidth, OrHeight);
        g2.draw(shapeMaker.makeShape(points));

        Trazo trazoRectangulo = new Trazo();
        trazoRectangulo.setColor(mainColor);
        trazoRectangulo.addPunto(points[0]);
        trazoRectangulo.addPunto(points[1]);
        trazoRectangulo.addForma(shapeMaker);
        trazos.add(trazoRectangulo);
        getPixelesForma(shapeMaker, points);
        System.out.println("Hola");
    }


    /*
    Method will emulate a rectangle round drawing graphic.
    By drawing a shadow rectangle for an origin mouse
    coordinate pair to a moving mouse coordinate
    pair, until the mouse has been release from
    dragmode.
     */
    public void simularRectanguloRedondo(MouseEvent e) {
        Graphics g = this.lienzo.getGraphics();
        g.setColor(mainColor);

        /*
        In initial state setup default values
        for mouse coordinates
         */
        if (initialRect) {
            setGraphicalDefaults(e);
            initialRect = false;
        }

        /*
        Make sure that the mouse has actually
        moved from its previous position.
         */
        if (mouseHasMoved(e)) {
            /*
            Delete previous rectangle shadow
            by xor-ing the graphical object
             */
            g.setXORMode(this.lienzo.getBackground());
            g.drawRoundRect(drawX, drawY, OrWidth, OrHeight, ArcWidth, ArcHeight);

            /* Update new mouse coordinates */
            mousex = e.getX();
            mousey = e.getY();

            /* Check new mouse coordinates for negative errors */
            setActualBoundry();

            /* Draw rectangle shadow */
            g.drawRoundRect(drawX, drawY, OrWidth, OrHeight, ArcWidth, ArcHeight);
        }

    }

    /*
    Method is invoked when mouse has been released
    and current operation is Rectangle
     */
    public void rectanguloRedondo() {

        initialRect = true;
        Graphics g = this.lienzo.getGraphics();
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(mainColor);

        Formas shapeMaker = (Formas) new RectanguloRedondo();
        int n = shapeMaker.getPointCount();
        points = new Point2D[n];
        points[0] = new Point2D.Double(Orx, Ory);
        points[1] = new Point2D.Double(mousex, mousey);
        //g2.drawRect(drawX, drawY, OrWidth, OrHeight);
        g2.draw(shapeMaker.makeShape(points));

        Trazo trazoRectangulo = new Trazo();
        trazoRectangulo.setColor(mainColor);
        trazoRectangulo.addPunto(points[0]);
        trazoRectangulo.addPunto(points[1]);
        trazoRectangulo.addForma(shapeMaker);
        trazos.add(trazoRectangulo);
        getPixelesForma(shapeMaker, points);
    }
    /*
    Method will emulate a oval drawing graphic.
    By drawing a shadow oval for an origin mouse
    coordinate pair to a moving mouse coordinate
    pair, until the mouse has been release from
    dragmode.
     */

    public void simularOvalo(MouseEvent e) {
        Graphics g = this.lienzo.getGraphics();
        g.setColor(mainColor);

        /*
        In initial state setup default values
        for mouse coordinates
         */
        if (initialOval) {
            setGraphicalDefaults(e);
            initialOval = false;
        }

        /*
        Make sure that the mouse has actually
        moved from its previous position.
         */
        if (mouseHasMoved(e)) {
            /*
            Delete previous oval shadow
            by xor-ing the graphical object
             */
            g.setXORMode(this.lienzo.getBackground());//xorColor
            g.drawOval(drawX, drawY, OrWidth, OrHeight);

            /* Update new mouse coordinates */
            mousex = e.getX();
            mousey = e.getY();

            /* Check new mouse coordinates for negative errors */
            setActualBoundry();

            /* Draw oval shadow */
            g.drawOval(drawX, drawY, OrWidth, OrHeight);

        }

    }

    /*
    Method is invoked when mouse has been released
    and current operation is Oval
     */
    public void ovalo() {

        initialOval = true;
        Graphics g = this.lienzo.getGraphics();
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(mainColor);
        Formas shapeMaker = (Formas) new Ovalo();
        int n = shapeMaker.getPointCount();
        points = new Point2D[n];
        points[0] = new Point2D.Double(Orx, Ory);
        points[1] = new Point2D.Double(mousex, mousey);
        //g.drawOval(drawX, drawY, OrWidth, OrHeight);
        g2.draw(shapeMaker.makeShape(points));

        Trazo trazoOvalo = new Trazo();
        trazoOvalo.setColor(mainColor);
        trazoOvalo.addPunto(points[0]);
        trazoOvalo.addPunto(points[1]);
        trazoOvalo.addForma(shapeMaker);
        trazos.add(trazoOvalo);
        getPixelesForma(shapeMaker, points);
    }

    private void cargarFormasHechas() {
        shapeMaker = (Formas) new Arco();
        formasHechas.put(ARCO, shapeMaker);
        shapeMaker = (Formas) new Poligono();
        formasHechas.put(POLIGONO, shapeMaker);
        shapeMaker = (Formas) new Curva();
        formasHechas.put(CURVA, shapeMaker);
        shapeMaker = (Formas) new Triangulo();
        formasHechas.put(TRIANGULO, shapeMaker);
    }

    private Formas getFormaHecha(int opcionDibujo) {
        return (Formas) formasHechas.get(opcionDibujo);
    }

    public void setShapeMaker() {
        shapeMaker = getFormaHecha(getOpcionDibujo());
        int n = shapeMaker.getPointCount();
        points = new Point2D[n];
        for (int i = 0; i < n; i++) {
            double x = generator.nextDouble() * lienzo.getWidth();
            double y = generator.nextDouble() * lienzo.getHeight();
            points[i] = new Point2D.Double(x, y);
        }
        Graphics g = this.lienzo.getGraphics();
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(mainColor);
        g2.draw(shapeMaker.makeShape(points));

        Trazo shape = new Trazo();
        shape.setColor(mainColor);
        for (int i = 0; i < n; i++) {
            shape.addPunto(points[i]);
        }
        shape.addForma(shapeMaker);
        trazos.add(shape);
        getPixelesForma(shapeMaker, points);
    }

    public void getPixelesForma(Formas forma, Point2D[] points) {
        Rectangle2D re = forma.makeShape(points).getBounds2D();
        BufferedImage bufferedImage = new BufferedImage(new Double(this.lienzo.getWidth()).intValue() + 5, new Double(this.lienzo.getHeight()).intValue() + 5, BufferedImage.TYPE_INT_RGB);
        Graphics2D paint2 = bufferedImage.createGraphics();
        paint2.setColor(Color.GRAY);
        paint2.fillRect(0, 0, new Double(this.lienzo.getWidth()).intValue() + 5, new Double(this.lienzo.getHeight()).intValue() + 5);
        paint2.setColor(Color.BLACK);

        paint2.draw(forma.makeShape(points));

        File file = new File("c:\\newimage.jpg");
        RenderedImage rendImage = (RenderedImage) bufferedImage.getSubimage(new Double(re.getX()).intValue() - 1, new Double(re.getY()).intValue() - 1, (new Double(re.getMaxX()).intValue() - new Double(re.getMinX()).intValue()) + 4, (new Double(re.getMaxY()).intValue() - new Double(re.getMinY()).intValue()) + 4);
        try {
            ImageIO.write(rendImage, "jpg", file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /*
    Method determines weather the mouse has moved
    from its last recorded position.
    If mouse has deviated from previous position,
    the value returned will be true, otherwise
    the value that is returned will be false.
     */

    public boolean mouseHasMoved(MouseEvent e) {
        return (mousex != e.getX() || mousey != e.getY());
    }

    /*
    Method sets all the drawing varibles to the default
    state which is the current position of the mouse cursor
    Also the height and width varibles are zeroed off.
     */
    public void setGraphicalDefaults(MouseEvent e) {
        mousex = e.getX();
        mousey = e.getY();
        prevx = e.getX();
        prevy = e.getY();
        Orx = e.getX();
        Ory = e.getY();
        drawX = e.getX();
        drawY = e.getY();
        OrWidth = 0;
        OrHeight = 0;
    }

    /*
    Method is a key segment in the operations where
    there are more than 2 variables deviating to
    makeup a graphical operation.
    This method calculates the new values for the
    global varibles drawX and drawY according to
    the new positions of the mouse cursor.
    This method eleviates the possibility that
    a negative width or height can occur.
     */
    public void setActualBoundry() {

        /*
        If the any of the current mouse coordinates
        are smaller than the origin coordinates, meaning
        if drag occured in a negative manner, where either
        the x-shift occured from right and/or y-shift occured
        from bottom to top.
         */
        if (mousex < Orx || mousey < Ory) {

            /*
            if the current mouse x coordinate is smaller
            than the origin x coordinate,
            equate the drawX to be the difference between
            the current width and the origin x coordinate.
             */
            if (mousex < Orx) {
                OrWidth = Orx - mousex;
                drawX = Orx - OrWidth;
            } else {
                drawX = Orx;
                OrWidth = mousex - Orx;

            }

            /*
            if the current mouse y coordinate is smaller
            than the origin y coordinate,
            equate the drawY to be the difference between
            the current height and the origin y coordinate.
             */
            if (mousey < Ory) {
                OrHeight = Ory - mousey;
                drawY = Ory - OrHeight;
            } else {
                drawY = Ory;
                OrHeight = mousey - Ory;
            }

        } /*
        Else if drag was done in a positive manner meaning
        x-shift occured from left to right and or y-shift occured
        from top to bottom
         */ else {
            drawX = Orx;
            drawY = Ory;
            OrWidth = mousex - Orx;
            OrHeight = mousey - Ory;
        }

    }

    /**
     * @return the opcionDibujo
     */
    public int getOpcionDibujo() {
        return opcionDibujo;
    }

    /**
     * @param opcionDibujo the opcionDibujo to set
     */
    public void setOpcionDibujo(int opcionDibujo) {
        this.opcionDibujo = opcionDibujo;
    }
}
