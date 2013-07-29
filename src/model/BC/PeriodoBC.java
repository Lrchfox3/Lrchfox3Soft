/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model.BC;


import java.util.List;
import java.sql.SQLException;
import model.DAC.PeriodoDAC;
import com.gmail.lrchfox3.model.dto.Catalogo;
import model.DTO.Periodo;

/**
 *
 * @author Administrador
 */
public class PeriodoBC implements CatalogoBC {
    private static PeriodoDAC dac = new PeriodoDAC();

    public PeriodoBC() {}
    
    public static List<Periodo> getListaPeriodos() throws SQLException, Exception{
        return dac.getListaPeriodos();
    }

    public static Periodo getPeriodo(int codigo) throws SQLException, Exception{
        return dac.getPeriodo(codigo);
    }

    public static boolean editarPeriodo(int accion, Periodo dto ) throws SQLException, Exception{
        return dac.editar(accion, dto);
    }

    public static List<Periodo> buscarPorCriterio(String criterio) throws SQLException, Exception {
        return dac.buscarPorCriterio(criterio);
    }

    public static boolean importarEstudiantesFromExcel(String archivo) throws Exception{
        return dac.importarPeriodoFromExcel(archivo);
    }

    public boolean editar(int accion, Catalogo cat) throws SQLException, Exception {
        return dac.editar(accion, cat);
    }

    public List<Catalogo> getListaCampos() throws SQLException, Exception {
        return dac.getListaCampos();
    }

    public Catalogo getCatalogo(int codigo) throws SQLException, Exception {
        return dac.getCatalogoPeriodo(codigo);
    }

    public  List<Catalogo> buscarEnCatalogo(String criterio) throws SQLException, Exception {
        return dac.buscarEnCatalogo(criterio);
    }
}
