/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAC;


import com.gmail.lrchfox3.basedatos.BD;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.DTO.Monitoreo;

/**
 *
 * @author chinchillal
 */
public class MonitoreoDAC extends BD {

    private Monitoreo dtoBase = new Monitoreo();

    public void registrarMovimiento(Monitoreo log) {
        String sql = "";
        PreparedStatement pstr = null;
        try {
            sql = "INSERT INTO " + dtoBase.getTabla() + "(" + dtoBase.getNombreCampos(new int[]{2, 3, 4, 5, 6}) + ") VALUES(?,?,?,?,?)";
            pstr = prepararSentencia(sql);
            pstr.setString(1, log.getCodigoReferencia());
            pstr.setString(2, log.getAccion());
            pstr.setString(3, log.getDescripcion());
            pstr.setString(4, log.getUsuario());
            pstr.setString(5, log.getFechaHora());
            pstr.executeUpdate();
            commit();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
