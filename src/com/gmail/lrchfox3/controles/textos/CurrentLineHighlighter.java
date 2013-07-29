/**
 * CurrentLineHighlighter.java
 *
 * Created on December 4, 2006, 12:41 PM
 *
 * @author C�digo Libre
 * @Objetivo: permite que en las cajas de texto se remarque la l�nea que
 * actualmente tiene el foco.
 */
package com.gmail.lrchfox3.controles.textos;

// <editor-fold defaultstate="collapsed" desc=" Librerias ">
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;
import javax.swing.event.*;
import java.util.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.LayeredHighlighter.LayerPainter;
import javax.swing.text.LayeredHighlighter.*;
import javax.swing.text.*;
// </editor-fold>

public class CurrentLineHighlighter {

    private static final String LINE_HIGHLIGHT = "linehilight"; //NOI18N - used as clientproperty 
    private static final String PREVIOUS_CARET = "previousCaret"; //NOI18N - used as clientproperty 
    private static Color col = new Color(225, 235, 245); //new Color(255, 255, 204); //Color used for highlighting the line  new Color(184, 208, 226); 

    // to be used as static utility 
    private CurrentLineHighlighter() {
    }

    // Installs CurrentLineHilighter for the given JTextComponent 
    public static void install(JTextComponent c) {
        try {
            Object obj = c.getHighlighter().addHighlight(0, 0, painter);
            c.putClientProperty(LINE_HIGHLIGHT, obj);
            c.putClientProperty(PREVIOUS_CARET, new Integer(c.getCaretPosition()));
            c.addCaretListener(caretListener);
            c.addMouseListener(mouseListener);
            c.addMouseMotionListener(mouseListener);
        } catch (BadLocationException ignore) {
        }
    }

    // Uninstalls CurrentLineHighligher for the given JTextComponent 
    public static void uninstall(JTextComponent c) {


        c.putClientProperty(LINE_HIGHLIGHT, null);
        c.putClientProperty(PREVIOUS_CARET, null);
        c.removeCaretListener(caretListener);
        c.removeMouseListener(mouseListener);
        c.removeMouseMotionListener(mouseListener);        

    }
    private static CaretListener caretListener = new CaretListener() {
        public void caretUpdate(CaretEvent e) {
            JTextComponent c = (JTextComponent) e.getSource();
            currentLineChanged(c);
        }
    };
    private static MouseInputAdapter mouseListener = new MouseInputAdapter() {
        public void mousePressed(MouseEvent e) {
            JTextComponent c = (JTextComponent) e.getSource();
            currentLineChanged(c);
        }

        public void mouseDragged(MouseEvent e) {
            JTextComponent c = (JTextComponent) e.getSource();
            currentLineChanged(c);
        }
    };

    /**
     * Fetches the previous caret location, stores the current caret location,
     * If the caret is on another line, repaint the previous line and the
     * current line
     *
     * @param c the text component
     */
    private static void currentLineChanged(JTextComponent c) {
        try {
            int previousCaret = ((Integer) c.getClientProperty(PREVIOUS_CARET)).intValue();
            Rectangle prev = c.modelToView(previousCaret);
            Rectangle r = c.modelToView(c.getCaretPosition());
            c.putClientProperty(PREVIOUS_CARET, new Integer(c.getCaretPosition()));

            if (prev == null) {
                c.repaint(0, 0, 0, 0);
            } else if (prev.y != r.y) {
                c.repaint(0, prev.y, c.getWidth(), r.height);
                c.repaint(0, r.y, c.getWidth(), r.height);
            }
        } catch (BadLocationException ignore) {
        }
    }
    private static Highlighter.HighlightPainter painter = new Highlighter.HighlightPainter() {
        public void paint(Graphics g, int p0, int p1, Shape bounds, JTextComponent c) {
            try {
                Rectangle r = c.modelToView(c.getCaretPosition());
                g.setColor(col);
                g.fillRect(0, r.y, c.getWidth(), r.height);
            } catch (BadLocationException ignore) {
            }
        }
    };   
}
