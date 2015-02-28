package com.gmail.lrchfox3.paint.prueba;

/**
 * @author Luis R. Chinchilla H.
 * Paint.java
 * Created on 03-16-2009, 09:39:36 AM
 */

// <editor-fold defaultstate="collapsed" desc=" Librerias ">
import javax.swing.JButton;
import javax.swing.JFrame;
import com.gmail.lrchfox3.paint.OpcionesDibujo;
// </editor-fold>
public class Paint extends javax.swing.JFrame {

    /** Creates new form Paint */
    public Paint() {
        initComponents();
        Inicializar();        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblXY = new com.gmail.lrchfox3.controles.textos.JEtiquetaBase();
        pnlOpcionesDibujo = new com.gmail.lrchfox3.controles.paneles.JPanelConTitulo();
        BtnLinea = new com.gmail.lrchfox3.controles.botones.paint.JBotonLinea();
        BtnCurva = new com.gmail.lrchfox3.controles.botones.paint.JBotonCurva();
        BtnOvalo = new com.gmail.lrchfox3.controles.botones.paint.JBotonOvalo();
        BtnRectangulo = new com.gmail.lrchfox3.controles.botones.paint.JBotonRectangulo();
        BtnRectanguloRedondo = new com.gmail.lrchfox3.controles.botones.paint.JBotonRectanguloRedondo();
        BtnPoligono = new com.gmail.lrchfox3.controles.botones.paint.JBotonPoligono();
        BtnBorrador = new com.gmail.lrchfox3.controles.botones.paint.JBotonBorrador();
        chkArrastrarFiguras = new com.gmail.lrchfox3.controles.JCheckBoxBase();
        BtnLapiz = new com.gmail.lrchfox3.controles.botones.paint.JBotonLapiz();
        lblOpcionDibujo = new com.gmail.lrchfox3.controles.textos.JEtiquetaBase();
        BtnTriangulo = new com.gmail.lrchfox3.controles.botones.paint.JBotonTriangulo();
        jButton1 = new javax.swing.JButton();
        btnArco = new com.gmail.lrchfox3.controles.botones.paint.JBotonArco();
        pnlAreaDibujo = new com.gmail.lrchfox3.controles.paneles.JPanelConTitulo();
        pntAreaDibujo = new com.gmail.lrchfox3.paint.JPanelPaint();
        jBotonAceptar1 = new com.gmail.lrchfox3.controles.botones.JBotonAceptar();
        JBotonCancelar1 = new com.gmail.lrchfox3.controles.botones.JBotonCancelar();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblXY.setForeground(java.awt.SystemColor.inactiveCaptionText);
        lblXY.setText("X=0 Y=0");
        getContentPane().add(lblXY, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, -1, -1));

        pnlOpcionesDibujo.setTitulo("Opciones de Dibujo");

        BtnLinea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLineaActionPerformed(evt);
            }
        });

        BtnCurva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCurvaActionPerformed(evt);
            }
        });

        BtnOvalo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnOvaloActionPerformed(evt);
            }
        });

        BtnRectangulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRectanguloActionPerformed(evt);
            }
        });

        BtnRectanguloRedondo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRectanguloRedondoActionPerformed(evt);
            }
        });

        BtnPoligono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPoligonoActionPerformed(evt);
            }
        });

        BtnBorrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBorradorActionPerformed(evt);
            }
        });

        chkArrastrarFiguras.setBackground(javax.swing.UIManager.getDefaults().getColor("CheckBox.background"));
        chkArrastrarFiguras.setToolTipText("Si Arrastrar Figuras esta marcado, las Opciondes de Dibujo se encontraran en estado deshabilitado");
        chkArrastrarFiguras.setText("Arrastrar  Figuras");
        chkArrastrarFiguras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkArrastrarFigurasActionPerformed(evt);
            }
        });

        BtnLapiz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLapizActionPerformed(evt);
            }
        });

        lblOpcionDibujo.setText("lapíz");
        lblOpcionDibujo.setFont(new java.awt.Font("Segoe UI", 0, 10));

        BtnTriangulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTrianguloActionPerformed(evt);
            }
        });

        jButton1.setText("Combinar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnArco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArcoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlOpcionesDibujoLayout = new javax.swing.GroupLayout(pnlOpcionesDibujo);
        pnlOpcionesDibujo.setLayout(pnlOpcionesDibujoLayout);
        pnlOpcionesDibujoLayout.setHorizontalGroup(
            pnlOpcionesDibujoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOpcionesDibujoLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(pnlOpcionesDibujoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlOpcionesDibujoLayout.createSequentialGroup()
                        .addComponent(BtnBorrador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(lblOpcionDibujo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlOpcionesDibujoLayout.createSequentialGroup()
                        .addComponent(BtnLapiz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnLinea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnOvalo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlOpcionesDibujoLayout.createSequentialGroup()
                        .addGroup(pnlOpcionesDibujoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlOpcionesDibujoLayout.createSequentialGroup()
                                .addComponent(BtnRectangulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnRectanguloRedondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlOpcionesDibujoLayout.createSequentialGroup()
                                .addComponent(BtnCurva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnArco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlOpcionesDibujoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(BtnPoligono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnTriangulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(chkArrastrarFiguras, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        pnlOpcionesDibujoLayout.setVerticalGroup(
            pnlOpcionesDibujoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOpcionesDibujoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlOpcionesDibujoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlOpcionesDibujoLayout.createSequentialGroup()
                        .addGroup(pnlOpcionesDibujoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(BtnLinea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnOvalo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnLapiz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlOpcionesDibujoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(BtnRectanguloRedondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnRectangulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnPoligono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlOpcionesDibujoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlOpcionesDibujoLayout.createSequentialGroup()
                                .addGroup(pnlOpcionesDibujoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(BtnCurva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BtnTriangulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnBorrador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnArco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkArrastrarFiguras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOpcionesDibujoLayout.createSequentialGroup()
                        .addComponent(lblOpcionDibujo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70))))
        );

        getContentPane().add(pnlOpcionesDibujo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 160, 240));

        pnlAreaDibujo.setTitulo("Área deDibujo");
        pnlAreaDibujo.setLayout(new javax.swing.BoxLayout(pnlAreaDibujo, javax.swing.BoxLayout.LINE_AXIS));

        pntAreaDibujo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pntAreaDibujoMouseExited(evt);
            }
        });
        pntAreaDibujo.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                pntAreaDibujoMouseMoved(evt);
            }
        });
        pnlAreaDibujo.add(pntAreaDibujo);

        getContentPane().add(pnlAreaDibujo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 390, 240));
        getContentPane().add(jBotonAceptar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 320, -1, -1));
        getContentPane().add(JBotonCancelar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 320, -1, -1));

        jMenu1.setText("Opciones");

        jMenuItem1.setText("Abrir");
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Guardar");
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Graficas");
        jMenu1.add(jMenuItem3);
        jMenu1.add(jSeparator1);

        jMenuItem4.setText("Salir");
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Ayuda");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-604)/2, (screenSize.height-413)/2, 604, 413);
    }// </editor-fold>//GEN-END:initComponents

    private void Inicializar() {
        try {
            setOpcionDibujo(this.BtnLapiz.getOpcionDibujo(), this.BtnLapiz);
        } catch (Exception e) {
            System.out.println("Colocar Manejados de errores: " + e.getMessage());
        }
    }
    private void BtnPoligonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPoligonoActionPerformed
        try {
            setOpcionDibujo(this.BtnPoligono.getOpcionDibujo(), this.BtnPoligono);
        } catch (Exception e) {
            System.out.println("Colocar Manejados de errores: " + e.getMessage());
        }
}//GEN-LAST:event_BtnPoligonoActionPerformed

    private void BtnBorradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBorradorActionPerformed
        try {
            setOpcionDibujo(this.BtnBorrador.getOpcionDibujo(), this.BtnBorrador);
        } catch (Exception e) {
            System.out.println("Colocar Manejados de errores: " + e.getMessage());
        }
}//GEN-LAST:event_BtnBorradorActionPerformed

    private void BtnOvaloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnOvaloActionPerformed
        try {
            setOpcionDibujo(this.BtnOvalo.getOpcionDibujo(), this.BtnOvalo);
        } catch (Exception e) {
            System.out.println("Colocar Manejados de errores: " + e.getMessage());
        }
}//GEN-LAST:event_BtnOvaloActionPerformed

    private void BtnLapizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLapizActionPerformed
        try {
            setOpcionDibujo(this.BtnLapiz.getOpcionDibujo(), this.BtnLapiz);
        } catch (Exception e) {
            System.out.println("Colocar Manejados de errores: " + e.getMessage());
        }
    }//GEN-LAST:event_BtnLapizActionPerformed

    private void BtnLineaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLineaActionPerformed
        try {
            setOpcionDibujo(this.BtnLinea.getOpcionDibujo(), this.BtnLinea);
        } catch (Exception e) {
            System.out.println("Colocar Manejados de errores: " + e.getMessage());
        }
    }//GEN-LAST:event_BtnLineaActionPerformed

    private void BtnRectanguloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRectanguloActionPerformed
        try {
            setOpcionDibujo(this.BtnRectangulo.getOpcionDibujo(), this.BtnRectangulo);
        } catch (Exception e) {
            System.out.println("Colocar Manejados de errores: " + e.getMessage());
        }
    }//GEN-LAST:event_BtnRectanguloActionPerformed

    private void BtnRectanguloRedondoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRectanguloRedondoActionPerformed
        try {
            setOpcionDibujo(this.BtnRectanguloRedondo.getOpcionDibujo(), this.BtnRectanguloRedondo);
        } catch (Exception e) {
            System.out.println("Colocar Manejados de errores: " + e.getMessage());
        }
    }//GEN-LAST:event_BtnRectanguloRedondoActionPerformed

    private void BtnCurvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCurvaActionPerformed
        try {
            setOpcionDibujo(this.BtnCurva.getOpcionDibujo(), this.BtnCurva);
        } catch (Exception e) {
            System.out.println("Colocar Manejados de errores: " + e.getMessage());
        }
    }//GEN-LAST:event_BtnCurvaActionPerformed

    private void BtnTrianguloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTrianguloActionPerformed
        try {
            setOpcionDibujo(this.BtnTriangulo.getOpcionDibujo(), this.BtnTriangulo);
        } catch (Exception e) {
            System.out.println("Colocar Manejados de errores: " + e.getMessage());
        }
}//GEN-LAST:event_BtnTrianguloActionPerformed

    private void chkArrastrarFigurasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkArrastrarFigurasActionPerformed
        if (chkArrastrarFiguras.isSelected()) {
            this.pntAreaDibujo.modoArrastrar();
        } else {
            this.pntAreaDibujo.modoPintar();
        }
}//GEN-LAST:event_chkArrastrarFigurasActionPerformed

    private void pntAreaDibujoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pntAreaDibujoMouseMoved
        lblXY.setText("X=" + evt.getX() + " Y=" + evt.getY());
    }//GEN-LAST:event_pntAreaDibujoMouseMoved

    private void pntAreaDibujoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pntAreaDibujoMouseExited
        lblXY.setText("X=0 Y=0");        // TODO add your handling code here:
    }//GEN-LAST:event_pntAreaDibujoMouseExited

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFrame f = new JFrame();
        f.add(new CombinarFiguras());
        f.setSize(220, 220);
        f.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnArcoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArcoActionPerformed
        try {
            setOpcionDibujo(this.btnArco.getOpcionDibujo(), this.btnArco);
        } catch (Exception e) {
            System.out.println("Colocar Manejados de errores: " + e.getMessage());
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnArcoActionPerformed

    private void setOpcionDibujo(int index, JButton btn) throws Exception {
        switch (index) {
            case OpcionesDibujo.MANO_ALZADA:
                this.lblOpcionDibujo.setText(btn.getToolTipText());
                this.pntAreaDibujo.setOpcionDibujo(OpcionesDibujo.MANO_ALZADA);
                break;
            case OpcionesDibujo.LINEA:
                this.lblOpcionDibujo.setText(btn.getToolTipText());
                this.pntAreaDibujo.setOpcionDibujo(OpcionesDibujo.LINEA);
                break;
            case OpcionesDibujo.BORRADOR:
                this.lblOpcionDibujo.setText(btn.getToolTipText());
                this.pntAreaDibujo.setOpcionDibujo(OpcionesDibujo.BORRADOR);
                break;
            case OpcionesDibujo.ARCO:
                this.lblOpcionDibujo.setText(btn.getToolTipText());
                this.pntAreaDibujo.setOpcionDibujo(OpcionesDibujo.ARCO);
                this.pntAreaDibujo.dibujarForma();
                break;
            case OpcionesDibujo.CURVA:
                this.lblOpcionDibujo.setText(btn.getToolTipText());
                this.pntAreaDibujo.setOpcionDibujo(OpcionesDibujo.CURVA);
                this.pntAreaDibujo.dibujarForma();
                break;
            case OpcionesDibujo.MANO_ALZADA_CONTINUA:
                this.lblOpcionDibujo.setText(btn.getToolTipText());
                this.pntAreaDibujo.setOpcionDibujo(OpcionesDibujo.MANO_ALZADA_CONTINUA);
                break;
            case OpcionesDibujo.OVALO:
                this.lblOpcionDibujo.setText(btn.getToolTipText());
                this.pntAreaDibujo.setOpcionDibujo(OpcionesDibujo.OVALO);
                break;
            case OpcionesDibujo.POLIGONO:
                this.lblOpcionDibujo.setText(btn.getToolTipText());
                this.pntAreaDibujo.setOpcionDibujo(OpcionesDibujo.POLIGONO);
                this.pntAreaDibujo.dibujarForma();
                break;
            case OpcionesDibujo.RECTANGULO:
                this.lblOpcionDibujo.setText(btn.getToolTipText());
                this.pntAreaDibujo.setOpcionDibujo(OpcionesDibujo.RECTANGULO);
                break;
            case OpcionesDibujo.RECTANGULO_REDONDO:
                this.lblOpcionDibujo.setText(btn.getToolTipText());
                this.pntAreaDibujo.setOpcionDibujo(OpcionesDibujo.RECTANGULO_REDONDO);
                break;
            case OpcionesDibujo.TRIANGULO:
                this.lblOpcionDibujo.setText(btn.getToolTipText());
                this.pntAreaDibujo.setOpcionDibujo(OpcionesDibujo.TRIANGULO);
                this.pntAreaDibujo.dibujarForma();
                break;
            default:
                this.lblOpcionDibujo.setText(btn.getToolTipText());
                this.pntAreaDibujo.setOpcionDibujo(OpcionesDibujo.MANO_ALZADA);
                break;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Paint().setVisible(true);
            }
        });
    }
// <editor-fold defaultstate="collapsed" desc=" Declaración de Variables ">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.gmail.lrchfox3.controles.botones.paint.JBotonBorrador BtnBorrador;
    private com.gmail.lrchfox3.controles.botones.paint.JBotonCurva BtnCurva;
    private com.gmail.lrchfox3.controles.botones.paint.JBotonLapiz BtnLapiz;
    private com.gmail.lrchfox3.controles.botones.paint.JBotonLinea BtnLinea;
    private com.gmail.lrchfox3.controles.botones.paint.JBotonOvalo BtnOvalo;
    private com.gmail.lrchfox3.controles.botones.paint.JBotonPoligono BtnPoligono;
    private com.gmail.lrchfox3.controles.botones.paint.JBotonRectangulo BtnRectangulo;
    private com.gmail.lrchfox3.controles.botones.paint.JBotonRectanguloRedondo BtnRectanguloRedondo;
    private com.gmail.lrchfox3.controles.botones.paint.JBotonTriangulo BtnTriangulo;
    private com.gmail.lrchfox3.controles.botones.paint.JBotonArco btnArco;
    private com.gmail.lrchfox3.controles.JCheckBoxBase chkArrastrarFiguras;
    private com.gmail.lrchfox3.controles.botones.JBotonAceptar jBotonAceptar1;
    private com.gmail.lrchfox3.controles.botones.JBotonCancelar JBotonCancelar1;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JSeparator jSeparator1;
    private com.gmail.lrchfox3.controles.textos.JEtiquetaBase lblOpcionDibujo;
    private com.gmail.lrchfox3.controles.textos.JEtiquetaBase lblXY;
    private com.gmail.lrchfox3.controles.paneles.JPanelConTitulo pnlAreaDibujo;
    private com.gmail.lrchfox3.controles.paneles.JPanelConTitulo pnlOpcionesDibujo;
    private com.gmail.lrchfox3.paint.JPanelPaint pntAreaDibujo;
    // End of variables declaration//GEN-END:variables

// </editor-fold>
}
