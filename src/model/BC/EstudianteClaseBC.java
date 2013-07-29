/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.BC;

import java.sql.SQLException;
import java.util.List;
import model.DAC.EstudianteClaseDAC;
import model.DTO.EstudianteClase;

/**
 *
 * @author Administrador
 */
public class EstudianteClaseBC {

    private static EstudianteClaseDAC dac = new EstudianteClaseDAC();

    public static List<Integer> getEstudianteClases(int _codigoEstudiante) throws SQLException, Exception {
        return dac.getEstudianteClases(_codigoEstudiante);
    }    

    public static boolean editar(int _accion, EstudianteClase _dto) throws SQLException, Exception {
        return dac.editar(_accion, _dto);
    }

    public static boolean editar(int _accion, int _codigoEstudiante) throws SQLException, Exception {
        return dac.editar(_accion, _codigoEstudiante);
    }
}
