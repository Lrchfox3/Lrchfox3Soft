/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model.BC;


import java.sql.SQLException;
import java.util.List;
import com.gmail.lrchfox3.model.dto.Catalogo;

/**
 *
 * @author chinchillal
 */
public interface CatalogoBC {

    public List<Catalogo> getListaCampos() throws SQLException, Exception;

    public Catalogo getCatalogo(int codigo) throws SQLException, Exception;

    public boolean editar(int accion, Catalogo cat) throws SQLException, Exception;

    public List<Catalogo> buscarEnCatalogo(String criterio) throws SQLException, Exception;
}
