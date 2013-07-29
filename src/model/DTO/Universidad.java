/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DTO;

import com.gmail.lrchfox3.basedatos.Campo;
import com.gmail.lrchfox3.basedatos.SqlTipos;



/**
 *
 * @author lchinchilla
 */
public class Universidad extends Base {

    protected Campo codigo = null; //1
    protected Campo nombre = null; //2
    protected Campo codigoReferencia = null; //3
    protected Campo iniciales = null; //4
    protected Campo mensaje = null; //5
    protected Campo logo = null; //6

    public Universidad() {
        codigo = new Campo("codigo_carrera", "Código", SqlTipos.INTEGER, true, false);
        nombre = new Campo("nombre", "Nombre", SqlTipos.VARCHAR, 64);
        codigoReferencia = new Campo("codigo_referencia", "Código Referencia", SqlTipos.VARCHAR, false, false);
        iniciales = new Campo("iniciales", "Iniciales", SqlTipos.VARCHAR, 16);
        mensaje = new Campo("mensaje", "Mensaje", SqlTipos.VARCHAR, 512);
        logo = new Campo("logo", "Logo", SqlTipos.VARCHAR, 128);

        setTabla("UNIVERSIDAD");
        setTitulo("Universidades");
    }

    public int getCodigo() {
        return codigo.getIntValue();
    }

    public void setCodigo(int codigo) {
        this.codigo.setValue(codigo);
    }

    public String getNombre() {
        return nombre.getStringValue();
    }

    public void setNombre(String nombre) {
        this.nombre.setValue(nombre);
    }

    public String getCodigoReferencia() {
        return codigoReferencia.getStringValue();
    }

    public void setCodigoReferencia(String codigoReferencia) {
        this.codigoReferencia.setValue(codigoReferencia);
    }

    public String getIniciales() {
        return iniciales.getStringValue();
    }

    public void setIniciales(String iniciales) {
        this.iniciales.setValue(iniciales);
    }

    public String getMensaje() {
        return mensaje.getStringValue();
    }

    public void setMensaje(String mensaje) {
        this.mensaje.setValue(mensaje);
    }

    public String getLogo() {
        return logo.getStringValue();
    }

    public void setLogo(String logo) {
        this.logo.setValue(logo);
    }

    public Campo Codigo() {
        return codigo;
    }

    public Campo Nombre() {
        return nombre;
    }

    public Campo CodigoReferencia() {
        return codigoReferencia;
    }

    public Campo Iniciales() {
        return iniciales;
    }

    public Campo Mensaje() {
        return mensaje;
    }

    public Campo Logo() {
        return logo;
    }

    @Override
    public String toString() {
        return this.nombre.getStringValue();

    }
}
