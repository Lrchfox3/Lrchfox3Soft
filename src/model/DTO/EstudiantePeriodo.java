/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DTO;

import com.gmail.lrchfox3.basedatos.Campo;
import com.gmail.lrchfox3.basedatos.SqlTipos;



/**
 *
 * @author lchinchilla
 */
public class EstudiantePeriodo extends Base {
       protected Campo codigoEstudiante = null;
    protected Campo codigoPeriodo = null;

    public EstudiantePeriodo() {
        codigoEstudiante = new Campo("codigo_estudiante", "Código Estudiante", SqlTipos.INTEGER, true, false);
        codigoPeriodo = new Campo("codigo_periodo", "Código Período", SqlTipos.INTEGER);

        setTabla("estudiante_x_periodo");
        setTitulo("Estudiante por Periodo");
    }
    
    public Campo CodigoEstudiante() {
        return codigoEstudiante;
    }

    public Campo CodigoPeriodo() {
        return codigoPeriodo;
    }
    
    
     /**
     * @return the codigo estudiante
     */
    public int getCodigoEstudiante() {
        return codigoEstudiante.getIntValue();
    }

    /**
     * @param codigo the codigo estudiante to set
     */
    public void setCodigoEstudiante(int _codigo) {
        this.codigoEstudiante.setValue(_codigo);
    }
    
        /**
     * @return the codigo Periodo
     */
    public int getCodigoPeriodo() {
        return codigoPeriodo.getIntValue();
    }

    /**
     * @param codigo the codigo Periodo to set
     */
    public void setCodigoPeriodo(int _codigo) {
        this.codigoPeriodo.setValue(_codigo);
    }
}
