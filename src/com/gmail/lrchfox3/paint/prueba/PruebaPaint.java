/**
 * Javier Abell�n, 24 Mayo 2006
 * 
 * Programa de prueba con main y applet de jPanelPaint
 */
package com.gmail.lrchfox3.paint.prueba;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JApplet;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;

import com.gmail.lrchfox3.paint.JPanelPaint;

/**
 * Clase para prueba de jPanelPaint
 * 
 * @author Chuidiang
 *
 */
public class PruebaPaint extends JApplet {

    /**
     *  serial uid
     */
    private static final long serialVersionUID = 3690194364970250292L;

    /**
     * Inatancia esta clase y llama al m�todo que la lanza en un JFrame.
     * @param args
     */
    public static void main(String[] args) {
        new PruebaPaint().lanzaVentanaSeparada();

    }

    /**
     * Crea un JFrame, le mete una barra de herramientas y un jPanelPaint y lo
     * visualiza todo
     *
     */
    public void lanzaVentanaSeparada() {
        JFrame v = new JFrame();
        construyeTodo(v.getContentPane());
        v.setSize(500, 500);
        v.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        v.setVisible(true);
        Image im = Toolkit.getDefaultToolkit().getImage("http://www.sld.cu/galerias/imagen/sitios/pdguanabo/icono_powerpoint.gif");
        v.setIconImage(im);
    }

    /**
     * M�todo que inicializa el applet. Mete dentro del applet la barra de
     * herramientas y el jPanelPaint
     */
    public void init() {
        construyeTodo(this);
    }

    /**
     * Inicilaiza las variables de esta clase.
     */
    private void inicializaVariables() {
        cadenasColor = new String[]{"Rojo", "Verde", "Azul", "Blanco",
                    "Negro"};
        //cadenasOpciones = new String[]{"Mano alzada", "Línea"};

        colores = new Color[]{Color.red, Color.green, Color.blue,
                    Color.white, Color.black};
    }

    /**
     * Inicializa las variables, construye la barra de herramientas y el 
     * jPanelPaint y lo mete todo en el contenedor que se le pasa.
     * @param contenedor
     */
    private void construyeTodo(Container contenedor) {
        inicializaVariables();
        contenedor.setLayout(new BorderLayout());
        panelPaint = new JPanelPaint();
        panelPaint.setBackground(Color.GRAY);
        contenedor.add(panelPaint, BorderLayout.CENTER);
        Component panelHerramientas = damePanelHerramientas();
        contenedor.add(panelHerramientas, BorderLayout.NORTH);
    }

    /**
     * Construye la barra de herramientas y la devuelve.
     * @return La barra de herramientas
     */
    private Component damePanelHerramientas() {
        JPanel panelHerramientas = new JPanel(new FlowLayout());
        ButtonGroup grupoColor = new ButtonGroup();
        JRadioButton botonesColor[] = new JRadioButton[cadenasColor.length];
        for (int i = 0; i < cadenasColor.length; i++) {
            botonesColor[i] = new JRadioButton(cadenasColor[i]);
            botonesColor[i].setActionCommand(cadenasColor[i]);
            grupoColor.add(botonesColor[i]);
            panelHerramientas.add(botonesColor[i]);
            botonesColor[i].addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    for (int i = 0; i < cadenasColor.length; i++) {
                        if (cadenasColor[i].equals(e.getActionCommand())) {
                            panelPaint.setColorActual(colores[i]);
                            break;
                        }
                    }
                }
            });
        }

        ButtonGroup grupoOpcion = new ButtonGroup();
//        JRadioButton botonesOpcion[] = new JRadioButton[cadenasOpciones.length];
//        for (int i = 0; i < cadenasOpciones.length; i++) {
//            botonesOpcion[i] = new JRadioButton(cadenasOpciones[i]);
//            botonesOpcion[i].setActionCommand(String.valueOf(i));
//
//            panelHerramientas.add(botonesOpcion[i]);
//            botonesOpcion[i].addActionListener(new ActionListener() {
//
//                public void actionPerformed(ActionEvent e) {
//                    if (Integer.valueOf(e.getActionCommand()).intValue() == lrch.Paint.OpcionesDibujo.MANO_ALZADA) {
//                        panelPaint.setOpcionDibujo(lrch.Paint.OpcionesDibujo.MANO_ALZADA);
//                    } else if (Integer.valueOf(e.getActionCommand()).intValue() == lrch.Paint.OpcionesDibujo.LINEA) {
//                        panelPaint.setOpcionDibujo(lrch.Paint.OpcionesDibujo.LINEA);
//                    }
//                }
//            });
//        }


        optManoAlzada.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                panelPaint.setOpcionDibujo(com.gmail.lrchfox3.paint.OpcionesDibujo.MANO_ALZADA);
            }
        });
        grupoOpcion.add(optManoAlzada);
        panelHerramientas.add(optManoAlzada);
        optLinea.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                panelPaint.setOpcionDibujo(com.gmail.lrchfox3.paint.OpcionesDibujo.LINEA);
            }
        });
        grupoOpcion.add(optLinea);
        panelHerramientas.add(optLinea);

        optRactangulo.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                panelPaint.setOpcionDibujo(com.gmail.lrchfox3.paint.OpcionesDibujo.RECTANGULO);
            }
        });
        grupoOpcion.add(optRactangulo);
        panelHerramientas.add(optRactangulo);

        optOvalo.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                panelPaint.setOpcionDibujo(com.gmail.lrchfox3.paint.OpcionesDibujo.OVALO);
            }
        });
        grupoOpcion.add(optOvalo);
        panelHerramientas.add(optOvalo);

        optManoAlzadaContinua.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                panelPaint.setOpcionDibujo(com.gmail.lrchfox3.paint.OpcionesDibujo.MANO_ALZADA_CONTINUA);
            }
        });
        grupoOpcion.add(optManoAlzadaContinua);
        panelHerramientas.add(optManoAlzadaContinua);
        checkArrastrar = new JCheckBox("Arrastrar");
        checkArrastrar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (checkArrastrar.isSelected()) {
                    panelPaint.modoArrastrar();
                } else {
                    panelPaint.modoPintar();
                }
            }
        });
        panelHerramientas.add(checkArrastrar);
        panelHerramientas.add(label);
        return panelHerramientas;
    }
    /**
     * Texto para los colores
     */
    private String[] cadenasColor = null;
    /**
     * Colores
     */
    private Color[] colores = null;
    /**
     * Panel de dibujo estilo paint
     */
    private JPanelPaint panelPaint;
    /**
     * Check para decidir si arrastrar o pintar los trazos
     */
    private JCheckBox checkArrastrar;
    private JRadioButton optManoAlzada = new JRadioButton("Mano Alzada");
    private JRadioButton optLinea = new JRadioButton("Línea");
    private JRadioButton optRactangulo = new JRadioButton("Rectángulo");
    private JRadioButton optOvalo = new JRadioButton("Óvalo");
    private JRadioButton optManoAlzadaContinua = new JRadioButton("Mano Alzada Continua");
    private JLabel label = new JLabel("Coor");
}
