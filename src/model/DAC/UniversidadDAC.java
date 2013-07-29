/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAC;


import com.gmail.lrchfox3.basedatos.BD;
import com.gmail.lrchfox3.utilitarios.Excel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import com.gmail.lrchfox3.model.dto.Catalogo;
import model.DTO.Universidad;

/**
 *
 * @author Administrador
 */
public class UniversidadDAC extends BD {

    private Universidad dtoBase = new Universidad();

    public List<Universidad> getListaUniversidades() throws SQLException, Exception {
        List<Universidad> lst = new ArrayList<Universidad>();
        String sql = "SELECT " + dtoBase.getNombreCampos() + " FROM " + dtoBase.getTabla();
        System.out.println("SELECT: " + sql);
        Statement st = crearSentencia();
        ResultSet rs = st.executeQuery(sql);        
        //rs.first();
        while (rs.next()) {
            lst.add(getRegistro(rs));
        }
        if (st != null) {
            st.close();
        }
        if (rs != null) {
            rs.close();
        }
        return lst;
    }

    public List<Universidad> getListaCampos() throws SQLException, Exception {

        List<Universidad> lst = new ArrayList<Universidad>();

        for (Universidad p : getListaUniversidades()) {
            lst.add(p);

        }
        return lst;
    }

    public Universidad getUniversidad(int codigo) throws SQLException, Exception {
        Universidad dto = new Universidad();
        String sql = "SELECT " + dtoBase.getNombreCampos()
                + " FROM " + dtoBase.getTabla() + " WHERE " + dtoBase.getNombreCampo(1) + " = " + codigo;
        Statement st = crearSentencia();

        ResultSet rs = st.executeQuery(sql);
        //rs.first();
        while (rs.next()) {
            dto = getRegistro(rs);
        }
        if (st != null) {
            st.close();
        }
        if (rs != null) {
            rs.close();
        }
        return dto;
    }

    public List<Universidad> buscarPorCriterio(String criterio) throws SQLException, Exception {
        List<Universidad> lst = new ArrayList<Universidad>();
        String sql = "SELECT " + dtoBase.getNombreCampos() + " FROM " + dtoBase.getTabla() + " WHERE " + dtoBase.getNombreCampo(2) + " like '%" + criterio + "%' OR " + dtoBase.getNombreCampo(3) + " like '%" + criterio + "%' ";
        Statement st = crearSentencia();
        ResultSet rs = st.executeQuery(sql);
        //rs.first();
        while (rs.next()) {
            lst.add(getRegistro(rs));
        }
        //if (st != null) {
            st.close();
        //}
        //if (rs != null) {
            rs.close();
        //}
        return lst;
    }


    private Universidad getRegistro(ResultSet prs) throws SQLException, Exception {
        Universidad dto = new Universidad();
        dto.setCodigo(prs.getInt(dtoBase.getNombreCampo(1)));
        dto.setNombre(prs.getString(dtoBase.getNombreCampo(2)));
        dto.setCodigoReferencia(prs.getString(dtoBase.getNombreCampo(3)));
        dto.setIniciales(prs.getString(dtoBase.getNombreCampo(4)));
        dto.setMensaje(prs.getString(dtoBase.getNombreCampo(5)));
        dto.setLogo(prs.getString(dtoBase.getNombreCampo(6)));
        return dto;
    }

    public boolean editar(int accion, Universidad dto) throws SQLException, Exception {
        String sql = "";
        PreparedStatement pstr = null;
        if (accion == BD.ACCION_AGREGAR) {
            sql = "INSERT INTO " + dtoBase.getTabla() + "(" + dtoBase.getNombreCampos(new int[]{2, 3}) + ") VALUES(?, ?)";
            pstr = prepararSentencia(sql);
            pstr.setString(1, dto.getNombre());
            pstr.setString(2, dto.getCodigoReferencia());
            pstr.executeUpdate();
            commit();
        } else if (accion == BD.ACCION_EDITAR) {
            sql = "UPDATE " + dtoBase.getTabla() + " SET " + dtoBase.getNombreCampo(2) + " = ?, " + dtoBase.getNombreCampo(3) + " = ?  WHERE " + dtoBase.getNombreCampo(1) + " = ?";
            pstr = prepararSentencia(sql);
            pstr.setString(1, dto.getNombre());
            pstr.setString(2, dto.getCodigoReferencia());
            pstr.setInt(3, dto.getCodigo());
            pstr.executeUpdate();
            commit();
        } else if (accion == BD.ACCION_ELIMINAR) {
            sql = "DELETE FROM " + dtoBase.getTabla() + " WHERE " + dtoBase.getNombreCampo(1) + " = ?";
            pstr = prepararSentencia(sql);
            pstr.setInt(1, dto.getCodigo());
            pstr.executeUpdate();
            commit();
        }

        if (pstr != null) {
            pstr.close();
        }
        return true;
    }

    public boolean editar(int accion, Catalogo cat) throws SQLException, Exception {
        Universidad dto = (Universidad) cat;
        return editar(accion, dto);
    }

    public boolean importarClaseDesdeExcel(String archivo) throws Exception {
        String sql = "INSERT INTO " + dtoBase.getTabla() + "(" + dtoBase.getNombreCampos(new int[]{2, 3}) + ") VALUES(?,?)";
        int tipoDatos[] = {Types.VARCHAR, Types.VARCHAR};
        Excel excel = new Excel();
        excel.importarExcel(archivo, sql, tipoDatos);
        return true;
    }
}
