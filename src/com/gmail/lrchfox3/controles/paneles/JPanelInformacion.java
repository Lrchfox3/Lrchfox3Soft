/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.lrchfox3.controles.paneles;

/**
 *
 * @author chinchillal
 */
import com.gmail.lrchfox3.basedatos.Campo;
import com.gmail.lrchfox3.controles.textos.JEtiquetaBase;
import com.gmail.lrchfox3.controles.textos.JTextoBase;
import com.gmail.lrchfox3.model.dto.Catalogo;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Panel Personalizado.
 *
 * Este panel contiene los siguientes controles
 * Etiqueta y Texto para la nombre
 * Etiqueta y texto para el apellido
 * etiqueta y Texto para la edad
 *
 */
public class JPanelInformacion extends javax.swing.JPanel {

    //Declaración de Variables
    private boolean editable = false;
    private int posXe = 11, posYe = 12;
    private int posXt = 11, posYt = 32;
    private int anchoTexto = 88;
    private int diferencia = 0; //Indica la diferencia entre el ancho default y el ancho indicado por el usuario
    private Catalogo catalogo;

    public JPanelInformacion() {
    }

    public JPanelInformacion(Catalogo cat) {
        this.catalogo = cat;
        inicializar(cat);
    }

    public JPanelInformacion(Catalogo cat, int anchoTexto) {
        this.catalogo = cat;
        setAnchoTexto(anchoTexto);
        inicializar(cat);
    }

    private void inicializar(Catalogo cat) {
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        int row = 1;
        try {
            for (Campo c : cat.getCampos()) {
                if (!c.esLlave()) {
                    JEtiquetaBase etiqueta = new JEtiquetaBase();
                    etiqueta.setText(c.getEtiqueta());
                    add(etiqueta, new org.netbeans.lib.awtextra.AbsoluteConstraints(posXe, posYe, -1, -1));
                    JTextoBase texto = new JTextoBase();
                    texto.setName(c.getNombre());
                    texto.setText(c.getValue()!=null?c.getValue().toString():"");
                    add(texto, new org.netbeans.lib.awtextra.AbsoluteConstraints(posXt, posYt, anchoTexto, -1));

                    if (row < 2) {
                        // Mueve los controles a la segunda columna
                        posXt += (94 + diferencia);
                        posXe += (94 + diferencia);
                    } else {
                        //Coloca los controles una linea abajo
                        row = 0;
                        posXe = 11;
                        posYe += 46;
                        posXt = 11;
                        posYt += 46;
                    }
                    row++;
                }                
            }
        } catch (Exception ex) {
            System.out.println("Error, " + ex.getMessage());
        }
        setEditable();
        JTextoBase texto = (JTextoBase) this.getComponent(1);
        texto.requestFocus();
    }

    private void setAnchoTexto(int ancho) {
        diferencia = ancho - anchoTexto;
        anchoTexto = ancho;
    }

    /**
     * retorna si las cajas de texto son editables
     */
    public boolean isEditable() {
        return editable;
    }

    /**
     * setea las cajas de texto para ser editables o no
     */
    public void setEditable(boolean editable) {
        this.editable = editable;
        setEditable();
    }

    private void setEditable() {

        for (Component comp : this.getComponents()) {
            int objTexto = comp.getClass().toString().indexOf(JTextoBase.class.getName());
            if (objTexto >= 0) {
                JTextoBase texto = (JTextoBase) comp;
                texto.setEditable(isEditable());
            }
        }
    }

    /**
     * limpia las cajas de texto
     */
    public void limpiarCajasTexto() {
        for (Component comp : this.getComponents()) {
            int objTexto = comp.getClass().toString().indexOf(JTextoBase.class.getName());
            if (objTexto >= 0) {
                JTextoBase texto = (JTextoBase) comp;
                texto.setText("");
            }
        }
    }

    /**
     * retorna los controles de texto, con la información digitada.
     * Ademas setea los valores de las variables que forman el catalogo
     */
    public List<JTextoBase> getInformacion() throws Exception {
        List<JTextoBase> lst = new ArrayList<JTextoBase>();
        for (Component comp : this.getComponents()) {
            int objTexto = comp.getClass().toString().indexOf(JTextoBase.class.getName());
            if (objTexto >= 0) {
                JTextoBase texto = (JTextoBase) comp;

                for ( Campo campo : catalogo.getCampos()) {
                    if (campo.getNombre().equals(texto.getName())) {
                        campo.setValue(texto.getText());
                        break;
                    }
                }

                lst.add(texto);
            }
        }
        return lst;
    }
}
