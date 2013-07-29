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
import model.DTO.Periodo;

/**
 *
 * @author Administrador
 */
public class PeriodoDAC extends BD {

    private Periodo dtoBase = new Periodo();

    public List<Periodo> getListaPeriodos() throws SQLException, Exception {
        List<Periodo> lst = new ArrayList<Periodo>();
        String sql = "SELECT " + dtoBase.getNombreCampos() + " FROM " + dtoBase.getTabla();
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

    public List<Catalogo> getListaCampos() throws SQLException, Exception {

        List<Catalogo> lst = new ArrayList<Catalogo>();

        for (Periodo p : getListaPeriodos()) {
            lst.add(p);

        }
        return lst;
    }

    public Periodo getPeriodo(int codigo) throws SQLException, Exception {
        Periodo dto = new Periodo();
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


    public Catalogo getCatalogoPeriodo(int codigo) throws SQLException, Exception {
        Catalogo dto = (Catalogo) getPeriodo(codigo);
        return dto;
    }

    public List<Periodo> buscarPorCriterio(String criterio) throws SQLException, Exception {
        List<Periodo> lst = new ArrayList<Periodo>();
        String sql = "SELECT " + dtoBase.getNombreCampos() + " FROM " + dtoBase.getTabla() + " WHERE " + dtoBase.getNombreCampo(2) + " like '%" + criterio + "%'";
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

    public List<Catalogo> buscarEnCatalogo(String criterio) throws SQLException, Exception {
         List<Catalogo> lst = new ArrayList<Catalogo>();

        for (Periodo p : buscarPorCriterio(criterio)) {
            lst.add(p);

        }
        return lst;
    }

    private Periodo getRegistro(ResultSet prs) throws SQLException, Exception {
        Periodo dto = new Periodo();
        dto.setCodigo(prs.getInt(dtoBase.getNombreCampo(1)));
        dto.setPeriodo(prs.getString(dtoBase.getNombreCampo(2)));
        return dto;
    }

    public boolean editar(int accion, Periodo dto) throws SQLException, Exception {
        String sql = "";
        PreparedStatement pstr = null;
        if (accion == BD.ACCION_AGREGAR) {
            sql = "INSERT INTO " + dtoBase.getTabla() + "(" + dtoBase.getNombreCampo(2) + ") VALUES(?)";
            pstr = prepararSentencia(sql);
            pstr.setString(1, dto.getPeriodo());
            pstr.executeUpdate();
            commit();
        } else if (accion == BD.ACCION_EDITAR) {
            sql = "UPDATE " + dtoBase.getTabla() + " SET " + dtoBase.getNombreCampo(2) + " = ?  WHERE " + dtoBase.getNombreCampo(1) + " = ?";
            pstr = prepararSentencia(sql);
            pstr.setString(1, dto.getPeriodo());
            pstr.setInt(2, dto.getCodigo());
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
        Periodo dto = (Periodo) cat;
        return editar(accion, dto);
    }

    public boolean importarPeriodoFromExcel(String archivo) throws Exception {
        String sql = "INSERT INTO " + dtoBase.getTabla() + "(" + dtoBase.getNombreCampo(1) + ") VALUES(?,?,?,?)";
        int tipoDatos[] = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
        Excel excel = new Excel();
        excel.importarExcel(archivo, sql, tipoDatos);
        return true;
    }
}
