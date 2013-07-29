/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model.BC;

import com.gmail.lrchfox3.utilitarios.Utileria;
import model.DAC.MonitoreoDAC;
import model.DTO.Monitoreo;




/**
 *
 * @author chinchillal
 */
public class MonitoreoBC {

    private static MonitoreoDAC dac = new MonitoreoDAC();

    public static void registrarMovimiento(Monitoreo log) {
        dac.registrarMovimiento(log);
    }

    public static void registrarMovimiento(String codigoReferencia, String accion, String descripcion, String usuario) {
        Monitoreo log = new Monitoreo();
        log.setCodigoReferencia(codigoReferencia);
        log.setAccion(accion);
        log.setDescripcion(descripcion);
        log.setUsuario(usuario);        
        log.setFechaHora(Utileria.getFechaActual());
        dac.registrarMovimiento(log);
    }
}
