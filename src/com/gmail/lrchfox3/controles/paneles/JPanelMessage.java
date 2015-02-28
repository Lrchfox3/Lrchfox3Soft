/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.lrchfox3.controles.paneles;

import com.gmail.lrchfox3.utilitarios.Propiedades;
import java.net.URL;

/**
 *
 * @author lchinchilla
 */
public class JPanelMessage extends JPanelConTitulo {

    private Propiedades p = new Propiedades();
    public final static int ICON_NONE = -1;
    public final static int ICON_ERROR = 0;
    public final static int ICON_INFO = 1;    
    public final static int ICON_WARNING = 2;
    public final static int ICON_QUESTION = 3;
    public final static int ICON_DEFAULT = 4;

    public JPanelMessage() {
        super();
        inicializar();
    }

    private void inicializar() {
        lblIcon = new com.gmail.lrchfox3.controles.textos.JEtiquetaBase();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDetail = new com.gmail.lrchfox3.controles.textos.JComentarioBase();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMessage = new com.gmail.lrchfox3.controles.textos.JComentarioBase();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        setIcon(ICON_DEFAULT);
        add(lblIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 23, -1, -1));

        txtMessage.setEditable(false);
        txtMessage.setColumns(20);
        txtMessage.setRows(5);
        txtMessage.setOpaque(false);        
        jScrollPane1.setViewportView(txtMessage);

        txtDetail.setEditable(false);
        txtDetail.setColumns(20);
        txtDetail.setForeground(new java.awt.Color(255, 0, 0));
        txtDetail.setRows(5);        
        setTxtDetail("");
        txtDetail.setOpaque(false);
        jScrollPane2.setViewportView(txtDetail);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 380, 40));

        setMostrarDetalle(true);

        this.setSize(getPreferredSize());
        this.setAutoscrolls(true);

    }
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.gmail.lrchfox3.controles.textos.JEtiquetaBase lblIcon;
    private com.gmail.lrchfox3.controles.textos.JComentarioBase txtDetail;
    private com.gmail.lrchfox3.controles.textos.JComentarioBase txtMessage;

    public void setIcon(URL _image) {
        lblIcon.setIcon(new javax.swing.ImageIcon(_image));
    }

    public void setIcon(int _type) {
        switch (_type) {
            case ICON_INFO:
                lblIcon.setIcon(new javax.swing.ImageIcon(p.getImgInfo()));
                add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 340, 40));
                break;
            case ICON_ERROR:
                lblIcon.setIcon(new javax.swing.ImageIcon(p.getImgError()));
                add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 340, 40));
                break;
            case ICON_WARNING:
                lblIcon.setIcon(new javax.swing.ImageIcon(p.getImgWarning()));
                add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 340, 40));
                break;
            case ICON_QUESTION:
                lblIcon.setIcon(new javax.swing.ImageIcon(p.getImgQuestion()));
                add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 340, 40));
                break;
            case ICON_NONE:
                add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 380, 40));
                lblIcon.setIcon(null);
                break;
            case ICON_DEFAULT:
            default:
                add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 340, 40));
                lblIcon.setIcon(new javax.swing.ImageIcon(p.getImgNota()));
                break;

        }
    }

    public void setMostrarDetalle(boolean _mostrar) {
        jScrollPane2.setVisible(_mostrar);
        if (_mostrar) {
            this.setPreferredSize(new java.awt.Dimension(400, 120));
        } else {
            this.setPreferredSize(new java.awt.Dimension(400, 70));
        }
    }

    public void setTxtMessage(String _txtMessage) {
        this.txtMessage.setText(_txtMessage);
    }

    public void setTxtDetail(String _txtDetail) {
        this.txtDetail.setText(Propiedades.config.getString("Detail") + " " + _txtDetail);
    }
}
