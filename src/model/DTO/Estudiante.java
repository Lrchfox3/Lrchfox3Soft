/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DTO;

import com.gmail.lrchfox3.basedatos.Campo;
import com.gmail.lrchfox3.basedatos.SqlTipos;



/**
 *
 * @author Administrador
 */
public class Estudiante extends Base  {   
    protected Campo codigoEstudiante = null;        //1
    protected Campo nombre = null;        //2
    protected Campo numeroCuenta = null; //3
    protected Campo correo = null;        //4
    protected Campo comentario = null;          //5
    protected Campo telefono = null;      //6
    protected Campo orden = null;      //7

    public Estudiante() {
        codigoEstudiante = new Campo("codigo_estudiante", "Código", SqlTipos.INTEGER, true);
        nombre = new Campo("nombre", "Nombre", SqlTipos.VARCHAR);
        numeroCuenta = new Campo("numero_cuenta", "No. Cuenta", SqlTipos.VARCHAR);
        correo = new Campo("correo", "Correo", SqlTipos.VARCHAR);
        comentario = new Campo("comentario", "Comentario", SqlTipos.VARCHAR);
        telefono = new Campo("telefono", "Teléfono", SqlTipos.VARCHAR);
        orden = new Campo("orden", "Orden", SqlTipos.INTEGER);
        setTabla("ESTUDIANTE");
        setTitulo("Estudiantes");
    }

    public Campo Comentario() {
        return comentario;
    }

    public Campo CodigoEstudiante() {
        return codigoEstudiante;
    }

    public Campo Correo() {
        return correo;
    }

    public Campo Nombre() {
        return nombre;
    }

    public Campo NumeroCuenta() {
        return numeroCuenta;
    }

    public Campo Telefono() {
        return telefono;
    }
    
     public Campo Orden() {
        return orden;
    }

    /**
     * @return the codigo
     */
    public int getCodigoEstudiante() {
        return codigoEstudiante.getIntValue();
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigoEstudiante(int codigo) {
        this.codigoEstudiante.setValue(codigo);
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre.getStringValue();
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre.setValue(nombre);
    }

    /**
     * @return the numeroCuenta
     */
    public String getNumeroCuenta() {
        return numeroCuenta.getStringValue();
    }

    /**
     * @param numeroCuenta the numeroCuenta to set
     */
    public void setNumeroCuenta(String numero_cuenta) {
        this.numeroCuenta.setValue(numero_cuenta);
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo.getStringValue();
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo.setValue(correo);
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono.getStringValue();
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono.setValue(telefono);
    }

    /**
     * @return the codigo_carrera
     */
    public String getComentario() {
        return comentario.getStringValue();
    }

    /**
     * @param codigo_carrera the codigo_carrera to set
     */
    public void setComentario(String codigo_carrera) {
        this.comentario.setValue(codigo_carrera);
    }

    public int getOrden() {
        return orden.getIntValue();
    }

    public void setOrden(int _orden) {
        this.orden.setValue(_orden);
    }
    
    @Override
    public String toString(){
        return this.getNombre();
    }
    
    
}
