/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.BC;


import java.util.List;
import java.sql.SQLException;
import model.DAC.ClaseDAC;
import com.gmail.lrchfox3.model.dto.Catalogo;
import model.DTO.Clase;

/**
 *
 * @author Administrador
 */
public class ClaseBC implements CatalogoBC {

    private static ClaseDAC dac = new ClaseDAC();

    public ClaseBC() {
    }

    public static List<Clase> getListaClases() throws SQLException, Exception {
        return dac.getListaClases();
    }

    public static Clase getClase(int codigo) throws SQLException, Exception {
        return dac.getClase(codigo);
    }

    public static boolean editarClase(int accion, Clase dto) throws SQLException, Exception {
        return dac.editar(accion, dto);
    }

    public static List<Clase> buscarPorCriterio(String criterio) throws SQLException, Exception {
        return dac.buscarPorCriterio(criterio);
    }

    public static boolean importarClaseFromExcel(String archivo) throws Exception {
        return dac.importarClaseDesdeExcel(archivo);
    }

    public boolean editar(int accion, Catalogo cat) throws SQLException, Exception {
        return dac.editar(accion, cat);
    }

    public List<Catalogo> getListaCampos() throws SQLException, Exception {
        return dac.getListaCampos();
    }

    public Catalogo getCatalogo(int codigo) throws SQLException, Exception {
        return dac.getCatalogoClase(codigo);
    }

    public List<Catalogo> buscarEnCatalogo(String criterio) throws SQLException, Exception {
        return dac.buscarEnCatalogo(criterio);
    }
}
