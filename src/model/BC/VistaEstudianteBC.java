/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model.BC;

import java.util.List;
import java.sql.SQLException;
import model.DAC.VistaEstudianteDAC;
import model.DTO.VistaEstudiante;

/**
 *
 * @author Administrador
 */
public class VistaEstudianteBC {
    private static VistaEstudianteDAC dac = new VistaEstudianteDAC();

    public static List<VistaEstudiante> getListaEstudiantes() throws SQLException, IllegalArgumentException, IllegalAccessException, Exception{
        return dac.getListaEstudiantes();
    }
    
    public static List<VistaEstudiante> getListaEstudiantes(Integer _codigoClase, Integer _codigoPeriodo) throws SQLException, IllegalArgumentException, IllegalAccessException, Exception{
        return dac.getListaEstudiantes( _codigoClase,  _codigoPeriodo);
    }

    public static VistaEstudiante getEstudiante(int codigo) throws SQLException, IllegalArgumentException, IllegalAccessException, Exception{
        return dac.getEstudiante(codigo);
    }

    public static List<VistaEstudiante> buscarPorCriterio(String criterio) throws SQLException, IllegalArgumentException, IllegalAccessException, Exception {
        return dac.buscarPorCriterio(criterio);
    }
    
    public static List<VistaEstudiante> buscarPorCriterio(String criterio, Integer _codigoClase, Integer _codigoPeriodo) throws SQLException, IllegalArgumentException, IllegalAccessException, Exception {
        return dac.buscarPorCriterio(criterio,   _codigoClase,  _codigoPeriodo);
    }

}
