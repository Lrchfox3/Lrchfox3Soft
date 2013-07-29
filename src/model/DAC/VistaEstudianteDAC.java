/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAC;


import com.gmail.lrchfox3.basedatos.BD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.DTO.VistaEstudiante;

/**
 *
 * @author Administrador
 */


public class VistaEstudianteDAC extends BD {

    protected VistaEstudiante dtoBase = new VistaEstudiante();

    public List<VistaEstudiante> getListaEstudiantes() throws SQLException, IllegalArgumentException, IllegalAccessException, Exception {
        List<VistaEstudiante> lst = new ArrayList<VistaEstudiante>();
        String sql = "SELECT " + dtoBase.getNombreCampos(new int[]{1, 2, 3, 4, 5, 6, 7}) + " FROM " + dtoBase.getTabla() + " GROUP BY " + dtoBase.getNombreCampos(new int[]{1, 2, 3, 4, 5, 6, 7});
        System.out.println(sql);
         Statement st = crearSentencia();
        ResultSet rs = st.executeQuery(sql);
        //rs.first();
        while (rs.next()) {
            lst.add(getRegistro(rs, true));
        }
        if (st != null) {
            st.close();
        }
        if (rs != null) {
            rs.close();
        }
        return lst;
    }

    public List<VistaEstudiante> getListaEstudiantes(Integer _codigoClase, Integer _codigoPeriodo) throws SQLException, IllegalArgumentException, IllegalAccessException, Exception {
        List<VistaEstudiante> lst = new ArrayList<VistaEstudiante>();
        String sql = "SELECT " + dtoBase.getNombreCampos() + " FROM " + dtoBase.getTabla();
        if (_codigoClase != null) {
            sql = sql + " WHERE " + dtoBase.getNombreCampo(8) + " = " + _codigoClase.intValue();
        } else if (_codigoPeriodo != null) {
            sql = sql + " WHERE " + dtoBase.getNombreCampo(10) + " = " + _codigoPeriodo.intValue();
        } else if (_codigoClase != null && _codigoPeriodo != null) {
            sql = sql + " WHERE " + dtoBase.getNombreCampo(8) + " = " + _codigoClase.intValue()
                    + " AND " + dtoBase.getNombreCampo(10) + " = " + _codigoPeriodo.intValue();
        }
        Statement st = crearSentencia();
        ResultSet rs = st.executeQuery(sql);
        //rs.first();
        while (rs.next()) {
            lst.add(getRegistro(rs, false));
        }
        if (st != null) {
            st.close();
        }
        if (rs != null) {
            rs.close();
        }
        return lst;
    }

    public VistaEstudiante getEstudiante(int codigo) throws SQLException, IllegalArgumentException, IllegalAccessException, Exception {
        VistaEstudiante dto = new VistaEstudiante();
        String sql = "SELECT " + dtoBase.getNombreCampos(new int[]{1, 2, 3, 4, 5, 6, 7})
                + " FROM " + dtoBase.getTabla() + " WHERE " + dtoBase.getNombreCampo(1) + " = " + codigo + " GROUP BY " + dtoBase.getNombreCampos(new int[]{1, 2, 3, 4, 5, 6, 7});;
        Statement st = crearSentencia();

        ResultSet rs = st.executeQuery(sql);
        //rs.first();
        while (rs.next()) {
            dto = getRegistro(rs, true);
        }
        if (st != null) {
            st.close();
        }
        if (rs != null) {
            rs.close();
        }
        return dto;
    }

    public List<VistaEstudiante> buscarPorCriterio(String criterio) throws SQLException, IllegalArgumentException, IllegalAccessException, Exception {
        List<VistaEstudiante> lst = new ArrayList<VistaEstudiante>();
        String sql = "SELECT " + dtoBase.getNombreCampos(new int[]{1, 2, 3, 4, 5, 6, 7}) + " FROM " + dtoBase.getTabla() + " WHERE (" + dtoBase.getNombreCampo(2) + " like '%" + criterio + "%' OR " + dtoBase.getNombreCampo(3) + " like '%" + criterio + "%')" + " GROUP BY " + dtoBase.getNombreCampos(new int[]{1, 2, 3, 4, 5, 6, 7});
        Statement st = crearSentencia();
        ResultSet rs = st.executeQuery(sql);
        //rs.first();
        while (rs.next()) {
            lst.add(getRegistro(rs, true));
        }
        if (st != null) {
            st.close();
        }
        if (rs != null) {
            rs.close();
        }
        return lst;
    }

    public List<VistaEstudiante> buscarPorCriterio(String criterio, Integer _codigoClase, Integer _codigoPeriodo) throws SQLException, IllegalArgumentException, IllegalAccessException, Exception {
        List<VistaEstudiante> lst = new ArrayList<VistaEstudiante>();
        String sql = "SELECT " + dtoBase.getNombreCampos(new int[]{1, 2, 3, 4, 5, 6, 7}) + " FROM " + dtoBase.getTabla() + " WHERE (" + dtoBase.getNombreCampo(2) + " like '%" + criterio + "%' OR " + dtoBase.getNombreCampo(3) + " like '%" + criterio + "%')";

        if (_codigoClase != null) {
            sql = sql + " AND " + dtoBase.getNombreCampo(8) + " = " + _codigoClase.intValue();
        } else if (_codigoPeriodo != null) {
            sql = sql + " AND " + dtoBase.getNombreCampo(10) + " = " + _codigoPeriodo.intValue();
        } else if (_codigoClase != null && _codigoPeriodo != null) {
            sql = sql + " AND (" + dtoBase.getNombreCampo(8) + " = " + _codigoClase.intValue()
                    + " AND " + dtoBase.getNombreCampo(10) + " = " + _codigoPeriodo.intValue() + ")";
        }
        sql = sql + " GROUP BY " + dtoBase.getNombreCampos(new int[]{1, 2, 3, 4, 5, 6, 7});
        Statement st = crearSentencia();
        ResultSet rs = st.executeQuery(sql);
        //rs.first();
        while (rs.next()) {
            lst.add(getRegistro(rs, true));
        }
        if (st != null) {
            st.close();
        }
        if (rs != null) {
            rs.close();
        }
        return lst;
    }

    private VistaEstudiante getRegistro(ResultSet prs, boolean _group) throws SQLException, IllegalArgumentException, IllegalAccessException {
        VistaEstudiante dto = new VistaEstudiante();
        dto.setCodigo(prs.getInt(dtoBase.getNombreCampo(1)));
        dto.setNombre(prs.getString(dtoBase.getNombreCampo(2)));
        dto.setNumeroCuenta(prs.getString(dtoBase.getNombreCampo(3)));
        dto.setCorreo(prs.getString(dtoBase.getNombreCampo(4)));
        dto.setComentario(prs.getString(dtoBase.getNombreCampo(5)));
        dto.setTelefono(prs.getString(dtoBase.getNombreCampo(6)));
        dto.setOrden(prs.getInt(dtoBase.getNombreCampo(7)));
        if (!_group) {
            dto.setCodigoClase(prs.getInt(dtoBase.getNombreCampo(8)));
            dto.setNombreClase(prs.getString(dtoBase.getNombreCampo(9)));
            dto.setCodigoPeriodo(prs.getInt(dtoBase.getNombreCampo(10)));
            dto.setPeriodo(prs.getString(dtoBase.getNombreCampo(11)));
        }

        return dto;
    }
}
