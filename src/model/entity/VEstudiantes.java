/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lchinchilla
 */
@Entity
@Table(name = "v_estudiantes", catalog = "control_clases", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VEstudiantes.findAll", query = "SELECT v FROM VEstudiantes v"),
    @NamedQuery(name = "VEstudiantes.findByCodigoEstudiante", query = "SELECT v FROM VEstudiantes v WHERE v.codigoEstudiante = :codigoEstudiante"),
    @NamedQuery(name = "VEstudiantes.findByNombre", query = "SELECT v FROM VEstudiantes v WHERE v.nombre = :nombre"),
    @NamedQuery(name = "VEstudiantes.findByNumeroCuenta", query = "SELECT v FROM VEstudiantes v WHERE v.numeroCuenta = :numeroCuenta"),
    @NamedQuery(name = "VEstudiantes.findByCorreo", query = "SELECT v FROM VEstudiantes v WHERE v.correo = :correo"),
    @NamedQuery(name = "VEstudiantes.findByComentario", query = "SELECT v FROM VEstudiantes v WHERE v.comentario = :comentario"),
    @NamedQuery(name = "VEstudiantes.findByTelefono", query = "SELECT v FROM VEstudiantes v WHERE v.telefono = :telefono"),
    @NamedQuery(name = "VEstudiantes.findByOrden", query = "SELECT v FROM VEstudiantes v WHERE v.orden = :orden"),
    @NamedQuery(name = "VEstudiantes.findByNombreClase", query = "SELECT v FROM VEstudiantes v WHERE v.nombreClase = :nombreClase"),
    @NamedQuery(name = "VEstudiantes.findByPeriodo", query = "SELECT v FROM VEstudiantes v WHERE v.periodo = :periodo")})
public class VEstudiantes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "codigo_estudiante", nullable = false)
    @Id
    private int codigoEstudiante;
    @Basic(optional = false)
    @Column(nullable = false, length = 64)
    private String nombre;
    @Basic(optional = false)
    @Column(name = "numero_cuenta", nullable = false, length = 32)
    private String numeroCuenta;
    @Column(length = 64)
    private String correo;
    @Column(length = 1024)
    private String comentario;
    @Column(length = 16)
    private String telefono;
    private Integer orden;
    @Column(name = "nombre_clase", length = 64)
    private String nombreClase;
    @Column(length = 32)
    private String periodo;

    public VEstudiantes() {
    }

    public int getCodigoEstudiante() {
        return codigoEstudiante;
    }

    public void setCodigoEstudiante(int codigoEstudiante) {
        this.codigoEstudiante = codigoEstudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getNombreClase() {
        return nombreClase;
    }

    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    
}