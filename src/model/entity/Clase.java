/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author lchinchilla
 */
@Entity
@Table(name = "clase", catalog = "control_clases", schema = "")
@NamedQueries({
    @NamedQuery(name = "Clase.findAll", query = "SELECT c FROM Clase c"),
    @NamedQuery(name = "Clase.findByNombreClase", query = "SELECT c FROM Clase c WHERE c.nombreClase = :nombreClase"),
    @NamedQuery(name = "Clase.findByReferenciaClase", query = "SELECT c FROM Clase c WHERE c.referencia = :referencia"),
    @NamedQuery(name = "Clase.findByInfoClase", query = "SELECT c FROM Clase c WHERE c.nombreClase = :nombreClase OR c.referencia = :referencia")})
public class Clase implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_clase", nullable = false)
    private Integer codigoClase;
    @Column(name = "nombre_clase", length = 64, nullable = false)
    private String nombreClase;
    @Column(name = "comentario", length = 512)
    private String comentario;
    @Column(name = "referencia", length = 9, nullable = false)
    private String referencia;

    public Clase() {
    }

    /**
     * @return the codigoClase
     */
    public Integer getCodigoClase() {
        return codigoClase;
    }

    /**
     * @param _codigoClase the codigoClase to set
     */
    public void setCodigoClase(Integer _codigoClase) {
        Integer oldCodigoClase = this.codigoClase;
        this.codigoClase = _codigoClase;
        changeSupport.firePropertyChange("codigoClase", oldCodigoClase, _codigoClase);
    }

    /**
     * @return the nombreClase
     */
    public String getNombreClase() {
        return nombreClase;
    }

    /**
     * @param _nombreClase the nombreClase to set
     */
    public void setNombreClase(String _nombreClase) {
        String oldNombreClase = this.nombreClase;
        this.nombreClase = _nombreClase;
        changeSupport.firePropertyChange("nombreClase", oldNombreClase, _nombreClase);
    }

    /**
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param _comentario the comentario to set
     */
    public void setComentario(String _comentario) {
        String oldComentarioClase = this.comentario;
        this.comentario = _comentario;
        changeSupport.firePropertyChange("comentario", oldComentarioClase, _comentario);
    }

    /**
     * @return the referencia
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * @param _referencia the referencia to set
     */
    public void setReferencia(String _referencia) {
        String oldReferenciaClase = this.referencia;
        this.referencia = _referencia;
        changeSupport.firePropertyChange("referencia", oldReferenciaClase, _referencia);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoClase != null ? codigoClase.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clase)) {
            return false;
        }
        Clase other = (Clase) object;
        if ((this.codigoClase == null && other.codigoClase != null) || (this.codigoClase != null && !this.codigoClase.equals(other.codigoClase))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nombreClase;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
}
