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
import model.DTO.Clase;

/**
 *
 * @author Administrador
 */
public class ClaseDAC extends BD {

    private Clase dtoBase = new Clase();

    public List<Clase> getListaClases() throws SQLException, Exception {
        List<Clase> lst = new ArrayList<Clase>();
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

    public List<Catalogo> getListaCampos() throws SQLException, Exception {

        List<Catalogo> lst = new ArrayList<Catalogo>();

        for (Clase p : getListaClases()) {
            lst.add(p);

        }
        return lst;
    }

    public Clase getClase(int codigo) throws SQLException, Exception {
        Clase dto = new Clase();
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

    public Catalogo getCatalogoClase(int codigo) throws SQLException, Exception {
        Catalogo dto = (Catalogo) getClase(codigo);
        return dto;
    }

    public List<Clase> buscarPorCriterio(String criterio) throws SQLException, Exception {
        List<Clase> lst = new ArrayList<Clase>();
        String sql = "SELECT " + dtoBase.getNombreCampos() + " FROM " + dtoBase.getTabla() + " WHERE " + dtoBase.getNombreCampo(2) + " like '%" + criterio + "%' OR " + dtoBase.getNombreCampo(3) + " like '%" + criterio + "%' ";
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

        for (Clase p : buscarPorCriterio(criterio)) {
            lst.add(p);

        }
        return lst;
    }

    private Clase getRegistro(ResultSet prs) throws SQLException, Exception {
        Clase dto = new Clase();
        dto.setCodigo(prs.getInt(dtoBase.getNombreCampo(1)));
        dto.setNombreClase(prs.getString(dtoBase.getNombreCampo(2)));
        dto.setReferencia(prs.getString(dtoBase.getNombreCampo(3)));
        dto.setComentario(prs.getString(dtoBase.getNombreCampo(4)));
        return dto;
    }

    public boolean editar(int accion, Clase dto) throws SQLException, Exception {
        String sql = "";
        PreparedStatement pstr = null;
        if (accion == BD.ACCION_AGREGAR) {
            sql = "INSERT INTO " + dtoBase.getTabla() + "(" + dtoBase.getNombreCampos(new int[]{2, 3}) + ") VALUES(?, ?)";
            pstr = prepararSentencia(sql);
            pstr.setString(1, dto.getNombreClase());
            pstr.setString(2, dto.getReferencia());
            pstr.executeUpdate();
            commit();
        } else if (accion == BD.ACCION_EDITAR) {
            sql = "UPDATE " + dtoBase.getTabla() + " SET " + dtoBase.getNombreCampo(2) + " = ?, " + dtoBase.getNombreCampo(3) + " = ?  WHERE " + dtoBase.getNombreCampo(1) + " = ?";
            pstr = prepararSentencia(sql);
            pstr.setString(1, dto.getNombreClase());
            pstr.setString(2, dto.getReferencia());
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
        Clase dto = (Clase) cat;
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
