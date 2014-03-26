/*
 * jTextoBase.java
 *
 * Created on January 14, 2007, 7:01 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */
package com.gmail.lrchfox3.controles.textos;

import com.gmail.lrchfox3.basedatos.Base;
import com.gmail.lrchfox3.utilitarios.Propiedades;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis R. Chinchilla H.
 */
public class JTextoBase extends javax.swing.JTextField {

    private Propiedades propiedades = new Propiedades();
    private Base bean = new Base();
    private int index = -1;

    /**
     * Creates a new instance of jTextoBase
     */
    public JTextoBase() {
        inicializar();

    }

    private void inicializar() {
        setFont(propiedades.getFontTextos());
        popupMenu = new JPopupTextoBase();
        this.setComponentPopupMenu(popupMenu);

        RoundedCornerBorder r = new RoundedCornerBorder(false, roundedCornerBorder);
        setBorder(r);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                validar(evt);
            }

        });

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (getText().length() >= getMaxCaracteres() + 1) {
                    getToolkit().beep();
                    requestFocus();
                    errorMaxCaracteres(getText());
                }
                RoundedCornerBorder r = new RoundedCornerBorder(false, roundedCornerBorder);
                setBorder(r);
            }

            public void focusGained(java.awt.event.FocusEvent evt) {
                RoundedCornerBorder r = new RoundedCornerBorder(true, roundedCornerBorder);
                setBorder(r);    
                setMaxCaracteres();
            }
        });

    }

    public void setBindingBean(Base bean) {
        this.bean = bean;

    }

    public void setIndexBindingBean(int index) {
        this.index = index;        
    }

    @Override
    public void setEditable(boolean value) {
        if (!value) {
            //setBackground(javax.swing.UIManager.getDefaults().getColor("TextPane.disabledBackground"));
        }
        super.setEditable(value);
    }

    private void validar(KeyEvent evt) {
        int max = getMaxCaracteres();
        int l = getText().length();
        String textoSel = getSelectedText();
        if (textoSel != null) {
            l = l - textoSel.length();
        }

        if (l >= max) {
            getToolkit().beep();
            evt.consume();
        }
    }

    public boolean isMandatory() {
        boolean retvalue = false;
        try {
            if (this.bean != null && index > -1) {
                if (this.bean.getCampo(index).isNulo()) {
                    if (getText().length() <= 0) {
                        RoundedCornerBorder r = new RoundedCornerBorder(false, roundedCornerBorder, true);
                        setBorder(r);
                        retvalue = true;
                    }
                } else {
                    retvalue = false;
                    RoundedCornerBorder r = new RoundedCornerBorder(false, roundedCornerBorder);
                    setBorder(r);
                }
            }
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(JTextoBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(JTextoBase.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retvalue;
    }

    public void setMaxCaracteres(int numeroMaximoCaracteres) {
        this.numeroMaximoCaracteres = numeroMaximoCaracteres;
        
        
        try {
            if (this.bean != null && index > -1) {
                if (this.bean.getCampo(index).getLength() > 0) {
                    numeroMaximoCaracteres = this.bean.getCampo(index).getLength();
                }
            }
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(JTextoBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(JTextoBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        public void setMaxCaracteres() {        
        
        
        try {
            if (this.bean != null && index > -1) {
                if (this.bean.getCampo(index).getLength() > 0) {
                    numeroMaximoCaracteres = this.bean.getCampo(index).getLength();
                }
            }
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(JTextoBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(JTextoBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getMaxCaracteres() {
        return numeroMaximoCaracteres;
    }

    public void errorMaxCaracteres(String texto) {
        setText("");
        javax.swing.JOptionPane.showMessageDialog(this, "Máximo Caracteres: " + getMaxCaracteres() + "\n" + "texto: " + texto + " \n(" + texto.length() + " caracteres)", "Elemento no encontrado", javax.swing.JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void setComponentPopupMenu(javax.swing.JPopupMenu popup) {
        if (popup != null) {
            super.setComponentPopupMenu(popup);
        }
    }

    public boolean isRoundedCornerBorder() {
        return roundedCornerBorder;
    }

    public void setRoundedCornerBorder(boolean roundedCornerBorder) {
        this.roundedCornerBorder = roundedCornerBorder;
        RoundedCornerBorder r = new RoundedCornerBorder(false, roundedCornerBorder);
        setBorder(r);
    }
    // <editor-fold defaultstate="collapsed" desc=" DeclaraciÃ³n de variables ">
    private JPopupTextoBase popupMenu;
    public int numeroMaximoCaracteres = 32000;
    private boolean roundedCornerBorder = false;
    // </editor-fold>
}
