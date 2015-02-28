/*
 * MensajesActualizacion.java
 *
 * Created on January 20, 2007, 6:05 PM
 *
 * Conexiones Base de Datos Microsot SQL Server.
 *
 * Copyright 2007 GO Consultores, Inc. All rights reserved.
 * Use is subject to license terms.
 *
 * @author Luis R. Chinchilla H.
 */

package com.gmail.lrchfox3.basedatos;

import java.util.ArrayList;
/**
 *
 * @author Luis R. Chinchilla H.
 */
public class MensajesActualizacion {
    private BD bd;
    private String mensajePrincipal;
    private String etiquetas[];
    private ArrayList objetosMensajes;           // posicion de los campos que se presentaran en las ventanas de mensaje para agregar, modificar y eliminar.
    private boolean[] mensajesAgregar =  {false, true};  // Indica si los mensajes de agregar se desplegar�n. 1-Pregunta de Confirmaci�n de agregado.  2-Informaci�n de agregado.
    private boolean[] mensajesEditar =   {false, true};   // Indica si los mensajes de edici�n se desplegar�n. 1-Pregunta de Confirmaci�n de edici�n.  2-Informaci�n de edici�n.
    private boolean[] mensajesEliminar = {true, true}; // Indica si los mensajes de eliminaci�n se desplegar�n. 1-Pregunta de Confirmaci�n de eliminaci�n.  2-Informaci�n de eliminaci�n.
    
    public final int MENSAJE_CONFIRMACION = 101;
    public final int MENSAJE_INFORMACION = 102;
    public final int MENSAJE_ERROR = 103;
    
    /** Creates a new instance of MensajesActualizacion */
    public MensajesActualizacion() {}
    
    public MensajesActualizacion(BD bd) {
     this.bd = bd;
    }
    
    /**
     *
     */
    public BD getBD() {
        return bd;
    }

    public void setBD(BD bd) {
        this.bd = bd;
    }
    
    
    public String getMensajePrincipal() {
        return mensajePrincipal;
    }

    public void setMensajePrincipal(String mensajePrincipal) {
        this.mensajePrincipal = mensajePrincipal;
    }
    
    public String[] getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(String[] etiquetas) {
        this.etiquetas = etiquetas;
    }
   
    public ArrayList getObjetosMensajes() {
        return objetosMensajes;
    }

    public void setObjetosMensajes(ArrayList objetosMensajes) {
        if (this.objetosMensajes != null) this.objetosMensajes.clear();
        this.objetosMensajes = objetosMensajes;
    }
    
    public boolean[] getMensajesAgregar() {
        return mensajesAgregar;
    }

    public void setMensajesAgregar(boolean[] mensajesAgregar) {
        this.mensajesAgregar = mensajesAgregar;
    }

    public boolean[] getMensajesEditar() {
        return mensajesEditar;
    }

    public void setMensajesEditar(boolean[] mensajesEditar) {
        this.mensajesEditar = mensajesEditar;
    }

    public boolean[] getMensajesEliminar() {
        return mensajesEliminar;
    }

    public void setMensajesEliminar(boolean[] mensajesEliminar) {
        this.mensajesEliminar = mensajesEliminar;
    }
    
    public boolean mensaje(int accion, int numeroMensaje) throws Exception {        
        String titulo = "";
        String[] datosMensaje = {"", getMensajePrincipal()};
        boolean resultado = true;
        boolean esMensaje = false;        
        com.gmail.lrchfox3.utilitarios.Mensajes msg = new com.gmail.lrchfox3.utilitarios.Mensajes();
        com.gmail.lrchfox3.utilitarios.Propiedades propiedades = new com.gmail.lrchfox3.utilitarios.Propiedades();
        
        if (accion == getBD().ACCION_AGREGAR || accion == getBD().ACCION_EDITAR || accion == getBD().ACCION_ELIMINAR ) {
            datosMensaje[1] = getMensajePrincipal();
            
            if (accion == getBD().ACCION_AGREGAR)  {
                titulo = "Agregar";                
                if (getMensajesAgregar()[0]) esMensaje = true;
            }
            else if (accion == getBD().ACCION_EDITAR)  {
                titulo = "Editar";
                 if (getMensajesEditar()[0]) esMensaje = true;
            }
            else if (accion == getBD().ACCION_ELIMINAR)  {
                titulo = "Eliminar";
                 if (getMensajesEliminar()[0]) esMensaje = true;
            }
            
            datosMensaje[0] = titulo;
        }
        else {
            if (accion == getBD().ACCION_ACEPTAR_AGREGAR) {
                titulo = "Agregar";
                datosMensaje[0] = "Agregado";
                if (getMensajesAgregar()[1]) esMensaje = true;
            }
            else if (accion == getBD().ACCION_ACEPTAR_EDITAR) {
                titulo = "Editar";
                datosMensaje[0] = "Editado";
                if (getMensajesEditar()[1]) esMensaje = true;
            }
            else if (accion == getBD().ACCION_ACEPTAR_EDITAR) {
                titulo = "Eliminar";
                
                datosMensaje[0] = "Eliminar";
                if (getMensajesEliminar()[1]) esMensaje = true;
            }
        }
        
        if (esMensaje) {  
            String mensaje = "";
            com.gmail.lrchfox3.utilitarios.Objetos obj = new com.gmail.lrchfox3.utilitarios.Objetos();
            
            int numeroObjetos = getObjetosMensajes().size();
            for (int i = 0; i < numeroObjetos; i++) {
                Object objeto = getObjetosMensajes().get(i);
                int tipo = obj.tipoObjeto(getObjetosMensajes().get(i));
                
                //texto
                if ((tipo == obj.TIPO_TEXTO) || (tipo == obj.TIPO_TEXTO_FORMATEADO) || (tipo == obj.TIPO_TEXTO_FECHA)) {
                    javax.swing.text.JTextComponent texto = ( javax.swing.text.JTextComponent)objeto;
                    if (!texto.getText().trim().equals(""))
                        mensaje = mensaje + "\n" + getEtiquetas()[i] + ": " + texto.getText();
                }
                
                //fecha
                else if (tipo == obj.TIPO_FECHA) {
                    com.gmail.lrchfox3.utilitarios.calendario.JDateChooser fecha = (com.gmail.lrchfox3.utilitarios.calendario.JDateChooser)objeto;
                    if (fecha != null)
                        mensaje = mensaje + "\n" + getEtiquetas()[i] + ": " + fecha.getDate().toString();
                }
                
                //combo
                else if (tipo == obj.TIPO_COMBO) {
                    javax.swing.JComboBox combo = ((javax.swing.JComboBox)objeto);
                    com.gmail.lrchfox3.utilitarios.Item item = (com.gmail.lrchfox3.utilitarios.Item)combo.getSelectedItem();
                    if (item != null) 
                        mensaje = mensaje + "\n" + getEtiquetas()[i] + ": " + item.getItemData().toString();                    
                }
            }
                           
            datosMensaje[1] = datosMensaje[1] + mensaje;
            if (msg.mensajes( null, bd, numeroMensaje, titulo, datosMensaje) != javax.swing.JOptionPane.YES_OPTION)
                resultado = false;
        }
        
        return resultado;
    }

}
