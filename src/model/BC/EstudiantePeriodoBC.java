/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model.BC;

import java.sql.SQLException;
import java.util.List;
import model.DAC.EstudiantePeriodoDAC;
import model.DTO.EstudiantePeriodo;

/**
 *
 * @author Administrador
 */
public class EstudiantePeriodoBC {
    private static EstudiantePeriodoDAC dac = new EstudiantePeriodoDAC();

     public static List<Integer> getEstudiantePeriodos(int _codigoEstudiante) throws SQLException, Exception {
        return dac.getEstudiantePeriodos(_codigoEstudiante);
    }   
     
    public static boolean editar(int _accion, EstudiantePeriodo _dto ) throws SQLException, Exception{
        return dac.editar(_accion, _dto);
    }

     public static boolean editar(int _accion, int _codigoEstudiante ) throws SQLException, Exception{
        return dac.editar(_accion, _codigoEstudiante);
    }
}
