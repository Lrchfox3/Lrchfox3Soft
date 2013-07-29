/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DTO;

import com.gmail.lrchfox3.basedatos.Campo;
import com.gmail.lrchfox3.basedatos.SqlTipos;
import com.gmail.lrchfox3.utilitarios.Utileria;



/**
 * Mantiene un monitoreo de las acciones que realizan los usuario dentro del sistema
 *
 * @author chinchillal
 */
public class Monitoreo extends Base {

    protected Campo codigo = null;           //1
    protected Campo codigoReferencia = null; //2
    protected Campo accion = null;           //3
    protected Campo descripcion = null;      //4
    protected Campo usuario = null;          //5
    protected Campo fechaHora = null;       //6

    public Monitoreo() {
        codigo = new Campo("codigo", "Código", SqlTipos.INTEGER);
        codigoReferencia = new Campo("codigo_referencia", "Código Referencia", SqlTipos.VARCHAR);
        accion = new Campo("accion", "Acción", SqlTipos.VARCHAR);
        descripcion = new Campo("descripcion", "Descripción", SqlTipos.VARCHAR);
        usuario = new Campo("usuario", "Usuario", SqlTipos.VARCHAR);
        fechaHora = new Campo("fecha_hora", "Fecha y Hora", SqlTipos.DATE_TIME);
        setTabla("MONITOREO");
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo.getIntValue();
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo.setValue(codigo);
    }

    /**
     * @return the codigoReferencia
     */
    public String getCodigoReferencia() {
        return codigoReferencia.getStringValue();
    }

    /**
     * @param codigoReferencia the codigoReferencia to set
     */
    public void setCodigoReferencia(String codigoReferencia) {
        this.codigoReferencia.setValue(codigoReferencia);
    }

    /**
     * @return the accion
     */
    public String getAccion() {
        return accion.getStringValue();
    }

    /**
     * @param accion the accion to set
     */
    public void setAccion(String accion) {
        this.accion.setValue(accion);
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion.getStringValue();
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion.setValue(descripcion);
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario.getStringValue();
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario.setValue(usuario);
    }

    /**
     * @return the fecha_hora
     */
    public String getFechaHora() {
        return Utileria.getStrFechaActual(fechaHora.getDateValue(),"dd/MM/yyyy HH:mm:ss");
    }

    /**
     * @param fecha_hora the fecha_hora to set
     */
    public void setFechaHora(java.sql.Date fechaHora) {
        this.fechaHora.setValue(fechaHora);
    }
}
