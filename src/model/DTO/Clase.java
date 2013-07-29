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
public class Clase extends Base implements Catalogo {

    protected Campo codigo = null;
    protected Campo nombreclase = null;
    protected Campo referencia = null;
    protected Campo comentario = null;

    public Clase() {
        codigo = new Campo("codigo_clase", "Código", SqlTipos.INTEGER, true, false);
        nombreclase = new Campo("nombre_clase", "Nombre", SqlTipos.VARCHAR);
        referencia = new Campo("referencia", "Referencia", SqlTipos.VARCHAR);
        comentario = new Campo("comentario", "Comentario", SqlTipos.VARCHAR);

        setTabla("CLASE");
        setTitulo("Clases");
    }

    public Campo Codigo() {
        return codigo;
    }

    public Campo NombreClase() {
        return nombreclase;
    }

    public Campo Referencia() {
        return referencia;
    }

    public Campo Comentario() {
        return comentario;
    }

    public int getCodigo() {
        return codigo.getIntValue();
    }

    public void setCodigo(int codigo) {
        this.codigo.setValue(codigo);
    }

    public String getNombreClase() {
        return nombreclase.getStringValue();
    }

    public void setNombreClase(String value) {
        this.nombreclase.setValue(value);
    }

    public String getReferencia() {
        return referencia.getStringValue();
    }

    public void setReferencia(String value) {
        this.referencia.setValue(value);
    }

    public String getComentario() {
        return comentario.getStringValue();
    }

    public void setComentario(String value) {
        this.comentario.setValue(value);
    }

    public String getNombreTabla() {
        return this.getTabla();
    }

    public List<Campo> getCampos() throws Exception {
        List<Campo> lst = new ArrayList<Campo>();
        lst.add(codigo);
        lst.add(nombreclase);
        lst.add(referencia);
        lst.add(comentario);
        return lst;
    }

    public String toString() {
        return this.getNombreClase();
    }
}
