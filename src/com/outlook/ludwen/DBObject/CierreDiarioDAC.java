/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.ludwen.DBObject;

import com.gmail.lrchfox3.basedatos.CnnMySql;
import com.gmail.lrchfox3.utilitarios.Excel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class CierreDiarioDAC extends CnnMySql {

    protected CierreDiario dtoBase = new CierreDiario();

    public List<CierreDiario> getListaCierreDiarios() throws SQLException, IllegalArgumentException, IllegalAccessException {
        List<CierreDiario> lst = new ArrayList<CierreDiario>();
        String sql = "SELECT " + dtoBase.getNombreCampos() + " FROM " + dtoBase.getTabla();
        Statement st = getCnn().createStatement();
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

    public CierreDiario getCierreDiario(int codigo) throws SQLException, IllegalArgumentException, IllegalAccessException {
        CierreDiario dto = new CierreDiario();
        String sql = "SELECT " + dtoBase.getNombreCampos()
                + " FROM " + dtoBase.getTabla() + " WHERE " + dtoBase.getNombreCampo(1) + " = " + codigo;
        Statement st = getCnn().createStatement();

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

    public List<CierreDiario> buscarPorCriterio(String criterio) throws SQLException, IllegalArgumentException, IllegalAccessException {
        List<CierreDiario> lst = new ArrayList<CierreDiario>();
        String sql = "SELECT " + dtoBase.getNombreCampos() + " FROM " + dtoBase.getTabla() + " WHERE " + dtoBase.getNombreCampo(2) + " like '%" + criterio + "%' OR " + dtoBase.getNombreCampo(3) + " like '%" + criterio + "%'";
        Statement st = getCnn().createStatement();
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

    private CierreDiario getRegistro(ResultSet prs) throws SQLException, IllegalArgumentException, IllegalAccessException {
        CierreDiario dto = new CierreDiario();
        dto.setSecuencia(prs.getInt(dtoBase.getNombreCampo(1)));
        dto.setCodigoTienda(prs.getInt(dtoBase.getNombreCampo(2)));
        dto.setFechaCierre(prs.getDate(dtoBase.getNombreCampo(3)));
        dto.setCajaInicio(prs.getDouble(dtoBase.getNombreCampo(4)));
        dto.setCantidad500(prs.getInt(dtoBase.getNombreCampo(5)));
        dto.setCantidad100(prs.getInt(dtoBase.getNombreCampo(6)));
        dto.setCantidad50(prs.getInt(dtoBase.getNombreCampo(7)));
        dto.setCantidad20(prs.getInt(dtoBase.getNombreCampo(8)));
        dto.setCantidad10(prs.getInt(dtoBase.getNombreCampo(9)));
        dto.setCantidad5(prs.getInt(dtoBase.getNombreCampo(10)));
        dto.setCantidad2(prs.getInt(dtoBase.getNombreCampo(11)));
        dto.setCantidad1(prs.getInt(dtoBase.getNombreCampo(12)));
        dto.setMontoMoneda(prs.getDouble(dtoBase.getNombreCampo(13)));
        dto.setMontoFacturado(prs.getDouble(dtoBase.getNombreCampo(14)));
        dto.setMontoPOS1(prs.getDouble(dtoBase.getNombreCampo(15)));
        dto.setMontoPOS2(prs.getDouble(dtoBase.getNombreCampo(16)));
        dto.setMontoJustificacion(prs.getDouble(dtoBase.getNombreCampo(17)));
        dto.setJustificacion(prs.getString(dtoBase.getNombreCampo(18)));
        dto.setMontoDeposito(prs.getDouble(dtoBase.getNombreCampo(19)));
        dto.setCajaFinal(prs.getDouble(dtoBase.getNombreCampo(20)));
        return dto;
    }

    public boolean editar(int accion, CierreDiario dto) throws SQLException, IllegalArgumentException, IllegalAccessException {
        String sql = "";
        PreparedStatement pstr = null;
        if (accion == CnnMySql.ACCION_INSERT) {
            int[] indices = {3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
            sql = "INSERT INTO " + dtoBase.getTabla() + " (" + dtoBase.getNombreCampos(indices) + ") VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pstr = getCnn().prepareStatement(sql);

            pstr.setDate(1, dto.getFechaCierre());
            pstr.setDouble(2, dto.getCajaInicio());
            pstr.setInt(3, dto.getCantidad500());
            pstr.setInt(4, dto.getCantidad100());
            pstr.setInt(5, dto.getCantidad50());
            pstr.setInt(6, dto.getCantidad20());
            pstr.setInt(7, dto.getCantidad10());
            pstr.setInt(8, dto.getCantidad5());
            pstr.setInt(9, dto.getCantidad2());
            pstr.setInt(10, dto.getCantidad1());
            pstr.setDouble(11, dto.getMontoMoneda());
            pstr.setDouble(12, dto.getMontoFacturado());
            pstr.setDouble(13, dto.getMontoPOS1());
            pstr.setDouble(14, dto.getMontoPOS2());
            pstr.setDouble(15, dto.getMontoJustificacion());
            pstr.setString(16, dto.getJustificacion());
            pstr.setDouble(17, dto.getMontoDeposito());
            pstr.setDouble(18, dto.getCajaFinal());            
            pstr.executeUpdate();
            getCnn().commit();
        } else if (accion == CnnMySql.ACCION_UPDATE) {
            sql = "UPDATE " + dtoBase.getTabla() + " SET " 
                    + dtoBase.getNombreCampo(3) + " = ? ," + dtoBase.getNombreCampo(4) + " = ? ,"
                    + dtoBase.getNombreCampo(5) + " = ?, " + dtoBase.getNombreCampo(6) + " = ?, "
                    + dtoBase.getNombreCampo(7) + " = ?, " + dtoBase.getNombreCampo(8)+ " = ?, "
                    + dtoBase.getNombreCampo(9) + " = ?, " + dtoBase.getNombreCampo(10)+ " = ?, "
                    + dtoBase.getNombreCampo(11) + " = ?, " + dtoBase.getNombreCampo(12)+ " = ?, "
                    + dtoBase.getNombreCampo(13) + " = ?, " + dtoBase.getNombreCampo(14)+ " = ?, "
                    + dtoBase.getNombreCampo(15) + " = ?, " + dtoBase.getNombreCampo(16)+ " = ?, "
                    + dtoBase.getNombreCampo(17) + " = ?, " + dtoBase.getNombreCampo(18)+ " = ?, "
                    + dtoBase.getNombreCampo(19) + " = ?, " + dtoBase.getNombreCampo(20)
                    + " = ? WHERE " + dtoBase.getNombreCampo(1) + " = ?";
            pstr = getCnn().prepareStatement(sql);
            pstr.setDate(1, dto.getFechaCierre());
            pstr.setDouble(2, dto.getCajaInicio());
            pstr.setInt(3, dto.getCantidad500());
            pstr.setInt(4, dto.getCantidad100());
            pstr.setInt(5, dto.getCantidad50());
            pstr.setInt(6, dto.getCantidad20());
            pstr.setInt(7, dto.getCantidad10());
            pstr.setInt(8, dto.getCantidad5());
            pstr.setInt(9, dto.getCantidad2());
            pstr.setInt(10, dto.getCantidad1());
            pstr.setDouble(11, dto.getMontoMoneda());
            pstr.setDouble(12, dto.getMontoFacturado());
            pstr.setDouble(13, dto.getMontoPOS1());
            pstr.setDouble(14, dto.getMontoPOS2());
            pstr.setDouble(15, dto.getMontoJustificacion());
            pstr.setString(16, dto.getJustificacion());
            pstr.setDouble(17, dto.getMontoDeposito());
            pstr.setDouble(18, dto.getCajaFinal());
            pstr.executeUpdate();
            getCnn().commit();
        } else if (accion == CnnMySql.ACCION_DELETE) {
            sql = "DELETE FROM " + dtoBase.getTabla() + " WHERE " + dtoBase.getNombreCampo(1) + " = ?";
            pstr = getCnn().prepareStatement(sql);
            pstr.setInt(1, dto.getSecuencia());
            pstr.executeUpdate();
            getCnn().commit();
        }

        if (pstr != null) {
            pstr.close();
        }
        return true;
    }

    public boolean editar(List<String> dml) throws SQLException, IllegalArgumentException, IllegalAccessException {
        Statement stmt = null;
        stmt = getCnn().createStatement();
        if (dml.size() > 0) {
            for (String query : dml) {
                if (query.length() > 0) {
                    stmt.addBatch(query);
                }
            }
            stmt.executeBatch();
            getCnn().commit();
        }
        if (stmt != null) {
            stmt.close();
        }
        return true;
    }

    public boolean importarCierreDiariosFromExcel(String archivo) throws Exception {
        int[] indices = {3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        String sql = "INSERT INTO " + dtoBase.getTabla() + " (" + dtoBase.getNombreCampos(indices) + ") VALUES(@param,@param,@param,@param,@param,@param,@param,@param,@param,@param,@param,@param,@param,@param,@param,@param,@param,@param,@param)";
        int tipoDatos[] = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
        Excel excel = new Excel();
        //excel.LeerHoja(archivo);
        List<String> dml = excel.importarExcel(archivo, sql, tipoDatos);
        editar(dml);

        return true;
    }
}
