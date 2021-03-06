/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.lrchfox3.controles.textos;

import com.gmail.lrchfox3.utilitarios.Propiedades;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.border.AbstractBorder;

/**
 *
 * @author lchinchilla
 */
public class RoundedCornerBorder extends AbstractBorder {

    private boolean foco = false;
    private boolean roundedCorner = false;
    private boolean mandatory = false;

    private Propiedades propiedades = new Propiedades();

    public RoundedCornerBorder(boolean _foco, boolean _roundedCorner) {
        foco = _foco;
        roundedCorner = _roundedCorner;
    }

    public RoundedCornerBorder(boolean _foco, boolean _roundedCorner, boolean _mandatory) {
        foco = _foco;
        roundedCorner = _roundedCorner;
        mandatory = _mandatory;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int r = 0;
        if (roundedCorner) {
            r = height - 1;
        }
        RoundRectangle2D round = new RoundRectangle2D.Float(x, y, width - 1, height - 1, r, r);
        Container parent = c.getParent();
        if (parent != null) {
            g2.setColor(parent.getBackground());
            Area corner = new Area(new Rectangle2D.Float(x, y, width, height));
            corner.subtract(new Area(round));
            g2.fill(corner);
        }

        if (mandatory) {
            g2.setColor(propiedades.getColorEtiquetaObligatorios());
        } else {
            if (foco) {
                g2.setColor(Color.ORANGE);
            } else {
                g2.setColor(Color.GRAY);
            }
        }
        g2.draw(round);
        g2.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(4, 8, 4, 8);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = insets.right = 8;
        insets.top = insets.bottom = 4;
        return insets;
    }

    public boolean isFoco() {
        return foco;
    }

    public void setFoco(boolean foco) {
        this.foco = foco;
    }

    public boolean isRoundedCorner() {
        return roundedCorner;
    }

    public void setRoundedCorner(boolean roundedCorner) {
        this.roundedCorner = roundedCorner;
    }
}
