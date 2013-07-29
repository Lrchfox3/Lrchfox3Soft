/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DTO;

import com.gmail.lrchfox3.model.dto.Catalogo;
import com.gmail.lrchfox3.basedatos.Campo;
import com.gmail.lrchfox3.basedatos.SqlTipos;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chinchillal
 */
public class Carrera extends Base implements Catalogo {

    protected Campo codigo = null;
    protected Campo nombre = null;
    protected Campo codigoReferencia = null;
    protected Campo codigoUniversidad = null;

    public Carrera() {
        codigo = new Campo("codigo_carrera", "Código", SqlTipos.INTEGER, true, false);
        nombre = new Campo("nombre", "Nombre", SqlTipos.VARCHAR);
        codigoReferencia = new Campo("codigo_referencia", "Código Referencia", SqlTipos.VARCHAR, false, false);
        codigoUniversidad = new Campo("codigo_universidad", "Código Universidad", SqlTipos.INTEGER, false, false);

        setTabla("CARRERA");
        setTitulo("Carreras");
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

    public Campo CodigoUniversidad() {
        return codigoUniversidad;
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

    public void setNombre(String value) {
        this.nombre.setValue(value);
    }

    public String getCodigo_referencia() {
        return this.codigoReferencia.getStringValue();
    }

    public void setCodigo_referencia(String value) {
        this.codigoReferencia.setValue(value);
    }

    public int getCodigo_universidad() {
        return codigoUniversidad.getIntValue();
    }

    public void setCodigo_universidad(int value) {
        this.codigoUniversidad.setValue(value);
    }

    /**
     *
     * @return
     */
    @Override
    public String getNombreTabla() {
        return this.getTabla();
    }

    /**
     *
     * @return @throws Exception
     */
    @Override
    public List<Campo> getCampos() throws Exception {
        List<Campo> lst = new ArrayList();
        lst.add(codigo);
        lst.add(nombre);
        lst.add(codigoReferencia);
        lst.add(codigoUniversidad);
        return lst;
    }

    @Override
    public String toString() {
        return this.nombre.getStringValue();

    }
}
