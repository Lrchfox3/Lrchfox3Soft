package com.gmail.lrchfox3.paint;

/**
 * @author Luis R. Chinchilla H.
 * Formas.java
 * Created on 03-14-2009, 10:53:38 PM
 */

// <editor-fold defaultstate="collapsed" desc=" Librerias ">
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.geom.CubicCurve2D;
// </editor-fold>

public abstract class Formas {

// <editor-fold defaultstate="collapsed" desc=" Declaración de Variables ">

// </editor-fold>
    public Formas(int aPointCount) {
        pointCount = aPointCount;
    }

    public int getPointCount() {
        return pointCount;
    }

    public abstract Shape makeShape(Point2D[] p);

    @Override
    public String toString() {
        return getClass().getName();
    }
    private int pointCount;
}

class Linea extends Formas {

    public Linea() {
        super(2);
    }

    public Shape makeShape(Point2D[] p) {
        return new Line2D.Double(p[0], p[1]);
    }
}

class Rectangulo extends Formas {

    public Rectangulo() {
        super(2);
    }

    public Shape makeShape(Point2D[] p) {
        Rectangle2D s = new Rectangle2D.Double();
        s.setFrameFromDiagonal(p[0], p[1]);
        return s;
    }
}

class RectanguloRedondo extends Formas {

    public RectanguloRedondo() {
        super(2);
    }

    public Shape makeShape(Point2D[] p) {
        RoundRectangle2D s = new RoundRectangle2D.Double(0, 0, 0, 0, OpcionesDibujo.ARC_WIDTH, OpcionesDibujo.ARC_HEIGHT);
        s.setFrameFromDiagonal(p[0], p[1]);
        return s;
    }
}

class Ovalo extends Formas {

    public Ovalo() {
        super(2);
    }

    public Shape makeShape(Point2D[] p) {
        Ellipse2D s = new Ellipse2D.Double();
        s.setFrameFromDiagonal(p[0], p[1]);
        return s;
    }
}

class Arco extends Formas {

    public Arco() {
        super(3);
    }

    public Shape makeShape(Point2D[] p) {
        return new QuadCurve2D.Double(p[0].getX(), p[0].getY(), p[1].getX(),
                p[1].getY(), p[2].getX(), p[2].getY());
    }
}

class Curva extends Formas {
  public Curva() {
    super(4);
  }

  public Shape makeShape(Point2D[] p) {
    return new CubicCurve2D.Double(p[0].getX(), p[0].getY(), p[1].getX(),
        p[1].getY(), p[2].getX(), p[2].getY(), p[3].getX(), p[3].getY());
  }
}

class Poligono extends Formas {

    public Poligono() {
        super(6);
    }

    public Shape makeShape(Point2D[] p) {
        GeneralPath s = new GeneralPath();
        s.moveTo((float) p[0].getX(), (float) p[0].getY());
        for (int i = 1; i < p.length; i++) {
            s.lineTo((float) p[i].getX(), (float) p[i].getY());
        }
        s.closePath();
        return s;
    }
}

class Triangulo extends Formas {

    public Triangulo() {
        super(3);
    }

    public Shape makeShape(Point2D[] p) {
        GeneralPath s = new GeneralPath();
        s.moveTo((float) p[0].getX(), (float) p[0].getY());
        for (int i = 1; i < p.length; i++) {
            s.lineTo((float) p[i].getX(), (float) p[i].getY());
        }
        s.closePath();
        return s;
    }
}
