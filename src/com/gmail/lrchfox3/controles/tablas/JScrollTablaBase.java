/*
 * JScrollTablaBase.java
 *
 * Created on December 27, 2006, 10:16 AM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.gmail.lrchfox3.controles.tablas;



/**
 *
 * @author Luis R. Chinchilla H.
 */
public class JScrollTablaBase extends javax.swing.JScrollPane {
    
    private int posX = 15;
    private int posY = 15;
    private JScrollTablaBase[] jScrollsSincronizar = null;
    public JTablaBase jtabla;
    
    /**
     * Creates a new instance of JScrollTablaBase 
     */
    
    public JScrollTablaBase() {        
        inicializar();
    }
            
    public JScrollTablaBase(javax.swing.JTable tabla) {
        super(tabla);
        inicializar();
    }
    
    private void sincronizar(java.awt.event.AdjustmentEvent evt) {
        JScrollTablaBase js[] = getJScrollsSincronizar();
        if (js != null)
            for (int i = 0; i < js.length; i ++)
                if (js[i] != null) js[i].getHorizontalScrollBar().setValue(getHorizontalScrollBar().getValue());        
        
    }
            
    private void resize() {
        java.awt.Dimension d = getMaximumSize();        
        if (getWidth() > d.getWidth())  setSize((int)d.getWidth() , (int)getSize().getHeight());                
    }
   
    public void setTamanyo(int ancho, int alto) {
        int altoScroll = 2;        
        ancho = ancho + 21;
        if (ancho > getMaximumSize().getWidth()) {
            ancho = (int)getMaximumSize().getWidth();
        }
        
        if (alto > getMaximumSize().getHeight()) {
            alto = (int)getMaximumSize().getHeight();
        } 
        setSize(ancho, alto + altoScroll);
    }
    
    private void setTamanyoMaximo() {
        com.gmail.lrchfox3.utilitarios.Pantalla p = new com.gmail.lrchfox3.utilitarios.Pantalla();
        p.resolucion(); 
        setMaximumSize(new java.awt.Dimension(p.getAncho() - ((getPosX() * 2) + 10), p.getAlto() - ((getPosY() * 2 ) + 10)));        
    }
    
    private void inicializar() {
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                resize();
            }
        }); 
       
        getHorizontalScrollBar().addAdjustmentListener(new java.awt.event.AdjustmentListener() {
            public void adjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {                
                sincronizar(evt);
            }
        });
        
        setLocation(getPosX(), getPosY());
        getViewport().setBackground(new java.awt.Color(236, 233, 216));
        setBackground(new java.awt.Color(236, 233, 216));
        setBorder(new javax.swing.border.EtchedBorder(javax.swing.border.EtchedBorder.LOWERED));
        setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);        
        setTamanyoMaximo();
    }
    
    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
        setLocation(getPosX(), getPosY());
        setTamanyoMaximo();
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
        setLocation(getPosX(), getPosY());
        setTamanyoMaximo();
    }
    
    public JScrollTablaBase[] getJScrollsSincronizar() {
        return jScrollsSincronizar;
    }
    
    public void setJScrollsSincronizar(JScrollTablaBase[] jScrollsSincronizar) {
        this.jScrollsSincronizar = jScrollsSincronizar;        
    }

}
