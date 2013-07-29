/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model.BC;


import java.util.List;
import java.sql.SQLException;
import model.DAC.EstudianteDAC;
import model.DTO.Estudiante;

/**
 *
 * @author Administrador
 */
public class EstudianteBC {
    private static EstudianteDAC dac = new EstudianteDAC();

    public static List<Estudiante> getListaEstudiantes() throws SQLException, IllegalArgumentException, IllegalAccessException, Exception{
        return dac.getListaEstudiantes();
    }

    public static Estudiante getEstudiante(int codigo) throws SQLException, IllegalArgumentException, IllegalAccessException, Exception{
        return dac.getEstudiante(codigo);
    }

    public static boolean editar(int accion, Estudiante dto ) throws SQLException, IllegalArgumentException, IllegalAccessException, Exception{
        return dac.editar(accion, dto);
    }

    public static List<Estudiante> buscarPorCriterio(String criterio) throws SQLException, Exception {
        return dac.buscarPorCriterio(criterio);
    }

    public static boolean importarEstudiantesFromExcel(String archivo) throws Exception{
        return dac.importarEstudiantesFromExcel(archivo);
    }
}
