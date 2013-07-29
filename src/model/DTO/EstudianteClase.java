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
public class EstudianteClase extends Base {

    protected Campo codigoEstudiante = null;
    protected Campo codigoClase = null;

    public EstudianteClase() {
        codigoEstudiante = new Campo("codigo_estudiante", "Código Estudiante", SqlTipos.INTEGER, true, false);
        codigoClase = new Campo("codigo_clase", "Código Clase", SqlTipos.INTEGER);

        setTabla("estudiante_x_clase");
        setTitulo("Estudiante por Clase");
    }
    
    public Campo CodigoEstudiante() {
        return codigoEstudiante;
    }

    public Campo CodigoClase() {
        return codigoClase;
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
     * @return the codigo Clase
     */
    public int getCodigoClase() {
        return codigoClase.getIntValue();
    }

    /**
     * @param codigo the codigo Clase to set
     */
    public void setCodigoClase(int _codigo) {
        this.codigoClase.setValue(_codigo);
    }
}
