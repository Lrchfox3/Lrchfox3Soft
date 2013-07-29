/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.BC;


import java.util.List;
import java.sql.SQLException;
import model.DAC.CarreraDAC;
import model.DTO.Carrera;
import com.gmail.lrchfox3.model.dto.Catalogo;

/**
 *
 * @author Administrador
 */
public class CarreraBC implements CatalogoBC {

    private static CarreraDAC dac = new CarreraDAC();

    public CarreraBC() {
    }

    public static List<Carrera> getListaCarreras() throws SQLException, Exception {
        return dac.getListaCarreras();
    }

    public static Carrera getCarrera(int codigo) throws SQLException, Exception {
        return dac.getCarrera(codigo);
    }

    public static boolean editarCarrera(int accion, Carrera dto) throws SQLException, Exception {
        return dac.editar(accion, dto);
    }

    public static List<Carrera> buscarPorCriterio(String criterio) throws SQLException, Exception {
        return dac.buscarPorCriterio(criterio);
    }

    public static boolean importarCarreraFromExcel(String archivo) throws Exception {
        return dac.importarCarreraDesdeExcel(archivo);
    }

    public boolean editar(int accion, Catalogo cat) throws SQLException, Exception {
        return dac.editar(accion, cat);
    }


    public List<Catalogo> getListaCampos() throws SQLException, Exception {
        return dac.getListaCampos();
    }


    public Catalogo getCatalogo(int codigo) throws SQLException, Exception {
        return dac.getCatalogoCarrera(codigo);
    }


    public List<Catalogo> buscarEnCatalogo(String criterio) throws SQLException, Exception {
        return dac.buscarEnCatalogo(criterio);
    }
}
