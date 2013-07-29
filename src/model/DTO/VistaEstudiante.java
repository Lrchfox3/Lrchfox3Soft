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
public class VistaEstudiante extends Base{
    protected Campo codigo_estudiante = null;        //1
    protected Campo nombre = null;        //2
    protected Campo numeroCuenta = null; //3
    protected Campo correo = null;        //4
    protected Campo comentario = null;          //5
    protected Campo telefono = null;      //6
    protected Campo orden = null;      //7
    protected Campo codigoClase = null;      //8
    protected Campo nombreClase = null;      //9
    protected Campo codigoPeriodo = null;      //10
    protected Campo periodo = null;      //11
    
    
     public VistaEstudiante() {
        codigo_estudiante = new Campo("codigo_estudiante", "Código", SqlTipos.INTEGER, true);
        nombre = new Campo("nombre", "Nombre", SqlTipos.VARCHAR, 64);
        numeroCuenta = new Campo("numero_cuenta", "No. Cuenta", SqlTipos.VARCHAR,32);
        correo = new Campo("correo", "Correo", SqlTipos.VARCHAR,64);
        comentario = new Campo("comentario", "Comentario", SqlTipos.VARCHAR,1024);
        telefono = new Campo("telefono", "Teléfono", SqlTipos.VARCHAR,16);
        orden = new Campo("orden", "Orden", SqlTipos.INTEGER);
        codigoClase = new Campo("codigo_clase", "Código Clase", SqlTipos.INTEGER);
        nombreClase = new Campo("nombre_clase", "Teléfono", SqlTipos.VARCHAR,64);
        codigoPeriodo = new Campo("codigo_periodo", "Código Período", SqlTipos.INTEGER);
        periodo = new Campo("periodo", "Período", SqlTipos.VARCHAR,32);
        
        setTabla("v_estudiantes");
        setTitulo("Estudiantes");
    }

    public Campo Comentario() {
        return comentario;
    }

    public Campo Codigo() {
        return codigo_estudiante;
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
    
    public Campo CodigoClase() {
        return codigoClase;
    }
    
    public Campo NombreClase() {
        return nombreClase;
    }
    
    public Campo CodigoPeriodo() {
        return codigoPeriodo;
    }
    
    public Campo Periodo() {
        return periodo;
    }
    
    public Campo Orden() {
        return orden;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo_estudiante.getIntValue();
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int _codigo) {
        this.codigo_estudiante.setValue(_codigo);
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
    public void setNombre(String _nombre) {
        this.nombre.setValue(_nombre);
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
    public void setNumeroCuenta(String _numeroCuenta) {
        this.numeroCuenta.setValue(_numeroCuenta);
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
    public void setCorreo(String _correo) {
        this.correo.setValue(_correo);
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
    public void setTelefono(String _telefono) {
        this.telefono.setValue(_telefono);
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
    public void setComentario(String _codigoCarrera) {
        this.comentario.setValue(_codigoCarrera);
    }

    public int getCodigoClase() {
        return codigoClase.getIntValue();
    }

    public void setCodigoClase(int _codigo) {
        this.codigoClase.setValue(_codigo);
    }
    
    public String getNombreClase() {
        return nombreClase.getStringValue();
    }

    public void setNombreClase(String _nombreClase) {
        this.nombreClase.setValue(_nombreClase);
    }
    
    public int getCodigoPeriodo() {
        return codigoPeriodo.getIntValue();
    }

    public void setCodigoPeriodo(int _codigo) {
        this.codigoPeriodo.setValue(_codigo);
    }
    
    public String getPeriodo() {
        return periodo.getStringValue();
    }

    public void setPeriodo(String _periodo) {
        this.periodo.setValue(_periodo);
    }
    
    public int getOrden() {
        return orden.getIntValue();
    }

    public void setOrden(int _orden) {
        this.orden.setValue(_orden);
    }
    
    
    @Override
    public String toString(){
        return this.nombre.getStringValue();
    }
    
}
