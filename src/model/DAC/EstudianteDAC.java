/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAC;

import com.gmail.lrchfox3.basedatos.BD;
import com.gmail.lrchfox3.utilitarios.Excel;
import com.gmail.lrchfox3.utilitarios.Utileria;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import model.DTO.Estudiante;

/**
 *
 * @author Administrador
 */
public class EstudianteDAC extends BD {

    protected Estudiante dtoBase = new Estudiante();

    public List<Estudiante> getListaEstudiantes() throws SQLException, IllegalArgumentException, IllegalAccessException, Exception {
        List<Estudiante> lst = new ArrayList<Estudiante>();
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

    public Estudiante getEstudiante(int codigo) throws SQLException, IllegalArgumentException, IllegalAccessException, Exception {
        Estudiante dto = new Estudiante();
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

    public List<Estudiante> buscarPorCriterio(String criterio) throws SQLException, Exception {
        List<Estudiante> lst = new ArrayList<Estudiante>();
        String sql = "SELECT " + dtoBase.getNombreCampos() + " FROM " + dtoBase.getTabla() + " WHERE " + dtoBase.getNombreCampo(2) + " like '%" + criterio + "%' OR " + dtoBase.getNombreCampo(3) + " like '%" + criterio + "%'";
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

    private Estudiante getRegistro(ResultSet prs) throws SQLException, IllegalArgumentException, IllegalAccessException {
        Estudiante dto = new Estudiante();
        dto.setCodigoEstudiante(prs.getInt(dtoBase.getNombreCampo(1)));
        dto.setNombre(prs.getString(dtoBase.getNombreCampo(2)));
        dto.setNumeroCuenta(prs.getString(dtoBase.getNombreCampo(3)));
        dto.setCorreo(prs.getString(dtoBase.getNombreCampo(4)));
        dto.setComentario(prs.getString(dtoBase.getNombreCampo(5)));
        dto.setTelefono(prs.getString(dtoBase.getNombreCampo(6)));
        dto.setOrden(prs.getInt(dtoBase.getNombreCampo(7)));
        return dto;
    }

    public boolean editar(int accion, Estudiante dto) throws SQLException, IllegalArgumentException, IllegalAccessException, Exception {
        String sql = "";
        PreparedStatement pstr = null;
        if (accion == BD.ACCION_AGREGAR) {
            int[] indices = {2, 3, 4, 5, 6, 7};
            sql = "INSERT INTO " + dtoBase.getTabla() + " (" + dtoBase.getNombreCampos(indices) + ") VALUES(" + dtoBase.getSignosParametros() + ")";
            System.out.println(sql);
            pstr = prepararSentencia(sql);
            pstr.setString(1, dto.getNombre());
            pstr.setString(2, dto.getNumeroCuenta());
            pstr.setString(3, dto.getCorreo());
            pstr.setString(4, dto.getComentario());
            pstr.setString(5, dto.getTelefono());
            pstr.setInt(6, getSiguienteOrden()); //Mediante SQL se obtiene el siguiente orden
            pstr.executeUpdate();
            commit();
        } else if (accion == BD.ACCION_EDITAR) {
            sql = "UPDATE " + dtoBase.getTabla() + " SET " + dtoBase.getNombreCampo(2) + " = ? ," + dtoBase.getNombreCampo(3) + " = ? ," + dtoBase.getNombreCampo(4) + " = ? ," + dtoBase.getNombreCampo(5) + " = ?, " + dtoBase.getNombreCampo(6) + " = ?, " + dtoBase.getNombreCampo(7) + " = ? WHERE " + dtoBase.getNombreCampo(1) + " = ?";
            pstr = prepararSentencia(sql);
            pstr.setString(1, dto.getNombre());
            pstr.setString(2, dto.getNumeroCuenta());
            pstr.setString(3, dto.getCorreo());
            pstr.setString(4, dto.getComentario());
            pstr.setString(5, dto.getTelefono());
            pstr.setInt(6, dto.getOrden());
            pstr.setInt(7, dto.getCodigoEstudiante());
            pstr.executeUpdate();
            commit();
        } else if (accion == BD.ACCION_ELIMINAR) {
            sql = "DELETE FROM " + dtoBase.getTabla() + " WHERE " + dtoBase.getNombreCampo(1) + " = ?";
            pstr = prepararSentencia(sql);
            pstr.setInt(1, dto.getCodigoEstudiante());
            pstr.executeUpdate();
            commit();
        }

        if (pstr != null) {
            pstr.close();
        }
        return true;
    }

    public boolean editar(List<String> dml) throws SQLException, IllegalArgumentException, IllegalAccessException, Exception {
        Statement stmt = null;
        stmt = crearSentencia();
        if (dml.size() > 0) {
            for (String query : dml) {
                if (query.length() > 0) {
                    stmt.addBatch(query);
                }
            }
            stmt.executeBatch();
           commit();
        }
        if (stmt != null) {
            stmt.close();
        }
        return true;
    }

    public boolean importarEstudiantesFromExcel(String archivo) throws Exception {
        int[] indices = {2, 3, 4, 5, 6};
        String sql = "INSERT INTO " + dtoBase.getTabla() + " (" + dtoBase.getNombreCampos(indices) + ") VALUES(@param,@param,@param,@param,@param)";
        int tipoDatos[] = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
        Excel excel = new Excel();
        //excel.LeerHoja(archivo);
        List<String> dml = excel.importarExcel(archivo, sql, tipoDatos);
        editar(dml);

        return true;
    }

    public int getSiguienteOrden() throws SQLException, IllegalArgumentException, IllegalAccessException, Exception {
        return Utileria.getIntValue(getConexion(), "SELECT (IFNULL(MAX(" + dtoBase.getNombreCampo(7) + "),0) +1) AS VALOR FROM " + dtoBase.getTabla());
    }
}
